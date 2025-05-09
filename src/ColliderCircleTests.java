import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

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

    @Test
    public void moveTest()
    {
        ColliderCircle cc = new ColliderCircle(new Transform(new Point(0, 0), 2, 0, 1), new Point(1, 2), 3);
        cc.move(new Point(3, 7));
        assertEquals("(3.00,7.00) 3.00", cc.toString());
    }

    @Test
    public void scaleTest()
    {
        ColliderCircle cc = new ColliderCircle(new Transform(new Point(0, 0), 2, 0, 1), new Point(1, 2), 3);
        cc.scale(1);
        assertEquals("(0.00,0.00) 6.00", cc.toString());
    }

    @Test
    public void checkColisionTest()
    {
        ColliderCircle c1 = new ColliderCircle(new Point(2, 2), 3);
        ColliderCircle c2 = new ColliderCircle(new Point(1, 3), 2);

        assertTrue(c1.checkCollision(new ColliderPolygon(new ArrayList<>(Arrays.asList(new Point(1, 1), new Point(3, 3), new Point(2, 4))))));
        assertFalse(c2.checkCollision(new ColliderCircle(new Point(10, 10), 1)));
    }

    @Test
    public void checkCollisionCircleTest()
    {
        ColliderCircle c1 = new ColliderCircle(new Point(2, 2), 3);
        assertFalse(c1.checkCollisionCircle(new ColliderCircle(new Point(10, 10), 1)));
        assertTrue(c1.checkCollisionCircle(new ColliderCircle(new Point (1, 1), 2)));
    }

    @Test
    public void circleOnUpdateTest()
    {
        ColliderCircle circle = new ColliderCircle(new Point(10, 10), 5.0);
        Transform transform = new Transform(new Point(15, 20), 1, 45.0, 2.0);

        circle.transform(transform);
        circle.onUpdate();

        assertEquals(transform.position().x(), circle.centroid().x());
        assertEquals(transform.position().y(), circle.centroid().y());
        assertEquals(10.0, circle.radius());
        assertEquals(transform.scale(), circle.scale);
        assertEquals(transform.angle(), circle.angle);
    }
}
