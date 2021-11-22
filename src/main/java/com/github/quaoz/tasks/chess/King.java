package com.github.quaoz.tasks.chess;

import it.unimi.dsi.fastutil.ints.IntIntImmutablePair;
import it.unimi.dsi.fastutil.ints.IntIntImmutablePair;

public class King extends Piece {
	public King(IntIntImmutablePair position, boolean black) {
		super("king", position, black);
	}

	@Override
	public boolean moveTo(IntIntImmutablePair dest, Piece[][] board) {
		return false;
	}

	@Override
	public String getSymbol() {
		return "K";
	}
}
