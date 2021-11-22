package com.github.quaoz.tasks.chess;

import it.unimi.dsi.fastutil.ints.IntIntImmutablePair;

public class Night extends Piece {
	public Night(IntIntImmutablePair position, boolean black) {
		super("night", position, black);
	}

	@Override
	public MoveResult moveTo(IntIntImmutablePair dest, Piece[][] board) {
		return null;
	}

	@Override
	public String getSymbol() {
		return "N";
	}
}

