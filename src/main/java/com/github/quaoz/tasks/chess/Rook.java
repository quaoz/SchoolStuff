package com.github.quaoz.tasks.chess;

import it.unimi.dsi.fastutil.ints.IntIntImmutablePair;
import it.unimi.dsi.fastutil.ints.IntIntImmutablePair;

public class Rook extends Piece {
	public Rook(IntIntImmutablePair position, boolean black) {
		super("rook", position, black);
	}

	@Override
	public boolean moveTo(IntIntImmutablePair dest, Piece[][] board) {
		return false;
	}

	@Override
	public String getSymbol() {
		return "R";
	}
}
