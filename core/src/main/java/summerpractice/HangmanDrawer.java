package summerpractice;

public class HangmanDrawer {
    private static final String[] stages = {
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

    public String getDrawing(int mistakes) {
        return stages[Math.min(mistakes, stages.length - 1)];
    }
}
