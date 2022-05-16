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

    public static void main(String[] args)
    {
        int[] randomNumbers = new int[10];
        Random random = new Random();
        for (int i = 0; i < randomNumbers.length; i++)
            randomNumbers[i] = random.nextInt(100);

        final String format = "%15s\t%s";
        System.out.println(String.format(format, "Unsorted:", Arrays.toString(randomNumbers)));

        System.out.println(String.format(format, "Median:", DivideAndConquer.findMedian(randomNumbers)));

        for (int i = 1; i <= randomNumbers.length; i++)
            System.out.println(String.format(format, "k = " + i + ":", DivideAndConquer.getNthLargestValue(randomNumbers, i)));

        DivideAndConquer.quickSort(randomNumbers);
        System.out.println(String.format(format, "Quick Sort:", Arrays.toString(randomNumbers)));
    }
}
