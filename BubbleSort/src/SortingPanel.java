import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class SortingPanel extends JPanel implements ActionListener {
    private static final long serialVersionUID = 1L;
    private Random random = new Random();
    private int[] array = new int[80];
    private int array_index;
    private int compare_index;

    JButton start = new JButton("Start");
    JButton reset = new JButton("Reset");

    private boolean isRunning;

    public SortingPanel() {
        this.array_index = 0;
        this.compare_index = Integer.MAX_VALUE;

        this.setArray();

        start.setBackground(Color.WHITE);
        start.setFocusPainted(false);
        start.setBorderPainted(false);

        reset.setBackground(Color.WHITE);
        reset.setFocusPainted(false);
        reset.setBorderPainted(false);

        this.add(start);
        this.add(reset);

        start.addActionListener(this);
        reset.addActionListener(this);

    }

    public int[] getArray() {
        return this.array;
    }

    public void setArray() {
        for (int i = 0; i < this.array.length; i++) {
            this.array[i] = random.nextInt(510) + 40;
        }
    }

    public void sortOnlyOneItem() {
        if (array[compare_index] > array[compare_index + 1]) {

            int temp = array[compare_index];
            array[compare_index] = array[compare_index + 1];
            array[compare_index + 1] = temp;
        }
        if ((compare_index + 1) >= (array.length - array_index - 1)) {
            array_index++;
            compare_index = 0;

        } else compare_index++;
    }

    public Boolean isSorted() {
        for (int i = 0; i < (array.length) - 1; i++) {
            if (array[i] > array[i + 1]) {
                return false;
            }

        }
        return true;
    }

    public void BubbleSortAnimate() throws Exception
    {
        compare_index = 0;
        Timer timer = new Timer(1, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              if (isSorted())
              {
                  compare_index = Integer.MAX_VALUE;
                  ((Timer)e.getSource()).stop();
              }
              else {
                  if (isRunning == true)
                  {
                      sortOnlyOneItem();
                  }
                  repaint();
              }
            }

        });
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setBackground(Color.black);

        for (int i = 0; i< array.length - 1; i++)
        {
            g.setColor(Color.cyan);
            if (i==compare_index || i == compare_index + 1)
            {
                g.setColor(Color.red);
            }
            g.drawRect(i*15, 600 - array[i],14, array[i]);
            g.fillRect(i*15, 600 - array[i],14, array[i]);

        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == start)
        {
                try {
                    isRunning = true;
                    BubbleSortAnimate();

                }
                catch (Exception exception)
                {
                    exception.printStackTrace();
                }
    }
        if (e.getSource() == reset)
        {
                setArray();
                compare_index = Integer.MAX_VALUE;
                array_index = 0;
                isRunning = false;
                repaint();
        }
}
}
