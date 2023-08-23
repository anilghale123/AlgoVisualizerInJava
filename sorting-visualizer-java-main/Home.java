
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
class Home extends JPanel implements ActionListener {

    private JButton start = new JButton("Get Started");
    private JPanel cardPanel;

    public Home(JPanel cardPanel) {
        this.cardPanel = cardPanel; // Store the reference to the cardPanel
        setLayout(null);

        start.setBounds(500, 500, 100, 30);
        start.addActionListener(this);

        add(start);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == start) {
            SortingPanel sortingPanel = new SortingPanel();
            cardPanel.add(sortingPanel, "sortingPanel");
            CardLayout cardLayout = (CardLayout) cardPanel.getLayout();
            cardLayout.show(cardPanel, "sortingPanel");
        }
    }
}