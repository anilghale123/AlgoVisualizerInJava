import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

class Home extends JPanel implements ActionListener {

    private JButton start = new JButton("Get Started");
    private JPanel cardPanel;

    JLabel label = new JLabel("Hello Algorithms");
    JLabel label1 = new JLabel("\"An algorithm must be seen to be believed.\"");
    JLabel label2 = new JLabel("<html>This application helps you to understand sorting algorithms <br>better by visualizing them.</html>");

    public Home(JPanel cardPanel) {
        this.cardPanel = cardPanel; // Store the reference to the cardPanel
        setLayout(null);
        setBackground(Color.darkGray);

        label.setForeground(Color.white);
        label1.setForeground(Color.white);
        label2.setForeground(Color.white);

        label.setFont(new Font("Monospaced", Font.PLAIN, 80));
        label1.setFont(new Font("Monospaced", Font.PLAIN, 22));
        label2.setFont(new Font("Monospaced", Font.BOLD, 20));

        // Set custom position for the label (x, y, width, height)
        label.setBounds(200, 30, 1300, 100);
        label1.setBounds(270, 150, 800, 50);
        label2.setBounds(200, 350, 1300, 100);

        start.setBounds(500, 500, 100, 30);
        start.addActionListener(this);
        start.setBackground(Color.green);
        start.setFocusable(false);

        add(start);
        add(label);
        add(label1);
        add(label2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == start) {
            playButtonClickSound(); // Play sound when the "Get Started" button is clicked
            SortingPanel sortingPanel = new SortingPanel();
            cardPanel.add(sortingPanel, "sortingPanel");
            CardLayout cardLayout = (CardLayout) cardPanel.getLayout();
            cardLayout.show(cardPanel, "sortingPanel");
        }
    }

    private void playButtonClickSound() {
        try {
            File soundFile = new File("C:\\Algorithm Visualizer\\sorting-visualizer-java-main\\out\\production\\sorting-visualizer-java-main\\button-11.wav");
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (LineUnavailableException | UnsupportedAudioFileException | IOException ex) {
            ex.printStackTrace();
        }
    }
}
