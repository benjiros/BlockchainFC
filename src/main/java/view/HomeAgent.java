package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeAgent extends JFrame implements ActionListener {

    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private int width = screenSize.width;
    private int height = screenSize.height;
    Font policeGeneral = new Font(" Arial ",Font.BOLD,18);
    Font policeButton = new Font(" Arial ",Font.BOLD,22);
    Font policeTitle = new Font(" Arial ",Font.BOLD,40);
    String username;
    String profil;

    Object[][] data = {
            {"Cysboy", "28 ans", "1.80 m"},
            {"BZHHydde", "28 ans", "1.80 m"},
            {"IamBow", "24 ans", "1.90 m"},
            {"FunMan", "32 ans", "1.85 m"}
    };

    JButton playersAgentButton;
    JButton contractsAgentButton;
    JButton playersAvailableAgentButton;

    JTable table;
    JTextField percentageContract;
    JTextField numberYearsContract;
    JButton confirmButton;

    public HomeAgent(){
    }

    public static void main(String[] args){
        new HomeAgent("Bernes", "poppff");
    }

    public HomeAgent(String username, String password){
        this.username = username;
        this.setTitle("Votre espace perso - " + username);
        this.setSize(width/2, height/3);
        this.setLocationRelativeTo(null); // centrer la fenêtre sur l'écran
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // fin du programme lors de la fermeture de la fenêtre
        this.setResizable(false);

        JPanel titleArea = new JPanel();
        JLabel title = new JLabel(username.toUpperCase() + " - " + profil.toUpperCase());
        title.setFont(policeTitle);
        titleArea.add(title);

        if(profil.equalsIgnoreCase("agent")){

            //JButton playersAgentButton;
            //contractsAgentButton;
            //playersAvailableAgentButton;

            JPanel playersAgentArea = new JPanel();
            playersAgentButton = new JButton("Voir votre liste de joueurs");
            playersAgentButton.setFont(policeButton);
            playersAgentButton.setBackground(Color.WHITE);
            playersAgentButton.addActionListener(this);
            playersAgentArea.add(playersAgentButton);

            JPanel playersAvailableAgentArea = new JPanel();
            playersAvailableAgentButton = new JButton("Proposer d'être agent d'un joueur");
            playersAvailableAgentButton.setFont(policeButton);
            playersAvailableAgentButton.setBackground(Color.WHITE);
            playersAvailableAgentButton.addActionListener(this);
            playersAvailableAgentArea.add(playersAvailableAgentButton);

            JPanel contractsAgentArea = new JPanel();
            contractsAgentButton = new JButton("Voir les propositions de contrat");
            contractsAgentButton.setFont(policeButton);
            contractsAgentButton.setBackground(Color.WHITE);
            contractsAgentButton.addActionListener(this);
            contractsAgentArea.add(contractsAgentButton);

            JPanel all = new JPanel();
            all.setLayout(new BoxLayout(all, BoxLayout.PAGE_AXIS));
            all.add(titleArea);
            all.add(playersAgentArea);
            all.add(playersAvailableAgentArea);
            all.add(contractsAgentArea);
            // all.add(loginArea);
            this.getContentPane().add(all);
            this.setVisible(true);
        }
    }

    public void playersLinkedToAgentFrame(){
        JFrame frame = new JFrame();
        frame.setTitle("Votre liste de joueurs");
        frame.setSize(width/2, height/3);
        frame.setLocationRelativeTo(null); // centrer la fenêtre sur l'écran
        frame.setResizable(false);
        //TODO RECUP LISTE JOUEURS
        String titre[] = {"Pseudo", "Age", "Taille"};
        Object[][] data = {
                {"Cysboy", "28 ans", "1.80 m"},
                {"BZHHydde", "28 ans", "1.80 m"},
                {"IamBow", "24 ans", "1.90 m"},
                {"FunMan", "32 ans", "1.85 m"}
        };

        JPanel titleArea = new JPanel();
        JLabel title = new JLabel("Voici votre liste de joueurs Monsieur " + username.toUpperCase());
        title.setFont(policeButton);
        titleArea.add(title);

        JPanel listPlayersArea = new JPanel();
        JTable table = new JTable(data, titre);
        table.setFont(policeGeneral);
        table.setRowHeight(30);
        for(int i=0; i<data[0].length; i++){
            table.getColumnModel().getColumn(i).setPreferredWidth(180);
        }
        table.setEnabled(false);
        listPlayersArea.add(table);
        JPanel all = new JPanel();
        all.setLayout(new BoxLayout(all, BoxLayout.PAGE_AXIS));
        all.add(titleArea);
        all.add(listPlayersArea);
        frame.getContentPane().add(all);
        frame.setVisible(true);
    }

    public void proposeContractToPlayerFrame(){
        JFrame frame = new JFrame();
        frame.setTitle("Joueurs disponibles");
        frame.setSize(width/2, height/3);
        frame.setLocationRelativeTo(null); // centrer la fenêtre sur l'écran
        frame.setResizable(false);

        JPanel titleArea = new JPanel();
        JLabel title = new JLabel("Voici la liste des joueurs disponibles");
        title.setFont(policeButton);
        titleArea.add(title);

        //TODO RECUP LISTE JOUEURS
        String titre[] = {"Pseudo", "Age", "Taille"};
        Object[][] data = {
                {"Cysboy", "28 ans", "1.80 m"},
                {"BZHHydde", "28 ans", "1.80 m"},
                {"IamBow", "24 ans", "1.90 m"},
                {"FunMan", "32 ans", "1.85 m"}
        };

        JPanel listPlayersAvailableArea = new JPanel();
        table = new JTable(data, titre);
        table.setFont(policeGeneral);
        table.setRowHeight(30);
        for(int i=0; i<data[0].length; i++){
            table.getColumnModel().getColumn(i).setPreferredWidth(180);
        }
        //table.setEnabled(false);
        listPlayersAvailableArea.add(table);

        JPanel numberYearsArea = new JPanel();
        // usernameArea.setLayout(new BoxLayout(usernameArea,BoxLayout.LINE_AXIS));
        JLabel indication1 = new JLabel("Durée du contrat (en années) : ");
        indication1.setFont(policeGeneral);
        numberYearsContract = new JTextField(20);
        numberYearsArea.add(indication1);
        numberYearsArea.add(numberYearsContract);

        JPanel percentageArea = new JPanel();
        JLabel indication2 = new JLabel("Votre pourcentage de gain : ");
        indication2.setFont(policeGeneral);
        percentageContract = new JTextField(20);
        percentageArea.add(indication2);
        percentageArea.add(percentageContract);

        JPanel confirmArea = new JPanel();
        confirmButton = new JButton("Envoyer la proposition");
        confirmButton.setFont(policeButton);
        confirmButton.setBackground(Color.WHITE);
        confirmButton.addActionListener(this);
        confirmArea.add(confirmButton);

        JPanel all = new JPanel();
        all.setLayout(new BoxLayout(all, BoxLayout.PAGE_AXIS));
        all.add(titleArea);
        all.add(listPlayersAvailableArea);
        all.add(numberYearsArea);
        all.add(percentageArea);
        all.add(confirmButton);
        frame.getContentPane().add(all);
        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==playersAgentButton){
            playersLinkedToAgentFrame();
        }
        if (e.getSource()==playersAvailableAgentButton){
            proposeContractToPlayerFrame();
        }
        if (e.getSource()==contractsAgentButton){

        }

        if (e.getSource()==confirmButton){
            //TODO Envoyer proposition de l'agent
            System.out.println(table.getSelectedRow() + " - " + percentageContract.getText() + "    " + numberYearsContract.getText());
        }

    }
}
