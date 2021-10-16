package com.github.quaoz.common.sorts;

import com.github.quaoz.common.arrayutils.Comparisons;
import com.github.quaoz.common.arrayutils.Swap;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class HeapSort {

    /**
     * Implements a generic heap sort algorithm
     *
     * @param array The array to be sorted
     * @return The sorted array
     */
    @Contract("_ -> param1")
    public static <T extends Comparable<T>> T @NotNull [] sort(T @NotNull [] array) {
        final int size = array.length;

        // Build heap
        for (int i = size / 2 - 1; i >= 0; i--) {
            heapify(array, size, i);
        }

        // Extract an element from the heap
        for (int i = size - 1; i >= 0; i--) {
            // Move the current root to the end
            Swap.swap(array, 0, i);

            // Heapify the reduced heap
            heapify(array, i, 0);
        }

        return array;
    }

    /**
     * Heapifies the subtree
     *
     * @param array The array to be heapified
     * @param size  The size of the subtree
     * @param root  The node root of the subtree
     */
    // to heapify a subtree rooted with node root which is an index in array[]
    public static <T extends Comparable<T>> void heapify(T[] array, int size, int root) {
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
