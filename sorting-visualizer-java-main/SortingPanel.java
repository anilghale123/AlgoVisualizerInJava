import javax.sound.sampled.AudioInputStream;
import javax.swing.*;
import javax.sound.sampled.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.io.File;

import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import java.util.List;


public class SortingPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    private Random random;
    private int[] array;
    private BubbleSort bubbleSort;
    private InsertionSort insertionSort;
    private SelectionSort selectionSort;
    private QuickSort quickSort;

    private MergeSort mergeSort;

    private JButton start;
    private JButton bubble;
    private JButton insertion;
    private JButton selection;
    private JButton quick;
    private JButton reset;

    private JButton merge;


    private boolean isBubble = false;
    private boolean isInsertion = false;
    private boolean isSelection = false;
    private boolean isQuick = false;

    private boolean isMerge = false;

    private boolean isRunning;
    JLabel label1 = new JLabel("Sorting");
    JLabel label2 = new JLabel("Algorithm");
    JLabel label3 = new JLabel("Visualizer");

    private JSlider speedSlider;



    int i = 0;

    private void playButtonClickSound() {
        try {
            File soundFile = new File("C:\\Algorithm Visualizer\\sorting-visualizer-java-main\\out\\production\\sorting-visualizer-java-main\\button-11.wav");
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public SortingPanel() {
        setLayout(null);


        random = new Random();
        array = new int[70];
        this.setArray();

        label1.setBounds(450, 10, 200, 50);
        label1.setFont(new Font("Arial", Font.BOLD, 30));
        label1.setForeground(Color.red);

        label2.setBounds(570, 10, 200, 50);
        label2.setFont(new Font("Arial", Font.BOLD, 30));
        label2.setForeground(Color.blue);

        label3.setBounds(720, 10, 200, 50);
        label3.setFont(new Font("Arial", Font.BOLD, 30));
        label3.setForeground(Color.green);

        bubbleSort = new BubbleSort(array);
        insertionSort = new InsertionSort(array);
        selectionSort = new SelectionSort(array);
        quickSort = new QuickSort(array);
        mergeSort = new MergeSort(array);

        start = new JButton("start");
        bubble = new JButton("bubble");
        insertion = new JButton("insertion");
        selection = new JButton("selection");
        quick = new JButton("quick");
        reset = new JButton("reset");
        merge = new JButton("merge");

        start.setModel(new ButtonModel());
        start.setBackground(Color.GREEN);
        start.setFocusPainted(false);
        start.setBorderPainted(false);
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    start.setBackground(Color.lightGray);
                    if (isRunning == false)
                        isRunning = true;
                    animate();
                    playButtonClickSound();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });


        bubble.setModel(new ButtonModel());
        bubble.setBackground(Color.WHITE);
        bubble.setFocusPainted(false);
        bubble.setBorderPainted(false);
        bubble.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    if (isRunning == false) {
                        isBubble = true;
                        isInsertion = false;
                        isSelection = false;
                        isQuick = false;
                        isMerge = false;

                        setButtonBackground();
                        bubble.setBackground(Color.lightGray);
                        playButtonClickSound();
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });

        insertion.setModel(new ButtonModel());
        insertion.setBackground(Color.WHITE);
        insertion.setFocusPainted(false);
        insertion.setBorderPainted(false);
        insertion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (isRunning == false) {
                        isInsertion = true;
                        isMerge = false;
                        isBubble = false;
                        isSelection = false;
                        isQuick = false;

                        setButtonBackground();
                        insertion.setBackground(Color.lightGray);
                        playButtonClickSound();
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });

        selection.setModel(new ButtonModel());
        selection.setBackground(Color.WHITE);
        selection.setFocusPainted(false);
        selection.setBorderPainted(false);
        selection.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (isRunning == false) {
                        isSelection = true;
                        isMerge = false;
                        isBubble = false;
                        isInsertion = false;
                        isQuick = false;

                        setButtonBackground();
                        selection.setBackground(Color.lightGray);
                        playButtonClickSound();
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });

        quick.setModel(new ButtonModel());
        quick.setBackground(Color.WHITE);
        quick.setFocusPainted(false);
        quick.setBorderPainted(false);
        quick.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (isRunning == false) {
                        isQuick = true;
                        isMerge = false;
                        isBubble = false;
                        isInsertion = false;
                        isSelection = false;

                        setButtonBackground();
                        quick.setBackground(Color.lightGray);
                        playButtonClickSound();
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });


        merge.setModel(new ButtonModel());
        merge.setBackground(Color.WHITE);
        merge.setFocusPainted(false);
        merge.setBorderPainted(false);
        merge.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (isRunning == false) {

                        isMerge = true;
                        isBubble = false;
                        isInsertion = false;
                        isSelection = false;
                        isQuick = false;
                        setButtonBackground();
                        merge.setBackground(Color.lightGray);
                        playButtonClickSound();


                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });


        reset.setModel(new ButtonModel());
        reset.setBackground(Color.red);
        reset.setFocusPainted(false);
        reset.setBorderPainted(false);
        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playButtonClickSound();

                reset.setBackground(Color.red);
                start.setBackground(Color.green);

                setArray();

                // reset bubbleSort object
                bubbleSort.setCompareIndex(Integer.MAX_VALUE);
                bubbleSort.setArrayIndex(0);
                bubbleSort.setArray(array);

                // reset insertionSort object
                insertionSort.setCompareIndex(Integer.MAX_VALUE);
                insertionSort.setArrayIndex(Integer.MAX_VALUE);
                insertionSort.setArray(array);
                insertionSort.setStartOfIteration(false);

                // reset quickSort object
                quickSort.setSP(-1);
                quickSort.push(0);
                quickSort.push(69);
                quickSort.setArrayIndex(Integer.MAX_VALUE);
                quickSort.setCompareIndex(Integer.MAX_VALUE);
                quickSort.setPartition(-1);
                quickSort.setIsPartioning(false);


                mergeSort.leftIndex = 0;
                mergeSort.rightIndex = array.length - 1;
                mergeSort.mergeIndex = Integer.MAX_VALUE;


                isRunning = false;


                Timer timer = new Timer(10, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        reset.setBackground(Color.WHITE);
                        ((Timer) e.getSource()).stop();
                    }
                });

                timer.start();
                repaint();
            }
        });

        start.setBounds(0, 160, 160, 30);
        reset.setBounds(0, 190, 160, 30);
        insertion.setBounds(0, 220, 160, 30);
        selection.setBounds(0, 250, 160, 30);
        bubble.setBounds(0, 280, 160, 30);
        merge.setBounds(0, 310, 160, 30);
        quick.setBounds(0, 340, 160, 30);


// Inside the SortingPanel constructor
        speedSlider = new JSlider(JSlider.HORIZONTAL, 0, 1000, 500); // Adjust the range and initial value as needed
        speedSlider.setBounds(0, 370, 160, 30);
        this.add(speedSlider);

        speedSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                 //delay = speedSlider.getValue();
            }
        });




        this.add(bubble);
        this.add(insertion);
        this.add(selection);
        this.add(quick);

        this.add(merge);
        this.add(start);
        this.add(reset);

        this.add(label1);
        this.add(label2);
        this.add(label3);
    }

    public void setButtonBackground() {

        bubble.setBackground(Color.WHITE);
        insertion.setBackground(Color.WHITE);
        selection.setBackground(Color.WHITE);
        quick.setBackground(Color.WHITE);
        merge.setBackground(Color.WHITE);

    }

    public int[] getArray() {
        return this.array;
    }

    public void setArray() {
        for (int i = 0; i < this.array.length; i++) {
            this.array[i] = random.nextInt(510) + 40;
        }
    }


    public boolean isSorted() {

        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] > array[i + 1]) {
                return false;
            }
        }

        return true;
    }

    public void animate() throws Exception {
        int delay = speedSlider.getValue();


        if (isBubble) {

            bubbleSort.setCompareIndex(0);

            Timer bubbleTimer = new Timer(delay, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    if (isSorted() || isRunning == false) {
                        bubbleSort.setCompareIndex(Integer.MAX_VALUE);
                        isRunning = false;
                        start.setBackground(Color.WHITE);
                        ((Timer) e.getSource()).stop();
                    } else {
                        if (isRunning == true)
                            array = bubbleSort.sortOnlyOneItem();
                    }

                    repaint();
                }
            });

            bubbleTimer.start();
        }

        if (isInsertion) {

            insertionSort.setArrayIndex(1);

            Timer insertionTimer = new Timer(delay, new ActionListener() {
                @Override

                public void actionPerformed(ActionEvent e) {

                    if (isSorted() || isRunning == false) {
                        insertionSort.setCompareIndex(Integer.MAX_VALUE);
                        insertionSort.setArrayIndex(Integer.MAX_VALUE);
                        insertionSort.setStartOfIteration(false);
                        isRunning = false;
                        start.setBackground(Color.WHITE);
                        ((Timer) e.getSource()).stop();
                    } else {
                        if (isRunning == true)
                            array = insertionSort.sortOnlyOneItem();
                    }

                    repaint();
                }
            });

            insertionTimer.start();
        }

        if (isSelection) {

            selectionSort.setArrayIndex(0);
            selectionSort.setCompareIndex(1);
            selectionSort.setMinIdx(0);

            Timer selectionTimer = new Timer(delay, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    if (isSorted() || isRunning == false) {
                        selectionSort.setCompareIndex(Integer.MAX_VALUE);
                        selectionSort.setArrayIndex(Integer.MAX_VALUE);
                        selectionSort.setMinIdx(Integer.MAX_VALUE);
                        isRunning = false;
                        start.setBackground(Color.WHITE);
                        ((Timer) e.getSource()).stop();
                    } else {
                        if (isRunning == true)
                            array = selectionSort.sortOnlyOneItem();

                    }

                    repaint();
                }
            });

            selectionTimer.start();
        }

        if (isQuick) {

            Timer quickTimer = new Timer(delay, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    if (isSorted() || isRunning == false) {
                        quickSort.setSP(-1);
                        quickSort.push(0);
                        quickSort.push(69);
                        quickSort.setArrayIndex(Integer.MAX_VALUE);
                        quickSort.setCompareIndex(Integer.MAX_VALUE);
                        quickSort.setPartition(-1);
                        quickSort.setIsPartioning(false);
                        isRunning = false;
                        start.setBackground(Color.green);
                        ((Timer) e.getSource()).stop();
                    } else {
                        if (isRunning == true)
                            array = quickSort.sortOnlyOneItem();
                    }

                    repaint();
                }
            });

            quickTimer.start();
        }


        // Inside the SortingPanel class
        if (isMerge) {
            MergeSort mergeSort = new MergeSort(array); // Create a MergeSort instance
            mergeSort.mergeSortStep(0, array.length - 1); // Perform the sorting

            List<int[]> sortingSteps = mergeSort.getSteps(); // Get the list of steps

            // Keep track of the current step index

            Timer mergeTimer = new Timer(delay, new ActionListener() {
                private int stepIndex = 0;

                @Override
                public void actionPerformed(ActionEvent e) {
                    if (stepIndex < sortingSteps.size() && isRunning) {
                        array = sortingSteps.get(stepIndex); // Get the array for the current step
                        stepIndex++;

                        repaint(); // Repaint with the current step's array
                    } else {
                        isRunning = false;
                        start.setBackground(Color.WHITE);
                        ((Timer) e.getSource()).stop();
                    }
                }
            });

            mergeTimer.start();
        }


    }


    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        this.setBackground(Color.BLACK);

        for (int i = 0; i < array.length; i++) {

            g.setColor(Color.BLACK);


            if (isBubble) {

                if (i == bubbleSort.getCompareIndex() || i == (bubbleSort.getCompareIndex() + 1)) {
                    g.setColor(Color.MAGENTA);
                }

            }


            if (isMerge) {
                if (i == mergeSort.rightIndex || i == mergeSort.leftIndex) {
                    g.setColor(Color.red);
                }
                if (i == mergeSort.mergeIndex) {
                    g.setColor(Color.blue);
                }
            }

            if (isInsertion) {

                if (i == insertionSort.getCompareIndex() || i == (insertionSort.getCompareIndex() + 1)) {
                    g.setColor(Color.MAGENTA);
                }

                if (i == insertionSort.getArrayIndex()) {
                    g.setColor(Color.GREEN);
                }
            }

            if (isSelection) {

                if (i == selectionSort.getCompareIndex() || i == selectionSort.getMinIdx()) {
                    g.setColor(Color.MAGENTA);
                }

                if (i == selectionSort.getArrayIndex()) {
                    g.setColor(Color.GREEN);
                }
            }


            if (isQuick) {

                if (i == quickSort.getArrayIndex()) {
                    g.setColor(Color.MAGENTA);
                }

                if (i == quickSort.getCompareIndex()) {
                    g.setColor(Color.BLUE);
                }

                if (i == quickSort.getPartition())
                    g.setColor(Color.GREEN);
            }

            if (g.getColor() != Color.MAGENTA && g.getColor() != Color.GREEN && g.getColor() != Color.BLUE && g.getColor() != Color.RED)
                g.setColor(Color.yellow);
            int xCoordinate = 200 + i * 14;

            g.drawRect(xCoordinate, 600 - array[i], 9, array[i]);
            g.fillRect(xCoordinate, 600 - array[i], 9, array[i]);

        }
    }


}