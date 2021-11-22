package com.github.quaoz.tasks.chess;

import it.unimi.dsi.fastutil.ints.IntIntImmutablePair;
import org.jetbrains.annotations.NotNull;

public class Rook extends Piece {
	public Rook(IntIntImmutablePair position, boolean black) {
		super("rook", position, black);
	}

	@Override
	public MoveResult moveTo(@NotNull IntIntImmutablePair dest, Piece[][] pieces) {
		MoveResult moveResult = new MoveResult();
		boolean valid = true;

		// Check if move is sideways
		if (dest.leftInt() == pos.leftInt() && dest.rightInt() != pos.rightInt()) {
			int start = pos.rightInt();
			int end = dest.rightInt();

			// Find the smaller one and the bigger one
			if (dest.rightInt() > pos.rightInt()) {
				end = dest.rightInt();
				start = pos.rightInt();
			}

			// Iterate from smaller to bigger
			for (int i = start; i < end - 1; i++) {
				if (pieces[pos.leftInt()][i] != null) {
					valid = false;
				}
			}
		} else if (dest.rightInt() == pos.rightInt() && dest.leftInt() != pos.leftInt()) {
			int start = pos.leftInt();
			int end = dest.leftInt();

			// Find the smaller one and the bigger one
			if (dest.leftInt() > pos.leftInt()) {
				end = dest.leftInt();
				start = pos.leftInt();
			}

			// Iterate from smaller to bigger
			for (int i = start; i < end - 1; i++) {
				if (pieces[i][pos.rightInt()] != null) {
					valid = false;
				}
			}
		}

		// If the destination has a piece take it
		if (pieces[pos.leftInt()][dest.rightInt()] != null) {
			// Only allow taking is the piece is not of the same colour
			if (pieces[pos.leftInt()][dest.rightInt()].isBlack() != this.isBlack()) {
				moveResult.take(dest);
			} else {
				valid = false;
			}
		}

		// If valid confirm the move
		if (valid) {
			moveResult.setPos(dest);
		}

		return moveResult;
	}

	@Override
	public String getSymbol() {
		return "R";
	}
}
