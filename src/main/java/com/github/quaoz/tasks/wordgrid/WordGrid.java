package com.github.quaoz.tasks.wordgrid;

import com.github.quaoz.common.searches.LinearSearch;
import org.jetbrains.annotations.NotNull;

class WordGrid {
	private final Character[][] grid;

	/**
	 * @param string
	 */
	WordGrid(@NotNull String string) {
		this.grid = new Character[10][string.length()];

		for (int i = 0; i < 9; i++) {
			this.grid[i] = string.chars().mapToObj(c -> (char) c).toArray(Character[]::new);
		}
	}

	/**
	 * Returns the character at the requested position in the grid
	 *
	 * @param row    The row of the requested character
	 * @param column The column of the requested character
	 *
	 * @return The character in the specified position
	 */
	public Character getCharacter(int row, int column) {
		return grid[row][column];
	}

	public void setCharacter(Character character, int row, int column) {
		grid[row][column] = character;
	}

	public String getColumn(int column) {
		StringBuilder columnString = new StringBuilder();

		for (Character[] aGrid : grid) {
			columnString.append(aGrid[column]);
		}

		return columnString.toString();
	}

	public String getDiagonal(int row) {
		StringBuilder diagonalString = new StringBuilder();
		int count = 0;

		for (int i = row; i < grid.length; i++) {
			diagonalString.append(grid[i][count++]);

			if (count == grid[0].length) {
				count = 0;
			}
		}

		return diagonalString.toString();
	}

	public int[] find(Character character) {
		int[] position = {-1, -1};

		for (int i = 0; i < grid.length; i++) {
			int result = LinearSearch.find(grid[i], character);

			if (result >= 0) {
				position[0] = i;
				position[1] = result;
				break;
			}
		}

		return position;
	}
}

