package summerpractice;

import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ConsoleUITestMock {

    @Test
    void testSelectDifficultyEasy() {
        Scanner scanner = mock(Scanner.class);
        when(scanner.nextLine()).thenReturn("1");

        ConsoleUI ui = new ConsoleUI(scanner);
        String file = ui.selectDifficulty();

        assertEquals("easy.txt", file);
    }

    @Test
    void testSelectDifficultyWrongThenMedium() {
        Scanner scanner = mock(Scanner.class);
        when(scanner.nextLine()).thenReturn("5", "2");

        ConsoleUI ui = new ConsoleUI(scanner);
        String file = ui.selectDifficulty();

        assertEquals("medium.txt", file);
    }

    @Test
    void testPlayGameWithCorrectGuess() {
        Scanner scanner = mock(Scanner.class);
        when(scanner.nextLine()).thenReturn("a");

        Game mockGame = mock(Game.class);
        when(mockGame.isOver()).thenReturn(false, true);
        when(mockGame.alreadyGuessed('a')).thenReturn(false);
        when(mockGame.makeGuess('a')).thenReturn(true);
        when(mockGame.getHangmanDrawing()).thenReturn("drawing");
        when(mockGame.getCurrentWordState()).thenReturn("_ _ _");

        ConsoleUI ui = new ConsoleUI(scanner);
        ui.playGame(mockGame);

        verify(mockGame).alreadyGuessed('a');
        verify(mockGame).makeGuess('a');
        verify(mockGame, atLeastOnce()).getHangmanDrawing();
        verify(mockGame, atLeastOnce()).getCurrentWordState();
    }

    @Test
    void testShowGameResultWin() {
        Game mockGame = mock(Game.class);
        when(mockGame.isWon()).thenReturn(true);
        when(mockGame.getHangmanDrawing()).thenReturn("drawing");
        when(mockGame.getSecretWord()).thenReturn("apple");

        ConsoleUI ui = new ConsoleUI(new Scanner(System.in));
        ui.showGameResult(mockGame);

        verify(mockGame).isWon();
        verify(mockGame).getSecretWord();
    }

    @Test
    void testShowGameResultLose() {
        Game mockGame = mock(Game.class);
        when(mockGame.isWon()).thenReturn(false);
        when(mockGame.getHangmanDrawing()).thenReturn("drawing");
        when(mockGame.getSecretWord()).thenReturn("banana");

        ConsoleUI ui = new ConsoleUI(new Scanner(System.in));
        ui.showGameResult(mockGame);

        verify(mockGame).isWon();
        verify(mockGame).getSecretWord();
    }
}