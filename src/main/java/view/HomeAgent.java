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

    Object[][] data = {
            {"Cysboy", "28 ans", "1.80 m"},
            {"BZHHydde", "28 ans", "1.80 m"},
            {"IamBow", "24 ans", "1.90 m"},
            {"FunMan", "32 ans", "1.85 m"}
    };

    JFrame propositionFrame;
    JFrame answerFrame;

    JButton playersAgentButton;
    JButton contractsAgentButton;
    JButton playersAvailableAgentButton;

    JTable table;
    JTextField percentageContract;
    JTextField numberYearsContract;
    JButton confirmButton;
    JButton acceptPropositionButton;
    JButton declinePropositionButton;

    public HomeAgent(){
    }

    public static void main(String[] args){
        new HomeAgent("Bernes");
    }

    public HomeAgent(String username){
        this.username = username;
        this.setTitle("Votre espace perso - " + username);
        this.setSize(width/2, height/3);
        this.setLocationRelativeTo(null); // centrer la fenêtre sur l'écran
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // fin du programme lors de la fermeture de la fenêtre
        this.setResizable(false);

        JPanel titleArea = new JPanel();
        JLabel title = new JLabel(username.toUpperCase() + " - " + "AGENT");
        title.setFont(policeTitle);
        titleArea.add(title);

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
        this.getContentPane().add(all);
        this.setVisible(true);

    }

    public void playersLinkedToAgentFrame(){
        JFrame frame = new JFrame();
        frame.setTitle("Votre liste de joueurs");
        frame.setSize(width/2, height/3);
        frame.setLocationRelativeTo(null); // centrer la fenêtre sur l'écran
        frame.setResizable(false);
        //TODO RECUP LISTE JOUEURS
        String titre[] = {"Nom", "Age", "Taille"};

        JPanel titleArea = new JPanel();
        JLabel title = new JLabel("Voici votre liste de joueurs" );
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
        propositionFrame = new JFrame();
        propositionFrame.setTitle("Joueurs disponibles");
        propositionFrame.setSize(width/2, height/3);
        propositionFrame.setLocationRelativeTo(null); // centrer la fenêtre sur l'écran
        propositionFrame.setResizable(false);

        JPanel titleArea = new JPanel();
        JLabel title = new JLabel("Voici la liste des joueurs disponibles");
        title.setFont(policeButton);
        titleArea.add(title);

        //TODO RECUP LISTE JOUEURS
        String titre[] = {"Pseudo", "Age", "Taille"};

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
        propositionFrame.getContentPane().add(all);
        propositionFrame.setVisible(true);

    }

    public void answerToProposition(){
        answerFrame = new JFrame();
        answerFrame.setTitle("Propositions de contrat en attente");
        answerFrame.setSize(width/2, height/3);
        answerFrame.setLocationRelativeTo(null); // centrer la fenêtre sur l'écran
        answerFrame.setResizable(false);

        JPanel titleArea = new JPanel();
        JLabel title = new JLabel("Voici les propositions de contrat en attente");
        title.setFont(policeButton);
        titleArea.add(title);

        //TODO RECUP LISTE PROPOSITIONS DE CONTRAT
        String titre[] = {"Pseudo", "Age", "Taille"};

        JPanel listPropositionsArea = new JPanel();
        table = new JTable(data, titre);
        table.setFont(policeGeneral);
        table.setRowHeight(30);
        for(int i=0; i<data[0].length; i++){
            table.getColumnModel().getColumn(i).setPreferredWidth(180);
        }
        listPropositionsArea.add(table);

        JPanel choiceArea = new JPanel();
        choiceArea.setLayout(new BoxLayout(choiceArea, BoxLayout.LINE_AXIS));
        acceptPropositionButton = new JButton("Accepter l'offre");
        acceptPropositionButton.setFont(policeButton);
        acceptPropositionButton.setBackground(Color.WHITE);
        acceptPropositionButton.addActionListener(this);
        choiceArea.add(acceptPropositionButton);
        declinePropositionButton = new JButton("Refuser l'offre");
        declinePropositionButton.setFont(policeButton);
        declinePropositionButton.setBackground(Color.WHITE);
        declinePropositionButton.addActionListener(this);
        choiceArea.add(declinePropositionButton);

        JPanel all = new JPanel();
        all.setLayout(new BoxLayout(all, BoxLayout.PAGE_AXIS));
        all.add(titleArea);
        all.add(listPropositionsArea);
        all.add(choiceArea);
        answerFrame.getContentPane().add(all);
        answerFrame.setVisible(true);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == playersAgentButton){
            playersLinkedToAgentFrame();
        }
        if (e.getSource() == playersAvailableAgentButton){
            proposeContractToPlayerFrame();
        }
        if (e.getSource() == contractsAgentButton){
            answerToProposition();
        }
        if (e.getSource() == confirmButton){
            //TODO Envoyer proposition de l'agent
            if(table.getSelectedRow() >= 0 && percentageContract.getText().length()!=0 && numberYearsContract.getText().length()!=0) {
                System.out.println(table.getSelectedRow() + " - " + percentageContract.getText() + "    " + numberYearsContract.getText());
                new Informations("La proposition de contrat a bien été envoyée");
                propositionFrame.dispose();
            }
        }
        if (e.getSource() == acceptPropositionButton){
            //TODO Accepter offre
            if(table.getSelectedRow() >= 0) {
                System.out.println(table.getSelectedRow());
                answerFrame.dispose();
                answerToProposition();
                new Informations("L'offre a bien été acceptée");
            }
        }
        if (e.getSource() == declinePropositionButton){
            //TODO Refuser offre
                if(table.getSelectedRow() >= 0) {
                System.out.println(table.getSelectedRow());
                answerFrame.dispose();
                answerToProposition();
                new Informations("L'offre a bien été refusée");
            }
        }
    }
}
