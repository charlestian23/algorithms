import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class DivideAndConquer
{
    public static void quickSort(int[] array)
    {
        Sort.quickSort(array);
    }

    public static int getNthLargestValue(int[] array, int n)
    {
        List<Integer> list = Arrays.stream(array).boxed().collect(Collectors.toList());
        return selection(list, n);
    }

    public static int findMedian(int[] array)
    {
        List<Integer> list = Arrays.stream(array).boxed().collect(Collectors.toList());
        return selection(list, list.size() / 2);
    }

    public static int selection(List<Integer> list, int k)
    {
        Random random = new Random();
        int v = list.get(random.nextInt(list.size()));
        List<Integer> s_left = new LinkedList<>();
        List<Integer> s_v = new LinkedList<>();
        List<Integer> s_right = new LinkedList<>();
        for (int num : list)
        {
            if (num < v)
                s_left.add(num);
            else if (num == v)
                s_v.add(num);
            else
                s_right.add(num);
        }
        if (k <= s_left.size())
            return selection(s_left, k);
        else if (s_left.size() < k && k <= s_left.size() + s_v.size())
            return v;
        else
            return selection(s_right, k - s_left.size() - s_v.size());
    }

    /**
     * Binary Search
     *
     * The time complexity is O(log n).
     *
     * Reference: https://www.geeksforgeeks.org/binary-search/
     *
     * @param array
     * @param target
     * @return          The index of the target in the array; if the target does
     *                  not exist in the array, returns -1
     */
    public static int binarySearch(int[] array, int target)
    {
        return binarySearchHelper(array, target, 0, array.length - 1);
    }

    private static int binarySearchHelper(int[] array, int target, int low, int high)
    {
        if (low > high)
            return -1;

        int mid = (low + high) / 2;
        if (array[mid] == target)
            return mid;
        else if (array[mid] > target)
            return binarySearchHelper(array, target, low, mid - 1);
        return binarySearchHelper(array, target, mid + 1, high);
    }

    public static void main(String[] args)
    {
        int[] randomNumbers = new int[10];
        Random random = new Random();
        for (int i = 0; i < randomNumbers.length; i++)
            randomNumbers[i] = random.nextInt(100);

        System.out.println("Unsorted: " + Arrays.toString(randomNumbers));

        System.out.println("Median: " + DivideAndConquer.findMedian(randomNumbers));

        System.out.println("nth Largest Value:");
        for (int i = 1; i <= randomNumbers.length; i++)
            System.out.println("\tn = " + i + ":\t" + DivideAndConquer.getNthLargestValue(randomNumbers, i));

        DivideAndConquer.quickSort(randomNumbers);
        System.out.println("Quick Sort:" + Arrays.toString(randomNumbers));

        int randomIndex = random.nextInt(randomNumbers.length);
        System.out.println("Binary Searching for " + randomIndex + ": " + DivideAndConquer.binarySearch(randomNumbers, randomNumbers[randomIndex]));
        System.out.println("Binary Searching for -100: " + DivideAndConquer.binarySearch(randomNumbers, -100));
    }
}
