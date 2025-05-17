package PlanetDefender;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import Engine.*;

public class EffectObjectTests
{
    @Test
    public void testConstructor()
    {
        Transform t = new Transform(new Point(0, 0), 0, 0, 1);
        EffectObject effectObject = new EffectObject("teste", t, 1);

        assertEquals("teste", effectObject.name());
        assertEquals(t.toString(), effectObject.transform().toString());
    }
}
