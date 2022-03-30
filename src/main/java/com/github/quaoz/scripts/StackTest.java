package com.github.quaoz.scripts;

import com.github.quaoz.common.datastructures.StackImplementation;

import java.util.List;

public class StackTest {
	public static void main(String[] args) {
		StackImplementation<Integer> stack = new StackImplementation<>();
		Integer[] array = {0, 1, 2, 3, 4, 5, 6, 7, 8};

		stack.pushAll(List.of(array));
		System.out.println(stack.size());

		System.out.println("-----------");

		for (Integer integer : stack) {
			System.out.println(integer);
		}

		System.out.println("-----------");

		while (!stack.isEmpty()) {
			System.out.println(stack.pop());
		}
	}
}
