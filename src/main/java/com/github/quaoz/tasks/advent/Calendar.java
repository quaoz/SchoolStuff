package com.github.quaoz.tasks.advent;

import com.github.quaoz.common.console.Format;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Calendar {
	private final Integer[][] grid;
	private final ArrayList<String> messages = new ArrayList<>();
	private boolean currentIsOpened;

	/**
	 * Constructor
	 *
	 * @param width  The width of the grid
	 * @param height the height of the grid
	 */
	public Calendar(final int width, final int height) {
		// Checks that the grid is big enough
		assert width * height >= 25 : "Grid too small";

		// Creates the messages
		for (int i = 1; i <= 25; i++) {
			messages.add(25 - i + " days left to christmas!");
		}
		messages.add("Merry christmas!");

		grid = new Integer[width][height];
		currentIsOpened = false;
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

	/**
	 * Displays the calendar
	 */
	public void display() {
		System.out.println("\nAdvent Calendar:\n");
		for (Integer[] integers : grid) {
			for (Integer integer : integers) {
				String string;

				if (integer == null) {
					string = "--";
				} else if (integer > 0) {
					// Display future days as their number
					string = integer.toString();
				} else if (integer == 0 || currentIsOpened) {
					// Display past days as []
					string = "[]";
				} else {
					// Make current day bold and underlined
					string = Format.bold(Format.underline(Integer.toString(-integer))) + "  ";
				}

				System.out.printf("%-4s", string);
			}
			System.out.println();
		}
	}

	/**
	 * Opens the current day and prints the message
	 */
	public void open() {
		currentIsOpened = true;
		display();
		System.out.println("\n" + messages.get(LocalDate.now().getDayOfMonth()));
	}
}
