package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Login extends JFrame implements ActionListener {

    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private int width = screenSize.width;
    private int height = screenSize.height;
    Font policeGeneral = new Font(" Arial ",Font.BOLD,18);
    Font policeButton = new Font(" Arial ",Font.BOLD,24);
    Font policeTitle = new Font(" Arial ",Font.BOLD,40);

    JTextField textField;
    JPasswordField passwordField;
    JRadioButton playerRadio = new JRadioButton("Player");
    JRadioButton agentRadio = new JRadioButton("Agent");
    JRadioButton clubRadio = new JRadioButton("Club");
    JButton loginButton;

    public static void main(String[] args){
        new Login();
    }
    public Login(){
        this.setTitle("Bienvenue sur Blockchain FC");
        this.setSize(width/2, height/3);
        this.setLocationRelativeTo(null); // centrer la fenêtre sur l'écran
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // fin du programme lors de la fermeture de la fenêtre
        this.setResizable(false);

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

        JPanel loginArea = new JPanel();
        loginButton = new JButton("LOGIN");
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
        this.getContentPane().add(all);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==loginButton && !textField.getText().isEmpty()) {
           String username = textField.getText();
           String password = String.valueOf(passwordField.getPassword());
           this.dispose();
           if(playerRadio.isSelected()) new HomePlayer(username,password);
           if(agentRadio.isSelected()) new HomeAgent(username,password);
           if(clubRadio.isSelected()) new HomeClub(username,password);
        }
    }
}
