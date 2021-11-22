package com.github.quaoz.tasks.chess;

import it.unimi.dsi.fastutil.ints.IntIntImmutablePair;

public class Rook extends Piece {
	public Rook(IntIntImmutablePair position, boolean black) {
		super("rook", position, black);
	}

	@Override
	public MoveResult moveTo(IntIntImmutablePair dest, Piece[][] board) {
		return null;
	}

	@Override
	public String getSymbol() {
		return "R";
	}
}
