package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeClub extends JFrame implements ActionListener {
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private int width = screenSize.width;
    private int height = screenSize.height;
    Font policeGeneral = new Font(" Arial ",Font.BOLD,18);
    Font policeButton = new Font(" Arial ",Font.BOLD,22);
    Font policeTitle = new Font(" Arial ",Font.BOLD,40);
    String username;

    JFrame propositionFrame;
    JFrame hireFrame;
    JFrame renewFrame;

    JTable table;
    JTextField yearsContract;
    JTextField priceContract;

    JButton playersClubButton;
    JButton transfertButton;
    JButton exchangeButton;
    JButton renewContractButton;
    JButton hirePlayerButton;

    JButton confirmPropositionButton;
    JButton confirmRenewButton;
    JButton confirmHireButton;

    Object[][] data = {
            {"Cysboy", "28 ans", "1.80 m"},
            {"BZHHydde", "28 ans", "1.80 m"},
            {"IamBow", "24 ans", "1.90 m"},
            {"FunMan", "32 ans", "1.85 m"}
    };

    public static void main(String[] args){
        new HomeClub("AS Monaco");
    }

    public HomeClub(String username) {
        this.username = username;
        this.setTitle("Votre espace perso - " + username);
        this.setSize(width/2, height/2);
        this.setLocationRelativeTo(null); // centrer la fenêtre sur l'écran
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // fin du programme lors de la fermeture de la fenêtre
        this.setResizable(false);

        JPanel titleArea = new JPanel();
        JLabel title = new JLabel(username.toUpperCase() + " - " + "CLUB");
        title.setFont(policeTitle);
        titleArea.add(title);

        JPanel playersClubArea = new JPanel();
        playersClubButton = new JButton("Voir votre liste de joueurs");
        playersClubButton.setFont(policeButton);
        playersClubButton.setBackground(Color.WHITE);
        playersClubButton.addActionListener(this);
        playersClubArea.add(playersClubButton);

        JPanel transfertnArea = new JPanel();
        transfertButton = new JButton("Faire une proposition de transfert");
        transfertButton.setFont(policeButton);
        transfertButton.setBackground(Color.WHITE);
        transfertButton.addActionListener(this);
        transfertnArea.add(transfertButton);

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
        all.add(titleArea);
        all.add(playersClubArea);
        all.add(transfertnArea);
        all.add(exchangeArea);
        all.add(renewContractArea);
        all.add(hirePlayerArea);
        this.getContentPane().add(all);
        this.setVisible(true);
    }

    public void seePlayers(){
        JFrame frame = new JFrame();
        frame.setTitle("Joueurs du club");
        frame.setSize(width/2, height/3);
        frame.setLocationRelativeTo(null); // centrer la fenêtre sur l'écran
        frame.setResizable(false);

        JPanel titleArea = new JPanel();
        JLabel title = new JLabel("Voici les joueurs du club");
        title.setFont(policeButton);
        titleArea.add(title);

        JPanel all = new JPanel();
        all.setLayout(new BoxLayout(all, BoxLayout.PAGE_AXIS));
        all.add(titleArea);
        all.add(listPlayersClubArea());
        frame.getContentPane().add(all);
        frame.setVisible(true);
    }

    public void transfertProposition(){
        propositionFrame = new JFrame();
        propositionFrame.setTitle("Proposition de contrat");
        propositionFrame.setSize(width/2, height/3);
        propositionFrame.setLocationRelativeTo(null); // centrer la fenêtre sur l'écran
        propositionFrame.setResizable(false);

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
        propositionFrame.getContentPane().add(all);
        propositionFrame.setVisible(true);
    }


    public void renewContract(){
        renewFrame = new JFrame();
        renewFrame.setTitle("Renouveler un contrat");
        renewFrame.setSize(width/2, height/3);
        renewFrame.setLocationRelativeTo(null); // centrer la fenêtre sur l'écran
        renewFrame.setResizable(false);

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
        renewFrame.getContentPane().add(all);
        renewFrame.setVisible(true);

    }
    public void hirePlayer(){
        hireFrame = new JFrame();
        hireFrame.setTitle("Renvoyer un joueur");
        hireFrame.setSize(width/2, height/3);
        hireFrame.setLocationRelativeTo(null); // centrer la fenêtre sur l'écran
        hireFrame.setResizable(false);

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
        hireFrame.getContentPane().add(all);
        hireFrame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == playersClubButton){
            seePlayers();
        }
        if (e.getSource() == transfertButton){
            transfertProposition();
        }
        if (e.getSource() == exchangeButton){}
        if (e.getSource() == renewContractButton){
            renewContract();
        }
        if (e.getSource() == hirePlayerButton){
            hirePlayer();
        }

        if (e.getSource() == confirmPropositionButton){
            if(table.getSelectedRow() >= 0 && yearsContract.getText().length()!=0 && priceContract.getText().length()!=0) {
                //TODO ENVOYER PROPOSITION DE TRANSFERT
                propositionFrame.dispose();
                transfertProposition();
                new Informations("La proposition de transfert a été envoyé");
            }
        }
        if (e.getSource() == confirmRenewButton){
            if(table.getSelectedRow() >= 0 && yearsContract.getText().length()!=0) {
                //TODO RENOUVELER LE CONTRAT
                //yearsContract.getText()
                renewFrame.dispose();
                renewContract();
                new Informations("La proposition a été envoyée");
            }
        }

        if (e.getSource() == confirmHireButton){
            if(table.getSelectedRow() >= 0) {
                //TODO RENVOYER JOUEUR SELECTIONNE
                hireFrame.dispose();
                hirePlayer();
                new Informations("Le joueur a été renvoyé");
            }
        }
    }

    public JPanel listPlayersClubArea(){
        //TODO RECUP LISTE JOUEURS DU CLUB
        String titre[] = {"Nom", "A", "Taille"};
        JPanel listPlayersArea = new JPanel();
        table = new JTable(data, titre);
        table.setFont(policeGeneral);
        table.setRowHeight(30);
        for(int i=0; i<data[0].length; i++){
            table.getColumnModel().getColumn(i).setPreferredWidth(180);
        }

        listPlayersArea.add(table);
        return listPlayersArea;
    }

    public JPanel listPlayersAvailablerea(){
        //TODO RECUP LISTE JOUEURS DISPONIBLES
        String titre[] = {"Nom", "Ag", "Taille"};
        JPanel listPlayersArea = new JPanel();
        table = new JTable(data, titre);
        table.setFont(policeGeneral);
        table.setRowHeight(30);
        for(int i=0; i<data[0].length; i++){
            table.getColumnModel().getColumn(i).setPreferredWidth(180);
        }

        listPlayersArea.add(table);
        return listPlayersArea;
    }
}
