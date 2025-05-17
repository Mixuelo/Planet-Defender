package Engine;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class LineSegmentTests
{
    @Test
    public void testToString()
    {
        assertEquals("[(1.00,2.00),(3.00,4.00)]", new LineSegment(new Point(1,2), new Point(3,4)).toString());
        assertEquals("[(1.00,-4.00),(-3.00,4.00)]", new LineSegment(new Point(1,-4), new Point(-3,4)).toString());
    }

    @Test
    public void testCheckEquals()
    {
        assertThrows(IllegalArgumentException.class , () -> {
            new LineSegment(new Point(1,2), new Point(1,2));
        });
        assertThrows(IllegalArgumentException.class , () -> {
            new LineSegment(new Point(-4,2), new Point(-4,2));
        });
        assertThrows(IllegalArgumentException.class , () -> {
            new LineSegment(new Point(6,-5), new Point(6,-5));
        });
    }

	@Test
	public void testPointOnSegment()
	{
		assertFalse(new LineSegment(new Point(1,1), new Point(5,1)).pointOnSegment(new Point(6,1)));
		assertFalse(new LineSegment(new Point(1,1), new Point(5,5)).pointOnSegment(new Point(6,6)));
		assertFalse(new LineSegment(new Point(1,5), new Point(5,1)).pointOnSegment(new Point(0,6)));
		assertFalse(new LineSegment(new Point(1,1), new Point(5,3)).pointOnSegment(new Point(7,4)));
		assertTrue(new LineSegment(new Point(1,1), new Point(5,1)).pointOnSegment(new Point(3,1)));
		assertTrue(new LineSegment(new Point(1,1), new Point(5,5)).pointOnSegment(new Point(2,2)));
		assertTrue(new LineSegment(new Point(1,5), new Point(5,1)).pointOnSegment(new Point(2,4)));
		assertTrue(new LineSegment(new Point(1,1), new Point(5,3)).pointOnSegment(new Point(3,2)));
	}

	@Test
	public void testPointCollinear()
	{
		assertFalse(new LineSegment(new Point(1,1), new Point(5,1)).pointCollinear(new Point(6,2)));
		assertFalse(new LineSegment(new Point(1,1), new Point(5,5)).pointCollinear(new Point(4,6)));
		assertFalse(new LineSegment(new Point(1,5), new Point(5,1)).pointCollinear(new Point(1,1)));
		assertFalse(new LineSegment(new Point(1,1), new Point(5,3)).pointCollinear(new Point(3,4)));
		assertTrue(new LineSegment(new Point(1,1), new Point(5,1)).pointCollinear(new Point(6,1)));
		assertTrue(new LineSegment(new Point(1,1), new Point(5,5)).pointCollinear(new Point(6,6)));
		assertTrue(new LineSegment(new Point(1,5), new Point(5,1)).pointCollinear(new Point(0,6)));
		assertTrue(new LineSegment(new Point(1,1), new Point(5,3)).pointCollinear(new Point(7,4)));
	}

	@Test
	public void testSegmentIntersect()
	{
		assertFalse(new LineSegment(new Point(1,1), new Point(5,5)).segmentIntersect(new LineSegment(new Point(1,2), new Point(5,6))));
		assertFalse(new LineSegment(new Point(1,1), new Point(5,1)).segmentIntersect(new LineSegment(new Point(1,2), new Point(7,1))));
		assertFalse(new LineSegment(new Point(1,1), new Point(5,5)).segmentIntersect(new LineSegment(new Point(4,7), new Point(7,4))));
		assertFalse(new LineSegment(new Point(1,1), new Point(5,5)).segmentIntersect(new LineSegment(new Point(2,2), new Point(6,6))));
		assertFalse(new LineSegment(new Point(1,1), new Point(5,1)).segmentIntersect(new LineSegment(new Point(1,2), new Point(4,1))));
		assertTrue(new LineSegment(new Point(1,1), new Point(5,5)).segmentIntersect(new LineSegment(new Point(1,5), new Point(5,1))));
	}

	@Test
	public void testMove()
	{
		LineSegment segment = new LineSegment(new Point(1, 1), new Point(4, 3));

		segment.move(new Point(2, -1));

		assertEquals(3.0, segment.p1().x());
		assertEquals(0.0, segment.p1().y());
		assertEquals(6.0, segment.p2().x());
		assertEquals(2.0, segment.p2().y());
	}

	@Test
	public void testScale()
	{
		LineSegment segment = new LineSegment(new Point(1, 1), new Point(3, 3));

		segment.scale(new Point(2, 2), 0.5);

		assertEquals(1.5, segment.p1().x());
		assertEquals(1.5, segment.p1().y());
		assertEquals(2.5, segment.p2().x());
		assertEquals(2.5, segment.p2().y());
	}

	@Test
	public void testClosestPointFromPointOnDiagonalSegment()
	{
		LineSegment segment = new LineSegment(new Point(0, 0), new Point(4, 4));

		Point result = segment.closestPointFromPoint(new Point(0, 4));

		assertEquals(2, result.x(), 1e-10);
		assertEquals(2, result.y(), 1e-10);
	}
}
