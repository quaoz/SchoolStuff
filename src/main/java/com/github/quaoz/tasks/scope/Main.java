package com.github.quaoz.tasks.scope;

public class Main {
	public static void main(String[] args) {
		System.out.println("Welcome to the library");
		Library myLibrary = new Library();
		myLibrary.listBooks();

		myLibrary.addBook("Dune", "Herbert, F", 412, false);
		myLibrary.addBook("A Wizard of Earthsea", "Le Guin, U", 205, false);
		myLibrary.addBook("The Very Hungry Caterpillar", "Carle, E", 22, true);
		myLibrary.addBook("Children of Dune", "Herbert, F", 444, false);
		myLibrary.addBook("God Emperor of Dune", "Herbert, F", 464, false);
		myLibrary.addBook("The Tombs of Atuan", "Le Guin, U", 163, false);
		myLibrary.addBook("The Farthest Shore", "Le Guin, U", 223, false);
		myLibrary.addBook("The Farthest Shore", "Le Guin, U", 223, false);

		myLibrary.listBooks();

		System.out.println("\nSorted:\n");
		myLibrary.sort();
		myLibrary.listBooks();
	}
}

