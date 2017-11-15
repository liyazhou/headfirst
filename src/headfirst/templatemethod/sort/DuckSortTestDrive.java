package headfirst.templatemethod.sort;

import java.util.Arrays;

public class DuckSortTestDrive {
    private static final int INSERTIONSORT_THRESHOLD = 7;

    public static void main(String[] args) {
        Duck[] ducks = {new Duck("Daffy", 8), new Duck("Dewey", 2),
                new Duck("Howard", 7), new Duck("Louie", 2),
                new Duck("Howard2", 72), new Duck("Louie1", 21),
                new Duck("Howard3", 73), new Duck("Louie4", 42),
                new Duck("Howard6", 67), new Duck("Louie5", 52),
                new Duck("Donald", 10), new Duck("Huey", 2)};

        System.out.println("Before sorting:");
        display(ducks);

        Arrays.sort(ducks);
//		sort(ducks);
        System.out.println("\nAfter sorting:");
        display(ducks);
    }

    public static void display(Duck[] ducks) {
        for (int i = 0; i < ducks.length; i++) {
            System.out.println(ducks[i]);
        }
    }

    public static void sort(Object[] a) {
        Object[] aux = (Object[]) a.clone();
        mergeSort(aux, a, 0, a.length, 0);
    }

    private static void mergeSort(Object[] src, Object[] dest, int low,
                                  int high, int off) {
        int length = high - low;

        // Insertion sort on smallest arrays
        if (length < INSERTIONSORT_THRESHOLD) {
            for (int i = low; i < high; i++)
                for (int j = i; j > low
                        && ((Comparable) dest[j - 1]).compareTo(dest[j]) > 0; j--)
                    swap(dest, j, j - 1);
            return;
        }
        // Recursively sort halves of dest into src
        int destLow = low;
        int destHigh = high;
        low += off;
        high += off;
        int mid = (low + high) >>> 1;
        mergeSort(dest, src, low, mid, -off);
        mergeSort(dest, src, mid, high, -off);

        // If list is already sorted, just copy from src to dest. This is an
        // optimization that results in faster sorts for nearly ordered lists.
        if (((Comparable) src[mid - 1]).compareTo(src[mid]) <= 0) {
            System.arraycopy(src, low, dest, destLow, length);
            return;
        }

        // Merge sorted halves (now in src) into dest
        for (int i = destLow, p = low, q = mid; i < destHigh; i++) {
            if (q >= high || p < mid
                    && ((Comparable) src[p]).compareTo(src[q]) <= 0)
                dest[i] = src[p++];
            else
                dest[i] = src[q++];
        }
    }

    /**
     * Swaps x[a] with x[b].
     */
    private static void swap(Object[] x, int a, int b) {
        Object t = x[a];
        x[a] = x[b];
        x[b] = t;
    }
}
