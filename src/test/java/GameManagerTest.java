import static org.junit.jupiter.api.Assertions.*;

import com.roboban.singleton.GameManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GameManagerTest {

    @BeforeEach
    void resetGameManager() {
        GameManager.getInstance().setCurrentLevel(1);
        GameManager.getInstance().addScore(-GameManager.getInstance().getScore());
        GameManager.getInstance().resetMoves();
    }

    @Test
    void testSingletonInstance() {
        GameManager gm1 = GameManager.getInstance();
        GameManager gm2 = GameManager.getInstance();
        assertSame(gm1, gm2);
    }

    @Test
    void testLevelAndScore() {
        GameManager manager = GameManager.getInstance();
        manager.setCurrentLevel(2);
        manager.addScore(10);

        assertEquals(2, manager.getCurrentLevel());
        assertEquals(10, manager.getScore());
    }

    @Test
    void testMovesCount() {
        GameManager manager = GameManager.getInstance();
        manager.resetMoves();
        manager.incrementMoves();
        manager.incrementMoves();
        assertEquals(2, manager.getMovesCount());
    }
}
