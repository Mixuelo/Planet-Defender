package Engine;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class LineTests
{
    @Test
    public void pointOrientationTest()
    {
        assertEquals(Line.orientation.CLOCKWISE, (new Line(new Point(0, 0), new Point(1, 1))).pointOrientation(new Point(1, 0)));
        assertEquals(Line.orientation.COUNTERCLOCKWISE, (new Line(new Point(0, 0), new Point(1, 1))).pointOrientation(new Point(0, 1)));
        assertEquals(Line.orientation.COLLINEAR, (new Line(new Point(0, 0), new Point(1, 1))).pointOrientation(new Point(2, 2)));
    }

    @Test
    public void pointCollinearTest()
    {
        assertTrue((new Line(new Point(0, 0), new Point(1, 1))).pointCollinear(new Point(2, 2)));
        assertFalse((new Line(new Point(0, 0), new Point(1, 1))).pointCollinear(new Point(1, 0)));
    }

    @Test
    public void testClosestPointFromPoint()
    {
        Line horizontalLine = new Line(new Point(0, 0), new Point(5, 0));
        Point testPoint1 = new Point(2, 3);
        Point expected1 = new Point(2, 0);
        Point result1 = horizontalLine.closestPointFromPoint(testPoint1);
        assertEquals(expected1.x(), result1.x(), 1e-9);
        assertEquals(expected1.y(), result1.y(), 1e-9);

        Line diagonalLine = new Line(new Point(0, 0), new Point(5, 5));
        Point testPoint3 = new Point(0, 5);
        Point expected3 = new Point(2.5, 2.5);
        Point result3 = diagonalLine.closestPointFromPoint(testPoint3);
        assertEquals(expected3.x(), result3.x(), 1e-9);
        assertEquals(expected3.y(), result3.y(), 1e-9);
    }
}
