pragma solidity ^0.4.17;

contract Mortal {
    address owner;
    constructor() public { owner = msg.sender; }
    function kill() public { if (msg.sender == owner) selfdestruct(owner); }
}

contract Transfer is Mortal {
    
    struct SignedContract{
        address clubOwner;
        address player;
        uint256 duration;
    }
    
    struct Transfer {
        address clubOwner;
        address clubOffer;
        address player;
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
    mapping(address => Transfer[]) getTransfersProposalForClub;
    mapping(address => Transfer[]) getCurrentPlayersForClub;
    mapping(address => Transfer[]) getTransfersProposalForPlayer;
    
    function proposeTransferToPlayer(address player, uint256 duration) public {
        Transfer memory t;   
        if(isPlayerEmployed[msg.sender])
        {
            SignedContract currentContract = getCurrentContractForPlayer[player]; 
            t = Transfer(currentContract.clubOwner, msg.sender, player, duration);
        } else 
        {
            t = Transfer(0, msg.sender, player, duration);        
        }
        Transfer[] playersTranfers = getTransfersProposalForPlayer[player];
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

    
    function getTransferPlayerProposal() public view returns (uint256){
        Transfer[] playersTranfers = getTransfersProposalForPlayer[msg.sender];
        return playersTranfers.length;
    }

    
}
    
    