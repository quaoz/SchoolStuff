package com.github.quaoz.tasks.chess;

import it.unimi.dsi.fastutil.ints.IntIntImmutablePair;

public class Main {
	public static void main(String[] args) {
		Board board = new Board();

		board.draw();
		System.out.println();

		board.movePiece(new IntIntImmutablePair(1, 1), new IntIntImmutablePair(3, 1));

		board.draw();
		System.out.println();

		board.movePiece(new IntIntImmutablePair(3, 1), new IntIntImmutablePair(4, 1));
		board.movePiece(new IntIntImmutablePair(4, 1), new IntIntImmutablePair(5, 1));

		board.draw();
		System.out.println();

		board.movePiece(new IntIntImmutablePair(5, 1), new IntIntImmutablePair(6, 2));

		board.draw();
		System.out.println();
	}
}

