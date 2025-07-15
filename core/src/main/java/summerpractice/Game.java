package summerpractice;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashSet;
import java.util.Set;

public class Game {
    private static final Logger logger = LogManager.getLogger(Game.class);

    private final String secretWord;
    private final Set<Character> guessedLetters = new HashSet<>();
    private final Player player = new Player();
    private final HangmanDrawer drawer = new HangmanDrawer();

    public Game(String secretWord) {
        this.secretWord = secretWord.toLowerCase();
        logger.info("Выбрано слово: {}", secretWord);
    }

    public String getCurrentWordState() {
        StringBuilder display = new StringBuilder();
        for (char c : secretWord.toCharArray()) {
            display.append(guessedLetters.contains(c) ? c + " " : "_ ");
        }
        return display.toString().trim();
    }

    public String getHangmanDrawing() {
        return drawer.getDrawing(player.getMistakes());
    }

    public boolean makeGuess(char letter) {
        letter = Character.toLowerCase(letter);

        if (guessedLetters.contains(letter)) {
            logger.warn("Повтор буквы: {}", letter);
            return false;
        }

        guessedLetters.add(letter);

        if (secretWord.indexOf(letter) >= 0) {
            logger.info("Угадана буква: {}", letter);
            return true;
        } else {
            logger.info("Промах: {}", letter);
            player.incrementMistakes();
            return false;
        }
    }

    public boolean alreadyGuessed(char letter) {
        return guessedLetters.contains(Character.toLowerCase(letter));
    }

    public boolean isOver() {
        return isWon() || player.isHanged();
    }

    public boolean isWon() {
        for (char c : secretWord.toCharArray()) {
            if (!guessedLetters.contains(c)) return false;
        }
        return true;
    }

    public boolean isLost() {
        return player.isHanged();
    }

    public String getSecretWord() {
        return secretWord;
    }
}