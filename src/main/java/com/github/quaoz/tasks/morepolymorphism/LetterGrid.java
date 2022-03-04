package com.github.quaoz.tasks.morepolymorphism;

public class LetterGrid extends Grid {

	public LetterGrid() {
		super();
	}

	public LetterGrid(int initValue) {
		super(initValue);
	}

	public LetterGrid(char mode) {
		super(mode);
	}

	protected LetterGrid(int width, int height) {
		super(width, height);
	}

	public char getLetter(int row, int col) {
		int element = getElement(row, col);

		if (element > 26) {
			return '!';
		} else {
			return ((char) (element + 65));
		}
	}
}
