package summerpractice;

public class Player {
    private static final int MAX_MISTAKES = 6;
    private int mistakes = 0;

    public void incrementMistakes() {
        mistakes++;
    }

    public int getMistakes() {
        return mistakes;
    }

    public boolean isHanged() {
        return mistakes >= MAX_MISTAKES;
    }
}
