package com.github.quaoz.common.sorts;

import com.github.quaoz.common.Comparisons;
import com.github.quaoz.common.arrayutils.Swap;
import org.jetbrains.annotations.NotNull;

/**
 * Heap sort is a fast sorting algorithm, works by dividing the array into a sorted and unsorted region and shrinking
 * the unsorted region by moving the largest element into the sorted region
 *
 * <p> Worst-case performance O(n log n), Best-case performance O(n log n), Average performance O(n log n)
 */
public class HeapSort {

    /**
     * Implements a generic heap sort algorithm without having to specify the bounds
     *
     * @param array The array to be sorted
     * @param <T>   The array type
     * @return T The sorted array
     */
    public static <T extends Comparable<T>> T @NotNull [] sort(T @NotNull [] array) {
        return sort(array, 0, array.length);
    }

    /**
     * Implements a generic heap sort algorithm
     *
     * @param array The array to be sorted
     * @param <T>   The array type
     * @param start The start index
     * @param end    The end index
     *
     * @return T The sorted array
     */
    static <T extends Comparable<T>> T @NotNull [] sort(T @NotNull [] array, int start, int end) {
        final int size = end - start;

        // Build heap
        for (int i = size / 2 - 1; i >= 0; i--) {
            heapify(array, size, i);
        }

        // Extract an element from the heap
        for (int i = size - 1; i >= 0; i--) {
            // Move the current root to the end
            Swap.swap(array, start, start + i);

            // Heapify the reduced heap
            heapify(array, i, start);
        }

        return array;
    }

    /**
     * Heapifies the subtree
     *
     * @param array The array to be heapified
     * @param size  The size of the subtree
     * @param root  The node root of the subtree
     * @param <T>   The array type
     */
    // to heapify a subtree rooted with node root which is an index in array[]
    static <T extends Comparable<T>> void heapify(T[] array, int size, int root) {
        int max = root;
        final int left = 2 * root + 1;
        final int right = 2 * root + 2;

        // If left is bigger than max replace max with it
        if (left < size && Comparisons.bigger(array[left], array[max])) {
            max = left;
        }

        // If right is bigger than max replace max with it
        if (right < size && Comparisons.bigger(array[right], array[max])) {
            max = right;
        }

        // Swaps the max and the root if they aren't equal
        if (max != root) {
            Swap.swap(array, root, max);

            // Recursively heapify the affected subtree
            heapify(array, size, max);
        }
    }
}
