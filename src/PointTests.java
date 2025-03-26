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
}
