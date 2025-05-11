import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.awt.event.InputEvent;
import java.awt.event.InputMethodEvent;

public class TitleBehaviourTests
{
    @Test
    public void onUpdateTest()
    {
        GameEngine engine = new GameEngine();
        TitleBehaviour titleBehaviour = new TitleBehaviour();
        GameObject titleObject = new GameObject("Title", new Transform(new Point(0, 0), 0, 0, 0), null, titleBehaviour);
        titleObject.engine(engine);

        InputEvent input = new java.awt.event.KeyEvent(
                new javax.swing.JFrame(),
                java.awt.event.KeyEvent.KEY_PRESSED,
                System.currentTimeMillis(),
                0,
                java.awt.event.KeyEvent.VK_SPACE,
                ' '
        );

        titleBehaviour.onUpdate(0.1, input);

        assertEquals(3, engine.getEnabled().size());
        assertFalse(engine.getEnabled().contains(titleObject));
    }
}
