package com.github.quaoz.tasks.tetris;

public class Tetronimo {
	private int[][] grid;

	public Tetronimo(int[][] grid) {
		this.grid = grid;
	}

	public void rotate() {
		int[][] rotatedGrid = new int[grid.length][grid[0].length];

		// Rotates the grid 90 degrees clockwise
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				rotatedGrid[j][grid.length - 1 - i] = grid[i][j];
			}
		}

		grid = rotatedGrid;
	}

	public void display() {
		for (int[] rows : grid) {
			for (int space : rows) {
				System.out.print(space == 1 ? " X " : "   ");
			}
			System.out.println();
		}
	}

	public boolean isValid() {
		int connections = 0;

		// Any valid shape will have six borders between squares as it will be made of four cubes meaning there are
		// three points of contact with two touching sides for each point
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				// Checks the current slot is occupied
				if (grid[i][j] == 1) {
					// Bounds check and check for neighbouring occupied slots
					if (i > 0 && grid[i - 1][j] == 1) {
						connections++;
					}
					if (i < grid.length - 1 && grid[i + 1][j] == 1) {
						connections++;
					}
					if (j > 0 && grid[i][j - 1] == 1) {
						connections++;
					}
					if (j < grid[0].length - 1 && grid[i][j + 1] == 1) {
						connections++;
					}
				}
			}
		}

		return connections == 6;
	}
}
