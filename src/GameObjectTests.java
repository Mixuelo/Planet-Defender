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
        GameObject go = new GameObject("Alien01", t, new ColliderCircle(t, new Point(2, 2), 3));
        assertEquals("Alien01\n(1.00,2.00) 1 0.00 1.00\n(1.00,2.00) 3.00", go.toString());
        go.move(new Point(1, 1), 0);
        go.rotate(90);
        assertEquals("Alien01\n(2.00,3.00) 1 90.00 1.00\n(2.00,3.00) 3.00", go.toString());
    }

    @Test
    public void teste2()
    {
        Transform t = new Transform(new Point(0, 0), 2, 0, 1);
        GameObject go = new GameObject("Alien02", t, new ColliderCircle(t, new Point(1, 2), 3));
        assertEquals("Alien02\n(0.00,0.00) 2 0.00 1.00\n(0.00,0.00) 3.00", go.toString());
        go.move(new Point(3, 7), 2);
        go.scale(1);
        assertEquals("Alien02\n(3.00,7.00) 4 0.00 2.00\n(3.00,7.00) 6.00", go.toString());
    }

    @Test
    public void teste3()
    {
        Transform t = new Transform(new Point(7, 9), 0, 0, 1);
        ArrayList<Point> points = new ArrayList<>(Arrays.asList(new Point(2, 2), new Point(2, 6), new Point(4, 6), new Point(4, 2)));
        GameObject go = new GameObject("PlayerOne", t, new ColliderPolygon(t, points));
        assertEquals("PlayerOne\n(7.00,9.00) 0 0.00 1.00\n(6.00,7.00) (6.00,11.00) (8.00,11.00) (8.00,7.00)", go.toString());
        go.move(new Point(3, 7), 2);
        go.rotate(90);
        go.scale(1);
        assertEquals("PlayerOne\n(10.00,16.00) 2 90.00 2.00\n(14.00,14.00) (6.00,14.00) (6.00,18.00) (14.00,18.00)", go.toString());
    }

    @Test
    public void checkCollisionTest2()
    {
        GameObject g1 = new GameObject("SmallC", new Transform(new Point(0, 4), 0, 0, 1), new ColliderCircle(new Transform(new Point(0, 4), 0, 0, 1), new Point(0, 4), 1));
        GameObject g2 = new GameObject("BigC", new Transform(new Point(0, 0), 0, 0, 1), new ColliderCircle(new Transform(new Point(0, 0), 0, 0, 1), new Point(0, 0), 2));
        GameObject g3 = new GameObject("Tri", new Transform(new Point(5.5, 2), 0, 0, 1), new ColliderPolygon(new Transform(new Point(5.5, 2), 0, 0, 1), new ArrayList<>(
                Arrays.asList(new Point(6, 1), new Point(5, 2), new Point(5, 3)))
        ));

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

        assertTrue(g2.checkColision(g3));
        assertFalse(g2.checkColision(g1));
        assertFalse(g1.checkColision(g3));
    }

    @Test
    public void checkCollisionTest7()
    {
        GameObject g1 = new GameObject("ball", new Transform(new Point(2, 6), -5, 0, 1), new ColliderCircle(new Transform(new Point(2, 6), -5, 0, 1), new Point(0, 0), 1));
        GameObject g2 = new GameObject("upSquare", new Transform(new Point(5, 6), -5, 0, 1), new ColliderPolygon(new Transform(new Point(5, 6), -5, 0, 1), new ArrayList<>(
                Arrays.asList(new Point(4, 5), new Point(4, 7), new Point(6, 7), new Point(6, 5)))
        ));
        GameObject g3 = new GameObject("dnSquare", new Transform(new Point(2, 2), 3, 0, 1), new ColliderPolygon(new Transform(new Point(2, 2), 3, 0, 1), new ArrayList<>(
                Arrays.asList(new Point(1, 1), new Point(1, 3), new Point(3, 3), new Point(3, 1)))
        ));
        GameObject g4 = new GameObject("rect", new Transform(new Point(8.5, 2.5), 3, 0, 1), new ColliderPolygon(new Transform(new Point(8.5, 2.5), 3, 0, 1), new ArrayList<>(
                Arrays.asList(new Point(7, 2), new Point(7, 3), new Point(10, 3), new Point(10, 2)))
        ));

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

        assertTrue(g1.checkColision(g2));
        assertFalse(g1.checkColision(g3));
        assertFalse(g1.checkColision(g4));

        assertTrue(g2.checkColision(g1));
        assertFalse(g2.checkColision(g3));
        assertFalse(g2.checkColision(g4));

        assertTrue(g3.checkColision(g4));
        assertFalse(g3.checkColision(g1));
        assertFalse(g3.checkColision(g2));

        assertTrue(g4.checkColision(g3));
        assertFalse(g4.checkColision(g1));
        assertFalse(g4.checkColision(g2));
    }
}
