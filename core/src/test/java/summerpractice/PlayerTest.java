package summerpractice;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    void testInitialMistakesIsZero() {
        Player player = new Player();
        assertEquals(0, player.getMistakes(), "Mistakes count should be 0 at initialization");
        assertFalse(player.isHanged(), "Player should not be hanged at the start");
    }

    @Test
    void testIncrementMistakes() {
        Player player = new Player();
        player.incrementMistakes();
        assertEquals(1, player.getMistakes(), "After one mistake, mistakes count should be 1");
        assertFalse(player.isHanged(), "Player should not be hanged after 1 mistake");
    }

    @Test
    void testIsHangedAtLimit() {
        Player player = new Player();
        for (int i = 0; i < 6; i++) {
            player.incrementMistakes();
        }
        assertEquals(6, player.getMistakes(), "Mistakes count should be 6");
        assertTrue(player.isHanged(), "Player should be hanged at 6 mistakes");
    }

    @Test
    void testIsHangedOverLimit() {
        Player player = new Player();
        for (int i = 0; i < 10; i++) {
            player.incrementMistakes();
        }
        assertTrue(player.isHanged(), "Player should be hanged if mistakes exceed 6");
        assertTrue(player.getMistakes() > 6, "Mistakes count should be greater than 6");
    }
}
