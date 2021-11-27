package com.github.quaoz.tasks.tetris;

public class Main {
	public static void main(String[] args) {
		final int[][] grid = {{1, 1, 1, 0}, {0, 1, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};
		Tetronimo tetronimo = new Tetronimo(grid);

		tetronimo.display();
		tetronimo.rotate();
		tetronimo.display();
		System.out.println(tetronimo.isValid());
	}
}
