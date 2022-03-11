package com.github.quaoz.common.console;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

/**
 * Class to print the program progress to the console
 */
public class PrintProgress {

	/**
	 * Prints a stylised progress bar to the console
	 *
	 * <p> Modified version of this https://stackoverflow.com/a/39257969 answer </p>
	 *
	 * @param startTime The starting time in milliseconds
	 * @param total     The total number of times this method will be called
	 * @param current   The current number of this method call
	 */
	public static void printProgressBar(long startTime, long total, long current) {
		long eta = current == 0
				? 0
				: (total - current) * (System.currentTimeMillis() - startTime) / current;

		String etaHms = current == 0
				? "N/A"
				: String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(eta),
				TimeUnit.MILLISECONDS.toMinutes(eta) % TimeUnit.HOURS.toMinutes(1),
				TimeUnit.MILLISECONDS.toSeconds(eta) % TimeUnit.MINUTES.toSeconds(1));

		int percent = (int) (((float) current / (float) total) * 100);

		String string = "\r" +
				String.join("", Collections.nCopies(percent == 0 ? 2 : 2 - (int) (Math.log10(percent)), " ")) +
				String.format("%d%% [", percent) +
				String.join("", Collections.nCopies(percent, "=")) + '>' +
				String.join("", Collections.nCopies(100 - percent, " ")) + ']' +
				String.join("", Collections.nCopies((int) (Math.log10(total)) - (int) (Math.log10(current)), " ")) +
				String.format(" %d/%d, ETA: %s", current, total, etaHms);

		System.out.print(string);
	}
}
