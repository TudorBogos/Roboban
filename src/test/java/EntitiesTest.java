import static org.junit.jupiter.api.Assertions.*;

import com.roboban.model.Player;
import com.roboban.model.Box;
import org.junit.jupiter.api.Test;

public class EntitiesTest {

    @Test
    void testPlayerPosition() {
        Player p = new Player(2, 2);
        assertEquals(2, p.getX());
        assertEquals(2, p.getY());

        p.setPosition(4, 5);
        assertEquals(4, p.getX());
        assertEquals(5, p.getY());
    }

    @Test
    void testBoxPosition() {
        Box b = new Box(1, 1);
        assertEquals(1, b.getX());
        assertEquals(1, b.getY());

        b.setPosition(2, 3);
        assertEquals(2, b.getX());
        assertEquals(3, b.getY());
    }
}
