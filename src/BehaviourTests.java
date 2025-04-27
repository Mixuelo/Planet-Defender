import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.awt.event.InputEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BehaviourTests
{

    private Behaviour behaviour = new Behaviour();
    private Transform t = new Transform(new Point(0, 0), 0, 0, 1);
    private GameObject gameObject = new GameObject("teste", t, new ColliderCircle(t, new Point(0, 0), 2));

    @Test
    void testGameObjectGetterAndSetter()
    {
        behaviour.gameObject(gameObject);
        assertEquals(gameObject, behaviour.gameObject());
    }

    @Test
    void testOnInit()
    {
        behaviour.gameObject(gameObject);
        behaviour.onInit();
        assertNotNull(behaviour.gameObject());
    }

    @Test
    void testOnEnabled()
    {
        behaviour.gameObject(gameObject);
        assertDoesNotThrow(() -> behaviour.onEnabled());
    }

    @Test
    void testOnDisabled()
    {
        behaviour.gameObject(gameObject);
        assertDoesNotThrow(() -> behaviour.onDisabled());
    }

    @Test
    void testOnDestroy()
    {
        behaviour.gameObject(gameObject);
        behaviour.onDestroy();
        assertTrue(behaviour.gameObject().equals(null));
    }

    @Test
    void testOnUpdate()
    {
        behaviour.gameObject(gameObject);
        Point pos1 = behaviour.gameObject().collider().centroid();
        assertNotNull(pos1);

        double deltaTime = 0.016; // 16 ms
        InputEvent event = null;

        behaviour.onUpdate(deltaTime, event);
        Point pos2 = behaviour.gameObject().collider().centroid();
        assertNotNull(pos2);

        assertFalse(pos1.equals(pos2)); // presumindo que a posição muda com o tempo
    }

    @Test
    void testOnCollision()
    {
        behaviour.gameObject(gameObject);
        List<IGameObject> collisionList = new ArrayList<>();
        collisionList.add(gameObject);

        assertDoesNotThrow(() -> behaviour.onCollision(collisionList));
    }
}
