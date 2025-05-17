package Engine;

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
    {
        ColliderPolygon cp = new ColliderPolygon(new Transform(new Point(7, 9), 0, 0, 1), new ArrayList<>(
                Arrays.asList(new Point(2, 2), new Point(2, 6), new Point(4, 6), new Point(4, 2))));
        cp.scale(1);
        assertEquals("(5.00,5.00) (5.00,13.00) (9.00,13.00) (9.00,5.00)", cp.toString());

    }

    @Test
    public void moveTest()
    {
        ColliderPolygon cp = new ColliderPolygon(new Transform(new Point(7, 9), 0, 0, 1), new ArrayList<>(
                Arrays.asList(new Point(2, 2), new Point(2, 6), new Point(4, 6), new Point(4, 2))));
        cp.move(new Point(3, 7));
        assertEquals("(9.00,14.00) (9.00,18.00) (11.00,18.00) (11.00,14.00)", cp.toString());

    }

    @Test
    public void rotateTest()
    {
        ColliderPolygon cp = new ColliderPolygon(new Transform(new Point(7, 9), 0, 0, 1), new ArrayList<>(
                Arrays.asList(new Point(2, 2), new Point(2, 6), new Point(4, 6), new Point(4, 2))));
        cp.rotate(90);
        assertEquals("(9.00,8.00) (5.00,8.00) (5.00,10.00) (9.00,10.00)", cp.toString());
    }

    @Test
    public void checkCollisionTest()
    {
        ColliderPolygon p1 = new ColliderPolygon(new ArrayList<>(Arrays.asList(new Point(1, 1), new Point(3, 3), new Point(2, 4))));
        ColliderPolygon p2 = new ColliderPolygon(new ArrayList<>(Arrays.asList(new Point(1, 2), new Point(3, 2), new Point(3, 4), new Point(1, 4))));

        assertTrue(p1.checkCollision(new ColliderCircle(new Point(2, 2), 3)));
        assertTrue(p2.checkCollision(p1));
    }

    @Test
    public void checkCollisionCircleTest()
    {
        ColliderPolygon p1 = new ColliderPolygon(new ArrayList<>(Arrays.asList(new Point(1, 1), new Point(3, 3), new Point(2, 4))));
        ColliderPolygon p2 = new ColliderPolygon(new ArrayList<>(Arrays.asList(new Point(1, 2), new Point(3, 2), new Point(3, 4), new Point(1, 4))));

        assertTrue(p1.checkCollisionCircle(new ColliderCircle(new Point(2, 2), 3)));
        assertFalse(p2.checkCollisionCircle(new ColliderCircle(new Point(11, 12), 2)));

    }

    @Test
    public void onUpdateTest()
    {
        ColliderPolygon polygon = new ColliderPolygon(new ArrayList<>(Arrays.asList(
                new Point(0.0, 0.0), new Point(2.0, 0.0),
                new Point(2.0, 2.0), new Point(0.0, 2.0)))
        );
        Transform transform = new Transform(new Point(5.0, 5.0), 1, 45.0, 2.0);

        polygon.transform(transform);
        polygon.onUpdate();

        assertEquals(transform.position().x(), polygon.centroid().x());
        assertEquals(transform.position().y(), polygon.centroid().y());
        assertEquals(transform.scale(), polygon.scale);
        assertEquals(transform.angle(), polygon.angle);

        assertEquals(5.00, polygon.vertices().get(0).x(), 0.01);
        assertEquals(2.17, polygon.vertices().get(0).y(), 0.01);
        assertEquals(7.83, polygon.vertices().get(1).x(), 0.01);
        assertEquals(5.00, polygon.vertices().get(1).y(), 0.01);
        assertEquals(5.00, polygon.vertices().get(2).x(), 0.01);
        assertEquals(7.83, polygon.vertices().get(2).y(), 0.01);
        assertEquals(2.17, polygon.vertices().get(3).x(), 0.01);
        assertEquals(5.00, polygon.vertices().get(3).y(), 0.01);
    }
}
