pragma solidity ^0.4.17;

contract Mortal {
    address owner;
    constructor() public { owner = msg.sender; }
    function kill() public { if (msg.sender == owner) selfdestruct(owner); }
}

contract Transfer is Mortal {

    struct TransferProposal {
        address club;
        address player;
        /*Contract thingies*/
        uint256 duration;
    }

    mapping(address => TransferProposal[]) getTransfersForPlayer;
    mapping(address => TransferProposal[]) getTransfersForClub;

    function proposeTransferToPlayer(address player, uint256 duration) public {
        TransferProposal memory t = TransferProposal(msg.sender, player, duration);
        getTransfersForPlayer[player].push(t);

    }

    function getTransferPlayerProposal() public view returns (string){
        TransferProposal[] playersTranfers = getTransfersForPlayer[msg.sender];
        if(playersTranfers.length == 0){
            return "You don't have any pending contract proposal";
        } else {
            return "You have at least one pending contract ";
        }
    }

}


