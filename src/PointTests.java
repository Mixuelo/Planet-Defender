import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class PointTests 
{
    @Test
    public void testToString()
    {
        assertEquals("(1.00,2.00)", new Point(1, 2).toString());
        assertEquals("(-1.00,-2.00)", new Point(-1, -2).toString());
        assertEquals("(4.50,9.00)", new Point(4.5, 9).toString());
        assertEquals("(1.23,2.00)", new Point(1.234, 2).toString());
        assertEquals("(1.00,-9.88)", new Point(1, -9.876543).toString());
    }

    @Test
    public void testSubNew()
    {
        assertEquals(new Point(4, 2), (new Point(5, 4).subNew(new Point(1, 2))));
    }

    @Test
    public void testAddNew()
    {
        assertEquals(new Point(4, 6), (new Point(1, 2)).addNew(new Point(3, 4)));
    }

    @Test
    public void testMultNew()
    {
        assertEquals(new Point(8, 15), (new Point(2.0, 3.0)).multNew(new Point(4.0, 5.0)));
        assertEquals(new Point(10, 12), (new Point(5, 6)).multNew(2));
    }

    @Test
    public void testSubThis()
    {
        Point p1 = new Point(5, 5);
        Point p2 = new Point(1, 2);
        p1.subThis(p2);
        assertEquals(4, p1.x());
        assertEquals(3, p1.y());
    }

    @Test
    public void testAddThis()
    {
        Point p1 = new Point(1, 2);
        Point p2 = new Point(3, 4);
        p1.addThis(p2);
        assertEquals(4, p1.x());
        assertEquals(6, p1.y());
    }

    @Test
    public void testMultThisWithPoint()
    {
        Point p1 = new Point(2, 3);
        Point p2 = new Point(4, 5);
        p1.multThis(p2);
        assertEquals(8, p1.x());
        assertEquals(15, p1.y());

        p1.multThis(10);
        assertEquals(80, p1.x());
        assertEquals(150, p1.y());
    }

    @Test
    public void testRotateThis()
    {
        Point p1 = new Point(0,1);
        p1.rotateThis(new Point(0,0), 90);
        assertEquals("(-1.00,0.00)", p1.toString());
    }

    @Test
    public void testScaleThis()
    {
        Point p1 = new Point(0,1);
        p1.scaleThis(new Point(0,0), 3);
        assertEquals("(0.00,3.00)", p1.toString());
    }
}
