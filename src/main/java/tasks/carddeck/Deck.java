package tasks.carddeck;

import common.arrayutils.Shuffle;
import common.searches.LinearSearch;
import common.sorts.MergeSort;

import java.util.Arrays;
import java.util.Locale;
import java.util.stream.IntStream;

/**
 * Class representing a deck of cards
 */
public class Deck {
	private static boolean compareByValue;
	private static boolean includeJokers;
	private final Card[] cards;
	private final Suit[] suits;

	/**
	 * Deck constructor
	 */
	public Deck() {
		this.cards = new Card[54];
		this.suits = new Suit[4];

		this.suits[0] = new Suit("Spades");
		this.suits[1] = new Suit("Clubs");
		this.suits[2] = new Suit("Diamonds");
		this.suits[3] = new Suit("Hearts");

		// Adds teh suits to the deck
		System.arraycopy(this.suits[0].getCards(), 0, this.cards, 0, 13);
		System.arraycopy(this.suits[1].getCards(), 0, this.cards, 13, 13);
		System.arraycopy(this.suits[2].getCards(), 0, this.cards, 26, 13);
		System.arraycopy(this.suits[3].getCards(), 0, this.cards, 39, 13);

		// Adds the jokers to the deck
		this.cards[52] = new Card("Jokers", 13);
		this.cards[51] = new Card("Jokers", 13);

		includeJokers = true;
		compareByValue = true;
	}

	/**
	 * @return Card[] The cards
	 */
	public Card[] getCards() {
		return cards;
	}

	/**
	 * @return Suit[] Returns the suits
	 */
	public Suit[] getSuits() {
		return suits;
	}

	/**
	 * @return boolean Returns compareByValue
	 */
	public static boolean isCompareByValue() {
		return Deck.compareByValue;
	}

	/**
	 * @param compareByValue boolean Sets compareByValue
	 */
	public static void setCompareByValue(final boolean compareByValue) {
		Deck.compareByValue = compareByValue;
	}

	/**
	 * @return boolean Returns includeJokers
	 */
	public static boolean isIncludeJokers() {
		return includeJokers;
	}

	/**
	 * @param includeJokers boolean Sets includeJokes
	 */
	public void setIncludeJokers(boolean includeJokers) {
		Deck.includeJokers = includeJokers;

		if (!includeJokers) {
			// Replaces the jokers with temporary cards
			cards[indexOf("Jokers", 15)] = new Card("Tmp", -1);
			cards[indexOf("Jokers", 15)] = new Card("Tmp", -1);

			// Iterates backwards twice through the array to fill in the spots where the jokers where
			IntStream.range(0, 2).flatMap(i -> IntStream
					.iterate(cards.length, j -> j > 0, j -> j - 1))
					.filter(j -> cards[j - 1].getFaceValue() == -1)
					.forEach(j -> cards[j - 1] = cards[j]);

			// Re-adds the joker to the end of the array
			cards[cards.length - 1] = new Card("Jokers", 13);
			cards[cards.length - 2] = new Card("Jokers", 13);
		}
	}

	/**
	 * Uses a generic linear search to find the index of a card
	 *
	 * @param suit String The cards suit
	 * @param faceValue int The cards face value
	 * @return int The index of the card, -2 if the card isn't found
	 */
	public int indexOf(String suit, int faceValue) {
		suit = suit.toLowerCase(Locale.ROOT);
		faceValue = faceValue - 2;

		Deck.setCompareByValue(false);
		int index = LinearSearch.find(cards, new Card(suit, faceValue));
		Deck.setCompareByValue(true);

		return index;
	}

	/**
	 * Overload for mergeSort(), performs a generic merge sort on the cards comparing by value by default
	 */
	public void mergeSort() {
		mergeSort(true);
	}

	/**
	 * Performs a generic merge sort on the cards
	 *
	 * @param compareByValue boolean Whether to sort using the face value or the cards full name
	 */
	public void mergeSort(final boolean compareByValue) {
		Deck.setCompareByValue(compareByValue);
		MergeSort.sort(cards);
		Deck.setCompareByValue(true);
	}

	/**
	 * Shuffles the cards
	 */
	public void shuffleDeck() {
		Shuffle.shuffle(cards);
	}

	/**
	 * @param hand int The number of cards per player
	 * @param players int The number of player
	 * @return Card[][] The dealt deck
	 * @throws AssertionError Not enough cards to deal them equally
	 */
	public Card[][] deal(int hand, int players) throws AssertionError {
		assert hand * players <= cards.length : "Not enough cards to deal them equally";
		Card[][] dealtDeck = new Card[players][hand];

		for (int i = 0; i < hand; i++) {
			for (int j = 0; j < players; j++) {
				dealtDeck[j][i] = cards[j + i * players];
			}
		}
		return dealtDeck;
	}

	/**
	 * Prints the deck
	 */
	public void printDeck() {
		Arrays.stream(cards).forEachOrdered(System.out::println);
	}
}
