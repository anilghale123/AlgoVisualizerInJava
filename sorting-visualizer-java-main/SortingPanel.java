import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.Random;

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

    private int mergeLeft = 0;
    private int mergeRight = 1;


    int i = 0;

    public SortingPanel() {

        random = new Random();
        array = new int[300];
        this.setArray();

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
        start.setBackground(Color.WHITE);
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
                        isInsertion = false;
                        isSelection = false;
                        isQuick = false;
                        isMerge = false;
                        isBubble = true;
                        setButtonBackground();
                        bubble.setBackground(Color.lightGray);
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
                        isMerge = false;
                        isBubble = false;
                        isSelection = false;
                        isQuick = false;
                        isInsertion = true;
                        setButtonBackground();
                        insertion.setBackground(Color.lightGray);
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
                        isMerge = false;
                        isBubble = false;
                        isInsertion = false;
                        isQuick = false;
                        isSelection = true;
                        setButtonBackground();
                        selection.setBackground(Color.lightGray);
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
                        isMerge = false;
                        isBubble = false;
                        isInsertion = false;
                        isSelection = false;
                        isQuick = true;
                        setButtonBackground();
                        quick.setBackground(Color.lightGray);
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
                        isBubble = false;
                        isInsertion = false;
                        isSelection = false;
                        isQuick = false;
                        isMerge = true;
                        setButtonBackground();
                        merge.setBackground(Color.lightGray);

                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });




        reset.setModel(new ButtonModel());
        reset.setBackground(Color.WHITE);
        reset.setFocusPainted(false);
        reset.setBorderPainted(false);
        reset.addActionListener(new ActionListener() { 
            @Override
            public void actionPerformed(ActionEvent e) { 

                reset.setBackground(Color.lightGray);
                start.setBackground(Color.WHITE);

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
                quickSort.push(299);
                quickSort.setArrayIndex(Integer.MAX_VALUE);
                quickSort.setCompareIndex(Integer.MAX_VALUE);
                quickSort.setPartition(-1);
                quickSort.setIsPartioning(false);



                mergeSort.leftIndex = 0;
                mergeSort.rightIndex = array.length - 1;
                mergeSort.mergeIndex = Integer.MAX_VALUE;





                isRunning = false;
                
                Timer timer  = new Timer(10, new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        reset.setBackground(Color.WHITE);
                        ((Timer)e.getSource()).stop();
                    }
                });

                timer.start();
                repaint();
            } 
        });

        this.add(start);
        this.add(bubble);
        this.add(insertion);
        this.add(selection);
        this.add(quick);

        this.add(merge);
        this.add(reset);
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

        if (isBubble) {

            bubbleSort.setCompareIndex(0);

            Timer bubbleTimer = new Timer(10, new ActionListener() {
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

            Timer insertionTimer = new Timer(10, new ActionListener() {
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

            Timer selectionTimer = new Timer(10, new ActionListener() {
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

            Timer quickTimer = new Timer(10, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    if (isSorted() || isRunning == false) {
                        quickSort.setSP(-1);
                        quickSort.push(0);
                        quickSort.push(299);
                        quickSort.setArrayIndex(Integer.MAX_VALUE);
                        quickSort.setCompareIndex(Integer.MAX_VALUE);
                        quickSort.setPartition(-1);
                        quickSort.setIsPartioning(false);
                        isRunning = false;
                        start.setBackground(Color.WHITE);
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

        if (isMerge) {

            if (isMerge) {
                mergeSort.mergeSort(mergeSort.leftIndex,mergeSort.rightIndex); // Start the merge sort process
                mergeLeft = 0; // Initialize merge indices
                mergeRight = 1;
                mergeAnimation(); // Start the animation for merging
            }
        }
    }


    private void mergeAnimation() {
        Timer timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (mergeRight < array.length) {
                    mergeSort.merge(mergeLeft, (mergeLeft + mergeRight) / 2, mergeRight);
                    mergeRight += 2;
                } else if (mergeLeft < array.length) {
                    mergeLeft += 2;
                    mergeRight = mergeLeft + 1;
                } else {
                    ((Timer)e.getSource()).stop();
                    isRunning = false;
                }

                repaint(); // Update GUI after each step
            }
        });

        timer.start(); // Start the animation timer
    }











    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        this.setBackground(Color.BLACK);

        for (int i = 0; i < array.length; i++) {
            
            g.setColor(Color.BLACK);
            g.drawRect(i*5, 600 - array[i], 14, array[i]);
            
            if (isBubble) {

                if (i == bubbleSort.getCompareIndex() || i == (bubbleSort.getCompareIndex() + 1)) {
                    g.setColor(Color.MAGENTA);
                }

            }


            if (isMerge)
            {
                if (i == mergeSort.rightIndex || i == mergeSort.leftIndex)
                {
                    g.setColor(Color.red);
                }
                if (i == mergeSort.mergeIndex)
                {
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
                g.setColor(Color.white);

            g.fillRect(i*3, 600 - array[i], 4, array[i]);
        }
    }




}
