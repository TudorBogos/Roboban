import static org.junit.jupiter.api.Assertions.*;

import com.roboban.model.*;
import com.roboban.mediator.GameMediator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GameMediatorTest {

    private Board board;
    private GameMediator mediator;

    @BeforeEach
    void setUp() {
        board = new Board(5, 5);
        board.setPlayer(new Player(2, 2));
        board.addBox(new Box(3, 2));
        mediator = new GameMediator(board);
    }

    @Test
    void testMediatorMovePlayerRight() {
        mediator.movePlayer(Direction.RIGHT);

        // Player ar trebui să se miște la (3,2), împinge cutia la (4,2)
        assertEquals(3, board.getPlayer().getX());
        assertEquals(2, board.getPlayer().getY());

        Box box = board.getBoxes().get(0);
        assertEquals(4, box.getX());
        assertEquals(2, box.getY());
    }

    @Test
    void testMediatorBlockedByBox() {
        // Facem tile-ul (4,2) ne-walkable
        board.getTileAt(4, 2).setWalkable(false);
        mediator.movePlayer(Direction.RIGHT);

        // Nu se mută nimic
        assertEquals(2, board.getPlayer().getX());
        assertEquals(2, board.getPlayer().getY());

        Box box = board.getBoxes().get(0);
        assertEquals(3, box.getX());
        assertEquals(2, box.getY());
    }
}
