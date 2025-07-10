package ru.summerpractice;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Game {
    private final String secretWord;
    private final Set<Character> guessedLetters = new HashSet<>();
    private final Player player = new Player();
    private final HangmanDrawer drawer = new HangmanDrawer();
    private final Scanner scanner = new Scanner(System.in);

    public Game(String secretWord) {
        this.secretWord = secretWord.toLowerCase();
    }

    public void start() {
        System.out.println("Добро пожаловать в игру Виселица!");

        while (true) {
            drawer.draw(player.getMistakes());
            displayWord();
            System.out.print("Введите букву: ");
            String input = scanner.nextLine().toLowerCase();

            if (input.length() != 1 || !Character.isLetter(input.charAt(0))) {
                System.out.println("Введите одну букву!");
                continue;
            }

            char guess = input.charAt(0);
            if (guessedLetters.contains(guess)) {
                System.out.println("Вы уже вводили эту букву.");
                continue;
            }

            guessedLetters.add(guess);

            if (secretWord.indexOf(guess) >= 0) {
                System.out.println("Угадали!");
                if (isWordGuessed()) {
                    System.out.println("Поздравляем! Вы угадали слово: " + secretWord);
                    break;
                }
            } else {
                player.incrementMistakes();
                System.out.println("Неправильно!");

                if (player.isHanged()) {
                    drawer.draw(player.getMistakes());
                    System.out.println("Вы проиграли. Загаданное слово: " + secretWord);
                    break;
                }
            }
        }
    }

    private void displayWord() {
        for (char c : secretWord.toCharArray()) {
            if (guessedLetters.contains(c)) {
                System.out.print(c + " ");
            } else {
                System.out.print("_ ");
            }
        }
        System.out.println();
    }

    private boolean isWordGuessed() {
        for (char c : secretWord.toCharArray()) {
            if (!guessedLetters.contains(c)) {
                return false;
            }
        }
        return true;
    }
}
