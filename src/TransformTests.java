import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class TransformTests
{
    @Test
    public void testToString()
    {
        assertEquals("(1.50,2.50) 3 30.00 2.00", (new Transform(new Point(1.5, 2.5), 3, 30.0, 2.0)).toString());
    }

    @Test
    public void testMove()
    {
        Transform t = new Transform(new Point(1, 2), 1, 0, 1);

        t.move(new Point(3, 4), 2);
        assertEquals(new Point(4, 6), t.position());
        assertEquals(3, t.layer());

        t.move(new Point(-1, -1), -1);
        assertEquals(new Point(3, 5), t.position());
        assertEquals(2, t.layer());
    }

    @Test
    public void testRotate()
    {
        Transform t = new Transform(new Point(0, 0), 0, 45.0, 1.0);

        t.rotate(30.0);
        assertEquals(75.0, t.angle());

        t.rotate(-20.0);
        assertEquals(55.0, t.angle());
    }

    @Test
    public void testScale()
    {
        Transform t = new Transform(new Point(0, 0), 0, 0.0, 1.0);

        t.scale(0.5);
        assertEquals(1.5, t.scale(), 0.0001);

        t.scale(-0.7);
        assertEquals(0.8, t.scale(), 0.0001);
    }
}
