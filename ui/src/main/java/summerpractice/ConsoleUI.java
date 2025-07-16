package summerpractice;

import java.util.Scanner;

public class ConsoleUI {
    private final Scanner scanner;

    public ConsoleUI() {
        this.scanner = new Scanner(System.in);
    }

    public ConsoleUI(Scanner scanner) {
        this.scanner = scanner;
    }

    public void run() {
        String resourceFile = selectDifficulty();
        WordProvider wordProvider = new WordProvider(resourceFile);
        Game game = new Game(wordProvider.getRandomWord());

        System.out.println("Добро пожаловать в Виселицу!");

        playGame(game);
        showGameResult(game);
    }

    public String selectDifficulty() {
        System.out.println("Выберите уровень сложности:");
        System.out.println("1. Лёгкий");
        System.out.println("2. Средний");
        System.out.println("3. Сложный");

        while (true) {
            System.out.print("Введите номер (1-3): ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    System.out.println("Выбран лёгкий уровень.");
                    return "easy.txt";
                case "2":
                    System.out.println("Выбран средний уровень.");
                    return "medium.txt";
                case "3":
                    System.out.println("Выбран сложный уровень.");
                    return "hard.txt";
                default:
                    System.out.println("Некорректный ввод. Попробуйте снова.");
            }
        }
    }

    public void playGame(Game game) {
        while (!game.isOver()) {
            System.out.println(game.getHangmanDrawing());
            System.out.println("Слово: " + game.getCurrentWordState());

            char guess = promptLetter();
            if (game.alreadyGuessed(guess)) {
                System.out.println("Вы уже вводили эту букву.");
                continue;
            }

            boolean correct = game.makeGuess(guess);
            System.out.println(correct ? "Верно!" : "Неверно!");
        }
    }

    public char promptLetter() {
        while (true) {
            System.out.print("Введите букву: ");
            String input = scanner.nextLine().trim().toLowerCase();

            if (input.length() == 1 && Character.isLetter(input.charAt(0))) {
                return input.charAt(0);
            }

            System.out.println("Ошибка: введите одну букву.");
        }
    }

    public void showGameResult(Game game) {
        System.out.println(game.getHangmanDrawing());

        if (game.isWon()) {
            System.out.println("Поздравляем! Вы угадали слово: " + game.getSecretWord());
        } else {
            System.out.println("Вы проиграли. Загаданное слово было: " + game.getSecretWord());
        }
    }
}
