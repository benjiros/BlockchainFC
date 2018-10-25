pragma solidity ^0.4.17;

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
    }
    
    struct TransferProposal {
        address clubOwner;
        address clubOffer;
        address player;
        bool playerAccepted;
        bool clubAccepted;
        /*Contract thingies*/
        uint256 duration;
    }
    
    function registerPlayer(address club, uint256 duration) public {
        isPlayerEmployed[msg.sender] = true;
        SignedContract memory c = SignedContract(club, msg.sender, duration);
        getCurrentContractForPlayer[msg.sender] = c;
    }
   
    function registerPlayer() public {
        isPlayerEmployed[msg.sender] = false;
    }
    
    mapping(address => bool) isPlayerEmployed;
    mapping(address => address) getCurrentAgent;
    mapping(address => SignedContract) getCurrentContractForPlayer;
    mapping(address => TransferProposal[]) getTransfersProposalForClub;
    mapping(address => TransferProposal[]) getCurrentPlayersForClub;
    mapping(address => TransferProposal[]) getTransfersProposalForPlayer;
    
    function proposeTransferToPlayer(address player, uint256 duration) public {
        TransferProposal memory t;   
        if(isPlayerEmployed[msg.sender])
        {
            SignedContract storage currentContract = getCurrentContractForPlayer[player]; 
            t = TransferProposal(currentContract.clubOwner, msg.sender, player, false, false, duration);
        } else 
        {
            t = TransferProposal(0, msg.sender, player, false, false, duration);        
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
            getTransfersProposalForClub[currentContract.clubOwner].push(t);
        }
    }

    
    function getTransferPlayerProposalNumber() public view returns (uint256){
        TransferProposal[] storage playersTranfers = getTransfersProposalForPlayer[msg.sender];
        return playersTranfers.length;
    }
/*
    function refuseTransferProposal() public  {
        TransferProposal[] storage playersTranfer = getTransfersProposalForPlayer[msg.sender];
        TransferProposal storage refused = playersTranfer[0];
        
    }

    function acceptTransferProposal() public {
        //Due to the limitation of the solidity framework you for now can only accept the first proposeTransferToPlayer
        
        TransferProposal[] storage playersTranfers = getTransfersProposalForPlayer[msg.sender];
        }*/
    
}
    
    