package view;

import back.Variables;
import model.Club;
import model.Player;
import org.web3j.crypto.CipherException;
import org.web3j.protocol.exceptions.TransactionException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.math.BigInteger;
import java.util.concurrent.ExecutionException;

public class HomeClub extends JFrame implements ActionListener {
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private int width = screenSize.width;
    private int height = screenSize.height;
    Font policeDisconnect = new Font(" Arial ",Font.BOLD,14);
    Font policeGeneral = new Font(" Arial ",Font.BOLD,18);
    Font policeButton = new Font(" Arial ",Font.BOLD,22);
    Font policeTitle = new Font(" Arial ",Font.BOLD,40);

    String address;
    String password;
    String mercato_address;
    Club club;

    Object [][] playersClub;
    Object [][] playersAvailable;
    Object [][] listOfferClub;
    Object [][] listSwapOffers;
    Object [][] listFinalizedTransfert;

    JFrame transfertReceivedFrame;
    JFrame finalizedTransfertFrame;
    JFrame propositionFrame;
    JFrame exchangeFrame;
    JFrame hireFrame;
    JFrame renewFrame;

    JTable tableClub;
    JTable tableAvailable;
    JTable tableTransfertReceived;
    JTable tableExchangeReceived;
    JTable tableFinalizedReceived;
    JTextField yearsContract;
    JTextField priceContract;
    JTextField messageFinalizedTransfert;

    JButton disconnectButton;
    JButton playersClubButton;
    JButton finalizedTransfertButton;
    JButton acceptFinalizedTransfertButton;
    JButton declineFinalizedTransfertButton;
    JButton acceptPropositionClubButton;
    JButton declinePropositionClubButton;
    JButton transfertReceivedButton;
    JButton transfertButton;
    JButton exchangeButton;
    JButton renewContractButton;
    JButton hirePlayerButton;

    JButton confirmPropositionButton;
    JButton confirmExchangeButton;
    JButton confirmRenewButton;
    JButton confirmHireButton;

    Object[][] data = {
            {"Cysboy", "28 ans", "1.80 m"},
            {"BZHHydde", "28 ans", "1.80 m"},
            {"IamBow", "24 ans", "1.90 m"},
            {"FunMan", "32 ans", "1.85 m"}
    };

    public static void main(String[] args){
        //new HomeClub("AS Monaco","");
    }

    public HomeClub(String address, String password) {
        this.address = address;
        this.password = password;

        this.club = Variables.listClubs.get(this.address);

        this.setTitle("Votre espace perso - " + club.name);
        this.setSize(width/2, height*3/4);
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
        JLabel title = new JLabel(club.name.toUpperCase() + " - " + "CLUB");
        title.setFont(policeTitle);
        titleArea.add(title);

        JPanel budgetArea = new JPanel();
        JLabel budgetLabel = new JLabel();
        try {
            budgetLabel = new JLabel("Votre budget actuel est " + club.getBalance());
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        budgetLabel.setFont(policeGeneral);
        budgetArea.add(budgetLabel);

        JPanel playersClubArea = new JPanel();
        playersClubButton = new JButton("Voir votre liste de joueurs");
        playersClubButton.setFont(policeButton);
        playersClubButton.setBackground(Color.WHITE);
        playersClubButton.addActionListener(this);
        playersClubArea.add(playersClubButton);

        JPanel transfertReceivedArea = new JPanel();
        transfertReceivedButton = new JButton("Voir les offres reçues");
        transfertReceivedButton.setFont(policeButton);
        transfertReceivedButton.setBackground(Color.WHITE);
        transfertReceivedButton.addActionListener(this);
        transfertReceivedArea.add(transfertReceivedButton);

        JPanel finalizedTransfertArea = new JPanel();
        finalizedTransfertButton = new JButton("Finaliser un transfert");
        finalizedTransfertButton.setFont(policeButton);
        finalizedTransfertButton.setBackground(Color.WHITE);
        finalizedTransfertButton.addActionListener(this);
        finalizedTransfertArea.add(finalizedTransfertButton);

        JPanel transfertArea = new JPanel();
        transfertButton = new JButton("Faire une proposition de transfert");
        transfertButton.setFont(policeButton);
        transfertButton.setBackground(Color.WHITE);
        transfertButton.addActionListener(this);
        transfertArea.add(transfertButton);

        JPanel exchangeArea = new JPanel();
        exchangeButton = new JButton("Faire une proposition d'échange de joueurs");
        exchangeButton.setFont(policeButton);
        exchangeButton.setBackground(Color.WHITE);
        exchangeButton.addActionListener(this);
        exchangeArea.add(exchangeButton);

        JPanel renewContractArea = new JPanel();
        renewContractButton = new JButton("Renouveler un contrat");
        renewContractButton.setFont(policeButton);
        renewContractButton.setBackground(Color.WHITE);
        renewContractButton.addActionListener(this);
        renewContractArea.add(renewContractButton);

        JPanel  hirePlayerArea = new JPanel();
        hirePlayerButton = new JButton("Renvoyer un joueur");
        hirePlayerButton.setFont(policeButton);
        hirePlayerButton.setBackground(Color.WHITE);
        hirePlayerButton.addActionListener(this);
        hirePlayerArea.add(hirePlayerButton);


        JPanel all = new JPanel();
        all.setLayout(new BoxLayout(all, BoxLayout.PAGE_AXIS));
        all.add(disconnectArea);
        all.add(titleArea);
        all.add(budgetArea);
        all.add(playersClubArea);
        all.add(transfertReceivedArea);
        all.add(finalizedTransfertArea);
        all.add(transfertArea);
        all.add(exchangeArea);
        //all.add(renewContractArea);
        //all.add(hirePlayerArea);
        this.getContentPane().add(new JScrollPane(all));
        this.setVisible(true);
    }

    public void seePlayers(){
        JFrame frame = new JFrame();
        frame.setTitle("Joueurs du club");
        frame.setSize(width/2, height/3);
        frame.setLocationRelativeTo(null); // centrer la fenêtre sur l'écran
        frame.setResizable(true);

        JPanel titleArea = new JPanel();
        JLabel title = new JLabel("Voici les joueurs du club");
        title.setFont(policeButton);
        titleArea.add(title);

        JPanel all = new JPanel();
        all.setLayout(new BoxLayout(all, BoxLayout.PAGE_AXIS));
        all.add(titleArea);
        all.add(listPlayersClubArea());
        frame.getContentPane().add(new JScrollPane(all));
        frame.setVisible(true);
    }

    public void transfertReceived(){
        transfertReceivedFrame = new JFrame();
        transfertReceivedFrame.setTitle("Propositions en attente");
        transfertReceivedFrame.setSize(width, height*3/4);
        transfertReceivedFrame.setLocationRelativeTo(null); // centrer la fenêtre sur l'écran
        transfertReceivedFrame.setResizable(true);

        JPanel titleArea = new JPanel();
        JLabel title = new JLabel("Voici les propositions en attente");
        title.setFont(policeButton);
        titleArea.add(title);

        //TODO RECUP LISTE PROPOSITIONS
        String titre[] = { "adresse Joueur","adresse club","Joueur", "Club Offrant", "Durée", "Montant du transfert", "Player choice", "Your Choice"};
        listOfferClub = new Object[0][0];

        try {
            listOfferClub = club.listOfferReceived();
        } catch (IOException | CipherException | InterruptedException | TransactionException | ExecutionException e) {
            e.printStackTrace();
        }

        JPanel listPropositionsArea = new JPanel();
        tableTransfertReceived = new JTable(listOfferClub, titre);
        tableTransfertReceived.setFont(policeGeneral);
        tableTransfertReceived.setRowHeight(30);
        if (listOfferClub.length > 0){
            for(int i=0; i<listOfferClub[0].length; i++){
                int specialWidth;
                if(i<2) {
                    specialWidth = 0;
                    tableTransfertReceived.getColumnModel().getColumn(i).setPreferredWidth(specialWidth);
                    tableTransfertReceived.getColumnModel().getColumn(i).setMinWidth(specialWidth);
                    tableTransfertReceived.getColumnModel().getColumn(i).setMaxWidth(specialWidth);
                }
                else {
                    specialWidth = 800;
                    tableTransfertReceived.getColumnModel().getColumn(i).setPreferredWidth(specialWidth);
                }
            }
        }
        listPropositionsArea.add(new JScrollPane(tableTransfertReceived));

        //TODO récupérer liste des échanges proposés
        JPanel listExchangeArea = new JPanel();
        String titreSwap[] = { "adresse Joueur","adresse Joueur","Joueur Proposé", "Mon Joueur", "Club ", "Durée Contrat Joueur 1", "Durée Contrat Joueur 2"};
        listSwapOffers = new Object[0][0];

        try {
            listSwapOffers = club.listSwapReceived();
        } catch (IOException | CipherException | InterruptedException | TransactionException | ExecutionException e) {
            e.printStackTrace();
        }

        tableExchangeReceived = new JTable(listSwapOffers, titreSwap);
        tableExchangeReceived.setFont(policeGeneral);
        tableExchangeReceived.setRowHeight(30);
        if (listSwapOffers.length > 0){
            for(int i=0; i<listSwapOffers[0].length; i++){
                int specialWidth;
                if(i<2) {
                    specialWidth = 0;
                    tableExchangeReceived.getColumnModel().getColumn(i).setMinWidth(specialWidth);
                    tableExchangeReceived.getColumnModel().getColumn(i).setMaxWidth(specialWidth);
                }
                else {
                    specialWidth = 800;
                }
                tableExchangeReceived.getColumnModel().getColumn(i).setPreferredWidth(specialWidth);

            }
        }
        listExchangeArea.add(new JScrollPane(tableExchangeReceived));


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
        all.add(listExchangeArea);
        all.add(choiceArea);
        transfertReceivedFrame.getContentPane().add(new JScrollPane(all));
        transfertReceivedFrame.setVisible(true);
    }

    public void finalizedTransfert(){
        finalizedTransfertFrame = new JFrame();
        finalizedTransfertFrame.setTitle("Finaliser un transfert");
        finalizedTransfertFrame.setSize(width, height/3);
        finalizedTransfertFrame.setLocationRelativeTo(null); // centrer la fenêtre sur l'écran
        finalizedTransfertFrame.setResizable(true);

        JPanel titleArea = new JPanel();
        JLabel title = new JLabel("Voici les offres pouvant être finalisées");
        title.setFont(policeButton);
        titleArea.add(title);

        //TODO RECUP LISTE PROPOSITIONS A finaliser
        String titre[] = { "adresse Joueur", "Joueur","Durée contrat", "Montant transfert"};
        listFinalizedTransfert = new Object[0][0];

        try {
            listFinalizedTransfert = club.finalizableOffers();
        } catch (Exception e) {
            e.printStackTrace();
        }


        JPanel listPropositionsArea = new JPanel();
        tableFinalizedReceived = new JTable(listFinalizedTransfert, titre);
        tableFinalizedReceived.setFont(policeGeneral);
        tableFinalizedReceived.setRowHeight(30);
        for(int i=0; i<listFinalizedTransfert[0].length; i++){
            tableFinalizedReceived.getColumnModel().getColumn(i).setPreferredWidth(160);
        }
        listPropositionsArea.add(new JScrollPane(tableFinalizedReceived));

        JPanel messageFinalizedTransfertArea = new JPanel();
        JLabel messageLabel = new JLabel("Message pour le club receveur : ");
        messageLabel.setFont(policeGeneral);
        messageFinalizedTransfert = new JTextField(30);
        messageFinalizedTransfertArea.add(messageLabel);


        JPanel choiceArea = new JPanel();
        choiceArea.setLayout(new BoxLayout(choiceArea, BoxLayout.LINE_AXIS));
        acceptFinalizedTransfertButton = new JButton("Finaliser l'offre");
        acceptFinalizedTransfertButton.setFont(policeButton);
        acceptFinalizedTransfertButton.setBackground(Color.WHITE);
        acceptFinalizedTransfertButton.addActionListener(this);
        choiceArea.add(acceptFinalizedTransfertButton);
        declineFinalizedTransfertButton = new JButton("Refuser l'offre");
        declineFinalizedTransfertButton.setFont(policeButton);
        declineFinalizedTransfertButton.setBackground(Color.WHITE);
        declineFinalizedTransfertButton.addActionListener(this);
        choiceArea.add(declineFinalizedTransfertButton);

        JPanel all = new JPanel();
        all.setLayout(new BoxLayout(all, BoxLayout.PAGE_AXIS));
        all.add(titleArea);
        all.add(listPropositionsArea);
        all.add(messageFinalizedTransfertArea);
        all.add(choiceArea);
        finalizedTransfertFrame.getContentPane().add(new JScrollPane(all));
        finalizedTransfertFrame.setVisible(true);
    }

    public void transfertProposition(){
        propositionFrame = new JFrame();
        propositionFrame.setTitle("Proposition de contrat");
        propositionFrame.setSize(width/2, height/3);
        propositionFrame.setLocationRelativeTo(null); // centrer la fenêtre sur l'écran
        propositionFrame.setResizable(true);

        JPanel titleArea = new JPanel();
        JLabel title = new JLabel("Sélectionner le joueur à qui faire une offre");
        title.setFont(policeButton);
        titleArea.add(title);

        JPanel yearsArea = new JPanel();
        JLabel years = new JLabel("Nombre d'années proposé : ");
        years.setFont(policeGeneral);
        yearsContract = new JTextField(20);
        yearsArea.add(years);
        yearsArea.add(yearsContract);

        JPanel priceArea = new JPanel();
        JLabel price = new JLabel("Prix proposé : ");
        price.setFont(policeGeneral);
        priceContract = new JTextField(20);
        priceArea.add(price);
        priceArea.add(priceContract);


        JPanel confirmPropositionArea = new JPanel();
        confirmPropositionArea.setLayout(new BoxLayout(confirmPropositionArea, BoxLayout.LINE_AXIS));
        confirmPropositionButton = new JButton("Envoyer l'offre de transfert");
        confirmPropositionButton.setFont(policeButton);
        confirmPropositionButton.setBackground(Color.WHITE);
        confirmPropositionButton.addActionListener(this);
        confirmPropositionArea.add(confirmPropositionButton);


        JPanel all = new JPanel();
        all.setLayout(new BoxLayout(all, BoxLayout.PAGE_AXIS));
        all.add(titleArea);
        all.add(listPlayersAvailablerea());
        all.add(yearsArea);
        all.add(priceArea);
        all.add(confirmPropositionArea);
        propositionFrame.getContentPane().add(new JScrollPane(new JScrollPane(all)));
        propositionFrame.setVisible(true);
    }

    public void exchangeProposition(){
        exchangeFrame = new JFrame();
        exchangeFrame.setTitle("Proposition d'échanges");
        exchangeFrame.setSize(width/2, height/3*2);
        exchangeFrame.setLocationRelativeTo(null); // centrer la fenêtre sur l'écran
        exchangeFrame.setResizable(true);

        JPanel titleArea = new JPanel();
        JLabel title = new JLabel("Sélectionner le joueur de votre club à échanger");
        title.setFont(policeButton);
        titleArea.add(title);

        JPanel secondTitleArea = new JPanel();
        JLabel secondTitle = new JLabel("Sélectionner le joueur à échanger");
        secondTitle.setFont(policeButton);
        secondTitleArea.add(secondTitle);

        JPanel confirmExchangeArea = new JPanel();
        confirmExchangeArea.setLayout(new BoxLayout(confirmExchangeArea, BoxLayout.LINE_AXIS));
        confirmExchangeButton = new JButton("Envoyer l'offre de transfert");
        confirmExchangeButton.setFont(policeButton);
        confirmExchangeButton.setBackground(Color.WHITE);
        confirmExchangeButton.addActionListener(this);
        confirmExchangeArea.add(confirmExchangeButton);


        JPanel all = new JPanel();
        all.setLayout(new BoxLayout(all, BoxLayout.PAGE_AXIS));
        all.add(titleArea);
        all.add(listPlayersClubArea());
        all.add(secondTitleArea);
        all.add(listPlayersAvailablerea());
        all.add(confirmExchangeArea);
        exchangeFrame.getContentPane().add(new JScrollPane(all));
        exchangeFrame.setVisible(true);

    }

    public void renewContract(){
        renewFrame = new JFrame();
        renewFrame.setTitle("Renouveler un contrat");
        renewFrame.setSize(width/2, height/3);
        renewFrame.setLocationRelativeTo(null); // centrer la fenêtre sur l'écran
        renewFrame.setResizable(true);

        JPanel titleArea = new JPanel();
        JLabel title = new JLabel("Sélectionner le joueur pour lequel vous voulez renouveler le contrat");
        title.setFont(policeButton);
        titleArea.add(title);

        JPanel yearsArea = new JPanel();
        JLabel years = new JLabel("Années supplémentaires : ");
        years.setFont(policeGeneral);
        yearsContract = new JTextField(20);
        yearsArea.add(years);
        yearsArea.add(yearsContract);


        JPanel confirmRenewArea = new JPanel();
        confirmRenewArea.setLayout(new BoxLayout(confirmRenewArea, BoxLayout.LINE_AXIS));
        confirmRenewButton = new JButton("Confirmer la proposition de renouvellement ");
        confirmRenewButton.setFont(policeButton);
        confirmRenewButton.setBackground(Color.WHITE);
        confirmRenewButton.addActionListener(this);
        confirmRenewArea.add(confirmRenewButton);

        JPanel all = new JPanel();
        all.setLayout(new BoxLayout(all, BoxLayout.PAGE_AXIS));
        all.add(titleArea);
        all.add(listPlayersClubArea());
        all.add(yearsArea);
        all.add(confirmRenewArea);
        renewFrame.getContentPane().add(new JScrollPane(all));
        renewFrame.setVisible(true);
    }

    public void hirePlayer(){
        hireFrame = new JFrame();
        hireFrame.setTitle("Renvoyer un joueur");
        hireFrame.setSize(width/2, height/3);
        hireFrame.setLocationRelativeTo(null); // centrer la fenêtre sur l'écran
        hireFrame.setResizable(true);

        JPanel titleArea = new JPanel();
        JLabel title = new JLabel("Sélectionner le joueur à renvoyer");
        title.setFont(policeButton);
        titleArea.add(title);

        JPanel confirmHireArea = new JPanel();
        confirmHireArea.setLayout(new BoxLayout(confirmHireArea, BoxLayout.LINE_AXIS));
        confirmHireButton = new JButton("Renvoyer le joueur sélectionné");
        confirmHireButton.setFont(policeButton);
        confirmHireButton.setBackground(Color.WHITE);
        confirmHireButton.addActionListener(this);
        confirmHireArea.add(confirmHireButton);

        JPanel all = new JPanel();
        all.setLayout(new BoxLayout(all, BoxLayout.PAGE_AXIS));
        all.add(titleArea);
        all.add(listPlayersClubArea());
        all.add(confirmHireArea);
        hireFrame.getContentPane().add(new JScrollPane(all));
        hireFrame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == disconnectButton){
            this.dispose();
            new Login();
        }
        if (e.getSource() == playersClubButton) seePlayers();
        if (e.getSource() == transfertReceivedButton) transfertReceived();
        if (e.getSource() == finalizedTransfertButton) finalizedTransfert();
        if (e.getSource() == transfertButton) transfertProposition();
        if (e.getSource() == exchangeButton) exchangeProposition();
        if (e.getSource() == renewContractButton) renewContract();
        if (e.getSource() == hirePlayerButton) hirePlayer();

        if (e.getSource() == acceptPropositionClubButton){
            //TODO Accepter offre club
            if(tableTransfertReceived.getSelectedRow() >= 0) {
                Player player = Variables.listPlayers.get(listOfferClub[tableTransfertReceived.getSelectedRow()][0]);
                Club clubOffering = Variables.listClubs.get(listOfferClub[tableTransfertReceived.getSelectedRow()][1]);

                try {
                    club.acceptTransferProposal(player,clubOffering);
                } catch (IOException | CipherException | InterruptedException | TransactionException | ExecutionException e1) {
                    e1.printStackTrace();
                }

                transfertReceivedFrame.dispose();
                transfertReceived();
                new Informations("L'offre de " + clubOffering.name + " pour " + player.name + " a été acceptée");
            }
            if(tableTransfertReceived.getSelectedRow() < 0 && tableExchangeReceived.getSelectedRow() >= 0){
                //TODO Accepter offre d'échange

                Player myPlayer     = Variables.listPlayers.get(listSwapOffers[tableExchangeReceived.getSelectedRow()][1]);
                Player playerWanted = Variables.listPlayers.get(listSwapOffers[tableExchangeReceived.getSelectedRow()][0]);

                try {
                    club.acceptSwapProposal(myPlayer,playerWanted);
                } catch (IOException | CipherException e1) {
                    e1.printStackTrace();
                }
            }
        }
        if (e.getSource() == declinePropositionClubButton){
            //TODO Refuser offre club
            if(tableTransfertReceived.getSelectedRow() >= 0) {
                Player player = Variables.listPlayers.get(listOfferClub[tableTransfertReceived.getSelectedRow()][0]);
                Club clubOffering = Variables.listClubs.get(listOfferClub[tableTransfertReceived.getSelectedRow()][1]);

                try {
                    club.refuseTransferProposal(player,clubOffering);
                } catch (IOException | CipherException | InterruptedException | TransactionException | ExecutionException e1) {
                    e1.printStackTrace();
                }

                transfertReceivedFrame.dispose();
                transfertReceived();
                new Informations("L'offre de " + clubOffering.name + " pour " + player.name + " a été refusé");
            }
        }

        if (e.getSource() == acceptFinalizedTransfertButton){
            //TODO finaliser offre transfert
            // Récupérer message : messageFinalizedTransfert.getText();
            if(tableFinalizedReceived.getSelectedRow() >= 0) {
                Player player = Variables.listPlayers.get(listFinalizedTransfert[tableFinalizedReceived.getSelectedRow()][0]);
                String amount = listFinalizedTransfert[tableFinalizedReceived.getSelectedRow()][3].toString();
                try {
                    club.finalizeTransfer(player,amount);
                } catch (IOException | CipherException | InterruptedException | TransactionException | ExecutionException e1) {
                    e1.printStackTrace();
                }


                finalizedTransfertFrame.dispose();
                finalizedTransfert();
                new Informations("a signé au club");
            }


        }
        if (e.getSource() == declineFinalizedTransfertButton){
            //TODO refuser de finaliser une offre de transfert
            // Récupérer message : messageFinalizedTransfert.getText();
            finalizedTransfertFrame.dispose();
            finalizedTransfert();
        }


        if (e.getSource() == confirmPropositionButton){
            if(tableAvailable.getSelectedRow() >= 0 && yearsContract.getText().length()!=0 && priceContract.getText().length()!=0) {
                //TODO ENVOYER PROPOSITION DE TRANSFERT

                Player player = Variables.listPlayers.get(playersAvailable[tableAvailable.getSelectedRow()][1]);
                int duration = Integer.parseInt(yearsContract.getText());

                try {
                    club.transferProposal(player, BigInteger.valueOf(duration),priceContract.getText());
                } catch (IOException | CipherException | InterruptedException | TransactionException | ExecutionException e1) {
                    e1.printStackTrace();
                }

                propositionFrame.dispose();
                transfertProposition();
                new Informations("La proposition de transfert a été envoyé");
            }
        }

        if (e.getSource() == confirmExchangeButton){
            if(tableAvailable.getSelectedRow() >= 0 && tableClub.getSelectedRow() >= 0){
                //TODO FAIRE PROPOSITION ECHANGE

                Player player   = Variables.listPlayers.get(playersAvailable[tableAvailable.getSelectedRow()][1]);
                Player myPlayer =  Variables.listPlayers.get(playersClub[tableClub.getSelectedRow()][0]);

                try {
                    club.swapProposal(myPlayer,player, BigInteger.valueOf(1),BigInteger.valueOf(1));
                } catch (IOException | CipherException e1) {
                    e1.printStackTrace();
                }
                exchangeFrame.dispose();
                exchangeProposition();
                new Informations("La proposition d'échange a été envoyée");
            }
        }

        if (e.getSource() == confirmRenewButton){
            if(tableClub.getSelectedRow() >= 0 && yearsContract.getText().length()!=0) {
                //TODO RENOUVELER LE CONTRAT
                //yearsContract.getText()
                renewFrame.dispose();
                renewContract();
                new Informations("La proposition a été envoyée");
            }
        }

        if (e.getSource() == confirmHireButton){
            if(tableClub.getSelectedRow() >= 0) {
                //TODO RENVOYER JOUEUR SELECTIONNE
                hireFrame.dispose();
                hirePlayer();
                new Informations("Le joueur a été renvoyé");
            }
        }
    }

    public JPanel listPlayersClubArea(){
        //TODO RECUP LISTE JOUEURS DU CLUB
        String titre[] = {"Adresse","Nom","Durée du contrat", "Prix d'achat",};
        JPanel listPlayersArea = new JPanel();

        try {
            playersClub = club.getPlayerOfClub();
        } catch (IOException | CipherException | InterruptedException | TransactionException | ExecutionException e) {
            e.printStackTrace();
        }




        tableClub = new JTable(playersClub, titre);
        tableClub.setFont(policeGeneral);
        tableClub.setRowHeight(30);

        for(int i=0; i<playersClub[0].length; i++){
            int specialWidth;
            if(i<1) {
                specialWidth = 0;
                tableClub.getColumnModel().getColumn(i).setMinWidth(specialWidth);
                tableClub.getColumnModel().getColumn(i).setMaxWidth(specialWidth);
            }
            else {
                specialWidth = 800;
            }
            tableClub.getColumnModel().getColumn(i).setPreferredWidth(specialWidth);

        }
        listPlayersArea.add(new JScrollPane(tableClub));
        return listPlayersArea;
    }

    public JPanel listPlayersAvailablerea(){
        //TODO RECUP LISTE JOUEURS DISPONIBLES
        String titre[] = {"Nom", "Club"};
        JPanel listPlayersArea = new JPanel();
        playersAvailable = club.getPlayersOnMarket();
     //   playersAvailable = new Object[Variables.listPlayers.size()][2];
/*
        int j=0;
        for(String key : Variables.listPlayers.keySet()){
            if (Variables.listPlayers.get(key).getAddressClub() != club.addressShort){
                System.out.println(Variables.listPlayers.get(key).name);
                playersAvailable[j][0] = Variables.listPlayers.get(key).name;
                playersAvailable[j][1] = Variables.listPlayers.get(key).getNameClub();
                j++;
            }
        }
*/
        tableAvailable = new JTable(playersAvailable, titre);
        tableAvailable.setFont(policeGeneral);
        tableAvailable.setRowHeight(30);
        //for(int i=0; i<playersAvailable[0].length; i++){
        tableAvailable.getColumnModel().getColumn(0).setPreferredWidth(180);
        tableAvailable.getColumnModel().getColumn(1).setPreferredWidth(0);
        //}

        listPlayersArea.add(new JScrollPane(tableAvailable));
        return listPlayersArea;
    }
}
