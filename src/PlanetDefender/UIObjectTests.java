package PlanetDefender;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import Engine.*;

public class UIObjectTests
{
    @Test
    public void testConstructor()
    {
        Transform t = new Transform(new Point(0, 0), 0, 0, 1);
        UIObject uiObject = new UIObject("teste", t, null);

        assertEquals("teste", uiObject.name());
        assertEquals(t.toString(), uiObject.transform().toString());
    }
}
