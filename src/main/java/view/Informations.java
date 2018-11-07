package view;

import javax.swing.*;
import java.awt.*;

public class Informations extends JFrame {

    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private int width = screenSize.width;
    private int height = screenSize.height;
    Font police = new Font(" Arial ",Font.BOLD,22);

    public Informations(String message) {
        JFrame frame = new JFrame();
        frame.setSize(width/3, height/5);
        frame.setLocationRelativeTo(null); // centrer la fenêtre sur l'écran
        frame.setResizable(false);
        frame.setTitle("Confirmation");

        JPanel titleArea = new JPanel();
        JLabel title = new JLabel(message);
        title.setForeground(new Color(0,139,0));
        title.setFont(police);
        titleArea.add(title);

        JPanel all = new JPanel();
        all.setLayout(new BoxLayout(all, BoxLayout.PAGE_AXIS));
        all.add(titleArea);

        frame.getContentPane().add(all);
        frame.setVisible(true);
    }
}
