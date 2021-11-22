package com.github.quaoz.tasks.chess;

import it.unimi.dsi.fastutil.ints.IntIntImmutablePair;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class Board {
	private final Piece[][] board;
	private final ArrayList<Piece> takenBlack = new ArrayList<>();
	private final ArrayList<Piece> takenWhite = new ArrayList<>();

	public Board() {
		board = new Piece[8][8];
		init();
	}

	/**
	 * Places all the pieces
	 */
	private void init() {
		for (int i = 0; i < board[1].length; i++) {
			board[1][i] = new Pawn(new IntIntImmutablePair(1, i), true);
			board[6][i] = new Pawn(new IntIntImmutablePair(6, i), false);
		}

		board[0][0] = new Rook(new IntIntImmutablePair(0, 0), true);
		board[0][7] = new Rook(new IntIntImmutablePair(0, 7), true);
		board[7][0] = new Rook(new IntIntImmutablePair(7, 0), false);
		board[7][7] = new Rook(new IntIntImmutablePair(7, 7), false);

		board[0][1] = new Night(new IntIntImmutablePair(0, 1), true);
		board[0][6] = new Night(new IntIntImmutablePair(0, 1), true);
		board[7][1] = new Night(new IntIntImmutablePair(7, 6), false);
		board[7][6] = new Night(new IntIntImmutablePair(7, 6), false);

		board[0][2] = new Bishop(new IntIntImmutablePair(0, 2), true);
		board[0][5] = new Bishop(new IntIntImmutablePair(0, 5), true);
		board[7][2] = new Bishop(new IntIntImmutablePair(7, 2), false);
		board[7][5] = new Bishop(new IntIntImmutablePair(7, 5), false);

		board[0][3] = new Queen(new IntIntImmutablePair(0, 2), true);
		board[7][4] = new Queen(new IntIntImmutablePair(0, 2), false);

		board[0][4] = new King(new IntIntImmutablePair(0, 5), true);
		board[7][3] = new King(new IntIntImmutablePair(0, 5), false);
	}

	/**
	 * Displays the board
	 */
	public void draw() {
		for (Piece[] pieces : board) {
			for (Piece piece : pieces) {
				System.out.print(piece != null ? " " + piece.getSymbol() + " " : "   ");
			}
			System.out.println();
		}
	}

	/**
	 * Moves a piece on the board
	 *
	 * @param srcPos  The position of the piece to move
	 * @param destPos The destination position of the piece
	 *
	 * @throws IndexOutOfBoundsException The source or destination position wasn't within the boards bounds
	 */
	public void movePiece(@NotNull IntIntImmutablePair srcPos, IntIntImmutablePair destPos) throws IndexOutOfBoundsException {
		final Piece piece = board[srcPos.leftInt()][srcPos.rightInt()];

		// Checks that there is a piece
		if (piece != null) {
			// Calls piece specific move
			if (piece.moveTo(destPos, board)) {
				// Copies the piece to its new location
				board[destPos.leftInt()][destPos.rightInt()] = piece;
				board[srcPos.leftInt()][srcPos.rightInt()] = null;
			}
		}
	}

	/**
	 * Takes the piece at the specified location
	 *
	 * @param pos The position of the piece to take
	 */
	 protected void take(@NotNull IntIntImmutablePair pos) {
		final Piece piece = board[pos.leftInt()][pos.rightInt()];

		if (piece != null) {
			if (piece.isBlack()) {
				takenBlack.add(piece);
			} else {
				takenWhite.add(piece);
			}

			board[pos.leftInt()][pos.rightInt()] = null;
		}
	}
}
