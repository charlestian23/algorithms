public class DynamicProgramming
{
    /**
     * Kadane's Algorithm: Finds the sum of the contiguous subarray that has the largest sum
     *
     * The time complexity is O(n), where n is the size of the array.
     *
     * Reference: https://www.geeksforgeeks.org/largest-sum-contiguous-subarray/
     *
     * @param array
     * @return
     */
    public static int kadane(int[] array)
    {
        int maxSoFar = Integer.MIN_VALUE;
        int maxEndingHere = 0;

        for (Integer number : array)
        {
            maxEndingHere += number;
            if (maxSoFar < maxEndingHere)
                maxSoFar = maxEndingHere;
            if (maxEndingHere < 0)
                maxEndingHere = 0;
        }
        return maxSoFar;
    }

    public static void main(String[] args)
    {
        int[] array = {-2, -3, 4, -1, -2, 1, 5, -3};
        System.out.println(DynamicProgramming.kadane(array));
    }
}
