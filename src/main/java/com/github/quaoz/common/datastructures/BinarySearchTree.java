package com.github.quaoz.common.datastructures;

import org.jetbrains.annotations.NotNull;

import java.util.Scanner;

public class BinarySearchTree<T extends Comparable<T>> {
	private BinaryNode<T> root;

	/**
	 * A simple node class for the binary search tree containing a value and a left and right child
	 */
	private static class BinaryNode<T extends Comparable<T>> {
		/**
		 * The value of the node
		 */
		T value;

		/**
		 * The left child of the node
		 */
		BinaryNode<T> left;

		/**
		 * The right child of the node
		 */
		BinaryNode<T> right;

		/**
		 * Constructs a new node with the given value
		 *
		 * @param value The nodes value
		 */
		public BinaryNode(T value) {
			this.value = value;
		}
	}

	/**
	 * Constructs a new empty binary search tree
	 */
	public BinarySearchTree() {
		root = null;
	}

	/**
	 * Constructs a new binary search tree with the given value as its root
	 *
	 * @param value The value to use as the root of this tree
	 */
	public BinarySearchTree(T value) {
		root = new BinaryNode<>(value);
	}

	/**
	 * Inserts the given value into the tree
	 *
	 * @param value The value to insert
	 */
	public void add(T value) {
		if (root == null) {
			root = new BinaryNode<>(value);
		} else {
			add(root, value);
		}
	}

	/**
	 * Inserts the given value into the tree
	 *
	 * @param node 	The node to start at
	 * @param value The value to insert
	 */
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

	/**
	 * Removes the given value from the tree
	 *
	 * @param value The value to remove
	 */
	public void remove(T value) {
		remove(root, value);
	}

	/**
	 * Removes the given value from the tree
	 *
	 * @param node 	The node to start at
	 * @param value The value to remove
	 */
	private BinaryNode<T> remove(BinaryNode<T> node, T value) {
		if (node == null) {
			return null;
		}

		if (node.value.compareTo(value) > 0) {
			node.left = remove(node.left, value);
		} else if (node.value.compareTo(value) < 0) {
			node.right = remove(node.right, value);
		} else {
			if (node.left == null) {
				if (node.right.value.compareTo(value) == 0) {
					remove(node.right, value);
				} else {
					// remove the node
					return node.right;
				}
			} else if (node.right == null) {
				if (node.left.value.compareTo(value) == 0) {
					remove(node.left, value);
				} else {
					// remove the node
					return node.left;
				}
			} else {
				// ?
			}
		}

		return node;
	}

	/**
	 * Traverses the tree in order and prints the values
	 */
	public void inOrder() {
		inOrder(root);
	}

	/**
	 * Traverses the tree in order and prints the values
	 *
	 * @param node The node to start at
	 */
	private void inOrder(BinaryNode<T> node) {
		if (node != null) {
			inOrder(node.left);
			System.out.println(node.value);
			inOrder(node.right);
		}
	}

	/**
	 * Traverses the tree pre-order and prints the values
	 */
	public void preOrder() {
		preOrder(root);
	}

	/**
	 * Traverses the tree pre-order and prints the values
	 *
	 * @param node The node to start at
	 */
	private void preOrder(BinaryNode<T> node) {
		if (node != null) {
			System.out.println(node.value);
			preOrder(node.left);
			preOrder(node.right);
		}
	}

	/**
	 * Traverses the tree post-order and prints the values
	 */
	public void postOrder() {
		postOrder(root);
	}

	/**
	 * Traverses the tree post-order and prints the values
	 *
	 * @param node The node to start at
	 */
	private void postOrder(BinaryNode<T> node) {
		if (node != null) {
			postOrder(node.left);
			postOrder(node.right);
			System.out.println(node.value);
		}
	}
}
