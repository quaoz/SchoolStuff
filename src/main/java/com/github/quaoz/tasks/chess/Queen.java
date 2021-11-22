package com.github.quaoz.tasks.chess;

import it.unimi.dsi.fastutil.ints.IntIntImmutablePair;
import it.unimi.dsi.fastutil.ints.IntIntImmutablePair;

public class Queen extends Piece {
	public Queen(IntIntImmutablePair position, boolean black) {
		super("queen", position, black);
	}

	@Override
	public boolean moveTo(IntIntImmutablePair dest, Piece[][] board) {
		return false;
	}

	@Override
	public String getSymbol() {
		return "Q";
	}
}
