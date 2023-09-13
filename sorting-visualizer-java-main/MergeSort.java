import java.util.ArrayList;
import java.util.List;

public class MergeSort {
      int tempArray[] = new int[300];
      int array[] = new int[300];



    public int leftIndex;
    public int rightIndex;
    public int mergeIndex;

    private List<int[]> steps;

    public MergeSort(int[] array) {
        this.array = array;
        this.steps = new ArrayList<>();
        this.steps.add(array.clone());

        this.leftIndex = 0;
        this.rightIndex = array.length - 1;
    }

    public List<int[]> getSteps() {
        return steps;
    }


    public void setArray(int[] array) {
        this.array = array;
        this.steps.clear();
        this.steps.add(array.clone());
        this.leftIndex = 0;
        this.rightIndex = array.length - 1;
    }


    // Inside your MergeSort class
    int count = 0;
    public void mergeSortStep(int left, int right) {
        if (left < right) {
            int middle = (left + right) / 2;

            mergeSortStep(left, middle);
            mergeSortStep(middle + 1, right);

            merge(left, middle, right);

            steps.add(array.clone()); // Store the state after this step
        }
    }


    public void merge(int left, int middle, int right) {
        int leftSize = middle - left + 1;
        int rightSize = right - middle;

        int[] leftSubarray = new int[leftSize];
        int[] rightSubarray = new int[rightSize];

        // Copy data to temporary arrays
        for (int i = 0; i < leftSize; i++) {
            leftSubarray[i] = array[left + i];
        }
        for (int j = 0; j < rightSize; j++) {
            rightSubarray[j] = array[middle + 1 + j];
        }

        int i = 0;
        int j = 0;
        int k = left;

        while (i < leftSize && j < rightSize) {
            if (leftSubarray[i] <= rightSubarray[j]) {
                array[k] = leftSubarray[i];
                i++;
            } else {
                array[k] = rightSubarray[j];
                j++;
            }
            k++;
        }

        // Copy remaining elements from leftSubarray, if any
        while (i < leftSize) {
            array[k] = leftSubarray[i];
            i++;
            k++;
        }

        // Copy remaining elements from rightSubarray, if any
        while (j < rightSize) {
            array[k] = rightSubarray[j];
            j++;
            k++;
        }



    }

}




