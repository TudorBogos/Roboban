import static org.junit.jupiter.api.Assertions.*;

import com.roboban.model.Board;
import com.roboban.model.Tile;
import com.roboban.model.Player;
import com.roboban.model.Box;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BoardTest {

    private Board board;

    @BeforeEach
    void setUp() {
        board = new Board(5, 5);
    }

    @Test
    void testBoardDimensions() {
        // Verificăm dacă board-ul are dimensiunea corectă
        assertNotNull(board.getTileAt(0, 0));
        assertNull(board.getTileAt(5, 5)); // În afara limitelor
    }

    @Test
    void testSetPlayer() {
        Player p = new Player(2, 2);
        board.setPlayer(p);
        assertEquals(p, board.getPlayer());
    }

    @Test
    void testAddBox() {
        Box box = new Box(3, 3);
        board.addBox(box);
        assertTrue(board.getBoxes().contains(box));
    }

    @Test
    void testTileProperties() {
        Tile tile = board.getTileAt(0, 0);
        assertTrue(tile.isWalkable());
        assertFalse(tile.isGoalTile());
    }
}
