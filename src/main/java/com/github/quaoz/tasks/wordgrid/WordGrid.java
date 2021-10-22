package com.github.quaoz.tasks.wordgrid;

import com.github.quaoz.common.searches.LinearSearch;
import org.jetbrains.annotations.NotNull;

class WordGrid {
	private final Character[][] grid;

	/**
	 * Constructor
	 *
	 * @param string The string to construct the grid from
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

	/**
	 * Sets the character at a specific location
	 *
	 * @param character The character to use
	 * @param row       The row of the element to set
	 * @param column    The column of the element to set
	 */
	public void setCharacter(Character character, int row, int column) {
		grid[row][column] = character;
	}

	/**
	 * Returns the requested column
	 *
	 * @param column The column to return
	 *
	 * @return String The column as a string
	 */
	public String getColumn(int column) {
		StringBuilder columnString = new StringBuilder();

		for (Character[] aGrid : grid) {
			columnString.append(aGrid[column]);
		}

		return columnString.toString();
	}

	/**
	 * Returns a diagonal from the grid
	 *
	 * @param row The row to start the diagonal from
	 *
	 * @return String The characters going diagonally down and right from the specified row
	 */
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

	/**
	 * Searches for the first occurrence of a character
	 *
	 * @param character The character to find
	 *
	 * @return int[] The position of the requested character, {-1, -1} if it's not found
	 */
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

