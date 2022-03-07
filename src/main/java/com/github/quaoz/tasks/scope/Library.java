package com.github.quaoz.tasks.scope;

import com.github.quaoz.common.datastructures.interpreter.ListInterpreter;
import com.github.quaoz.common.searches.BinarySearch;
import com.github.quaoz.common.sorts.BubbleSort;

import java.util.ArrayList;

class Library {
	private final ArrayList<Book> collection;

	Library() {
		collection = new ArrayList<>();
	}

	/**
	 * Lists all the book in the collection
	 */
	void listBooks() {
		for (Book currentBook : collection) {
			System.out.format("There %s of %s by %s, it is a %s book with %d pages\n",
					currentBook.getCopies() == 1 ? "is 1 copy" : "are " + currentBook.getCopies() + " copies",
					currentBook.title,
					currentBook.author.replace(",", ""),
					currentBook.hardback
							? "hardback"
							: "paperback",
					currentBook.getPageCount());
		}
	}

	/**
	 * Adds one copy of a paperback book to the collection
	 *
	 * @param title     The books title
	 * @param author    The books author
	 * @param pageCount The books page count
	 */
	void addBook(final String title, final String author, final int pageCount) {
		addBook(title, author, pageCount, false, 1);
	}

	/**
	 * Adds a paperback book to the collection
	 *
	 * @param title     The books title
	 * @param author    The books author
	 * @param pageCount The books page count
	 * @param copies    The number of copies of the book
	 */
	void addBook(final String title, final String author, final int pageCount, final int copies) {
		addBook(title, author, pageCount, false, copies);
	}

	/**
	 * Adds a book with one copy to the collection
	 *
	 * @param title     The books title
	 * @param author    The books author
	 * @param pageCount The books page count
	 * @param hardBack  Whether the book is hardback
	 */
	void addBook(final String title, final String author, final int pageCount, final boolean hardBack) {
		addBook(title, author, pageCount, hardBack, 1);
	}

	/**
	 * Adds a book to the collection
	 *
	 * @param title     The books title
	 * @param author    The books author
	 * @param pageCount The books page count
	 * @param hardBack  Whether the book is hardback
	 * @param copies    The number of copies of the book
	 */
	void addBook(final String title, final String author, final int pageCount, final boolean hardBack, final int copies) {
		final Book book = new Book(title, author, pageCount, hardBack, copies);
		final int bookPos = BinarySearch.find(new ListInterpreter<>(collection), book);

		if (bookPos == -2) {
			collection.add(book);
			sort();
		} else {
			collection.get(bookPos).addCopies(copies);
		}
	}

	void sort() {
		BubbleSort.sort(new ListInterpreter<>(collection));
	}
}

