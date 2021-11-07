package com.github.quaoz.tasks.scope;

import com.github.quaoz.common.Comparisons;
import org.jetbrains.annotations.NotNull;

public class Book implements Comparable<Book> {

	/**
	 * pageCount is private meaning it can only be accessed by the book class
	 * <p>
	 * This is the best scope for it as it has a getter method and doesn't need a setter as it should not change
	 */
	private final int pageCount;
	/**
	 * hardback is public meaning it can be accessed throughout the project
	 * <p>
	 * It should be private and have a getter method as it should not change once a book object is created meaning it
	 * doesn't need a setter method
	 */
	public boolean hardback;
	/**
	 * title and author are package-private meaning they can only be accessed by code in the same package
	 * <p>
	 * They could be private and have getter methods as they should not change once a book object is created meaning
	 * they don't need setter methods
	 */
	String title;
	String author;
	private int copies;

	// overloads
	Book(String title, String author, int pageCount, int copies) {
		this(title, author, pageCount, false, copies);
	}

	Book(String title, String author, int pageCount, boolean hardback) {
		this(title, author, pageCount, hardback, 1);
	}

	Book(String title, String author, int pageCount) {
		this(title, author, pageCount, false, 1);
	}

	Book(String title, String author, int pageCount, boolean hardback, int copies) {
		this.title = title;
		this.author = author;
		this.pageCount = pageCount;
		this.hardback = hardback;
		this.copies = copies;
	}

	int getPageCount() {
		return pageCount;
	}

	int getCopies() {
		return copies;
	}

	void addCopies(int copies) {
		this.copies += copies;
	}

	@Override
	public int compareTo(@NotNull Book o) {
		int result = -1;

		// Sort by author first
		if (Comparisons.bigger(this.author, o.author)) {
			result = 1;
		} else if (Comparisons.equal(this.author, o.author)) {
			// If they are equal compare the title
			if (Comparisons.bigger(this.title, o.title)) {
				result = 1;
			} else if (Comparisons.equal(this.title, o.title)) {
				result = 0;
			}
		}

		return result;
	}
}
