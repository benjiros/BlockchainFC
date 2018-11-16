package view;

import back.Variables;
import model.Club;
import model.Player;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import javax.swing.*;

public class Login extends JFrame implements ActionListener {
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private int width = screenSize.width;
    private int height = screenSize.height;

    String mercato_address = "";

    Font policeGeneral = new Font(" Arial ",Font.BOLD,18);
    Font policeButton = new Font(" Arial ",Font.BOLD,24);
    Font policeTitle = new Font(" Arial ",Font.BOLD,40);

    JFrame createAccountFrame;
    JTextField textField;
    JPasswordField passwordField;

    JRadioButton playerRadio = new JRadioButton("Joueur");
    JRadioButton agentRadio = new JRadioButton("Agent");
    JRadioButton clubRadio = new JRadioButton("Club");

    JRadioButton playerRadio2 = new JRadioButton("Joueur");
    JRadioButton agentRadio2 = new JRadioButton("Agent");
    JRadioButton clubRadio2 = new JRadioButton("Club");

    JButton createAccountButton;
    JButton loginButton;
    JButton confirmButton;

    HashMap<String, Club> listClubs;
    HashMap<String, Player> listPlayers;

    public static void main(String[] args) {

        Variables.initiate();
        new Login();
    }

    public Login(){
        this.setTitle("Bienvenue sur Blockchain FC");
        this.setSize(width/2, height/3);
        this.setLocationRelativeTo(null); // centrer la fenêtre sur l'écran
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // fin du programme lors de la fermeture de la fenêtre
        this.setResizable(true);


        JPanel titleArea = new JPanel();
        JLabel title = new JLabel("Bienvenue sur Blockchain FC");
        title.setFont(policeTitle);
        titleArea.add(title);


        JPanel usernameArea = new JPanel();
        JLabel indication = new JLabel("Votre nom d'utilisateur : ");
        indication.setFont(policeGeneral);
        textField = new JTextField(20);
        usernameArea.add(indication);
        usernameArea.add(textField);

        JPanel passwordArea = new JPanel();
        indication = new JLabel("Votre mot de passe : ");
        indication.setFont(policeGeneral);
        passwordField= new JPasswordField(20);
        passwordArea.add(indication);
        passwordArea.add(passwordField);

        JPanel profilArea = new JPanel();
        playerRadio.setFont(policeGeneral);
        agentRadio.setFont(policeGeneral);
        clubRadio.setFont(policeGeneral);
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(playerRadio);
        buttonGroup.add(agentRadio);
        buttonGroup.add(clubRadio);
        profilArea.add(playerRadio);
        profilArea.add(agentRadio);
        profilArea.add(clubRadio);

        JPanel createAccountArea = new JPanel();
        createAccountButton = new JButton("Créer un compte");
        createAccountButton.setFont(policeGeneral);
        createAccountButton.setBackground(Color.WHITE);
        createAccountButton.addActionListener(this);
        createAccountArea.add(createAccountButton);

        JPanel loginArea = new JPanel();
        loginButton = new JButton("Connexion");
        loginButton.setFont(policeButton);
        loginButton.setBackground(Color.WHITE);
        loginButton.addActionListener(this);
        loginArea.add(loginButton);

        JPanel all = new JPanel();
        all.setLayout(new BoxLayout(all, BoxLayout.PAGE_AXIS));
        all.add(titleArea);
        all.add(usernameArea);
        all.add(passwordArea);
        all.add(profilArea);
        all.add(loginArea);
        all.add(createAccountArea);
        this.getContentPane().add(new JScrollPane(all));
        this.setVisible(true);
    }

    public void createAccountFrame(){
        createAccountFrame = new JFrame();
        createAccountFrame.setTitle("Créer un compte");
        createAccountFrame.setSize(width/2, height/3);
        createAccountFrame.setLocationRelativeTo(null); // centrer la fenêtre sur l'écran
        createAccountFrame.setResizable(true);

        JPanel titleArea = new JPanel();
        JLabel title = new JLabel("Créer un compte");
        title.setFont(policeButton);
        titleArea.add(title);

        JPanel usernameArea = new JPanel();
        JLabel indication = new JLabel("Votre nom d'utilisateur : ");
        indication.setFont(policeGeneral);
        textField = new JTextField(20);
        usernameArea.add(indication);
        usernameArea.add(textField);

        JPanel passwordArea = new JPanel();
        indication = new JLabel("Votre mot de passe : ");
        indication.setFont(policeGeneral);
        passwordField= new JPasswordField(20);
        passwordArea.add(indication);
        passwordArea.add(passwordField);

        JPanel profilArea = new JPanel();
        playerRadio2.setFont(policeGeneral);
        agentRadio2.setFont(policeGeneral);
        clubRadio2.setFont(policeGeneral);
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(playerRadio2);
        buttonGroup.add(agentRadio2);
        buttonGroup.add(clubRadio2);
        profilArea.add(playerRadio2);
        profilArea.add(agentRadio2);
        profilArea.add(clubRadio2);

        JPanel createArea = new JPanel();
        confirmButton = new JButton("Valider votre compte");
        confirmButton.setFont(policeButton);
        confirmButton.setBackground(Color.WHITE);
        confirmButton.addActionListener(this);
        createArea.add(confirmButton);

        JPanel all = new JPanel();
        all.setLayout(new BoxLayout(all, BoxLayout.PAGE_AXIS));
        all.add(titleArea);
        all.add(usernameArea);
        all.add(passwordArea);
        all.add(profilArea);
        all.add(createArea);
        createAccountFrame.getContentPane().add(new JScrollPane(all));
        createAccountFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==loginButton && !textField.getText().isEmpty() && (playerRadio.isSelected() || agentRadio.isSelected() || clubRadio.isSelected())) {
            String username = textField.getText();
            System.out.println(username);
            String password = String.valueOf(passwordField.getPassword());
            this.dispose();
            if(playerRadio.isSelected()) new HomePlayer(username, password);
            if(agentRadio.isSelected()) new HomeAgent(username, password);
            if(clubRadio.isSelected()) new HomeClub(username, password);
        }

        if (e.getSource()==createAccountButton) {
            createAccountFrame();
        }

        if (e.getSource()==confirmButton) {
            //TODO CREATION COMPTE
            if((playerRadio2.isSelected() || agentRadio2.isSelected() || clubRadio2.isSelected()) && !textField.getText().isEmpty()){
                createAccountFrame.dispose();
                new Informations("Votre compte a bien été créé");
            }
        }
    }
}
