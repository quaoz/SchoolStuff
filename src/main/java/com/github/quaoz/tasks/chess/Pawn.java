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
	public MoveResult moveTo(@NotNull IntIntImmutablePair dest, Piece[][] pieces) {
		MoveResult moveResult = new MoveResult();

		// Check move is forwards, relative to colour
		if (dest.leftInt() == (isBlack()
				? pos.leftInt() + 1
				: pos.leftInt() - 1)) {

			// Check it isn't moving into an occupied space
			if (pieces[dest.leftInt()][dest.rightInt()] == null) {
				// Check it's not changing column
				if (dest.rightInt() == pos.rightInt()) {
					pos = dest;
					onStartLine = false;
					moveResult.setPos(pos);
				}
				// Allow diagonal moves if it is taking a piece
			} else if (dest.rightInt() == pos.rightInt() + 1
					| dest.rightInt() == pos.rightInt() - 1) {

				pos = dest;
				onStartLine = false;
				moveResult.take(pos);
				moveResult.setPos(pos);
			}
			// Allow moving forwards twice from the start line
		} else if (onStartLine && dest.leftInt() == (isBlack()
				? pos.leftInt() + 2
				: pos.leftInt() - 2)) {

			// Check next two spaces are empty
			if (pieces[dest.leftInt()][dest.rightInt()] == null && (isBlack()
					? pieces[dest.leftInt() - 1][dest.rightInt()]
					: pieces[dest.leftInt() + 1][dest.rightInt()]) == null) {

				pos = dest;
				onStartLine = false;
				moveResult.setPos(pos);
			}
		}

		return moveResult;
	}
}
