package ru.summerpractice;

public class HangmanDrawer {
    public void draw(int stage) {
        String[] hangman = {
                """
             +---+
             |   |
                 |
                 |
                 |
                 |
            =========
            """,
                """
             +---+
             |   |
             O   |
                 |
                 |
                 |
            =========
            """,
                """
             +---+
             |   |
             O   |
             |   |
                 |
                 |
            =========
            """,
                """
             +---+
             |   |
             O   |
            /|   |
                 |
                 |
            =========
            """,
                """
             +---+
             |   |
             O   |
            /|\\  |
                 |
                 |
            =========
            """,
                """
             +---+
             |   |
             O   |
            /|\\  |
            /    |
                 |
            =========
            """,
                """
             +---+
             |   |
             O   |
            /|\\  |
            / \\  |
                 |
            =========
            """
        };

        System.out.println(hangman[Math.min(stage, hangman.length - 1)]);
    }
}
