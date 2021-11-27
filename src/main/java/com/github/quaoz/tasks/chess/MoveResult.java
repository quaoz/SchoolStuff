package com.github.quaoz.tasks.chess;

import it.unimi.dsi.fastutil.ints.IntIntImmutablePair;

/**
 * Class used to return data about the move
 */
public class MoveResult {
	private boolean taking;
	private boolean valid;
	private IntIntImmutablePair pos;
	private IntIntImmutablePair take;

	public MoveResult() {
		valid = false;
		taking = false;
		pos = new IntIntImmutablePair(-1, -1);
		take = new IntIntImmutablePair(-1, -1);
	}

	/**
	 * Returns whether the move takes a piece
	 *
	 * @return Whether the move takes a piece
	 */
	public boolean isTaking() {
		return taking;
	}

	/**
	 * Returns whether the move is valid
	 *
	 * @return Whether the move is valid
	 */
	public boolean isValid() {
		return valid;
	}

	/**
	 * Returns the position of the piece being taken, in almost all cases this will be the same as the pieces new
	 * position however it needs to be separate to allow for en passant
	 *
	 * @return The position of the piece being taken
	 */
	public IntIntImmutablePair getTake() {
		return take;
	}

	/**
	 * Returns the pieces new position
	 *
	 * @return The pieces new position
	 */
	public IntIntImmutablePair getPos() {
		return pos;
	}

	/**
	 * Sets the pieces new position
	 *
	 * @param pos The pieces new position
	 */
	public void setPos(IntIntImmutablePair pos) {
		valid = true;
		this.pos = pos;
	}

	/**
	 * Takes the piece at the specified location
	 *
	 * @param pos The location of the piece to take
	 */
	public void take(IntIntImmutablePair pos) {
		taking = true;
		take = pos;
	}
}
