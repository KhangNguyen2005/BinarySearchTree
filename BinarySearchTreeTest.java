package assign08;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BinarySearchTreeTest {
	BinarySearchTree<Integer> bst;
	BinarySearchTree<Integer> singleBst;
	BinarySearchTree<Integer> balancedBst;

	@BeforeEach
	void setUp() throws Exception {
		BinarySearchTree<Integer> bst = new BinarySearchTree<>();
		this.bst = bst;
		// Add some elements to the tree
		bst.add(10);
		bst.add(5);
		bst.add(15);
		bst.add(3);
		bst.add(7);
		bst.add(12);
		bst.add(18);

		BinarySearchTree<Integer> singleBst = new BinarySearchTree<>();
		this.singleBst = singleBst;
		this.singleBst.add(10);

	}

// normal test cases
	@Test
	void testContains() {
		assertTrue(bst.contains(10)); // Tree contains 10
		assertTrue(bst.contains(5)); // Tree contains 5
		assertTrue(bst.contains(15)); // Tree contains 15
		assertTrue(bst.contains(3)); // Tree contains 3
		assertTrue(bst.contains(7)); // Tree contains 7
		assertTrue(bst.contains(12)); // Tree contains 12
		assertTrue(bst.contains(18)); // Tree contains 18

		assertFalse(bst.contains(0)); // Tree does not contain 0
		assertFalse(bst.contains(20)); // Tree does not contain 20
		assertFalse(bst.contains(8)); // Tree does not contain 8
		assertFalse(bst.contains(13)); // Tree does not contain 13
	}

	@Test
	void testContainsAll() {
		List<Integer> collection1 = Arrays.asList(10, 5, 15); // All elements exist in the tree
		List<Integer> collection2 = Arrays.asList(10, 5, 20); // Some elements exist in the tree
		List<Integer> collection3 = Arrays.asList(11, 20, 30); // No elements exist in the tree
		List<Integer> emptyCollection = Arrays.asList(); // No elements exist in the tree

		// Test containsAll method
		assertTrue(bst.containsAll(collection1)); // All elements in collection1 are in the tree
		assertFalse(bst.containsAll(collection2)); // Not all elements in collection2 are in the tree
		assertFalse(bst.containsAll(collection3)); // No elements in collection3 are in the tree
		assertFalse(bst.containsAll(emptyCollection)); // No elements in collection3 are in the tree

	}

	@Test
	void testClear() {
		bst.clear();
		assertTrue(bst.isEmpty(), "Clear method does not work as expected");
	}

	@Test
	void testFirst() {
		assertEquals(3, bst.first(), "Expected return the smallest number in the set");
	}

	@Test
	void testLast() {
		assertEquals(18, bst.last(), "Expected return the largest number in the set");
	}

	// focus on add
	@Test
	void testAddNavigateLeft() {
		bst.clear();
		// Add elements to the left side of the tree
		assertTrue(bst.add(10)); // Adding the root
		assertTrue(bst.add(5)); // Adding a left child of 10
		assertTrue(bst.add(3)); // Adding a left child of 5
		assertTrue(bst.add(7)); // Adding a right child of 5

		// Verify if elements are correctly positioned
		assertTrue(bst.contains(10)); // Root should be 10
		assertTrue(bst.contains(5)); // Left child of 10 should be 5
		assertTrue(bst.contains(3)); // Left child of 5 should be 3
		assertTrue(bst.contains(7)); // Right child of 5 should be 7

		assertFalse(bst.contains(15)); // 15 shouldn't be in the tree
		assertFalse(bst.contains(4)); // 4 shouldn't be in the tree
		assertFalse(bst.contains(8)); // 8 shouldn't be in the tree
	}

	@Test
	void testAddNavigateRight() {
		bst.clear();
		// Add elements to the left side of the tree
		assertTrue(bst.add(10)); // Adding the root
		assertTrue(bst.add(15)); // Adding a right child of 10
		assertTrue(bst.add(12)); // Adding a left child of 15
		assertTrue(bst.add(16)); // Adding a right child of 15

		// Verify if elements are correctly positioned
		assertTrue(bst.contains(10)); // Root should be 10
		assertTrue(bst.contains(15)); // right child of 10 should be 15
		assertTrue(bst.contains(12)); // Left child of 15 should be 12
		assertTrue(bst.contains(16)); // Right child of 15 should be 16

		assertFalse(bst.contains(9)); // 9 shouldn't be in the tree
		assertFalse(bst.contains(4)); // 4 shouldn't be in the tree
		assertFalse(bst.contains(8)); // 8 shouldn't be in the tree
	}

	@Test
	void testAddFalse() {
		bst.clear();
		assertFalse(bst.add(null));
		assertTrue(bst.isEmpty()); // The tree should still be empty

	}

	@Test
	void testAddAll() {
		bst.clear();
		List<Integer> elementsToAdd = Arrays.asList(10, 5, 15, 3, 7);
		assertTrue(bst.addAll(elementsToAdd));
		assertTrue(bst.contains(10)); // 10 should be in the tree
		assertTrue(bst.contains(5)); // 5 should be in the tree
		assertTrue(bst.contains(15)); // 15 should be in the tree
		assertTrue(bst.contains(3)); // 3 should be in the tree
		assertTrue(bst.contains(7)); // 7 should be in the tree

		// Ensure that other elements are not added to the tree
		assertFalse(bst.contains(20)); // 20 should not be in the tree
		assertFalse(bst.contains(1)); // 1 should not be in the tree
		assertFalse(bst.contains(12)); // 12 should not be in the tree
	}

	// focus on remove
	@Test
	void testRemoveOneElement() {
		bst.clear();
		bst.add(10);
		bst.remove(10);
		assertTrue(bst.isEmpty(), "10 has not been removed");
	}

	@Test
	void testRemoveNoChildren() {
		bst.clear();
		bst.add(10);
		bst.add(5);
		bst.add(15);
		bst.add(3);
		bst.add(7);

		// Remove a leaf node (node containing 3)
		assertTrue(bst.remove(3)); // Removing a leaf node should return true

		// Verify if the leaf node has been removed and other nodes are still present
		assertFalse(bst.contains(3)); // 3 should be removed from the tree
		assertTrue(bst.contains(10)); // 10 should still be in the tree
		assertTrue(bst.contains(5)); // 5 should still be in the tree
		assertTrue(bst.contains(15)); // 15 should still be in the tree
		assertTrue(bst.contains(7)); // 7 should still be in the tree
	}

	@Test
	void testRemoveOnlyOneChild() {
		bst.clear();
		// Add elements to the tree
		bst.add(10);
		bst.add(5);
		bst.add(15);
		bst.add(3);
		bst.add(7);
		bst.add(12);
		bst.add(18);

		// Remove a node with one child (node containing 15)
		assertTrue(bst.remove(15)); // Removing a node with one child should return true

		// Verify if the node with one child has been removed and other nodes are still
		// present
		assertFalse(bst.contains(15)); // 15 should be removed from the tree
		assertTrue(bst.contains(10)); // 10 should still be in the tree
		assertTrue(bst.contains(5)); // 5 should still be in the tree
		assertTrue(bst.contains(3)); // 3 should still be in the tree
		assertTrue(bst.contains(7)); // 7 should still be in the tree
		assertTrue(bst.contains(12)); // 12 should still be in the tree
		assertTrue(bst.contains(18)); // 18 should still be in the tree
	}

	@Test
	void testRemoveTwoChildren() {
		bst.clear();
		// Add elements to the tree
		bst.add(10);
		bst.add(5);
		bst.add(15);
		bst.add(3);
		bst.add(7);
		bst.add(12);
		bst.add(18);

		// Remove a node with one child (node containing 15)
		assertTrue(bst.remove(10)); // Removing a node with one child should return true

		// Verify if the node with one child has been removed and other nodes are still
		// present
		assertFalse(bst.contains(10)); // 15 should be removed from the tree
		assertTrue(bst.contains(15)); // 10 should still be in the tree
		assertTrue(bst.contains(5)); // 5 should still be in the tree
		assertTrue(bst.contains(3)); // 3 should still be in the tree
		assertTrue(bst.contains(7)); // 7 should still be in the tree
		assertTrue(bst.contains(12)); // 12 should still be in the tree
		assertTrue(bst.contains(18)); // 18 should still be in the tree
	}

	@Test
	void testRemoveAll() {
		List<Integer> elementsToRemove = Arrays.asList(5, 15, 7);
		List<Integer> emptyE = Arrays.asList();

		// Remove elements from the tree using removeAll method
		assertTrue(bst.removeAll(elementsToRemove)); // Removing elements should return true
		assertFalse(bst.removeAll(emptyE));
		// Verify if the elements are removed from the tree
		assertFalse(bst.contains(5)); // 5 should be removed from the tree
		assertFalse(bst.contains(15)); // 15 should be removed from the tree
		assertFalse(bst.contains(7)); // 7 should be removed from the tree
		assertTrue(bst.contains(10)); // 10 should still be in the tree
		assertTrue(bst.contains(3)); // 3 should still be in the tree
		assertTrue(bst.contains(12)); // 12 should still be in the tree
		assertTrue(bst.contains(18)); // 18 should still be in the tree
	}

	// focus on toArray
	@Test
	void testToArr() {
		bst.clear();
		// Add elements to the tree
		bst.add(10);
		bst.add(5);
		bst.add(15);
		bst.add(3);
		bst.add(7);
		bst.add(12);
		bst.add(18);

		ArrayList<Integer> arrayList = bst.toArrayList();

		// Create a List containing the same elements for comparison
		List<Integer> expectedList = Arrays.asList(3, 5, 7, 10, 12, 15, 18);

		// Verify if the ArrayList contains all elements from the tree and if the order
		// is correct
		assertEquals(expectedList, arrayList);
		assertEquals(7, arrayList.size());
	}

	// focus on Throws
	@Test
	void testFirstThrows() {
		bst.clear();

		assertThrows(NoSuchElementException.class, () -> bst.first());
	}

	@Test
	void testLastThrows() {
		bst.clear();

		assertThrows(NoSuchElementException.class, () -> bst.last());
	}

	// focus on edge (Large data set, duplication, ... )
	@Test
	void testIsEmpty() {
		// It starts with a 2-item tree, removes items, then calls isEmpty(). The result
		// should be true,
		bst.clear();
		bst.add(10);
		bst.add(12);

		bst.remove(10);
		bst.remove(12);
		assertTrue(bst.isEmpty(), "The result should be true");
	}

	@Test
	public void testAddDuplication() {
		bst.clear();
		assertTrue(bst.add(10)); // Adding the first element should return true
		assertTrue(bst.add(5)); // Adding a different element should return true

		// Adding a duplicate element should return false
		assertFalse(bst.add(10));
		assertFalse(bst.add(5));

		// Verify that duplicates are not added and the size remains unchanged
		assertEquals(2, bst.size()); // Size should remain as 2
		assertTrue(bst.contains(10)); // 10 should still be in the tree
		assertTrue(bst.contains(5)); // 5 should still be in the tree
	}

	@Test
	void testLargeProblemSize() {
		bst.clear();
		int problemSize = 1000;

		for (int i = 0; i < problemSize; i++) {
			assertTrue(bst.add(i));
		}

		// Verify that the size of the tree is correct
		assertEquals(problemSize, bst.size());

		// Verify that all elements are present in the tree
		for (int i = 0; i < problemSize; i++) {
			assertTrue(bst.contains(i));
		}
	}

	// EmptyTree
	@Test
	public void testAddOperation() {
		BinarySearchTree<Integer> bst = new BinarySearchTree<>();
		assertTrue(bst.add(10)); // Adding an element to an empty tree should return true
		assertFalse(bst.add(10)); // Adding a duplicate element should return false
		assertFalse(bst.add(null)); // Adding null should return false (if applicable)
	}

	@Test
	public void testRemoveOperation() {
		BinarySearchTree<Integer> bst = new BinarySearchTree<>();
		assertFalse(bst.remove(10)); // Removing an element from an empty tree should return false
		assertFalse(bst.remove(null)); // Removing null should return false (if applicable)
	}

	@Test
	public void testContainsOperation() {
		BinarySearchTree<Integer> bst = new BinarySearchTree<>();
		assertFalse(bst.contains(10)); // Searching for an element in an empty tree should return false
		assertFalse(bst.contains(null)); // Searching for null should return false (if applicable)
	}

	@Test
	public void testSizeOperation() {
		BinarySearchTree<Integer> bst = new BinarySearchTree<>();
		assertEquals(0, bst.size()); // Size of an empty tree should be 0
	}

	@Test
	public void testClearOperation() {
		BinarySearchTree<Integer> bst = new BinarySearchTree<>();
		bst.clear(); // Clearing an empty tree should have no effect
		assertEquals(0, bst.size()); // Size of the tree should still be 0
	}

	@Test
	public void testFirstOperation() {
		BinarySearchTree<Integer> bst = new BinarySearchTree<>();
		assertThrows(NoSuchElementException.class, () -> bst.first());
	}

	@Test
	public void testLastOperation() {
		BinarySearchTree<Integer> bst = new BinarySearchTree<>();
		assertThrows(NoSuchElementException.class, () -> bst.last());
	}

	@Test
	public void testToArrayListOperation() {
		BinarySearchTree<Integer> bst = new BinarySearchTree<>();
		List<Integer> result = bst.toArrayList(); // Converting an empty tree to an ArrayList should return an empty
													// list
		assertTrue(result.isEmpty());
	}

	@Test
	public void testGenerateDotFileOperation() {
		bst.generateDotFile("bst.dot"); // Generating a dot file for an empty tree should have no effect or
		File file = new File("bst.dot");
		assertTrue(file.exists());

	}

	@Test
	public void testGenerateDotFileOperationRootNull() {
		bst.clear();
		bst.generateDotFile("bst.dot");

		// Check if the DOT file exists
		File file = new File("bst.dot");
		assertTrue(file.exists());

		// Read the content of the generated DOT file
		String dotFileContent = null;
		try {
			dotFileContent = new String(Files.readAllBytes(file.toPath()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Assert that the DOT file content contains an empty line
		assertTrue(dotFileContent.contains("\n"), "Generated DOT file content does not contain an empty line");

	}

	@Test
	public void testAddAllOperation() {
		BinarySearchTree<Integer> bst = new BinarySearchTree<>();
		List<Integer> collection = Arrays.asList(10, 20, 30);
		assertTrue(bst.addAll(collection)); // Adding a collection of elements to an empty tree should return true
	}

	@Test
	public void testRemoveAllOperation() {
		BinarySearchTree<Integer> bst = new BinarySearchTree<>();
		List<Integer> collection = Arrays.asList(10, 20, 30);
		assertFalse(bst.removeAll(collection)); // Removing all elements from an empty tree should return false
	}

	@Test
	public void testContainsAllOperation() {
		BinarySearchTree<Integer> bst = new BinarySearchTree<>();
		List<Integer> collection = Arrays.asList(10, 20, 30);
		assertFalse(bst.containsAll(collection)); // Searching for a collection of elements in an empty tree should
													// return false
	}

	// SingleNodeTree
	@Test
	public void testSingleNodeAddOperation() {
		assertFalse(singleBst.add(10)); // Adding a duplicate element should return false
		assertFalse(singleBst.add(null)); // Adding null should return false (if applicable)
	}

	@Test
	public void testSingleNodeRemoveOperation() {
		assertTrue(singleBst.remove(10));
		assertNull(singleBst.remove(null, 10)); // Removing from a null node should return null
	}

	@Test
	public void testSingleNodeContainsOperation() {
		assertTrue(singleBst.contains(10));

	}

	@Test
	public void testSingleNodeSizeOperation() {
		singleBst.clear();
		assertEquals(0, singleBst.size()); // Size of an empty tree should be 0
	}

	@Test
	public void testSingleNodeClearOperation() {
		singleBst.clear(); // Clearing an empty tree should have no effect
		assertEquals(0, singleBst.size()); // Size of the tree should still be 0
	}

	@Test
	public void testSingleNodeToArrayListOperation() {
		singleBst.clear();
		List<Integer> result = singleBst.toArrayList(); // Converting an empty tree to an ArrayList should return an
														// empty list
		assertTrue(result.isEmpty());
	}

	@Test
	public void testSingleNodeGenerateDotFileOperation() {
		singleBst.generateDotFile("empty_tree.dot"); // Generating a dot file for an empty tree should have no effect or
														// produce an empty file
		// You can add additional assertions to check the file content or existence if
		// needed
	}

	@Test
	public void testSingleNodeAddAllOperation() {
		List<Integer> collection = Arrays.asList(10, 20, 30);
		List<Integer> emptyCollection = Arrays.asList();

		assertTrue(singleBst.addAll(collection)); // Adding a collection of elements to an empty tree should return true
		assertFalse(singleBst.addAll(emptyCollection));
	}

	@Test
	public void testSingleNodeContainsAllOperation() {
		List<Integer> collection = Arrays.asList(10, 20, 30);
		assertFalse(singleBst.containsAll(collection));// the single-node tree contains only the value 10, so it does
														// not contain all elements from the collection [10, 20, 30].
		// this test work the above does not
		singleBst.clear();
		List<Integer> emptyCollection = Arrays.asList();
		assertFalse(singleBst.containsAll(emptyCollection));

	}

	// DegenerateTree
	@Test
	void testDegenerateTreeAddOperation() {
		BinarySearchTree<Integer> degenerateBst = new BinarySearchTree<>();
		degenerateBst.add(1); // Adding the root element

		// Add elements to create a degenerate tree
		for (int i = 2; i <= 5; i++) {
			assertTrue(degenerateBst.add(i)); // Add elements in ascending order
		}

		// Verify the structure of the degenerate tree

		assertEquals(Integer.valueOf(1), degenerateBst.first());
		assertEquals(Integer.valueOf(5), degenerateBst.last());
		assertEquals(5, degenerateBst.size());

		// Check if the tree contains specific elements
		assertTrue(degenerateBst.contains(3));
		assertTrue(degenerateBst.contains(5));

		// Check if the tree does not contain specific elements
		assertFalse(degenerateBst.contains(0));
		assertFalse(degenerateBst.contains(6));

	}

	@Test
	void testDegenerateTreeRemoveOperation() {
		BinarySearchTree<Integer> degenerateBst = new BinarySearchTree<>();
		degenerateBst.add(1); // Adding the root element

		// Add elements to create a degenerate tree
		for (int i = 2; i <= 5; i++) {
			assertTrue(degenerateBst.add(i)); // Add elements in ascending order
		}

		degenerateBst.remove(5);
		assertEquals(4, degenerateBst.size());
		assertEquals(Integer.valueOf(1), degenerateBst.first());
		assertEquals(Integer.valueOf(4), degenerateBst.last());
		assertEquals(4, degenerateBst.size());
		assertTrue(degenerateBst.contains(3));
		assertFalse(degenerateBst.contains(5));
	}

// GradeScope Test
	@Test
	void testContainsandContainsAll10Items() {
		bst.clear();
		bst.add(5);
		bst.add(3);
		bst.add(7);
		bst.add(2);
		bst.add(4);
		bst.add(6);
		bst.add(8);
		bst.add(1);
		bst.add(9);
		bst.add(10);

		assertEquals(10, bst.size());
		assertTrue(bst.contains(5));
		assertTrue(bst.contains(3));
		assertTrue(bst.contains(7));
		assertTrue(bst.contains(2));
		assertTrue(bst.contains(4));
		assertTrue(bst.contains(6));
		assertTrue(bst.contains(8));
		assertTrue(bst.contains(1));
		assertTrue(bst.contains(9));
		assertTrue(bst.contains(10));
		List<Integer> numbers = Arrays.asList(5, 3, 7, 2, 4, 6, 8, 1, 9, 10);
		assertTrue(bst.containsAll(numbers));

		for (int num : Arrays.asList(5, 3, 7, 2, 4, 6, 8, 1, 9, 10)) {
			assertTrue(bst.contains(num), "Contains method failed for " + num);
		}

		// Test containsAll method with the provided list
		assertTrue(bst.containsAll(Arrays.asList(5, 3, 7, 2, 4, 6, 8, 1, 9, 10)),
				"ContainsAll method failed for the provided list");
		assertEquals(10, bst.size());

		assertTrue(bst.contains(5)); // Change the number if needed

		// Verify that contains returns false for a non-existing number
		assertFalse(bst.contains(19)); // Change the number if needed
	}

	@Test
	void testRemove10Items() {
		BinarySearchTree<Integer> bst = new BinarySearchTree<>();
		// Insert some but not all numbers from 1 to 10 into the BST
		for (int i = 1; i <= 9; i += 2) { // Insert odd numbers only
			bst.add(i);
		}

		List<Integer> collection = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

		// Verify that containsAll returns false because not all items in the collection
		// are in the BST
		assertFalse(bst.containsAll(collection), "ContainsAll method incorrectly returned true");
	}

	@Test
	void testRemoveAll10Items() {
		bst.clear();
		bst.add(5);
		bst.add(3);
		bst.add(7);
		bst.add(2);
		bst.add(4);
		bst.add(6);
		bst.add(8);
		bst.add(1);
		bst.add(9);
		bst.add(10);
		bst.removeAll(Arrays.asList(9, 10));
		assertEquals(8, bst.size());
	}

	@Test
	void testRemoveAllGen() {
		List<Integer> listWithDuplicates = new ArrayList<>();
		listWithDuplicates.add(1);
		listWithDuplicates.add(2);
		listWithDuplicates.add(3);
		listWithDuplicates.add(2);
		listWithDuplicates.add(4);
		assertTrue(listWithDuplicates.removeAll(List.of(2)));
		assertFalse(listWithDuplicates.contains(2)); // All occurrences of 2 should be removed
		assertEquals(3, listWithDuplicates.size()); // Size should be reduced by the number of occurrences of 2

		// Test removing all items from a list with duplicates
		List<Integer> listWithAllDuplicates = new ArrayList<>();
		listWithAllDuplicates.add(1);
		listWithAllDuplicates.add(1);
		listWithAllDuplicates.add(1);
		assertTrue(listWithAllDuplicates.removeAll(List.of(1)));
		assertTrue(listWithAllDuplicates.isEmpty()); // All items should be removed

		// Test removing from a list with no items to remove
		List<Integer> listNoItemsToRemove = new ArrayList<>();
		listNoItemsToRemove.add(1);
		listNoItemsToRemove.add(2);
		listNoItemsToRemove.add(3);
		assertFalse(listNoItemsToRemove.removeAll(List.of(4, 5, 6)));
		assertEquals(3, listNoItemsToRemove.size()); // Size should remain the same

		// Test removing from an empty list
		List<Integer> emptyList = new ArrayList<>();
		assertFalse(emptyList.removeAll(List.of(1, 2, 3)));
		assertTrue(emptyList.isEmpty()); // Collection should remain empty
	}
}
