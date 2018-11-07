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

    struct SignedService{
        address player;
        address agent;
        uint256 percentage;
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

    struct ServiceProposal {
        address agent;
        address player;
        uint256 percentage;
    }

    function registerPlayer(address club, uint256 duration, uint256 price) public returns (address, address) {
        isPlayerEmployed[msg.sender] = true;
        SignedContract memory c = SignedContract(club, msg.sender, duration, price);
        getCurrentContractForPlayer[msg.sender] = c;
        getCurrentContractForClub[club].push(c);

        return(msg.sender, club);
    }

    function registerPlayer() public {
        isPlayerEmployed[msg.sender] = false;
    }

    mapping(address => SignedService) getAgentForPlayer;
    mapping(address => SignedService[]) getPlayersForAgent;
    mapping(address => bool) isPlayerEmployed;
    mapping(address => address) getCurrentAgent;
    mapping(address => SignedContract) getCurrentContractForPlayer;
    mapping(address => SignedContract[]) getCurrentContractForClub;
    mapping(address => TransferProposal[]) getTransfersProposalForClub;
    mapping(address => TransferProposal[]) getCurrentPlayersForClub;
    mapping(address => TransferProposal[]) getTransfersProposalForPlayer;
    mapping(address => ServiceProposal[]) getServiceForPlayer;


    function acceptService() public returns (address, address, uint256){
        ServiceProposal storage proposal = getServiceForPlayer[msg.sender][0];
        SignedService memory signed = SignedService(msg.sender, proposal.agent, proposal.percentage);
        getAgentForPlayer[msg.sender] = signed;
        getPlayersForAgent[proposal.agent].push(signed);
        delete getServiceForPlayer[msg.sender];

        return(signed.player, signed.agent, signed.percentage);
    }

    function getTransferForPlayerAt(uint i) public view returns (address, uint256, uint256){
        TransferProposal storage proposal = getTransfersProposalForPlayer[msg.sender][i];

        return (proposal.clubOffer, proposal.duration, proposal.price);
    }

    function refuseService() public returns (uint32){
        ServiceProposal[] storage proposals = getServiceForPlayer[msg.sender];
        proposals[0] = proposals[proposals.length-1];
        proposals.length--;

        return(0);
    }

    function proposeServiceToPlayer(address player, uint256 percentage) public {
        ServiceProposal memory s = ServiceProposal(msg.sender, player, percentage);
        getServiceForPlayer[player].push(s);
    }

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

    function getServiceProposal() public view returns (address, uint256){
        ServiceProposal storage proposal = getServiceForPlayer[msg.sender][0];
        return(proposal.agent, proposal.percentage);
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

    function isOfferAccepted(address player) public returns (bool, bool){
        TransferProposal[] storage clubTranfers = getTransfersProposalForClub[msg.sender];
        for(uint32 i = 0 ; i < clubTranfers.length ; i++){
            if(clubTranfers[i].player == player){
                TransferProposal storage offer = clubTranfers[i];
            }
        }
        return (offer.clubAccepted, offer.playerAccepted);
    }

    function acceptTransferProposalPlayer(address club) public returns(bool, bool) {
        //Due to the limitation of the solidity framework you for now can only accept the first proposeTransferToPlayer
        TransferProposal[] storage playerTranfers = getTransfersProposalForPlayer[msg.sender];
        for(uint32 i = 0 ; i < playerTranfers.length ; i++){
            if(playerTranfers[i].clubOffer == club){
                TransferProposal storage offer = playerTranfers[i];
                playerTranfers[i].playerAccepted = true;
            }
        }

        TransferProposal[] storage clubTranfers = getTransfersProposalForClub[offer.clubOwner];
        for(uint32 j = 0 ; j < clubTranfers.length ; j++){
            if(clubTranfers[j].player == msg.sender && clubTranfers[j].clubOffer == club){
                clubTranfers[j].playerAccepted = true;
            }
        }

        return (offer.clubAccepted, offer.playerAccepted);
    }

    function acceptTransferProposalClub(address player, address club) public returns (bool) {
        TransferProposal[] storage clubTranfers = getTransfersProposalForClub[msg.sender];
        for(uint32 i = 0 ; i < clubTranfers.length ; i++){
            if(clubTranfers[i].player == player && clubTranfers[i].clubOffer == club){
                TransferProposal storage offer = clubTranfers[i];
                clubTranfers[i].clubAccepted = true;
            }
        }

        TransferProposal[] storage playerTranfers = getTransfersProposalForPlayer[player];
        for(uint32 j = 0 ; j < playerTranfers.length ; j++){
            if(playerTranfers[j].clubOffer == club){
                playerTranfers[j].clubAccepted = true;
            }
        }
        return (offer.clubAccepted);
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
        if(club != 0){
            SignedContract[] storage clubContracts = getCurrentContractForClub[club];
            for(uint32 j = 0 ; j < clubContracts.length ; j++){
                if(clubContracts[j].player == player){
                    clubContracts[j] = clubContracts[clubContracts.length-1];
                    clubContracts.length--;
                }
            }
        }
    }

    function signContract(TransferProposal transferProposal) private returns (SignedContract){
        SignedContract memory finalContract = SignedContract(transferProposal.clubOffer, transferProposal.player, transferProposal.duration, transferProposal.price);
        getCurrentContractForPlayer[transferProposal.player] = finalContract;
        getCurrentContractForClub[transferProposal.clubOffer].push(finalContract);
        return finalContract;
    }

    function finalizeTransfer(address player) public payable returns (bool, bool){

        TransferProposal[] storage playerTranfers = getTransfersProposalForPlayer[player];
        for(uint32 i = 0 ; i < playerTranfers.length ; i++){
            if(playerTranfers[i].clubOffer == msg.sender){
                TransferProposal storage acceptedProposal = playerTranfers[i];
            }
        }
        if(isPlayerEmployed[player]){
            if(acceptedProposal.clubAccepted && acceptedProposal.playerAccepted){
                require(msg.value >= acceptedProposal.price);
                signContract(acceptedProposal);
                //acceptedProposal.clubOwner.transfer(acceptedProposal.price);
                cleanUpPaperwork(acceptedProposal.player, acceptedProposal.clubOwner);

                return (true, true);
            } else {
                return (acceptedProposal.clubAccepted, acceptedProposal.playerAccepted);
            }
        }
        else {
            if(acceptedProposal.playerAccepted){
                require(msg.value >= acceptedProposal.price);
                signContract(acceptedProposal);
                cleanUpPaperwork(acceptedProposal.player, 0);
            }
        }

        return (true, true);
    }

    function getClubPlayerAt(uint i) public view returns(address, uint256, uint256){
        SignedContract storage currentContract = getCurrentContractForClub[msg.sender][i];
        return (currentContract.player, currentContract.duration, currentContract.price);
    }

    function getAgentPlayerAt(uint i) public view returns(address, uint256){
        SignedService storage currentService = getPlayersForAgent[msg.sender][i];
        return (currentService.player, currentService.percentage);
    }
}

    
