import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class GameEngineTests
{
    Point p = new Point(0, 0);
    Transform t = new Transform(p, 0, 0.0, 1.0);

    private GameEngine gameEngine =  new GameEngine();
    private GameObject gameObject = new GameObject("go", t, new ColliderCircle(t, p, 10));

    @Test
    void testEmptyList()
    {
        assertNotNull(gameEngine.objects());
        assertTrue(gameEngine.objects().isEmpty());
    }

    @Test
    void testAdd()
    {
        gameEngine.add(gameObject);

        assertNotNull(gameEngine.objects());
        assertEquals(1, gameEngine.objects().size());
        assertTrue(gameEngine.objects().contains(gameObject));
    }

    @Test
    void testDestroy()
    {
        gameEngine.add(gameObject);
        gameEngine.destroy(gameObject);

        assertEquals(0, gameEngine.objects().size());
        assertTrue(gameEngine.objects().isEmpty());

        Point p2 = new Point(1, 1);
        Transform t2 = new Transform(p2, 0, 0.0, 1.0);
        GameObject gameObject2 = new GameObject("go2", t2, new ColliderCircle(t2, p2, 5));

        gameEngine.destroy(gameObject2);
        assertTrue(gameEngine.objects().isEmpty());
    }
}
