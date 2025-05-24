package PlanetDefender;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import Engine.*;

public class EffectBehaviourTests
{
    /*
    @Test
    //TODO: decidir o q fazer com isto
    public void onUpdateTest()
    {
        GameEngine engine = new GameEngine();
        Transform transform = new Transform(new Point(0, 0), 0, 1, 0);
        EffectBehaviour effectBehaviour = new EffectBehaviour(1.0);
        GameObject effectObject = new GameObject("Object", transform, new ColliderCircle(transform, new Point(0, 0), 10), effectBehaviour);

        engine.addEnabled(effectObject);

        assertTrue(engine.getEnabled().contains(effectObject));

        effectBehaviour.onUpdate(0.5, null);
        assertTrue(engine.getEnabled().contains(effectObject));

        effectBehaviour.onUpdate(0.5, null);
        assertFalse(engine.getEnabled().contains(effectObject));
    }
    */
}
