import java.util.Arrays;
import java.util.Random;

public class Sort
{
    public static void insertionSort(int[] array)
    {
        for (int i = 0; i < array.length; i++)
        {
            int currentValue = array[i];
            int j = i - 1;
            while (j >= 0 && currentValue < array[j])
            {
                array[j + 1] = array[j];
                j -= 1;
            }
            array[j + 1] = currentValue;
        }
    }

    public static void quickSort(int[] array)
    {
        Sort.quickSortHelper(array, 0, array.length - 1);
    }

    private static void quickSortHelper(int[] array, int start, int end)
    {
        if (start >= end)
            return;
        int partitionIndex = Sort.quickSortPartition(array, start, end);
        Sort.quickSortHelper(array, start, partitionIndex - 1);
        Sort.quickSortHelper(array, partitionIndex + 1, end);
    }

    private static int quickSortPartition(int[] array, int start, int end)
    {
        int pivot = array[end];
        int i = start - 1;
        for (int j = start; j <= end - 1; j++)
        {
            if (array[j] <= pivot)
            {
                i++;
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }
        int temp = array[i + 1];
        array[i + 1] = array[end];
        array[end] = temp;
        return i + 1;
    }

    /**
     * Merge Sort
     *
     * The time complexity is always O(nlogn) time for worst, average,
     * and best cases since it is always dividing the array in half
     * and takes linear time to merge the two halves.
     *
     * References: https://www.geeksforgeeks.org/merge-sort/
     *
     * @param array     the array to be sorted
     */
    public static void mergeSort(int[] array)
    {
        mergeSortHelper(array, 0, array.length - 1);
    }

    public static void mergeSortHelper(int array[], int left, int right)
    {
        System.out.println(left + " " + right);
        if (left < right)
        {
            int middle = left + (right - left) / 2;

            // Sort first and second halves of array
            mergeSortHelper(array, left, middle);
            mergeSortHelper(array, middle + 1, right);

            mergeSortMergeSubarrays(array, left, middle, right);
        }
    }

    /**
     * Merges two subarrays of array
     * @param array
     * @param start     the starting index of the first subarray
     * @param middle    the ending index of the first subarray
     * @param end       the ending index of the second subarray
     */
    private static void mergeSortMergeSubarrays(int[] array, int start, int middle, int end)
    {
        // Create the two subarrays
        int left[] = new int[middle - start + 1];
        int right[] = new int[end - middle];

        // Copy the data to the subarrays
        for (int i = 0; i < left.length; i++)
            left[i] = array[start + i];
        for (int i = 0; i < right.length; i++)
            right[i] = array[middle + 1 + i];

        // Merge the subarrays
        int i = 0;
        int j = 0;
        int k = 1;
        while (i < left.length && j < right.length)
        {
            if (left[i] <= right[j])
            {
                array[k] = left[i];
                i++;
            }
            else
            {
                array[k] = right[j];
                j++;
            }
            k++;
        }

        // If applicable, copy remaining elements of left subarray
        while (i < left.length)
        {
            array[k] = left[i];
            i++;
            k++;
        }

        // If applicable, copy remaining elements of right subarray
        while (j < right.length)
        {
            array[k] = right[j];
            j++;
            k++;
        }
    }

    public static void main(String[] args)
    {
        int[] randomNumbers = new int[10];
        Random random = new Random();
        for (int i = 0; i < randomNumbers.length; i++)
            randomNumbers[i] = random.nextInt(100);

        final String format = "%15s\t%s";
        System.out.println(String.format(format, "Unsorted:", Arrays.toString(randomNumbers)));

        Sort.mergeSort(randomNumbers);
        System.out.println(String.format(format, "Merge Sort:", Arrays.toString(randomNumbers)));

//        Sort.insertionSort(randomNumbers);
//        System.out.println(String.format(format, "Insertion Sort:", Arrays.toString(randomNumbers)));

//        Sort.quickSort(randomNumbers);
//        System.out.println(String.format(format, "Quick Sort:", Arrays.toString(randomNumbers)));
    }
}
