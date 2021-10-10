package tasks.carddeck;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Locale;

/**
 * Class representing a card
 */
public class Card implements Comparable<Card> {
	final private String suit;
	final private int faceValue;

	/**
	 * Used to convert integer values to their word representations
	 */
	final private static HashMap<Integer, String> cardNames  = new HashMap<>() {{
		put(0, "Two");
		put(1, "Three");
		put(2, "Four");
		put(3, "Five");
		put(4, "Six");
		put(5, "Seven");
		put(6, "Eight");
		put(7, "Nine");
		put(8, "Ten");
		put(9, "Jack");
		put(10, "Queen");
		put(11, "King");
		put(12, "Ace");
	}};

	/**
	 * Card constructor
	 *
	 * @param suit A string containing the suit name
	 * @param faceValue A number from 0 to 12 representing the cards value
	 * @throws AssertionError Invalid face value
	 */
	public Card(@NotNull String suit, int faceValue) throws AssertionError {
		assert faceValue < cardNames.size() && faceValue >= 0 : "Invalid faceValue, should be between 0 and " + cardNames.size();
		this.suit = suit.toLowerCase(Locale.ROOT);
		this.faceValue = faceValue;
	}


	/**
	 * @return String The cards suit
	 */
	public String getSuit() {
		return suit;
	}

	/**
	 * @return int The cards face value
	 */
	public int getFaceValue() {
		return faceValue;
	}

	/**
	 * @return String The long form name, face value and suit
	 */
	public String getName() {
		return cardNames.get(faceValue) + " of " + suit;
	}

	/**
	 * @param c Card to compare to  this
	 * @return int -1 if c is greater, 0 if c equals this, 1 if c is smaller than this
	 */
	@Override
	public int compareTo(@NotNull Card c) {
		int result;
		if (Deck.isCompareByValue()) {
			if (c.getFaceValue() == this.getFaceValue()) {
				if (c.getSuit().equals("hearts") || this.getSuit().equals("hearts")) {
					result = c.getSuit().equals("hearts") ? -1 : 1;
				} else if (c.getSuit().equals("diamonds") || this.getSuit().equals("diamonds")) {
					result = c.getSuit().equals("diamonds") ? -1 : 1;
				} else {
					result = c.getSuit().equals("clubs") ? -1 : 1;
				}
			} else {
				result = Integer.compare(this.getFaceValue(), c.getFaceValue());
			}
		} else {
			result = this.getName().compareTo(c.getName());
		}
		return result;
	}
}
