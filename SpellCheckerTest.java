package assign08;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Dictionary;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SpellCheckerTest {

	@Test
	void testAddToDictionary() {
		SpellChecker spellChecker = new SpellChecker();
		spellChecker.addToDictionary("apple");
		spellChecker.addToDictionary("banana");

		assertTrue(spellChecker.spellCheck(new File("test_document.txt")).isEmpty(),
				"No misspelled words should be found after adding to the dictionary");
	}

	@Test
	void testRemoveFromDictionary() {
		// Prepare test file(s)
		File dict = new File("src/assign08/dictionary.txt");

		// Create an instance of SpellChecker
		SpellChecker spellChecker = new SpellChecker(dict);

		// Remove a word from the dictionary
		spellChecker.removeFromDictionary("sily");
		spellChecker.removeFromDictionary("end");

		// Spell-check a word known to be in the dictionary (not removed)
		// to indirectly verify that the removed word is absent
		File testFile = new File("src/assign08/testfile.txt");
		// Spell-check the document file
		List<String> misspelledWords = spellChecker.spellCheck(testFile);

		assertFalse(misspelledWords.contains("sily"), "The word 'sily' should be removed from the dictionary");
		assertFalse(misspelledWords.contains("end"), "The word 'sily' should be removed from the dictionary");

	}

	@Test
	void testSpellCheckWithFile() {
		// Prepare test file(s)
		File testFile = new File("src/assign08/input.txt");
		File dict = new File("src/assign08/dictionary.txt");

		// Create an instance of SpellChecker
		SpellChecker spellChecker = new SpellChecker(dict);
		List<String> misspelledWords = spellChecker.spellCheck(testFile);
		assertTrue(misspelledWords.contains("exmaple"));
		assertTrue(misspelledWords.contains("mispelled"));
		assertTrue(misspelledWords.contains("sily"));
	}

	@Test
	void testSpellCheck() {
		// Prepare list of words
		List<String> words = new ArrayList<>();
		words.add("apple");
		words.add("banana");
		words.add("orange");

		// Create SpellChecker instance with list of words
		SpellChecker spellChecker = new SpellChecker(words);

		List<String> misWords = new ArrayList<>();
		words.add("applex");
		words.add("banna");
		words.add("orarnge");
		SpellChecker spellCheckerM = new SpellChecker(misWords);
		assertFalse(spellChecker.equals(spellCheckerM));

	}

}
