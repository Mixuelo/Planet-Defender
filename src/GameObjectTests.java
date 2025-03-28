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
        assertEquals("Alien01\n(2.00,3.00) 2 90.00 1.00\n(1.00,2.00) 3.00", go.toString());
    }

    @Test
    public void teste2()
    {
        Transform t = new Transform(new Point(0, 0), 2, 0, 1);
        GameObject go = new GameObject("Alien02", t, new ColliderCircle(t, new Point(1, 2), 3));
        assertEquals("Alien02\n(3.00,7.00) 2 45.60 2.00\n(3.00,7.00) 6.00", go.toString());
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
        assertEquals("PlayerOne\n(5.00,9.00) 0 90.00 2.00\n(9.00,7.00) (1.00,7.00) (1.00,11.00) (9.00,11.00)", go.toString());
        go.move(new Point(3, 7), 2);
        go.rotate(90);
        go.scale(1);
        assertEquals("PlayerOne\n(10.00,16.00) 2 90.00 2.00\n(14.00,14.00) (6.00,14.00) (6.00,18.00) (14.00,18.00)", go.toString());
    }
}
