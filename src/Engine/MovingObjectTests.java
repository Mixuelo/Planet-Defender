package Engine;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class MovingObjectTests
{
    private Transform t = new Transform(new Point(0, 0), 0, 0, 1);
    private Collider c = new ColliderCircle(t, new Point(0, 0), 2);
    private MovingObject movingObject = new MovingObject("teste", t, c, null, new Point(1, 1), 10, 1);

    @Test
    public void testConstructor()
    {
        assertEquals("teste", movingObject.name());
        assertEquals(t.toString(), movingObject.transform().toString());
        assertEquals(c.toString(), movingObject.collider().toString());
        assertEquals(new Point(1, 1), movingObject.velocity);
        assertEquals(10, movingObject.topVelocity);
        assertEquals(1, movingObject.friction);
    }

    @Test
    public void testUpdateMovement()
    {
        movingObject.updateMovement();
        assertEquals(new Point(1, 1), movingObject.transform().position());
    }

    @Test
    public void testAddVelocity()
    {
        movingObject.addVelocity(new Point(1, 1));
        assertEquals(new Point(2, 2), movingObject.velocity);
        movingObject.setVelocity(new Point(1, 0));
        movingObject.addVelocity(new Point(12, 0));
        assertEquals(new Point(10, 0), movingObject.velocity);
    }

    @Test
    public void testSetVelocity()
    {
        Point newVelocity = new Point(4, 4);
        movingObject.setVelocity(newVelocity);
        assertEquals(newVelocity, movingObject.velocity);
        movingObject.setVelocity(new Point(0, 15));
        assertEquals(new Point(0, 10), movingObject.velocity);
    }
}
