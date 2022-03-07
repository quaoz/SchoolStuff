package com.github.quaoz.tasks.morepolymorphism;

import java.util.Arrays;

public class Grid {
	private static final int height = 10;
	private static final int width = 10;
	private final Integer[][] array;

	public Grid() {
		this(0);
	}

	public Grid(int initValue) {
		this(width, height);

		for (Integer[] integers : array) {
			Arrays.fill(integers, initValue);
		}
	}

	public Grid(char mode) {
		this(width, height);

		if (mode != 'a' && mode != 'd') {
			throw new RuntimeException("Invalid mode");
		}

		int count = mode == 'a'
				? 0
				: 100;

		for (int j = 0; j < array.length; j++) {
			for (int i = 0; i < array[0].length; i++) {
				array[j][i] = mode == 'a'
						? count++
						: count--;
			}
		}
	}

	protected Grid(int width, int height) {
		array = new Integer[width][height];
	}

	public int getElement(int row, int col) {
		return array[row][col];
	}

	public void setElement(int row, int col, int value) {
		array[row][col] = value;
	}

	public void display() {
		for (Integer[] integers : array) {
			for (Integer integer : integers) {
				System.out.print(integer + "  ");
			}
			System.out.println();
		}
	}
}
