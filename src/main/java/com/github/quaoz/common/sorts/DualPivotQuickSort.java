package com.github.quaoz.common.sorts;

import com.github.quaoz.common.arrayutils.Comparisons;
import com.github.quaoz.common.arrayutils.Swap;
import org.jetbrains.annotations.NotNull;

/**
 * Dual-pivot quick sort is a modified quick sort algorithm which uses two pivot values instead of one, works by picking
 * two elements as a pivots and partitioning the array around them
 *
 * <p> Worst-case performance O(n^2), Best-case performance O(n log n), Average performance O(n log n)
 */
public class DualPivotQuickSort {

    /**
     * Implements a generic dual-pivot quick sort algorithm without having to specify the left and right bounds
     *
     * @param array The array to be sorted
     * @return The sorted array
     */
    public static <T extends Comparable<T>> T[] sort(T[] array) {
        return sort(array, 0, array.length - 1);
    }

    /**
     * Implements a generic dual-pivot quick sort algorithm
     *
     * @param array The array to be sorted
     * @param left  The first index of the array
     * @param right The last index of the array
     * @return The sorted array
     */
    static <T extends Comparable<T>> T[] sort(T[] array, int left, int right) {
        if (left < right) {
            int[] pivots = pivots(array, left, right);

            sort(array, left, pivots[0] - 1);
            sort(array, pivots[0] + 1, pivots[1] - 1);
            sort(array, pivots[1] + 1, right);
        }

        return array;
    }

    /**
     * Selects two pivot values form an array
     *
     * @param array The array to get the pivots from
     * @param left  The first index of the array
     * @param right The last index of the array
     * @return The partition indexes
     */
    private static <T extends Comparable<T>> int @NotNull [] pivots(T @NotNull [] array, int left, int right) {
        T pivot1 = array[left];
        T pivot2 = array[right];

        // Finds the two pivots
        if (Comparisons.bigger(pivot1, pivot2)) {
            Swap.swap(array, left, right);
            pivot1 = array[left++];
            pivot2 = array[right];
        } else if (Comparisons.equal(pivot1, pivot2)) {
            while (Comparisons.equal(pivot1, pivot2) && left < right) {
                pivot1 = array[left++];
            }
        }

        return partition(array, left, right, pivot1, pivot2);
    }

    /**
     * Partitions an array around two pivot values
     *
     * @param array  The array to partition
     * @param left   The first index of the array
     * @param right  The last index of the array
     * @param pivot1 The first pivot
     * @param pivot2 The second pivot
     * @return The partition indexes
     */
    private static <T extends Comparable<T>> int @NotNull [] partition(T[] array, int left, int right, T pivot1, T pivot2) {
        int index = left + 1;
        int leftIndex = left + 1;
        int rightIndex = right - 1;

        while (index <= rightIndex) {
            if (Comparisons.smaller(array[index], pivot1)) {
                Swap.swap(array, index++, leftIndex++);
            } else if (Comparisons.smaller(pivot2, array[index])) {
                Swap.swap(array, index, rightIndex--);
            } else {
                index++;
            }
        }

        // Bring the pivots to their positions
        Swap.swap(array, left, --leftIndex);
        Swap.swap(array, right, ++rightIndex);

        return new int[]{leftIndex, rightIndex};
    }
}