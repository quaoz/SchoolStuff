package com.github.quaoz.tasks.wordgrid;

import java.util.Arrays;

public class Main {
	public static void main(String[] args) {
		WordGrid wordGrid = new WordGrid("hello");

		System.out.println(wordGrid.getColumn(1));
		System.out.println(wordGrid.getDiagonal(0));
		System.out.println(Arrays.toString(wordGrid.find('e')));
	}
}

