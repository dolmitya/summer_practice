package summerpractice;

import java.util.Scanner;

public class ConsoleUI {
    private final Scanner scanner = new Scanner(System.in);

    public void run() {
        WordProvider wordProvider = new WordProvider("words.txt");
        Game game = new Game(wordProvider.getRandomWord());

        System.out.println("Добро пожаловать в Виселицу!");

        while (!game.isOver()) {
            System.out.println(game.getHangmanDrawing());
            System.out.println("Слово: " + game.getCurrentWordState());

            System.out.print("Введите букву: ");
            String input = scanner.nextLine().trim().toLowerCase();

            if (input.length() != 1 || !Character.isLetter(input.charAt(0))) {
                System.out.println("Ошибка: введите одну букву.");
                continue;
            }

            char guess = input.charAt(0);

            if (game.alreadyGuessed(guess)) {
                System.out.println("Вы уже вводили эту букву.");
                continue;
            }

            boolean correct = game.makeGuess(guess);

            if (correct) {
                System.out.println("Верно!");
            } else {
                System.out.println("Неверно!");
            }
        }

        System.out.println(game.getHangmanDrawing());

        if (game.isWon()) {
            System.out.println("Поздравляем! Вы угадали слово: " + game.getSecretWord());
        } else {
            System.out.println("Вы проиграли. Загаданное слово было: " + game.getSecretWord());
        }
    }
}
