package summerpractice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    private Game game;

    @BeforeEach
    void setUp() {
        game = new Game("кот");
    }

    @Test
    void testInitialWordState() {
        assertEquals("_ _ _", game.getCurrentWordState());
    }

    @Test
    void testCorrectGuess() {
        boolean result = game.makeGuess('к');
        assertTrue(result);
        assertTrue(game.getCurrentWordState().startsWith("к"));
    }

    @Test
    void testIncorrectGuess() {
        boolean result = game.makeGuess('р');
        assertFalse(result);
        assertEquals("_ _ _", game.getCurrentWordState());
    }

    @Test
    void testAlreadyGuessedLetter() {
        game.makeGuess('о');
        assertTrue(game.alreadyGuessed('о'));
        assertFalse(game.makeGuess('о'));
    }

    @Test
    void testGameWon() {
        game.makeGuess('к');
        game.makeGuess('о');
        game.makeGuess('т');
        assertTrue(game.isWon());
        assertTrue(game.isOver());
        assertFalse(game.isLost());
    }

    @Test
    void testGameLost() {
        // делаем 6 неправильных попыток
        char[] wrongGuesses = {'а', 'б', 'в', 'г', 'д', 'е'};
        for (char c : wrongGuesses) {
            game.makeGuess(c);
        }
        assertTrue(game.isLost());
        assertTrue(game.isOver());
        assertFalse(game.isWon());
    }

    @Test
    void testSecretWord() {
        assertEquals("кот", game.getSecretWord());
    }

    @Test
    void testMixCorrectAndIncorrectGuesses() {
        game.makeGuess('о');
        game.makeGuess('е'); // ошибка
        game.makeGuess('к');
        assertEquals("к о _", game.getCurrentWordState());
        assertFalse(game.isOver());
    }
}
