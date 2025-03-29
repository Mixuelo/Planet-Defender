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
}
