package summerpractice;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.LinkedHashSet;
import java.util.Scanner;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ConsoleUITest {

    private final PrintStream originalOut = System.out;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    static class StubWordProvider extends WordProvider {
        private final String fixedWord;

        public StubWordProvider(String word) {
            super(null);
            this.fixedWord = word;
        }

        @Override
        public String getRandomWord() {
            return fixedWord;
        }
    }

    static class StubGame extends Game {
        private final String secretWord;
        private final LinkedHashSet<Character> guessed = new LinkedHashSet<>();
        private final StringBuilder currentWordState;
        private boolean isOver;
        private boolean isWon;
        private int wrongGuesses;

        public StubGame(String secretWord) {
            super(secretWord);
            this.secretWord = secretWord;
            this.currentWordState = new StringBuilder("_".repeat(secretWord.length()));
            this.isOver = false;
            this.isWon = false;
            this.wrongGuesses = 0;
        }

        @Override
        public boolean isOver() {
            return isOver;
        }

        @Override
        public boolean isWon() {
            return isWon;
        }

        @Override
        public String getSecretWord() {
            return secretWord;
        }

        @Override
        public String getHangmanDrawing() {
            return "[рисунок виселицы]";
        }

        @Override
        public String getCurrentWordState() {
            return currentWordState.toString();
        }

        @Override
        public boolean alreadyGuessed(char c) {
            return guessed.contains(c);
        }

        @Override
        public boolean makeGuess(char c) {
            guessed.add(c);
            if (secretWord.indexOf(c) >= 0) {
                for (int i = 0; i < secretWord.length(); i++) {
                    if (secretWord.charAt(i) == c) {
                        currentWordState.setCharAt(i, c);
                    }
                }
                if (currentWordState.indexOf("_") == -1) {
                    isWon = true;
                    isOver = true;
                }
                return true;
            } else {
                wrongGuesses++;
                if (wrongGuesses >= 6) {
                    isWon = false;
                    isOver = true;
                }
                return false;
            }
        }
    }

    private void runGameWithInputs(String input, String secretWord) {
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));
        ConsoleUI ui = new ConsoleUI(scanner);
        StubGame game = new StubGame(secretWord);

        System.out.println("Добро пожаловать в Виселицу!");
        ui.playGame(game);
        ui.showGameResult(game);
    }

    @Test
    void testEasyDifficultySelected() {
        String input = "1\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));
        ConsoleUI ui = new ConsoleUI(scanner);

        String resource = ui.selectDifficulty();
        assertEquals("easy.txt", resource);
    }

    @Test
    void testInvalidDifficultyInputThenValid() {
        String input = "5\nabc\n2\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));

        ConsoleUI ui = new ConsoleUI(scanner);
        String resource = ui.selectDifficulty();

        assertEquals("medium.txt", resource);
    }

    @Test
    void testGamePlayAndWin() {
        runGameWithInputs("к\nо\nт\n", "кот");

        String output = outContent.toString();
        assertTrue(output.contains("Поздравляем! Вы угадали слово: кот"));
    }

    @Test
    void testGamePlayAndLose() {
        runGameWithInputs("а\nб\nв\nг\nд\nе\n", "кот");

        String output = outContent.toString();
        assertTrue(output.contains("Вы проиграли. Загаданное слово было: кот"));
    }

    @Test
    void testRepeatedLetterInput() {
        runGameWithInputs("к\nк\nо\nт\n", "кот");

        String output = outContent.toString();
        assertTrue(output.contains("Вы уже вводили эту букву."));
        assertTrue(output.contains("Поздравляем! Вы угадали слово: кот"));
    }

    @Test
    void testInvalidLetterInput() {
        runGameWithInputs("\n1\nаб\nк\nо\nт\n", "кот");

        String output = outContent.toString();
        assertTrue(output.contains("Ошибка: введите одну букву."));
        assertTrue(output.contains("Поздравляем! Вы угадали слово: кот"));
    }
}
