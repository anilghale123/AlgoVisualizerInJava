import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Home extends JPanel implements ActionListener {

    private JButton start = new JButton("Get Started");
    private JPanel cardPanel;
    private List<Star> stars = new ArrayList<>();
    private Timer animationTimer;

    JLabel label = new JLabel("Hello Algorithms");
    JLabel label1 = new JLabel("\"An algorithm must be seen to be believed.\"");
    JLabel label2 = new JLabel("<html>This application helps you to understand sorting algorithms <br>better by visualizing them.</html>");

    public Home(JPanel cardPanel) {
        this.cardPanel = cardPanel; // Store the reference to the cardPanel
        setLayout(null);
        setBackground(Color.black);

        label.setForeground(Color.white);
        label1.setForeground(Color.white);
        label2.setForeground(Color.white);

        label.setFont(new Font("Monospaced", Font.PLAIN, 80));
        label1.setFont(new Font("Monospaced", Font.BOLD, 22));
        label2.setFont(new Font("Monospaced", Font.BOLD, 20));

        // Set custom position for the label (x, y, width, height)
        label.setBounds(200, 30, 1300, 100);
        label1.setBounds(270, 150, 800, 50);
        label2.setBounds(200, 350, 1300, 100);

        start.setBounds(500, 500, 100, 30);
        start.addActionListener(this);
        start.setBackground(Color.green);
        start.setFocusable(false);
        

        // Create a timer for the stardust animation
        animationTimer = new Timer(50, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveStars();
                repaint();
            }
        });

        // Initialize stardust
        //initializeStars();

        add(start);
        add(label);
        add(label1);
        add(label2);

        // Start the stardust animation
        animationTimer.start();
    }

     public void startStarAnimation()
     {
         initializeStars();
     }
    private void initializeStars() {
        // Create random stars and add them to the list
        Random random = new Random();
        int numStars = 100; // Adjust the number of stars as needed
        int panelWidth = getWidth();
        int panelHeight = getHeight();

        // Check if panelWidth and panelHeight are greater than zero
        if (panelWidth <= 0 || panelHeight <= 0) {
            // Handle the case where the panel dimensions are not positive
            return;
        }

        for (int i = 0; i < numStars; i++) {
            int x = random.nextInt(panelWidth);
            int y = random.nextInt(panelHeight);
            Color color = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
            stars.add(new Star(x, y, color, panelWidth, panelHeight));
        }
    }



    private void moveStars() {
        // Move the stars to create animation
        Random random = new Random();

        for (Star star : stars) {
            int dx = random.nextInt(5) - 2; // Random horizontal movement
            int dy = random.nextInt(5) - 2; // Random vertical movement
            star.move(dx, dy);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw stardust (stars)
        for (Star star : stars) {
            g.setColor(star.getColor());
            g.fillRect(star.getX(), star.getY(), 2, 2); // Adjust the size of stars as needed
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == start) {
            playButtonClickSound(); // Play sound when the "Get Started" button is clicked

            // Initialize the SortingPanel and add it to the cardPanel
            SortingPanel sortingPanel = new SortingPanel();
            cardPanel.add(sortingPanel, "sortingPanel");
            CardLayout cardLayout = (CardLayout) cardPanel.getLayout();
            cardLayout.show(cardPanel, "sortingPanel");

            // Initialize stardust after adding SortingPanel

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