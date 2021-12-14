package com.github.quaoz.tasks.advent;

import java.time.LocalDate;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Calendar {
	private final Integer[][] grid;

	/**
	 * Constructor
	 *
	 * @param width  The width of the grid
	 * @param height the height of the grid
	 */
	public Calendar(final int width, final int height) {
		if (width * height < 25) {
			throw new RuntimeException("Grid too small");
		}

		grid = new Integer[width][height];
		init();
	}

	/**
	 * Randomly fills the grid wit numbers from 1 to 25
	 */
	private void init() {
		final Random random = ThreadLocalRandom.current();
		final int day = LocalDate.now().getDayOfMonth();

		// Iterate from 1 to 25
		for (int i = 1; i <= 25; i++) {
			int x = random.nextInt(grid.length);
			int y = random.nextInt(grid[0].length);

			// Generate new random numbers until it finds an unoccupied slot
			while (grid[x][y] != null) {
				x = random.nextInt(grid.length);
				y = random.nextInt(grid[0].length);
			}

			if (i < day) {
				// If 'i' is smaller than the current day of the month set it to 0
				grid[x][y] = 0;
			} else if (i == day) {
				// If 'i' is the current day of the month make it negative
				grid[x][y] = -i;
			} else {
				// Otherwise, set it to i
				grid[x][y] = i;
			}
		}
	}

	public void display() {
		System.out.println("\nAdvent Calendar:\n");
		for (Integer[] integers : grid) {
			for (Integer integer : integers) {
				String string;

				if (integer == null) {
					string = "--";
				} else if (integer > 0) {
					string = integer.toString();
				} else if (integer == 0) {
					string = "[]";
				} else {
					string = "\033[4m" + -integer + "\033[0m  ";
				}

				System.out.printf("%-4s", string);
			}
			System.out.println();
		}
	}
}
