package assign08;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;

/**
 * A binary search tree (BST) implementation of the SortedSet interface. This
 * implementation allows adding, removing, and checking the presence of elements
 * efficiently using the binary search tree data structure.
 *
 * @author Phuc Do and Khang Nguyen
 * @version March 21, 2024
 */
public class BinarySearchTree<Type extends Comparable<? super Type>> implements SortedSet<Type> {
	private BinaryNode<Type> root;
	private int size;

	/**
	 * Constructs an empty binary search tree.
	 */
	public BinarySearchTree() {
		root = null;
		size = 0;
	}

	/**
	 * Adds the specified element to this binary search tree if it is not already
	 * present.
	 *
	 * @param item the element to be added to this binary search tree
	 * @return true if this binary search tree did not already contain the specified
	 *         element
	 */
	@Override
	public boolean add(Type item) {
		if (item == null)
			return false;

		if (root == null) {
			root = new BinaryNode<>(item);
			size++;
			return true;
		}

		return add(root, item);
	}

	/**
	 * Helper method to recursively add an element to the binary search tree.
	 *
	 * @param node the current node being evaluated
	 * @param item the element to be added to the binary search tree
	 * @return true if the element was added successfully, otherwise return false
	 */
	public boolean add(BinaryNode<Type> node, Type item) {
		int cmp = item.compareTo(node.element);

		// Navigating down the left side (If the item is smaller)
		if (cmp < 0) {
			if (node.leftChild == null) {
				node.leftChild = new BinaryNode<>(item);
				size++;
				return true;
			} else {
				return add(node.leftChild, item);
			}
		}

		// Navigating the right side (If the item is bigger)
		else if (cmp > 0) {
			if (node.rightChild == null) {
				node.rightChild = new BinaryNode<>(item);
				size++;
				return true;
			} else {
				return add(node.rightChild, item);
			}
		}
		return false;
	}

	/**
	 * Adds all of the elements in the specified collection to this binary search
	 * tree if they are not already present.
	 *
	 * @param items the collection containing elements to be added to this binary
	 *              search tree
	 * @return true if this binary search tree changed as a result of the call
	 * 
	 */
	@Override
	public boolean addAll(Collection<? extends Type> items) {
		if (items.isEmpty())
			return false;

		boolean finished = false;
		for (Type item : items) {
			if (this.add(item))
				finished = true;
		}

		return finished;
	}

	/**
	 * Removes all of the elements from this binary search tree.
	 */
	@Override
	public void clear() {
		this.root = null;
		this.size = 0;
	}

	/**
	 * Returns true if this binary search tree contains the specified element.
	 *
	 * @param item the element whose presence in this binary search tree is to be
	 *             tested
	 * @return true if this binary search tree contains the specified element
	 */
	@Override
	public boolean contains(Type item) {
		return contains(root, item);
	}

	/**
	 * Helper method to recursively check if an element is contained in the binary
	 * search tree.
	 *
	 * @param node the current node being evaluated
	 * @param item the element to be checked for containment
	 * @return true if the element is contained in the binary search tree
	 */
	private boolean contains(BinaryNode<Type> node, Type item) {
		if (node == null)
			return false;

		int cmp = item.compareTo(node.element);

		if (cmp < 0)
			return contains(node.leftChild, item);
		else if (cmp > 0)
			return contains(node.rightChild, item);
		return true;
	}

	/**
	 * Returns true if this binary search tree contains all of the elements in the
	 * specified collection.
	 *
	 * @param items the collection to be checked for containment in this binary
	 *              search tree
	 * @return true if this binary search tree contains all of the elements in the
	 *         specified collection
	 */
	@Override
	public boolean containsAll(Collection<? extends Type> items) {
		if (items.isEmpty())
			return false;

		for (Type item : items) {
			if (!this.contains(item))
				return false;
		}
		return true;
	}

	/**
	 * Returns the first (lowest) element currently in this binary search tree.
	 *
	 * @return the first (lowest) element in this binary search tree
	 * @throws NoSuchElementException if this binary search tree is empty
	 */
	@Override
	public Type first() throws NoSuchElementException {
		if (root == null)
			throw new NoSuchElementException("The tree is empty bro");
		return first(root);
	}

	/**
	 * Helper method to recursively find the first (lowest) element in the binary
	 * search tree.
	 *
	 * @param root the root node of the binary search tree
	 * @return the first (lowest) element in the binary search tree
	 */
	public Type first(BinaryNode<Type> root) {
		if (root.leftChild == null)
			return root.element;
		return first(root.leftChild);
	}

	/**
	 * Returns true if this binary search tree contains no elements.
	 *
	 * @return true if this binary search tree contains no elements
	 */
	@Override
	public boolean isEmpty() {
		return root == null;
	}

	/**
	 * Returns the last (highest) element currently in this binary search tree.
	 *
	 * @return the last (highest) element in this binary search tree
	 * @throws NoSuchElementException if this binary search tree is empty
	 */
	@Override
	public Type last() throws NoSuchElementException {
		if (root == null)
			throw new NoSuchElementException("The tree is empty bro");
		return last(root);
	}

	/**
	 * Helper method to recursively find the last (highest) element in the binary
	 * search tree.
	 *
	 * @param root the root node of the binary search tree
	 * @return the last (highest) element in the binary search tree
	 */
	public Type last(BinaryNode<Type> root) {
		if (root.rightChild == null)
			return root.element;

		return last(root.rightChild);
	}

	/**
	 * Removes the specified element from this binary search tree if it is present.
	 *
	 * @param item the element to be removed from this binary search tree, if
	 *             present
	 * @return true if this binary search tree contained the specified element
	 * @throws NullPointerException if the specified element is null
	 */
	@Override
	public boolean remove(Type item) {
		if (item == null || root == null)
			return false;

		int originalSize = size;
		root = remove(root, item);
		return size < originalSize;
	}

	/**
	 * Helper method to recursively remove an element from the binary search tree.
	 *
	 * @param node the current node being evaluated
	 * @param item the element to be removed from the binary search tree
	 * @return the updated node after removing the element
	 */
	public BinaryNode<Type> remove(BinaryNode<Type> node, Type item) {
		if (node == null)
			return null;

		int cmp = item.compareTo(node.element);

		// Navigating to the left if item is smaller
		if (cmp < 0) {
			node.leftChild = remove(node.leftChild, item);
		}

		// Navigating to the right if item is bigger
		else if (cmp > 0) {
			node.rightChild = remove(node.rightChild, item);
		}

		// Node to remove
		else {
			// Node to remove only has right children
			if (node.leftChild == null) {
				size--;
				return node.rightChild;
			}

			// Node to remove only has left children
			else if (node.rightChild == null) {
				size--;
				return node.leftChild;
			}

			// Node to remove has 2 children
			else {
				Type successor = first(node.rightChild);
				node.element = successor;

				node.rightChild = remove(node.rightChild, successor);
			}
		}
		return node;
	}

	/**
	 * Removes from this binary search tree all of its elements that are contained
	 * in the specified collection.
	 *
	 * @param items the collection containing elements to be removed from this
	 *              binary search tree
	 * @return true if this binary search tree changed as a result of the call
	 * @throws NullPointerException if the specified collection is null
	 */
	@Override
	public boolean removeAll(Collection<? extends Type> items) {
		if (items.isEmpty())
			return false;

		int originalSize = size;
		for (Type item : items) {
			remove(item);
		}

		return size < originalSize;
	}

	/**
	 * Returns the number of elements in this binary search tree.
	 *
	 * @return the number of elements in this binary search tree
	 */
	@Override
	public int size() {
		return this.size;
	}

	/**
	 * Returns an ArrayList containing all of the elements in this binary search
	 * tree in ascending order.
	 *
	 * @return an ArrayList containing all of the elements in this binary search
	 *         tree in ascending order
	 */
	@Override
	public ArrayList<Type> toArrayList() {
		ArrayList<Type> result = new ArrayList<>();
		toArrayList(root, result);

		return result;
	}

	/**
	 * Recursively traverses the binary search tree and adds its elements to an
	 * ArrayList in ascending order.
	 *
	 * @param node   the current node being evaluated
	 * @param result the ArrayList to store the elements of the binary search tree
	 */
	private void toArrayList(BinaryNode<Type> node, ArrayList<Type> result) {
		if (node == null)
			return;

		if (node.leftChild != null)
			toArrayList(node.leftChild, result);

		result.add(node.element);

		if (node.rightChild != null)
			toArrayList(node.rightChild, result);
	}

	/**
	 * Generates a DOT file representation of this binary search tree and writes it
	 * to the specified file.
	 *
	 * @param filename the name of the file to write the DOT representation to
	 */
	public void generateDotFile(String filename) {
		try {
			PrintWriter out = new PrintWriter(filename);
			out.println("digraph Tree {\n\tnode [shape=record]\n");

			if (root == null)
				out.println("");
			else
				out.print(root.generateDot());

			out.println("}");
			out.close();
		} catch (IOException e) {
			System.out.println(e);
		}
	}

}
