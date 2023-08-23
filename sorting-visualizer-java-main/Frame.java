
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Frame extends JFrame {

    private JPanel cardPanel = new JPanel();
    private Home home = new Home(cardPanel);

    public Frame() {
        setTitle("Sorting Visualizer");
        setSize(1200, 600);

        cardPanel.setLayout(new CardLayout());
        cardPanel.add(home, "home");

        setContentPane(cardPanel);

        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Frame();
        });
    }
}