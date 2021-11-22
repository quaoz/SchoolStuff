package com.github.quaoz.tasks.chess;

import it.unimi.dsi.fastutil.ints.IntIntImmutablePair;

public class MoveResult {
	private boolean taking;
	private boolean valid;
	private IntIntImmutablePair pos;
	private IntIntImmutablePair take;

	public MoveResult() {
		valid = false;
		taking = false;
		pos = new IntIntImmutablePair(-1, -1);
		take = new IntIntImmutablePair(-1, -1);
	}

	public boolean isTaking() {
		return taking;
	}

	public boolean isValid() {
		return valid;
	}

	public IntIntImmutablePair getTake() {
		return take;
	}

	public IntIntImmutablePair getPos() {
		return pos;
	}

	public void setPos(IntIntImmutablePair pos) {
		valid = true;
		this.pos = pos;
	}

	public void take(IntIntImmutablePair pos) {
		taking = true;
		take = pos;
	}
}
