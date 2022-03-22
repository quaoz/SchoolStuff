package com.github.quaoz.scripts;

import com.github.quaoz.common.datastructures.Queue;

public class QueueTest {
	public static void main(String[] args) {
		Queue<Integer> queue = new Queue<>();

		for (int i = 0; i < 10; i++) {
			queue.enqueue(i);
		}

		System.out.println(queue.dequeue());

		System.out.println("queue.size() = " + queue.size());
		System.out.println("queue.isEmpty() = " + queue.isEmpty());

		queue.clear();
		System.out.println("queue.isEmpty() = " + queue.isEmpty());
	}
}
