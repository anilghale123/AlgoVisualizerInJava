





public class MergeSort {
      int tempArray[] = new int[300];
      int array[] = new int[300];
    public int array_index;
    public int compare_index;

    public int leftIndex;
    public int rightIndex;
    public int mergeIndex;
    private int stepIndex; // Added to keep track of the current step

    public MergeSort(int[] array) {
        this.array = array;
        this.array_index = 0;
        this.compare_index = Integer.MAX_VALUE;

        this.leftIndex = 0;
        this.rightIndex = array.length - 1;
        this.mergeIndex = Integer.MAX_VALUE;

        this.stepIndex = 0; // Initialize stepIndex to 0
    }

    public boolean sortStep() {
        if (stepIndex == 0) {
            mergeSort(0, array.length - 1); // Start the sorting process
            stepIndex++; // Move to the next step
            return true; // Sorting is in progress
        } else if (stepIndex <= array.length - 1) {
            merge(0, stepIndex, Math.min(stepIndex * 2, array.length - 1)); // Perform merging
            stepIndex *= 2;
            return true; // Merging is in progress
        } else {
            stepIndex = 0; // Reset stepIndex when sorting is done
            return false; // Sorting is done
        }
    }

    public void merge(int left, int middle, int right) {
        for (int i = left; i <= right; i++) {
            tempArray[i] = array[i];
        }

        int i = left;
        int j = middle + 1;
        int k = left;

        while (i <= middle && j <= right) {
            if (tempArray[i] <= tempArray[j]) {
                array[k] = tempArray[i];
                i++;
            } else {
                array[k] = tempArray[j];
                j++;
            }
            k++;
        }

        while (i <= middle) {
            array[k] = tempArray[i];
            i++;
            k++;
        }
    }

    public void mergeSort(int left, int right) {
        if (left < right) {
            int middle = (left + right) / 2;
            mergeSort(left, middle);
            mergeSort(middle + 1, right);
            merge(left, middle, right);
        }
    }

}




