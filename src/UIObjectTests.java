import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

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
