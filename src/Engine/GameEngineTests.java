package Engine;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.List;

public class GameEngineTests
{
    Point p = new Point(0, 0);
    Transform t = new Transform(p, 0, 0.0, 1.0);
    Point p2 = new Point(1, 1);
    Transform t2 = new Transform(p2, 0, 0.0, 1.0);

    private GameEngine gameEngine =  new GameEngine();
    private GameObject gameObject1 = new GameObject("go", t, new ColliderCircle(t, p, 10), null);
    private GameObject gameObject2 = new GameObject("go2", t2, new ColliderCircle(t2, p2, 5), null);

    @Test
    void testDestroy()
    {
        gameEngine.addEnabled(gameObject1);
        gameEngine.destroy(gameObject1);

        assertEquals(0, gameEngine.getEnabled().size());
        assertTrue(gameEngine.getEnabled().isEmpty());

        gameEngine.destroy(gameObject2);
        assertTrue(gameEngine.getEnabled().isEmpty());
    }

    @Test
    void testAddEnabled()
    {
        gameEngine.addDisabled(gameObject2);
        gameEngine.addEnabled(gameObject1);
        assertTrue(gameEngine.getEnabled().contains(gameObject1));
        assertFalse(gameEngine.getEnabled().contains(gameObject2));
    }

    @Test
    void testAddDesabled()
    {
        gameEngine.addEnabled(gameObject1);
        gameEngine.addDisabled(gameObject2);
        assertTrue(gameEngine.getDisabled().contains(gameObject2));
        assertFalse(gameEngine.getDisabled().contains(gameObject1));
    }

    @Test
    void testEnable()
    {
        gameEngine.addDisabled(gameObject1);
        gameEngine.enable(gameObject1);
        assertTrue(gameEngine.isEnabled(gameObject1));
        assertFalse(gameEngine.isDisabled(gameObject1));
    }

    @Test
    void testDisable()
    {
        gameEngine.addEnabled(gameObject1);
        gameEngine.disable(gameObject1);
        assertTrue(gameEngine.isDisabled(gameObject1));
        assertFalse(gameEngine.isEnabled(gameObject1));
    }

    @Test
    void testIsEnabled()
    {
        gameEngine.addEnabled(gameObject1);
        gameEngine.addDisabled(gameObject2);

        assertTrue(gameEngine.isEnabled(gameObject1));
        assertFalse(gameEngine.isEnabled(gameObject2));
    }

    @Test
    void testIsDisabled()
    {
        gameEngine.addEnabled(gameObject1);
        gameEngine.addDisabled(gameObject2);

        assertFalse(gameEngine.isDisabled(gameObject1));
        assertTrue(gameEngine.isDisabled(gameObject2));
    }

    @Test
    void testGetEnabled()
    {
        gameEngine.addDisabled(gameObject2);
        gameEngine.addEnabled(gameObject1);

        List<IGameObject> enabled = gameEngine.getEnabled();

        assertEquals(1, enabled.size());
        assertTrue(enabled.contains(gameObject1));
        assertFalse(enabled.contains(gameObject2));
    }

    @Test
    void testGetDisabled()
    {
        gameEngine.addEnabled(gameObject1);
        gameEngine.addDisabled(gameObject2);

        List<IGameObject> disabled = gameEngine.getDisabled();

        assertEquals(1, disabled.size());
        assertTrue(disabled.contains(gameObject2));
        assertFalse(disabled.contains(gameObject1));
    }

    @Test
    void testDestroyAll()
    {
        gameEngine.addEnabled(gameObject1);
        gameEngine.addEnabled(gameObject2);
        gameEngine.destroyAll();
        assertTrue(gameEngine.getEnabled().isEmpty());
        assertTrue(gameEngine.getDisabled().isEmpty());
    }

    @Test
    void testCheckCollisions()
    {
        gameEngine.addEnabled(gameObject1);
        gameEngine.addEnabled(gameObject2);
        assertDoesNotThrow(() -> gameEngine.checkCollisions());
    }
}
