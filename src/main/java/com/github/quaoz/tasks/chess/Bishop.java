package com.github.quaoz.tasks.chess;

import it.unimi.dsi.fastutil.ints.IntIntImmutablePair;

public class Bishop extends Piece {
	public Bishop(IntIntImmutablePair position, boolean black) {
		super("bishop", position, black);
	}

	@Override
	public MoveResult moveTo(IntIntImmutablePair dest, Piece[][] pieces) {
		MoveResult moveResult = new MoveResult();
		boolean valid = true;

		// Check if move is diagonal
		if (pos.leftInt() - dest.leftInt() == pos.rightInt() - dest.rightInt()) {
			final int distance = pos.leftInt() - dest.leftInt();
			final int smallLeft = Math.min(pos.leftInt(), dest.leftInt());
			final int smallRight = Math.min(pos.rightInt(), dest.rightInt());

			// Check it does not go over any pieces
			for (int i = 0; i < distance - 1; i++) {
				if (pieces[smallLeft + i][smallRight + i] != null) {
					valid = false;
					break;
				}
			}
		}

		// If the destination has a piece take it
		if (pieces[pos.leftInt()][dest.rightInt()] != null) {
			// Only allow taking if the piece is not of the same colour
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
		return "B";
	}
}
