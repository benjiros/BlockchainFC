pragma solidity ^0.4.0;

contract Mortal {
    address owner;
    constructor() public { owner = msg.sender; }
    function kill() public { if (msg.sender == owner) selfdestruct(owner); }
}

contract Recruitment is Mortal{

    struct Agent {
        address add;
    }

    struct Player {
        address add;
    }

    struct ServiceProposal {
        address agent;
        address player;
        uint256 percentage;
    }

    Player[] public players;
    Agent[] public agents;

    mapping(address => Agent) getCurrentAgent;
    mapping(address => ServiceProposal[]) getServiceForPlayer;

    function proposeServiceToPlayer(address player, uint256 percentage) public {
        ServiceProposal memory s = ServiceProposal(msg.sender, player, percentage);
        getServiceForPlayer[player].push(s);
    }

    function getServicePlayerProposal() public view returns (string){
        ServiceProposal[] playersServices = getServiceForPlayer[msg.sender];
        if(playersServices.length == 0){
            return "You don't have any pending service proposal from an agent";
        } else {
            return "You have at least one pending service from an agent ";
        }
    }

    function acceptService(address agent) public {
        Agent currentAgent = getCurrentAgent[msg.sender];
    }

    function declineService(address agent) public {
        ServiceProposal[] playersServices = getServiceForPlayer[msg.sender];
        for (uint i = 0; i<playersServices.length-1; i++){
            if (playersServices[i].agent == agent){
                delete playersServices[i];
            }
        }
    }

}