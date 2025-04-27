import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class MovingObjectTests
{
    private Transform t = new Transform(new Point(0, 0), 0, 0, 1);
    private Collider c = new ColliderCircle(t, new Point(0, 0), 2);
    private MovingObject movingObject = new MovingObject("teste", t, c, new Point(1, 1), 0, 0);

    @Test
    public void testConstructor()
    {
        assertEquals("teste", movingObject.name());
        assertEquals(t.toString(), movingObject.transform().toString());
        assertEquals(c.toString(), movingObject.collider().toString());
        assertEquals(new Point(1, 1), movingObject.velocity);
        assertEquals(0, movingObject.topVelocity);
        assertEquals(0, movingObject.friction);
    }

    @Test
    public void testUpdateMovement()
    {
        Point initialPos = movingObject.collider().centroid;
        movingObject.updateMovement();
        assertNotEquals(initialPos, movingObject.collider().centroid);
        assertEquals(new Point(1, 1), movingObject.collider().centroid);
    }

    @Test
    public void testAddVelocity()
    {
        movingObject.addVelocity(new Point(1, 1));
        assertEquals(new Point(2, 2), movingObject.velocity);
    }

    @Test
    public void testSetVelocity()
    {
        Point newVelocity = new Point(8, 8);
        movingObject.setVelocity(newVelocity);
        assertEquals(newVelocity, movingObject.velocity);
    }
}
