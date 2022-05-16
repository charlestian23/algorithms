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

    public static void main(String[] args)
    {
        int[] randomNumbers = new int[10];
        Random random = new Random();
        for (int i = 0; i < randomNumbers.length; i++)
            randomNumbers[i] = random.nextInt(100);

        final String format = "%15s\t%s";
        System.out.println(String.format(format, "Unsorted:", Arrays.toString(randomNumbers)));

        Sort.insertionSort(randomNumbers);
        System.out.println(String.format(format, "Insertion Sort:", Arrays.toString(randomNumbers)));

        Sort.quickSort(randomNumbers);
        System.out.println(String.format(format, "Quick Sort:", Arrays.toString(randomNumbers)));
    }
}
