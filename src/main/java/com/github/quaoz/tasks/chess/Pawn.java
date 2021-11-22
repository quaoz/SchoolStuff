package com.github.quaoz.tasks.chess;

import it.unimi.dsi.fastutil.ints.IntIntImmutablePair;
import org.jetbrains.annotations.NotNull;

public class Pawn extends Piece {
	private boolean onStartLine;

	public Pawn(IntIntImmutablePair position, boolean black) {
		super("pawn", position, black);

		onStartLine = true;
	}

	@Override
	public String getSymbol() {
		return "P";
	}

	@Override
	public boolean moveTo(@NotNull IntIntImmutablePair dest, Piece[][] pieces, Board board) {
		boolean result = false;

		// Check move is forwards, relative to colour
		if (dest.leftInt() == (isBlack()
				? this.position.leftInt() + 1
				: this.position.leftInt() - 1)) {

			// Check it isn't moving into an occupied space
			if (pieces[dest.leftInt()][dest.rightInt()] == null) {
				// Check it's not changing column
				if (dest.rightInt() == this.position.rightInt()) {
					this.position = dest;
					onStartLine = false;
					result = true;
				}
				// Allow diagonal moves if it is taking a piece
			} else if (dest.rightInt() == this.position.rightInt() + 1
					| dest.rightInt() == this.position.rightInt() - 1) {

				board.take(dest);
				this.position = dest;
				onStartLine = false;
				result = true;
			}
			// Allow moving forwards twice from the start line
		} else if (onStartLine && dest.leftInt() == (isBlack()
				? this.position.leftInt() + 2
				: this.position.leftInt() - 2)) {

			// Check next two spaces are empty
			if (pieces[dest.leftInt()][dest.rightInt()] == null && (isBlack()
					? pieces[dest.leftInt() - 1][dest.rightInt()]
					: pieces[dest.leftInt() + 1][dest.rightInt()]) == null) {

				this.position = dest;
				onStartLine = false;
				result = true;
			}
		}

		return result;
	}
}
