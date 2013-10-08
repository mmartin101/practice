import java.util.Random;

/**
 * User: max
 * Date: 9/30/13
 * Time: 8:49 PM
 */
public class SortingUtil
{
    public static final int N = 1000000;

    public static void main(String[] args)
    {
        Random r = new Random();
        int[] A = new int[N];


        for (int i = 0; i < N; i ++)
        {
            A[i] = r.nextInt(1000000);
        }
//        System.out.printf("Unsorted \t");
//        printArray(A);
        long start = System.currentTimeMillis();
//        insertionSort(A);
        mergeSort(A, 0, A.length - 1);
        System.out.println("took " + (System.currentTimeMillis() - start) + " msecs");

//        System.out.printf("Sorted \t\t");
//        printArray(A);
    }

    public static void printArray(int[] A)
    {
        if (A == null)
        {
            System.out.println("You passed in null you idiot!!");
            return;
        }
        else if (A.length < 0)
        {
            System.out.println("A[]");
            return;
        }

        StringBuilder builder = new StringBuilder();
        builder.append("A[");
        builder.append(A[0]);

        for (int i = 1; i < A.length; i++)
        {
            builder.append(", " + A[i]);
        }

        builder.append("]");
        System.out.println(builder.toString());
    }

    public static void insertionSort(int[] A)
    {
        for (int j = 1; j < A.length; j++)
        {
            int key = A[j];
            int i = j - 1;

            while (i > -1 && A[i] > key)
            {
                A[i + 1] = A[i];
                i--;
            }
            A[i + 1] = key;
        }
    }

    public static void mergeSort(int[] A, int p, int r)
    {
        if (p < r)
        {
            // should be floor of (p+r)/2 but working with ints takes care of
            // that for us
            int q = (p + r)/ 2;
            mergeSort(A, p, q);
            mergeSort(A, q + 1, r);
            merge(A, p, q, r);
        }
    }

    private static void merge(int[] A, int p, int q, int r)
    {
        int n1 = q - p + 1;
        int n2 = r - q;

        int[] left = new int[n1 + 1];
        int[] right = new int[n2 + 1];

        int i;
        for (i = 0; i < n1; i ++)
        {
            left[i] = A[p + i];
        }

        int j;
        for (j = 0; j < n2; j ++)
        {
            right[j] = A[j + q + 1];
        }

        left[i] = right[j] = Integer.MAX_VALUE; // inf

        i = j = 0;

        for (int k = p; k < r + 1; k++)
        {
            if (left[i] < right[j])
            {
                A[k] = left[i];
                i++;
            }
            else
            {
                A[k] = right[j];
                j++;
            }
        }
    }
}
