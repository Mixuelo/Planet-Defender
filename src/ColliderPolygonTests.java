import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.ArrayList;

public class ColliderPolygonTests 
{
    @Test
    public void testToString()
    {
        assertEquals("(1.00,1.00) (3.00,1.00) (2.00,2.00)", new ColliderPolygon(new ArrayList<Point>
            (Arrays.asList(new Point(1,1), new Point(3,1), new Point(2,2)))).toString());
        assertEquals("(1.00,1.00) (3.00,1.00) (6.00,6.00)", new ColliderPolygon(new ArrayList<Point>
            (Arrays.asList(new Point(1,1), new Point(3,1), new Point(6,6)))).toString());
        assertEquals("(1.00,1.00) (3.00,1.00) (-2.00,-2.00)", new ColliderPolygon(new ArrayList<Point>
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

    @Test
    public void testVertices()
    {
        ArrayList<Point> a1 = new ArrayList<Point>
                (Arrays.asList(new Point(3,3), new Point(5,3), new Point(4,4)));
        ArrayList<Point> a2 = new ArrayList<Point>
                (Arrays.asList(new Point(3,3), new Point(5,3), new Point(8,8)));
        ArrayList<Point> a3 = new ArrayList<Point>
                (Arrays.asList(new Point(3,3), new Point(5,3), new Point(0,0)));

        assertEquals(new ColliderPolygon(a1).vertices(), a1);
        assertEquals(new ColliderPolygon(a2).vertices(), a2);
        assertEquals(new ColliderPolygon(a3).vertices(), a3);
    }

    @Test
    public void testTransformConstructor()
    {
        Transform t = new Transform(new Point(5,9), 0, 90, 2);
        ColliderPolygon c = new ColliderPolygon(t, new ArrayList<Point>(
            Arrays.asList(new Point(2,2), new Point(2,6), new Point(4,6), new Point(4,2))));
        assertEquals("(9.00,7.00) (1.00,7.00) (1.00,11.00) (9.00,11.00)", c.toString());
    }

    @Test
    public void scaleTest()
    {/*
        assertEquals(20, new ColliderPolygon(new ArrayList<Point>(
                Arrays.asList(new Point (1, 1), new Point(5, 1),
                        new Point(5, 6), new Point (1, 6)).scale())
        ));
        assertEquals(30, new ColliderPolygon(new ArrayList<Point>(
                Arrays.asList(new Point (1, 1), new Point(11, 1),
                        new Point(11, 4), new Point (1, 4)))
        ));*/
    }

    // move

    // rotate
}
