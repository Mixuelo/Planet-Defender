import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class UIObjectTests
{
    @Test
    public void testConstructor()
    {
        Transform t = new Transform(new Point(0, 0), 0, 0, 1);
        Collider c = new ColliderCircle(t, new Point(0, 0), 2);
        UIObject uiObject = new UIObject("teste", t, c);

        assertEquals("teste", uiObject.name());
        assertEquals(t.toString(), uiObject.transform().toString());
        assertEquals(c.toString(), uiObject.collider().toString());
    }
}
