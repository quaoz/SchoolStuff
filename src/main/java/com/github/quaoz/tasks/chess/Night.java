package com.github.quaoz.tasks.chess;

import it.unimi.dsi.fastutil.ints.IntIntImmutablePair;
import it.unimi.dsi.fastutil.ints.IntIntImmutablePair;

public class Night extends Piece {
	public Night(IntIntImmutablePair position, boolean black) {
		super("night", position, black);
	}

	@Override
	public boolean moveTo(IntIntImmutablePair dest, Piece[][] board) {
		return false;
	}

	@Override
	public String getSymbol() {
		return "N";
	}
}

