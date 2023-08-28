public class SelectionSort {
    
    private int array_index, compare_index, min_idx;
    private int[] array;
    private boolean findingMin;

    public SelectionSort(int[] array) {
        this.array = array;
        array_index = 0;
        compare_index = 1;
        min_idx = 0;
        findingMin = false;
    }

    public void swap(int arr[], int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    public int[] sortOnlyOneItem() {

        if (findingMin == false) {
            min_idx = array_index;
            findingMin = true;
        }

        if (array[compare_index] < array[min_idx]) {
            min_idx = compare_index;
        }

        compare_index++;

        if (compare_index >= 70) {
            swap(array, min_idx, array_index);
            array_index++;
            compare_index = array_index + 1;
            findingMin = false;
        }

        return array;
    }

    public int[] getArray() {
        return array;
    }

    public void setArray(int[] array) {
        this.array = array;
    }

    public int getArrayIndex() {
        return array_index;
    }

    public void setArrayIndex(int array_index) {
        this.array_index = array_index;
    }

    public int getCompareIndex() {
        return compare_index;
    }

    public void setCompareIndex(int compare_index) {
        this.compare_index = compare_index;
    }

    public int getMinIdx() {
        return min_idx;
    }

    public void setMinIdx(int min_idx) {
        this.min_idx = min_idx;
    } 
}
