

import static org.junit.jupiter.api.Assertions.*;

import com.roboban.builder.LevelBuilder;
import com.roboban.model.Board;
import com.roboban.model.Player;
import com.roboban.model.Box;
import org.junit.jupiter.api.Test;

public class LevelBuilderTest {

    @Test
    void testBuildBasicBoard() {
        Board board = new LevelBuilder()
                .setDimensions(5, 5)
                .addPlayer(2, 2)
                .addBox(3, 3)
                .build();

        assertNotNull(board);
        assertNotNull(board.getTileAt(4, 4)); // Ultimul tile e valid
        assertEquals(2, board.getPlayer().getX());
        assertEquals(2, board.getPlayer().getY());
        assertEquals(1, board.getBoxes().size());
    }
}
