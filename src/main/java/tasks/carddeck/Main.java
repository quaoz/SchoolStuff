package tasks.carddeck;

import java.util.Arrays;
import java.util.stream.IntStream;

public class Main {
	public static void main(String[] args) {
		Deck deck = new Deck();

		System.out.println("Unsorted array:\n");
		Arrays.stream(deck.getCards()).map(Card::getName).forEach(System.out::println);

		deck.mergeSort();
		System.out.println("\nArray sorted by value:\n");
		Arrays.stream(deck.getCards()).map(Card::getName).forEach(System.out::println);

		deck.mergeSort(false);
		System.out.println("\nArray sorted by name:\n");
		Arrays.stream(deck.getCards()).map(Card::getName).forEach(System.out::println);

		System.out.println("\nArray sorted by suit then value:\n");
		IntStream.range(0, deck.getSuits().length).forEachOrdered(i -> Arrays.stream(deck.getSuits()[i].getCards()).map(Card::getName).forEach(System.out::println));

		int index = deck.indexOf("spades", 9);
		System.out.println("\nThe nine of spades is at: " + index);
		System.out.println(deck.getCards()[index].getName() + " was found at " + index + "\n");

		System.out.println("The shuffled cards are:\n");
		deck.shuffleDeck();
		Arrays.stream(deck.getCards()).map(Card::getName).forEach(System.out::println);

		System.out.println("\n");
		Card[][] dealtDeck = deck.deal(13, 4);

		for (Card[] cards : dealtDeck) {
			for (int j = 0; j < dealtDeck[0].length; j++) {
				System.out.print(cards[j].getName() + ", ");
			}
			System.out.println("\n");
		}
	}
}



