import javax.sound.sampled.AudioInputStream;
import javax.swing.*;
import javax.sound.sampled.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.io.File;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.List;


public class SortingPanel extends JPanel {

    long time = 0;
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
    private JSlider sizeSlider;

    public int tempSize;
    int i = 0;
    int newSize = 10;

    Clip currentClip = null;

    private boolean bubblePressed = false;
    private boolean selectionPressed = false;
    private boolean insertionPressed = false;
    private boolean mergePressed = false;
    private boolean quickPressed = false;
    private boolean startPressed = false;

    private JLabel speed;
    private JLabel size;

    private int delay;

    private JLabel sortLabel;

    private int stepCount = 0;

    private long startTime = 0;
    private long elapsedTime = 0;



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

    public void playMusic(String filename) {
        try {
            if (currentClip != null && currentClip.isActive()) {
                currentClip.stop(); // stops the currently playing music
            }

            File soundFile = new File(filename);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
            currentClip = AudioSystem.getClip(); // updates current clip
            currentClip.open(audioInputStream);
            currentClip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void setLabel(String label) {
        // Check if there is an existing sortLabel, and remove it if it exists
        if (sortLabel != null) {
            this.remove(sortLabel);
        }

        sortLabel = new JLabel(label);
        sortLabel.setBounds(530, 40, 300, 50);
        sortLabel.setFont(new Font("Arial", Font.BOLD, 20));
        sortLabel.setForeground(Color.white);

        this.add(sortLabel);

        // Ensure that the changes are immediately reflected
        revalidate();
        repaint();
    }



    public SortingPanel() {
        setLayout(null);
        random = new Random();
        array = new int[newSize];

        // Create the sizeSlider
        sizeSlider = new JSlider(JSlider.HORIZONTAL, 1, 70, array.length);
        sizeSlider.setBounds(50, 40, 160, 30);
        sizeSlider.setMajorTickSpacing(10);
        sizeSlider.setPaintTicks(true);
        sizeSlider.setBackground(Color.black);

        size = new JLabel("array size");
        size.setBounds(65,5,160,30);
        size.setForeground(Color.white);
        size.setFont(new Font("Arial", Font.BOLD,20));

        // Add a ChangeListener to update the array size
        sizeSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                 newSize = sizeSlider.getValue();
                setArraySize(newSize);
                repaint();
            }
        });



        speedSlider = new JSlider(JSlider.HORIZONTAL, 500, 2000, 500); // Adjust the range and initial value as needed
// Set the bounds for the slider
        speedSlider.setBounds(255, 35, 160, 30);
        speedSlider.setBackground(Color.black);


        speed = new JLabel("speed");
        speed.setBounds(265,9,160,30);
        speed.setForeground(Color.white);
        speed.setFont(new Font("Arial", Font.BOLD,20));

        // speedSlider.setBorder(BorderFactory.createLineBorder(Color.red,1));



        speedSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                delay = speedSlider.getValue(); // Update the delay variable with the slider's value
            }
        });




        this.setArray(newSize);

        label1.setBounds(450, 10, 200, 50);
        label1.setFont(new Font("Arial", Font.BOLD, 30));
        label1.setForeground(Color.red);

        label2.setBounds(570, 10, 200, 50);
        label2.setFont(new Font("Arial", Font.BOLD, 30));
        label2.setForeground(Color.yellow);

        label3.setBounds(720, 10, 200, 50);
        label3.setFont(new Font("Arial", Font.BOLD, 30));
        label3.setForeground(Color.green);

        bubbleSort = new BubbleSort(array);
        insertionSort = new InsertionSort(array);
        selectionSort = new SelectionSort(array);
        quickSort = new QuickSort(array,newSize);
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
                        startPressed = true;
                        isRunning = true;
                    animate();
                    if (bubblePressed == true)
                    {
                        setLabel("Bubble Sort Algorithm");
                        playMusic("bubble.wav");

                    }

                    if (quickPressed == true)
                    {
                        playMusic("merge.wav");
                        setLabel("Quick Sort Algorithm");
                    }

                    if (insertionPressed == true)
                    {
                        playMusic("insert.wav");
                        setLabel("Insertion Sort Algorithm");
                    }

                    if (mergePressed == true)
                    {
                        setLabel("Merge Sort Algorithm");
                        playMusic("merge.wav");
                    }

                    if (selectionPressed == true)
                    {
                        setLabel("Selection Sort Algorithm");
                        playMusic("select.wav");
                    }



                    playButtonClickSound();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });


        bubble.setModel(new ButtonModel());
        bubble.setBackground(Color.black);
        bubble.setFocusPainted(false);
        bubble.setBorderPainted(false);
        bubble.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    if (isRunning == false) {
                        bubblePressed = true;
                        insertionPressed = false;
                        selectionPressed = false;
                        quickPressed = false;
                        mergePressed = false;


                        isBubble = true;
                        isInsertion = false;
                        isSelection = false;
                        isQuick = false;
                        isMerge = false;

                        setButtonBackground();
                        bubble.setBackground(Color.darkGray);
                        playButtonClickSound();

                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });

        insertion.setModel(new ButtonModel());
        insertion.setBackground(Color.black);
        insertion.setFocusPainted(false);
        insertion.setBorderPainted(false);
        insertion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (isRunning == false) {

                        bubblePressed = false;
                        insertionPressed = true;
                        selectionPressed = false;
                        quickPressed = false;
                        mergePressed = false;

                        isInsertion = true;
                        isMerge = false;
                        isBubble = false;
                        isSelection = false;
                        isQuick = false;

                        setButtonBackground();
                        insertion.setBackground(Color.darkGray);
                        playButtonClickSound();
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });

        selection.setModel(new ButtonModel());
        selection.setBackground(Color.black);
        selection.setFocusPainted(false);
        selection.setBorderPainted(false);
        selection.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (isRunning == false) {
                        bubblePressed = false;
                        insertionPressed = false;
                        selectionPressed = true;
                        quickPressed = false;
                        mergePressed = false;

                        isSelection = true;
                        isMerge = false;
                        isBubble = false;
                        isInsertion = false;
                        isQuick = false;

                        setButtonBackground();
                        selection.setBackground(Color.DARK_GRAY);
                        playButtonClickSound();
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });

        quick.setModel(new ButtonModel());
        quick.setBackground(Color.black);
        quick.setFocusPainted(false);
        quick.setBorderPainted(false);
        quick.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (isRunning == false) {

                        bubblePressed = false;
                        insertionPressed = false;
                        selectionPressed = false;
                        quickPressed = true;
                        mergePressed = false;


                        isQuick = true;
                        isMerge = false;
                        isBubble = false;
                        isInsertion = false;
                        isSelection = false;

                        setButtonBackground();
                        quick.setBackground(Color.darkGray);
                        playButtonClickSound();
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });


        merge.setModel(new ButtonModel());
        merge.setBackground(Color.black);
        merge.setFocusPainted(false);
        merge.setBorderPainted(false);
        merge.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (isRunning == false) {

                        bubblePressed = false;
                        insertionPressed = false;
                        selectionPressed = false;
                        quickPressed = false;
                        mergePressed = true;

                        isMerge = true;
                        isBubble = false;
                        isInsertion = false;
                        isSelection = false;
                        isQuick = false;
                        setButtonBackground();
                        merge.setBackground(Color.darkGray);
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
                setLabel(" ");
                if (currentClip != null && currentClip.isActive()) {

                    currentClip.stop(); // stops the currently playing music

                }

                reset.setBackground(Color.red);
                start.setBackground(Color.green);


                setArray(newSize);

                stepCount = 0;

                elapsedTime = 0;


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
                quickSort.push(newSize - 1);
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

        start.setBounds(5, 160, 160, 30);
        reset.setBounds(5, 192, 160, 30);
        selection.setBounds(5, 227, 160, 30);
        insertion.setBounds(5, 256, 160, 30);
        bubble.setBounds(5, 285, 160, 30);
        merge.setBounds(5, 315, 160, 30);
        quick.setBounds(5, 347, 160, 30);

        start.setFont(new Font("Arial",Font.BOLD,18));
        start.setForeground(Color.black);

        reset.setFont(new Font("Arial",Font.BOLD,18));
        //reset.setForeground(Color.WHITE);

        selection.setFont(new Font("Arial",Font.BOLD,18));
        insertion.setFont(new Font("Arial",Font.BOLD,18));
        bubble.setFont(new Font("Arial",Font.BOLD,18));
        merge.setFont(new Font("Arial",Font.BOLD,18));
        quick.setFont(new Font("Arial",Font.BOLD,18));


        start.setBorderPainted(true);
        reset.setBorderPainted(true);
        selection.setBorderPainted(true);
        bubble.setBorderPainted(true);
        merge.setBorderPainted(true);
        quick.setBorderPainted(true);
        insertion.setBorderPainted(true);



        // Set the line border with a thickness of 5 pixels
        start.setBorder(BorderFactory.createLineBorder(Color.darkGray, 1));
       reset.setBorder(BorderFactory.createLineBorder(Color.darkGray, 1));

        insertion.setBorder(BorderFactory.createLineBorder(Color.red, 1));
        insertion.setForeground(Color.white);

        selection.setBorder(BorderFactory.createLineBorder(Color.red, 1));
        selection.setForeground(Color.white);

        bubble.setBorder(BorderFactory.createLineBorder(Color.red, 1));
        bubble.setForeground(Color.white);

        merge.setBorder(BorderFactory.createLineBorder(Color.red, 1));
        merge.setForeground(Color.white);

        quick.setBorder(BorderFactory.createLineBorder(Color.red, 1));
        quick.setForeground(Color.white);












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


        this.add(sizeSlider);

        this.add(size);
        this.add(speed);
        this.add(speedSlider);



    }




    // Modify setArray method to accept the array size as a parameter
    public void setArray(int newSize) {
        array = new int[newSize];
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(480) + 40;
        }
    }

    // Add a method to set the array size
    public void setArraySize(int newSize) {
        setArray(newSize);
        bubbleSort.setArray(array);
        insertionSort.setArray(array);
        selectionSort.setArray(array);
        quickSort.setArray(array, newSize); // Pass the array size to quickSort
        mergeSort.setArray(array);
    }



    public void setButtonBackground() {

        bubble.setBackground(Color.black);
        insertion.setBackground(Color.black);
        selection.setBackground(Color.black);
        quick.setBackground(Color.black);
        merge.setBackground(Color.black);
        start.setBackground(Color.green);
        reset.setBackground(Color.red);

    }

    public int[] getArray() {
        return this.array;
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
        stepCount = 0;

        startTime = System.currentTimeMillis();

        if (isBubble) {
            bubbleSort.setCompareIndex(0);
            Timer bubbleTimer = new Timer(speedSlider.getMaximum() - delay, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    if (isSorted() || isRunning == false) {
                        bubbleSort.setCompareIndex(Integer.MAX_VALUE);
                        isRunning = false;
                        start.setBackground(Color.WHITE);
                        ((Timer) e.getSource()).stop();

                        if (currentClip != null && currentClip.isActive()) {
                            currentClip.stop(); // stops the currently playing music
                        }
                    } else {
                        if (isRunning == true) {
                            array = bubbleSort.sortOnlyOneItem();
                            stepCount++; // Increment the step count
                           // swapCount += bubbleSort.getSwapCount();




                        }
                        }

                        repaint();
                    }
            });

            bubbleTimer.start();
        }

        if (isInsertion) {

            insertionSort.setArrayIndex(1);

            Timer insertionTimer = new Timer(speedSlider.getMaximum() - delay, new ActionListener() {
                @Override

                public void actionPerformed(ActionEvent e) {

                    if (isSorted() || isRunning == false) {
                        insertionSort.setCompareIndex(Integer.MAX_VALUE);
                        insertionSort.setArrayIndex(Integer.MAX_VALUE);
                        insertionSort.setStartOfIteration(false);
                        isRunning = false;
                        start.setBackground(Color.WHITE);
                        ((Timer) e.getSource()).stop();

                        if (currentClip != null && currentClip.isActive()) {
                            currentClip.stop(); // stops the currently playing music
                        }
                    } else {
                        if (isRunning == true)
                            array = insertionSort.sortOnlyOneItem();
                        stepCount++;
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

            Timer selectionTimer = new Timer(speedSlider.getMaximum() - delay, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    if (isSorted() || isRunning == false) {
                        selectionSort.setCompareIndex(Integer.MAX_VALUE);
                        selectionSort.setArrayIndex(Integer.MAX_VALUE);
                        selectionSort.setMinIdx(Integer.MAX_VALUE);
                        isRunning = false;
                        start.setBackground(Color.WHITE);
                        ((Timer) e.getSource()).stop();

                        if (currentClip != null && currentClip.isActive()) {
                            currentClip.stop(); // stops the currently playing music
                        }
                    } else {
                        if (isRunning == true)
                            array = selectionSort.sortOnlyOneItem();
                        stepCount++;

                    }

                    repaint();
                }
            });

            selectionTimer.start();
        }

        if (isQuick) {

            Timer quickTimer = new Timer(speedSlider.getMaximum() - delay, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    if (isSorted() || isRunning == false) {
                        quickSort.setSP(-1);
                        quickSort.push(0);
                        quickSort.push(newSize - 1);
                        quickSort.setArrayIndex(Integer.MAX_VALUE);
                        quickSort.setCompareIndex(Integer.MAX_VALUE);
                        quickSort.setPartition(-1);
                        quickSort.setIsPartioning(false);
                        isRunning = false;
                        start.setBackground(Color.green);
                        ((Timer) e.getSource()).stop();

                        if (currentClip != null && currentClip.isActive()) {
                            currentClip.stop(); // stops the currently playing music
                        }
                    } else {

                        if (isRunning == true)
                            array = quickSort.sortOnlyOneItem();
                        stepCount++;
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

            Timer mergeTimer = new Timer(speedSlider.getMaximum() - delay, new ActionListener() {
                private int stepIndex = 0;

                @Override
                public void actionPerformed(ActionEvent e) {
                    if (stepIndex < sortingSteps.size() && isRunning) {
                        array = sortingSteps.get(stepIndex); // Get the array for the current step
                        stepIndex++;
                        stepCount++;

                        repaint(); // Repaint with the current step's array
                    } else {
                        isRunning = false;
                        start.setBackground(Color.WHITE);
                        ((Timer) e.getSource()).stop();

                        if (currentClip != null && currentClip.isActive()) {
                            currentClip.stop(); // stops the currently playing music
                        }
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

        Font numberFont = new Font("Arial", Font.BOLD, 12); // Define a font for the numbers
        g.setFont(numberFont);

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
                g.setColor(Color.YELLOW);

            if (isSorted())
                g.setColor(Color.white);


           // g.drawString("Swaps: " + swapCount, 970, 60);
            Font largerFont = new Font("Arial", Font.BOLD, 16);
            g.setFont(largerFont);


            if (isRunning) {
                long currentTime = System.currentTimeMillis();
                 elapsedTime = (currentTime - startTime) / 1000; // Convert to seconds
                g.drawString("Time: " + elapsedTime + " seconds", 970, 60);


            }

            g.drawString("Steps: " + stepCount, 970, 40);
            g.drawString("Time: " + elapsedTime + " seconds", 970, 60);



            int xCoordinate = 200 + i * 14;
            int rectWidth = 9;
            int rectHeight = array[i];

            // Draw the rectangle
            g.drawRect(xCoordinate, 600 - rectHeight, rectWidth, rectHeight);
            g.fillRect(xCoordinate, 600 - rectHeight, rectWidth, rectHeight);

            // Draw the number within the rectangle at the bottom
            String numberText = String.valueOf(array[i]);
            FontMetrics fm = g.getFontMetrics();
            int textWidth = fm.stringWidth(numberText);
            int textHeight = fm.getHeight();
            int textX =( xCoordinate + (rectWidth - textWidth) / 2)+9;
            int textY = 550 - textHeight / 2 + textHeight; // Align the text to the bottom of the rectangle
            g.setColor(Color.black); // Set a bright color for numbers

            Font small = new Font("Arial", Font.BOLD, 7);
            g.setFont(small);
            g.drawString(numberText, textX, textY);

        }
    }



}