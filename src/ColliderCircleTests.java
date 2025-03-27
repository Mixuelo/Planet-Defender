import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ColliderCircleTests 
{
    @Test
    public void testToString()
    {
        assertEquals("(0.00,0.00) 2.00", new ColliderCircle(new Point(0,0), 2).toString());
        assertEquals("(-1.00,4.00) 2.30", new ColliderCircle(new Point(-1,4), 2.3).toString());
        assertEquals("(1.23,5.68) 9.88", new ColliderCircle(new Point(1.234,5.678), 9.876).toString());
        assertEquals("(0.00,0.00) 0.00", new ColliderCircle(new Point(0,0), 0.001).toString());

    }

    @Test
    public void testCheckRadius()
    {
        assertThrows(IllegalArgumentException.class , () -> {
            new ColliderCircle(new Point(0, 0), 0);
        });
        assertThrows(IllegalArgumentException.class , () -> {
            new ColliderCircle(new Point(0, 0), -1);
        });
        assertThrows(IllegalArgumentException.class , () -> {
            new ColliderCircle(new Point(0, 0), -123);
        });
    }
}
