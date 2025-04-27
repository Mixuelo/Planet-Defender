import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class EffectObjectTests
{
    @Test
    public void testConstructor()
    {
        Transform t = new Transform(new Point(0, 0), 0, 0, 1);
        Collider c = new ColliderCircle(t, new Point(0, 0), 2);
        EffectObject effectObject = new EffectObject("teste", t, c);

        assertEquals("teste", effectObject.name());
        assertEquals(t.toString(), effectObject.transform().toString());
        assertEquals(c.toString(), effectObject.collider().toString());
    }
}
