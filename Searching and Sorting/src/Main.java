import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class Main {
	private static final Random random = new Random();
	private static final Timer timer = new Timer();

	public static void main(String[] args) {
		// creates an array
		int[] array = new int[10000];

		// fills the array with random numbers under 100
		IntStream.range(0, array.length - 1).forEach(i -> array[i] = random.nextInt(array.length));
		IntArray intArray = new IntArray(array);

		// searches for the term
		System.out.println("-2 = value not found, -1 = value found, anything else is the index of the value");
		System.out.println("Value found: " + intArray.linearSearch(5, true) + "\n");

		// prints the array
		System.out.println("The unsorted array: " + Arrays.toString(intArray.getArray()) + "\n");

		// sorts the array using merge sort
		timer.startTimerNano();
		int[] mergeSortedArray = intArray.mergeSort();
		long mergeTime = timer.stopAndGetElapsedTime();

		System.out.println("Merge sorted array: " + Arrays.toString(mergeSortedArray));
		System.out.println("Merge sort took: " + mergeTime + " milliseconds \n");

		// resets the array and timer
		intArray = new IntArray(array);
		timer.resetTimer();

		// sorts the array using bubble sort
		timer.startTimerNano();
		System.out.println("Bubble sorted array: " + Arrays.toString(intArray.bubbleSort(false)));
		long bubbleTime = timer.stopAndGetElapsedTime();

		System.out.println("Bubble sort took: " + bubbleTime + " milliseconds \n");

		System.out.println("Merge sort was " + bubbleTime / mergeTime + " times faster than bubble sort \n");

		String string = "the quick brown fox jumps over the lazy dog";
		char[] charArray = string.toCharArray();

	}
}
