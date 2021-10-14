package com.github.quaoz.tasks.carddeck;

import java.util.Arrays;
import java.util.stream.IntStream;

public class Main {
	public static void main(String[] args) {
		Deck deck = new Deck();

		System.out.println("Unsorted array:\n");
		deck.printDeck();

		deck.mergeSort();
		System.out.println("\nArray sorted by value:\n");
		deck.printDeck();

		deck.mergeSort(false);
		System.out.println("\nArray sorted by name:\n");
		deck.printDeck();

		System.out.println("\nArray sorted by suit then value:\n");
		int bound = deck.getSuits().length;
		IntStream.range(0, bound).forEachOrdered(i -> Arrays
						.stream(deck.getSuits()[i].getCards())
						.map(Card::getName)
						.forEach(System.out::println));

		int index = deck.indexOf("spades", 9);
		System.out.println("\nThe nine of spades is at: " + index);
		System.out.println(deck.getCards()[index].getName() + " was found at " + index + "\n");

		System.out.println("The shuffled cards are:\n");
		deck.shuffleDeck();
		deck.printDeck();

		System.out.println();
		Card[][] dealtDeck = deck.deal(13, 4);

		for (Card[] cards : dealtDeck) {
			IntStream.range(0, dealtDeck[0].length)
					.mapToObj(j -> cards[j].getName() + (j == dealtDeck[0].length - 1 ? "\n" : ", "))
					.forEach(System.out::print);
		}

		System.out.println("\nDeck without jokers:\n");

		deck.setIncludeJokers(false);
		deck.printDeck();
	}
}



