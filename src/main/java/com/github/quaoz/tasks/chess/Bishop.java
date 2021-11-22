package com.github.quaoz.tasks.chess;

import it.unimi.dsi.fastutil.ints.IntIntImmutablePair;
import it.unimi.dsi.fastutil.ints.IntIntImmutablePair;

public class Bishop extends Piece {
	public Bishop(IntIntImmutablePair position, boolean black) {
		super("bishop", position, black);
	}

	@Override
	public boolean moveTo(IntIntImmutablePair dest, Piece[][] board) {
		return false;
	}

	@Override
	public String getSymbol() {
		return "B";
	}
}

