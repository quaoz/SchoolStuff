package com.github.quaoz.common.datastructures;

import org.jetbrains.annotations.NotNull;

import java.util.TreeSet;

public class BinarySearchTree<T extends Comparable<T>> {
	private BinaryNode<T> root;

	private static class BinaryNode<T extends Comparable<T>> {
		T value;
		BinaryNode<T> left;
		BinaryNode<T> right;

		public BinaryNode(T value) {
			this.value = value;
		}
	}

	public BinarySearchTree() {
		root = null;
	}

	public BinarySearchTree(T root) {
		this.root = new BinaryNode<>(root);
	}

	public void add(T value) {
		if (root == null) {
			root = new BinaryNode<>(value);
		} else {
			add(root, value);
		}
	}

	private void add(@NotNull BinaryNode<T> node, T value) {
		if (node.value.compareTo(value) >= 0) {
			if (node.left == null) {
				node.left = new BinaryNode<>(value);
			} else {
				add(node.left, value);
			}
		} else {
			if (node.right == null) {
				node.right = new BinaryNode<>(value);
			} else {
				add(node.right, value);
			}
		}
	}

	public void inOrder() {
		inOrder(root);
	}

	private void inOrder(BinaryNode<T> node) {
		if (node != null) {
			inOrder(node.left);
			System.out.println(node.value);
			inOrder(node.right);
		}
	}

	public void preOrder() {
		preOrder(root);
	}

	private void preOrder(BinaryNode<T> node) {
		if (node != null) {
			preOrder(node.left);
			System.out.println(node.value);
			preOrder(node.right);
		}
	}

	public void postOrder() {
		postOrder(root);
	}

	private void postOrder(BinaryNode<T> node) {
		if (node != null) {
			postOrder(node.left);
			System.out.println(node.value);
			postOrder(node.right);
		}
	}
}
