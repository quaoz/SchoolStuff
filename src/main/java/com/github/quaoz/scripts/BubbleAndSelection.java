package com.github.quaoz.scripts;

import com.github.quaoz.common.sorts.BubbleSort;
import com.github.quaoz.common.sorts.SelectionSort;

public class BubbleAndSelection {
	public static void main(String[] args) {
		Integer[] array = new Integer[]{
				1,
				2,
				3,
				5,
				4,
				7,
				6
		};

		//BubbleSort.sort(array.clone());
		System.out.println();
		SelectionSort.sort(array.clone());
	}
}
