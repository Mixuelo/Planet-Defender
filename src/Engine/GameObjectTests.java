package Engine;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class GameObjectTests
{
    @Test
    public void teste1()
    {
        Transform t = new Transform(new Point(1, 2), 1, 0, 1);
        GameObject go = new GameObject("Alien01", t, new ColliderCircle(t, new Point(2, 2), 3), null);
        assertEquals("Alien01\n(1.00,2.00) 1 0.00 1.00\n(1.00,2.00) 3.00", go.toString());
        go.move(new Point(1, 1), 0);
        go.rotate(90);
        go.collider().onUpdate();
        assertEquals("Alien01\n(2.00,3.00) 1 90.00 1.00\n(2.00,3.00) 3.00", go.toString());
    }

    @Test
    public void teste2()
    {
        Transform t = new Transform(new Point(0, 0), 2, 0, 1);
        GameObject go = new GameObject("Alien02", t, new ColliderCircle(t, new Point(1, 2), 3), null);
        assertEquals("Alien02\n(0.00,0.00) 2 0.00 1.00\n(0.00,0.00) 3.00", go.toString());
        go.move(new Point(3, 7), 2);
        go.scale(1);
        go.collider().onUpdate();
        assertEquals("Alien02\n(3.00,7.00) 4 0.00 2.00\n(3.00,7.00) 6.00", go.toString());
    }

    @Test
    public void teste3()
    {
        Transform t = new Transform(new Point(7, 9), 0, 0, 1);
        ArrayList<Point> points = new ArrayList<>(Arrays.asList(new Point(2, 2), new Point(2, 6), new Point(4, 6), new Point(4, 2)));
        GameObject go = new GameObject("PlayerOne", t, new ColliderPolygon(t, points), null);
        assertEquals("PlayerOne\n(7.00,9.00) 0 0.00 1.00\n(6.00,7.00) (6.00,11.00) (8.00,11.00) (8.00,7.00)", go.toString());
        go.move(new Point(3, 7), 2);
        go.rotate(90);
        go.scale(1);
        go.collider().onUpdate();
        assertEquals("PlayerOne\n(10.00,16.00) 2 90.00 2.00\n(14.00,14.00) (6.00,14.00) (6.00,18.00) (14.00,18.00)", go.toString());
    }

    @Test
    public void checkCollisionTest2()
    {
        Transform t;
        GameObject g1 = new GameObject("SmallC",t = new Transform(new Point(0, 4), 0, 0, 1), new ColliderCircle(t, new Point(0, 4), 1), null);
        GameObject g2 = new GameObject("BigC",t = new Transform(new Point(0, 0), 0, 0, 1), new ColliderCircle(t, new Point(0, 0), 2), null);
        GameObject g3 = new GameObject("Tri",t = new Transform(new Point(5.5, 2), 0, 0, 1), new ColliderPolygon(t, new ArrayList<>(
                Arrays.asList(new Point(6, 1), new Point(5, 2), new Point(5, 3)))
        ), null);

        for(int i = 0; i < 3; i++)
        {
            g1.move(new Point(0, -1), 1);
            g1.rotate(0);
            g1.scale(0);

            g2.move(new Point(0, 1), 0);
            g2.rotate(0);
            g2.scale(0);

            g3.move(new Point(-2, 0), 0);
            g3.rotate(0);
            g3.scale(0);
        }

        g1.collider().onUpdate();
        g2.collider().onUpdate();
        g3.collider().onUpdate();

        assertTrue(g2.checkCollision(g3));
        assertFalse(g2.checkCollision(g1));
        assertFalse(g1.checkCollision(g3));
    }

    @Test
    public void checkCollisionTest7()
    {
        Transform t;
        GameObject g1 = new GameObject("ball",t = new Transform(new Point(2, 6), -5, 0, 1), new ColliderCircle(t, new Point(0, 0), 1), null);
        GameObject g2 = new GameObject("upSquare",t = new Transform(new Point(5, 6), -5, 0, 1), new ColliderPolygon(t, new ArrayList<>(
                Arrays.asList(new Point(4, 5), new Point(4, 7), new Point(6, 7), new Point(6, 5)))
        ), null);
        GameObject g3 = new GameObject("dnSquare",t = new Transform(new Point(2, 2), 3, 0, 1), new ColliderPolygon(t, new ArrayList<>(
                Arrays.asList(new Point(1, 1), new Point(1, 3), new Point(3, 3), new Point(3, 1)))
        ), null);
        GameObject g4 = new GameObject("rect",t = new Transform(new Point(8.5, 2.5), 3, 0, 1), new ColliderPolygon(t, new ArrayList<>(
                Arrays.asList(new Point(7, 2), new Point(7, 3), new Point(10, 3), new Point(10, 2)))
        ), null);

        for(int i = 0; i < 3; i++)
        {
            g1.move(new Point(1, -1), 0);
            g1.rotate(-30);
            g1.scale(0);

            g2.move(new Point(0, -1), 0);
            g2.rotate(0);
            g2.scale(0);

            g3.move(new Point(1, 0), 0);
            g3.rotate(0);
            g3.scale(0);

            g4.move(new Point(-1, 0), 0);
            g4.rotate(0);
            g4.scale(0);
        }

        g1.collider().onUpdate();
        g2.collider().onUpdate();
        g3.collider().onUpdate();
        g4.collider().onUpdate();

        assertTrue(g1.checkCollision(g2));
        assertFalse(g1.checkCollision(g3));
        assertFalse(g1.checkCollision(g4));

        assertTrue(g2.checkCollision(g1));
        assertFalse(g2.checkCollision(g3));
        assertFalse(g2.checkCollision(g4));

        assertTrue(g3.checkCollision(g4));
        assertFalse(g3.checkCollision(g1));
        assertFalse(g3.checkCollision(g2));

        assertTrue(g4.checkCollision(g3));
        assertFalse(g4.checkCollision(g1));
        assertFalse(g4.checkCollision(g2));
    }
}
