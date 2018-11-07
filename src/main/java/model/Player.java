package model;

import back.AskingMercato;

import java.util.List;

public class Player {

    public String addressShort;
    public String addressLong;
    public ClubPlayerContract currentClubContract;
    public AgentPlayerContract currentPlayerContract;
    public List<ClubPlayerContractProposal> transferProposals;
    public List<AgentPlayerContractProposal> serviceProposals;

    public Player(String addressShort, String addressLong,
                  ClubPlayerContract currentClubContract,
                  AgentPlayerContract currentPlayerContract,
                  List<ClubPlayerContractProposal> transferProposals,
                  List<AgentPlayerContractProposal> serviceProposals) {
        this.addressShort = addressShort;
        this.addressLong = addressLong;
        this.currentClubContract = currentClubContract;
        this.currentPlayerContract = currentPlayerContract;
        this.transferProposals = transferProposals;
        this.serviceProposals = serviceProposals;
    }

    public Club getCurrentClub(){
        return currentClubContract.club;
    }
}
