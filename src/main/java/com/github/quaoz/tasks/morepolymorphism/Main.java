package com.github.quaoz.tasks.morepolymorphism;

public class Main {
	public static void main(String[] args) {
		Grid zeroedGrid = new Grid();
		zeroedGrid.display();
		System.out.println();

		Grid ascendingGrid = new Grid('a');
		ascendingGrid.display();
		System.out.println();

		Grid descendingGrid = new Grid('d');
		descendingGrid.display();
		System.out.println();
	}
}
