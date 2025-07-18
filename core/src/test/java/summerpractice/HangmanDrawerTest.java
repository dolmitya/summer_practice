package summerpractice;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HangmanDrawerTest {

    @Test
    void testStagesWithinBounds() {
        HangmanDrawer drawer = new HangmanDrawer();

        for (int i = 0; i <= 6; i++) {
            String drawing = drawer.getDrawing(i);
            assertNotNull(drawing, "Drawing should not be null for i = " + i);
            assertTrue(drawing.contains("+---+"), "Drawing should contain scaffold for i = " + i);
        }
    }

    @Test
    void testStageBeyondBoundsReturnsLastStage() {
        HangmanDrawer drawer = new HangmanDrawer();

        String lastStage = drawer.getDrawing(6);

        // Проверяем, что на больших значениях возвращается последнее
        assertEquals(lastStage, drawer.getDrawing(7));
        assertEquals(lastStage, drawer.getDrawing(100));
        assertEquals(lastStage, drawer.getDrawing(Integer.MAX_VALUE));
    }

    @Test
    void testFirstStage() {
        HangmanDrawer drawer = new HangmanDrawer();
        String stage = drawer.getDrawing(0);

        assertTrue(stage.contains("+---+"));
        assertFalse(stage.contains("O"), "No head in first stage");
    }

    @Test
    void testFinalStage() {
        HangmanDrawer drawer = new HangmanDrawer();
        String stage = drawer.getDrawing(6);

        assertTrue(stage.contains("/ \\"), "Legs should be present in final stage");
    }
}
