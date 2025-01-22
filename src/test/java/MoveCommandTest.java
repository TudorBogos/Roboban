import static org.junit.jupiter.api.Assertions.*;

import com.roboban.model.*;
import com.roboban.command.MoveCommand;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MoveCommandTest {

    private Board board;
    private Player player;

    @BeforeEach
    void setUp() {
        board = new Board(5, 5);
        player = new Player(2, 2);
        board.setPlayer(player);
    }

    @Test
    void testMovePlayerNoObstacle() {
        // Player la (2,2). Ne mișcăm în sus
        MoveCommand moveUp = new MoveCommand(board, player, Direction.UP);
        moveUp.execute();

        // Ar trebui să fie (2,1)
        assertEquals(2, player.getX());
        assertEquals(1, player.getY());
    }

    @Test
    void testPushBoxIfPossible() {
        // Adăugăm o cutie în fața player-ului, la (2,1)
        Box box = new Box(2, 1);
        board.addBox(box);

        // Mișcare în sus
        MoveCommand moveUp = new MoveCommand(board, player, Direction.UP);
        moveUp.execute();

        // Playerul mutat la (2,1), cutia împinsă la (2,0) dacă e valid
        assertEquals(2, player.getX());
        assertEquals(1, player.getY());
        assertEquals(2, box.getX());
        assertEquals(0, box.getY());
    }

    @Test
    void testBlockedByBoxIfNoSpace() {
        // Adăugăm o cutie în fața player-ului la (2,1)
        // și un obstacol (tile ne-walkable) la (2,0)
        Box box = new Box(2, 1);
        board.addBox(box);

        // Facem tile-ul (2,0) ne-walkable
        Tile tile = board.getTileAt(2, 0);
        tile.setWalkable(false);

        // Mișcare în sus
        MoveCommand moveUp = new MoveCommand(board, player, Direction.UP);
        moveUp.execute();

        // Player rămâne la (2,2), cutia rămâne la (2,1)
        assertEquals(2, player.getX());
        assertEquals(2, player.getY());
        assertEquals(2, box.getX());
        assertEquals(1, box.getY());
    }
}
