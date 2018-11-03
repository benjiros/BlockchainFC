pragma solidity ^0.4.17;
//pragma experimental ABIEncoderV2;

contract Mortal {
    address owner;
    constructor() public { owner = msg.sender; }
    function kill() public { if (msg.sender == owner) selfdestruct(owner); }
}

contract Mercato is Mortal {
    
    struct SignedContract{
        address clubOwner;
        address player;
        uint256 duration;
        uint256 price;
    }
    
    struct TransferProposal {
        address clubOwner;
        address clubOffer;
        address player;
        bool playerAccepted;
        bool clubAccepted;
        /*Contract thingies*/
        uint256 duration;
        uint256 price;
    }
    
    function registerPlayer(address club, uint256 duration, uint256 price) public {
        isPlayerEmployed[msg.sender] = true;
        SignedContract memory c = SignedContract(club, msg.sender, duration, price);
        getCurrentContractForPlayer[msg.sender] = c;
        getCurrentContractForClub[club].push(c);
    }
   
    function registerPlayer() public {
        isPlayerEmployed[msg.sender] = false;
    }
    
    mapping(address => bool) isPlayerEmployed;
    mapping(address => address) getCurrentAgent;
    mapping(address => SignedContract) getCurrentContractForPlayer;
    mapping(address => SignedContract[]) getCurrentContractForClub;
    mapping(address => TransferProposal[]) getTransfersProposalForClub;
    mapping(address => TransferProposal[]) getCurrentPlayersForClub;
    mapping(address => TransferProposal[]) getTransfersProposalForPlayer;
    
    function proposeTransferToPlayer(address player, uint256 duration, uint256 price) public returns (address, address, uint256)  {
        TransferProposal memory t;   
        if(isPlayerEmployed[player])
        {
            SignedContract storage currentContract = getCurrentContractForPlayer[player]; 
            t = TransferProposal(currentContract.clubOwner, msg.sender, player, false, false, duration, price);
        } else 
        {
            t = TransferProposal(0, msg.sender, player, false, false, duration, price);        
        }
        TransferProposal[] storage playersTranfers = getTransfersProposalForPlayer[player];
        bool alreadyProposed = false;
        for(uint32 i = 0 ; i < playersTranfers.length ; i++){
            if(playersTranfers[i].clubOffer == t.clubOffer){
                alreadyProposed = true;
            }
        }
        if(!alreadyProposed){
            getTransfersProposalForPlayer[player].push(t);
            if(isPlayerEmployed[player]){
                getTransfersProposalForClub[currentContract.clubOwner].push(t);
            }
        }
        return(t.player, t.clubOffer, t.duration);
    }

    function getPlayerEmployed() public view returns (bool){
        return isPlayerEmployed[msg.sender];
    }
    
    function getTransferProposalNumberPlayer() public view returns (uint256){
        TransferProposal[] storage playersTranfers = getTransfersProposalForPlayer[msg.sender];
        return playersTranfers.length;
    }
    
    function getPlayerCurrentClub() public view returns (address){
        SignedContract storage signed = getCurrentContractForPlayer[msg.sender];
        return signed.clubOwner;
    }

    function getTransferProposalNumberClub() public view returns (uint256){
        TransferProposal[] storage clubTranfers = getTransfersProposalForClub[msg.sender];
        return clubTranfers.length;
    }
    
    function getClubNumberOfPlayers() public view returns (uint256) {
        SignedContract[] storage clubContracts = getCurrentContractForClub[msg.sender];
        return clubContracts.length;
    }

    function refuseTransferProposalPlayer() public  {
        TransferProposal[] storage playersTranfer = getTransfersProposalForPlayer[msg.sender];
        
        playersTranfer[0] = playersTranfer[playersTranfer.length-1];
        playersTranfer.length--;
    }
    
    function refuseTransferProposalClub() public  {
        TransferProposal[] storage clubTranfer = getTransfersProposalForClub[msg.sender];
        
        clubTranfer[0] = clubTranfer[clubTranfer.length-1];
        clubTranfer.length--;
    }
    
    function acceptPlayerTransferProposal() public {
        //Due to the limitation of the solidity framework you for now can only accept the first proposeTransferToPlayer
        TransferProposal storage offer = getTransfersProposalForPlayer[msg.sender][0];
        
        offer.playerAccepted = true;
        /*if(offer.clubAccepted){
            SignedContract memory s = signContract(offer.clubOffer, msg.sender, offer.duration, offer.price);
            cleanUpPaperwork(msg.sender, offer.clubOwner);
            executeMoneyTransfer(offer.clubOffer, offer.clubOwner, msg.sender, offer.price);
        }*/
    }
    
    function cleanUpPaperwork(address player, address club) private{
        delete getTransfersProposalForPlayer[player];
        TransferProposal[] storage clubTranfers = getTransfersProposalForClub[club];
        for(uint32 i = 0 ; i < clubTranfers.length ; i++){
            if(clubTranfers[i].player == player){
                clubTranfers[i] = clubTranfers[clubTranfers.length-1];
                clubTranfers.length--;
            }
        }
        
        SignedContract[] storage clubContracts = getCurrentContractForClub[club];
        for(uint32 j = 0 ; j < clubContracts.length ; j++){
            if(clubContracts[j].player == player){
                clubContracts[j] = clubContracts[clubContracts.length-1];
                clubContracts.length--;
            }
        }
    }
    
    function signContract(TransferProposal transferProposal) private returns (SignedContract){
        SignedContract memory finalContract = SignedContract(transferProposal.clubOffer, transferProposal.player, transferProposal.duration, transferProposal.price);
        getCurrentContractForPlayer[transferProposal.player] = finalContract;
        getCurrentContractForClub[transferProposal.clubOffer].push(finalContract);
        return finalContract;
    }
    
    function finalizeTransfer(address player) public payable returns (uint){
        
        TransferProposal[] storage playerTranfers = getTransfersProposalForPlayer[player];
        for(uint32 i = 0 ; i < playerTranfers.length ; i++){
            if(playerTranfers[i].clubOffer == msg.sender){
                TransferProposal storage acceptedProposal = playerTranfers[i];
            }
        }
        if(acceptedProposal.clubAccepted && acceptedProposal.playerAccepted){
            require(msg.value >= acceptedProposal.price);
            signContract(acceptedProposal);
            //acceptedProposal.clubOwner.transfer(acceptedProposal.price);
            cleanUpPaperwork(acceptedProposal.player, acceptedProposal.clubOwner);
            
            return msg.sender.balance;
        }
        return 0;
    }
    
}
    
    