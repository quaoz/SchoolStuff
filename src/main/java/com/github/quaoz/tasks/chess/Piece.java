package com.github.quaoz.tasks.chess;

import it.unimi.dsi.fastutil.ints.IntIntImmutablePair;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;

public abstract class Piece {
	private final String name;
	private final boolean black;
	protected IntIntImmutablePair pos;

	@Contract(pure = true)
	public Piece(@NotNull String name, IntIntImmutablePair position, boolean black) {
		this.name = name.toLowerCase(Locale.ROOT);
		this.pos = position;
		this.black = black;
	}

	public String getName() {
		return name;
	}

	public IntIntImmutablePair getPos() {
		return pos;
	}

	public abstract MoveResult moveTo(IntIntImmutablePair dest, Piece[][] board);

	public boolean isBlack() {
		return black;
	}

	public abstract String getSymbol();
}
