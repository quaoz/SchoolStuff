package com.github.quaoz.tasks.chess;

import it.unimi.dsi.fastutil.ints.IntIntImmutablePair;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;

/**
 * Base class representing a piece, declared abstract because some methods are entirely down to the pieces to implement
 */
public abstract class Piece {
	private final String name;
	private final boolean black;
	protected IntIntImmutablePair pos;

	/**
	 * Constructor
	 *
	 * @param name     The pieces name
	 * @param position The pieces staring position
	 * @param black    Whether the piece is black
	 */
	public Piece(@NotNull String name, IntIntImmutablePair position, boolean black) {
		this.name = name.toLowerCase(Locale.ROOT);
		this.pos = position;
		this.black = black;
	}

	/**
	 * Returns the pieces name
	 *
	 * @return The pieces name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the pieces symbol
	 *
	 * @return The pieces symbol
	 */
	public abstract String getSymbol();

	/**
	 * Trys to move the piece to the destination location
	 *
	 * @param dest   The destination position
	 * @param pieces The board object the piece is moving on, needed to check for collisions with other pieces
	 *
	 * @return The move result
	 */
	public abstract MoveResult moveTo(IntIntImmutablePair dest, Piece[][] pieces);

	/**
	 * Returns the pieces location
	 *
	 * @return The pieces location
	 */
	public IntIntImmutablePair getPos() {
		return pos;
	}

	/**
	 * Returns whether the piece is black
	 *
	 * @return Whether the piece is black
	 */
	public boolean isBlack() {
		return black;
	}
}
