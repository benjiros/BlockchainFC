package view;

import back.Variables;
import model.Club;
import model.Player;
import org.web3j.crypto.CipherException;
import org.web3j.protocol.exceptions.TransactionException;
import org.web3j.tuples.generated.Tuple3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class HomePlayer extends JFrame implements ActionListener {
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private int width = screenSize.width;
    private int height = screenSize.height;
    Font policeDisconnect = new Font(" Arial ",Font.BOLD,14);
    Font policeGeneral = new Font(" Arial ",Font.BOLD,18);
    Font policeButton = new Font(" Arial ",Font.BOLD,22);
    Font policeTitle = new Font(" Arial ",Font.BOLD,40);

    String address;
    String password;
    Player player;

    Object [][] listOfferPlayer;

    //TODO RECUP AGENT DU JOUEUR
    String agent = "Agent Bernes";
    //TODO RECUP CLUB DU JOUEUR
    String club = "AS Monaco";


    Object[][] data = {
            {"Cysboy", "28 ans", "1.80 m"},
            {"BZHHydde", "28 ans", "1.80 m"},
            {"IamBow", "24 ans", "1.90 m"},
            {"FunMan", "32 ans", "1.85 m"}
    };

    JFrame propositionFrame;
    JFrame answerClubFrame;
    JFrame proposeContractAgentFrame;

    JButton disconnectButton;
    JButton propositionsAgentButton;
    JButton propositionsClubButton;
    JButton proposeContractButton;
    JButton fireAgentButton;
    JButton resignButton;

    JButton acceptPropositionClubButton;
    JButton declinePropositionClubButton;
    JButton acceptPropositionAgentButton;
    JButton declinePropositionAgentButton;
    JButton sendPropositionAgentButton;

    JTable table;

    public static void main(String[] args){
        //new HomePlayer("Steven Gerrard", "");
    }

    public HomePlayer(String address, String password) {
        this.address = address;
        this.password = password;

        this.player = Variables.listPlayers.get(this.address);

        this.setTitle("Votre espace perso - " + player.name);
        this.setSize(width/2, height/2);
        this.setLocationRelativeTo(null); // centrer la fenêtre sur l'écran
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // fin du programme lors de la fermeture de la fenêtre
        this.setResizable(true);

        JPanel disconnectArea = new JPanel();
        disconnectButton = new JButton("Se déconnecter");
        disconnectButton.setFont(policeDisconnect);
        disconnectButton.setBackground(Color.WHITE);
        disconnectButton.addActionListener(this);
        disconnectArea.add(disconnectButton);

        JPanel titleArea = new JPanel();
        JLabel title = new JLabel(player.name.toUpperCase() + " - " + "JOUEUR");
        title.setFont(policeTitle);
        titleArea.add(title);

        //TODO GET CLUB & AGENT
        JPanel clubArea = new JPanel();
        JLabel clubLabel;
        if(player.getNameClub() != ""){
            clubLabel = new JLabel("Votre club actuel est " + player.getNameClub());
        } else {
            clubLabel = new JLabel("Vous n'avez pas de club");
        }
        clubLabel.setFont(policeGeneral);
        clubArea.add(clubLabel);
        /*
        JPanel agentArea = new JPanel();
        JLabel agentLabel;
        if(!agent.isEmpty()){
            agentLabel = new JLabel("Votre agent actuel est " + agent.toUpperCase());
        } else {
            agentLabel = new JLabel("Vous n'avez pas d'agent");
        }
        agentLabel.setFont(policeGeneral);
        agentArea.add(agentLabel);
        */

        JPanel propositionsClubArea = new JPanel();
        propositionsClubButton = new JButton("Voir les offres qui vous sont faites");
        propositionsClubButton.setFont(policeButton);
        propositionsClubButton.setBackground(Color.WHITE);
        propositionsClubButton.addActionListener(this);
        propositionsClubArea.add(propositionsClubButton);

        //JPanel propositionsAgentArea = new JPanel();
        //propositionsAgentButton = new JButton("Voir les offres d'agents");
        //propositionsAgentButton.setFont(policeButton);
        //propositionsAgentButton.setBackground(Color.WHITE);
        //propositionsAgentButton.addActionListener(this);
        //propositionsAgentArea.add(propositionsAgentButton);

        JPanel proposeContractArea = new JPanel();
        proposeContractButton = new JButton("Proposer un contrat à un agent");
        proposeContractButton.setFont(policeButton);
        proposeContractButton.setBackground(Color.WHITE);
        proposeContractButton.addActionListener(this);
        proposeContractArea.add(proposeContractButton);

        //TODO VERIFIER SI LE JOUEUR A UN AGENT
        JPanel fireAgentArea = new JPanel();
        fireAgentButton = new JButton("Virer votre agent");
        fireAgentButton.setFont(policeButton);
        fireAgentButton.setBackground(Color.WHITE);
        fireAgentButton.addActionListener(this);
        fireAgentArea.add(fireAgentButton);

        //TODO VERIFIER SI LE JOUEUR A UN CLUB
        JPanel resignArea = new JPanel();
        resignButton = new JButton("Quitter votre club");
        resignButton.setFont(policeButton);
        resignButton.setBackground(Color.WHITE);
        resignButton.addActionListener(this);
        resignArea.add(resignButton);

        JPanel all = new JPanel();
        all.setLayout(new BoxLayout(all, BoxLayout.PAGE_AXIS));
        all.add(disconnectArea);
        all.add(titleArea);
        all.add(clubArea);
        //all.add(agentArea);
        all.add(propositionsClubArea);
        //all.add(propositionsAgentArea);
        //all.add(proposeContractArea);
        all.add(fireAgentArea);
        all.add(resignArea);
        this.getContentPane().add(new JScrollPane(all));
        this.setVisible(true);
    }

    public void propositionClub() throws InterruptedException, ExecutionException, TransactionException, CipherException, IOException {
        answerClubFrame = new JFrame();
        answerClubFrame.setTitle("Propositions en attente");
        answerClubFrame.setSize(width/2, height/3);
        answerClubFrame.setLocationRelativeTo(null); // centrer la fenêtre sur l'écran
        answerClubFrame.setResizable(true);

        JPanel titleArea = new JPanel();
        JLabel title = new JLabel("Voici les propositions en attente");
        title.setFont(policeButton);
        titleArea.add(title);

        //TODO RECUP LISTE PROPOSITIONS
        String titre[] = {"Club", "Durée de contrat", "Montant du transfert"};
        listOfferPlayer = player.getListOfferPlayer();

        JPanel listPropositionsArea = new JPanel();
        table = new JTable(listOfferPlayer, titre);
        table.setFont(policeGeneral);
        table.setRowHeight(30);
        if(listOfferPlayer.length > 0){
            for(int i=0; i<listOfferPlayer[0].length; i++){
                table.getColumnModel().getColumn(i).setPreferredWidth(180);
            }
            listPropositionsArea.add(new JScrollPane(table));
        }

        JPanel choiceArea = new JPanel();
        choiceArea.setLayout(new BoxLayout(choiceArea, BoxLayout.LINE_AXIS));
        acceptPropositionClubButton = new JButton("Accepter l'offre");
        acceptPropositionClubButton.setFont(policeButton);
        acceptPropositionClubButton.setBackground(Color.WHITE);
        acceptPropositionClubButton.addActionListener(this);
        choiceArea.add(acceptPropositionClubButton);
        declinePropositionClubButton = new JButton("Refuser l'offre");
        declinePropositionClubButton.setFont(policeButton);
        declinePropositionClubButton.setBackground(Color.WHITE);
        declinePropositionClubButton.addActionListener(this);
        choiceArea.add(declinePropositionClubButton);

        JPanel all = new JPanel();
        all.setLayout(new BoxLayout(all, BoxLayout.PAGE_AXIS));
        all.add(titleArea);
        all.add(listPropositionsArea);
        all.add(choiceArea);
        answerClubFrame.getContentPane().add(new JScrollPane(all));
        answerClubFrame.setVisible(true);
    }

    public void proposeContract(){
        proposeContractAgentFrame = new JFrame();
        proposeContractAgentFrame.setTitle("Proposer une offre à un agent");
        proposeContractAgentFrame.setSize(width/2, height/3);
        proposeContractAgentFrame.setLocationRelativeTo(null); // centrer la fenêtre sur l'écran
        proposeContractAgentFrame.setResizable(true);

        JPanel titleArea = new JPanel();
        JLabel title = new JLabel("Choisissez un agent à qui faire une offre");
        title.setFont(policeButton);
        titleArea.add(title);

        //TODO RECUP LISTE AGENTS
        String titre[] = {"Pseudo", "Age", "Taille"};

        JPanel listPropositionsArea = new JPanel();
        table = new JTable(data, titre);
        table.setFont(policeGeneral);
        table.setRowHeight(30);
        for(int i=0; i<data[0].length; i++){
            table.getColumnModel().getColumn(i).setPreferredWidth(180);
        }
        listPropositionsArea.add(new JScrollPane(table));

        JPanel sendPropositionAgentArea = new JPanel();
        sendPropositionAgentArea.setLayout(new BoxLayout(sendPropositionAgentArea, BoxLayout.LINE_AXIS));
        sendPropositionAgentButton = new JButton("Envoyer l'offre");
        sendPropositionAgentButton.setFont(policeButton);
        sendPropositionAgentButton.setBackground(Color.WHITE);
        sendPropositionAgentButton.addActionListener(this);
        sendPropositionAgentArea.add(sendPropositionAgentButton);

        JPanel all = new JPanel();
        all.setLayout(new BoxLayout(all, BoxLayout.PAGE_AXIS));
        all.add(titleArea);
        all.add(listPropositionsArea);
        all.add(sendPropositionAgentArea);
        proposeContractAgentFrame.getContentPane().add(new JScrollPane(all));
        proposeContractAgentFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == disconnectButton){
            this.dispose();
            new Login();

        }

        if (e.getSource() == propositionsClubButton){

            try {
                propositionClub();
            } catch (InterruptedException | ExecutionException | CipherException | TransactionException | IOException e1) {
                e1.printStackTrace();
            }

        }

        //if (e.getSource() == propositionsAgentButton){
        //    propositionAgent();
        //}

        if (e.getSource() == proposeContractButton){
            proposeContract();
        }

        if (e.getSource() == fireAgentButton){
            //TODO VIRER AGENT
            this.dispose();
            new HomePlayer(address, password);
            new Informations("Vous avez viré votre agent");
        }

        if (e.getSource() == resignButton){
            //TODO QUITTER CLUB
            this.dispose();
            new HomePlayer(address, password);
            new Informations("Vous avez quitté votre club");
        }


        if (e.getSource() == acceptPropositionClubButton){
            //TODO Accepter offre club
            if(table.getSelectedRow() >= 0) {
                Club clubOffering = Variables.listClubs.get(listOfferPlayer[table.getSelectedRow()][0]);
                try {
                    player.acceptOfferClub(clubOffering);
                } catch (IOException | CipherException | InterruptedException | TransactionException | ExecutionException e1) {
                    e1.printStackTrace();
                }

                answerClubFrame.dispose();
                try {
                    propositionClub();
                } catch (InterruptedException | ExecutionException | CipherException | TransactionException | IOException e1) {
                    e1.printStackTrace();
                }
                new Informations("L'offre de " + clubOffering.name + " a été acceptée");
            }
        }
        if (e.getSource() == declinePropositionClubButton){
            //TODO Refuser offre club
            if(table.getSelectedRow() >= 0) {
                Club clubOffering = Variables.listClubs.get(listOfferPlayer[table.getSelectedRow()][0]);
                try {
                    player.refuseOfferClub(clubOffering);
                } catch (IOException | CipherException | InterruptedException | TransactionException | ExecutionException e1) {
                    e1.printStackTrace();
                }
                answerClubFrame.dispose();
                try {
                    propositionClub();
                } catch (InterruptedException | ExecutionException | CipherException | TransactionException | IOException e1) {
                    e1.printStackTrace();
                }
                new Informations("L'offre de " + clubOffering.name + " a été refusée");
            }
        }

        /*if (e.getSource() == acceptPropositionAgentButton){
            //TODO Accepter offre agent
            if(table.getSelectedRow() >= 0) {
                System.out.println(table.getSelectedRow());
                answerAgentFrame.dispose();
                propositionAgent();
                new Informations("L'offre de l'agent a été acceptée");
            }
        }
        if (e.getSource() == declinePropositionClubButton){
            //TODO Refuser offre agent
            if(table.getSelectedRow() >= 0) {
                System.out.println(table.getSelectedRow());
                answerClubFrame.dispose();
                propositionAgent();
                new Informations("L'offre de l'agent a été refusée");
            }
        }*/

        if (e.getSource() == sendPropositionAgentButton){
            //TODO Envoyer proposition à l'agent
            proposeContractAgentFrame.dispose();
            new Informations("La proposition a été envoyée");

        }
    }
}
