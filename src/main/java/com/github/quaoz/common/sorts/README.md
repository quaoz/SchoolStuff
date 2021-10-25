# Sorts

The best sorting algorithm depends on the data being processed, however dual-pivot intro sort or intro sort is the best
general purpose sorting algorithm in my testing. Most sorting algorithms can be modified to further improve performance
with specific data sets.

## Bubble Sort

- Shouldn't be used anywhere
- Terrible performance for anything but already sorted arrays

## Merge Sort

- Works well with moderately sized data sets
- Outperformed by other algorithms in most scenarios

## Insertion Sort

- Works very well on very sort sets of data (elements =< 16)
- Can be combined with other algorithms (tim sort and intro sort)

## Shell sort

- Modified insertion sort that works well with moderately sized data sets
- Alternative to merge sort or tim sort

## Quick sort

- Works well on all sets of data
- Good general sorting algorithm

## Dual-pivot quick sort

- Modified quick sort
- Faster in most scenarios

## Intro sort

- Runs very well on all sized data sets
- Combination of insertion, quick and heap sort
-

## Dual-pivot intro sort

- Modified intro sort using dual-pivot quick sort
- Faster in scenarios where the data is random

## Tim sort

- Combination of merge and insertion sort
- Works well with real-world data (semi-sorted data)

## Heap sort

- Works well on medium large sets of data
- Alternative to quick sort

