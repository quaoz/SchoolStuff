package tasks.carddeck;

import common.arrayutils.Shuffle;
import common.searches.LinearSearch;
import common.sorts.MergeSort;

import java.util.Locale;

/**
 * Class representing a deck of cards
 */
public class Deck {
	private static boolean compareByValue;
	private final Card[] cards = new Card[52];
	private final Suit[] suits = new Suit[4];

	/**
	 * Deck constructor
	 */
	public Deck() {
		this.suits[0] = new Suit("Spades");
		this.suits[1] = new Suit("Clubs");
		this.suits[2] = new Suit("Diamonds");
		this.suits[3] = new Suit("Hearts");

		System.arraycopy(this.suits[0].getCards(), 0, cards, 0, 13);
		System.arraycopy(this.suits[1].getCards(), 0, cards, 13, 13);
		System.arraycopy(this.suits[2].getCards(), 0, cards, 26, 13);
		System.arraycopy(this.suits[3].getCards(), 0, cards, 39, 13);

		compareByValue = true;
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
	 * @return Card[] The cards
	 */
	public Card[] getCards() {
		return cards;
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
		Deck.setCompareByValue(false);
		faceValue = faceValue - 2;

		int index = LinearSearch.find(cards, new Card(suit, faceValue));
		Deck.setCompareByValue(true);;
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
		assert hand * players <= 52 : "Not enough cards to deal them equally";

		Card[][] dealtDeck = new Card[players][hand];

		for (int i = 0; i < hand; i++) {
			for (int j = 0; j < players; j++) {
				dealtDeck[j][i] = cards[j + i * players];
			}
		}
		return dealtDeck;
	}
}
