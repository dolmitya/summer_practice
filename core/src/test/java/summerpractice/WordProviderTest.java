package summerpractice;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class WordProviderTest {

    @Test
    void testLoadWordsFromFile() {
        WordProvider provider = new WordProvider("easy.txt");
        String word = provider.getRandomWord();
        assertNotNull(word, "Random word should not be null");
        assertFalse(word.isBlank(), "Random word should not be blank");
    }

    @Test
    void testGetRandomWordReturnsWordFromList() {
        WordProvider provider = new WordProvider("test_words.txt");
        boolean foundExpected = false;
        for (int i = 0; i < 100; i++) {
            String word = provider.getRandomWord();
            if ("apple".equals(word) || "banana".equals(word)) {
                foundExpected = true;
                break;
            }
        }
        assertTrue(foundExpected, "Random word should be from the list of known words");
    }
}
