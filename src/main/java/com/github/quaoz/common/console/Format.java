package com.github.quaoz.common.console;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * Formats a string using ANSI escape codes
 *
 * See https://gist.github.com/fnky/458719343aabd01cfb17a3a4f7296797 for more
 */
public class Format {

	/**
	 * Make the given string bold
	 *
	 * @param string The string to make bold
	 *
	 * @return The bold string
	 */
	@Contract(pure = true)
	public static @NotNull String bold(String string) {
		return "\u001b[1m" + string + "\u001b[22m";
	}

	/**
	 * Make the given string italic
	 *
	 * @param string The string to make italic
	 *
	 * @return The italic string
	 */
	@Contract(pure = true)
	public static @NotNull String italic(String string) {
		return "\u001b[3m" + string + "\u001b[23m";
	}

	/**
	 * Underlines the given string
	 *
	 * @param string The string to underline
	 *
	 * @return The underlined string
	 */
	@Contract(pure = true)
	public static @NotNull String underline(String string) {
		return "\u001b[4m" + string + "\u001b[24m";
	}

	/**
	 * Inverts the given string
	 *
	 * @param string The string to invert
	 *
	 * @return The inverted string
	 */
	@Contract(pure = true)
	public static @NotNull String invert(String string) {
		return "\u001b[7m" + string + "\u001b[27m";
	}
}
