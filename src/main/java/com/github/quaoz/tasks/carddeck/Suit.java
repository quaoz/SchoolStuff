package com.github.quaoz.tasks.carddeck;

import java.util.Arrays;

/**
 * Class representing a suit of cards
 */
class Suit {
	private final String suitName;
	private final Card[] cards = new Card[13];

	/**
	 * Suit constructor
	 *
	 * @param suit String suit name
	 */
	public Suit(String suit) {
		this.suitName = suit;
		Arrays.setAll(this.cards, i -> new Card(suitName, i));
	}

	/**
	 * @return Card[] Returns the suits cards
	 */
	public Card[] getCards() {
		return cards;
	}

	/**
	 * @return String The suit name
	 */
	public String getSuitName() {
		return suitName;
	}
}
