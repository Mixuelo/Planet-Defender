import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.ArrayList;

public class ColliderPolygonTests 
{
    @Test
    public void testToString()
    {
        assertEquals("[(1.00,1.00), (3.00,1.00), (2.00,2.00)]", new ColliderPolygon(new ArrayList<Point>
            (Arrays.asList(new Point(1,1), new Point(3,1), new Point(2,2)))).toString());
        assertEquals("[(1.00,1.00), (3.00,1.00), (6.00,6.00)]", new ColliderPolygon(new ArrayList<Point>
            (Arrays.asList(new Point(1,1), new Point(3,1), new Point(6,6)))).toString());
        assertEquals("[(1.00,1.00), (3.00,1.00), (-2.00,-2.00)]", new ColliderPolygon(new ArrayList<Point>
            (Arrays.asList(new Point(1,1), new Point(3,1), new Point(-2,-2)))).toString());
    }

    @Test
    public void testCheckVertices()
    {
        assertThrows(IllegalArgumentException.class, () -> {
            new ColliderPolygon(new ArrayList<Point>(
            Arrays.asList(new Point(1,1), new Point(2,1), new Point(3,1), new Point(4,4))));
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new ColliderPolygon(new ArrayList<Point>(
            Arrays.asList(new Point(1,1), new Point(2,1))));
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new ColliderPolygon(new ArrayList<Point>(
            Arrays.asList(new Point(1,1))));
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new ColliderPolygon(new ArrayList<Point>(
            Arrays.asList(new Point(1,1), new Point(3,1), new Point(3,3), new Point(6,3))));
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new ColliderPolygon(new ArrayList<Point>());
        });
    }
}
