package PlanetDefender;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import Engine.*;

public class EnemyBomberBehaviourTests
{
    @Test
    public void onInitTest()
    {
        GameEngine engine = new GameEngine();
        EnemyBomberBehaviour bomberBehaviour = new EnemyBomberBehaviour();
        Transform transform = new Transform(new Point(0, 0), 0, 45, 0);
        MovingObject bomber = new MovingObject("Bomber", transform, new ColliderCircle(new Point(0, 0), 5), bomberBehaviour, new Point(0, 0), 100, 0);

        engine.addEnabled(bomber);
            
        assertEquals(-Math.sin(Math.toRadians(45)) * 15, bomber.velocity.x());
        assertEquals(Math.cos(Math.toRadians(45)) * 15, bomber.velocity.y());
    }

    @Test
    public void onUpdateTest()
    {
        GameEngine engine = new GameEngine();
        EnemyBomberBehaviour bomberBehaviour = new EnemyBomberBehaviour();
        Transform bomberTransform = new Transform(new Point(100, 100), 0, 1, 0);
        MovingObject bomber = new MovingObject("Bomber", bomberTransform, new ColliderCircle(new Point(0, 0), 5), bomberBehaviour, new Point(0,0), 100, 0);

        PlayerShipBehaviour playerBehaviour = new PlayerShipBehaviour();
        Transform targetTransform = new Transform(new Point(200, 150), 0, 1, 0);
        GameObject target = new GameObject("Target", targetTransform, new ColliderCircle(new Point(0, 0), 5), playerBehaviour);

        bomberBehaviour.target(target);

        engine.addEnabled(bomber);
        engine.addEnabled(target);

        bomberTransform.move(new Point(10, 0), 0);
        bomberBehaviour.onUpdate(0.1, null);

        assertEquals(2, engine.getEnabled().size()); // sem bomba

        bomberTransform.move(new Point(-20, 0), 0);
        bomberBehaviour.onUpdate(0.1, null);

        assertEquals(3, engine.getEnabled().size()); // boom

        MovingObject bomb = (MovingObject) engine.getEnabled().get(2);
        assertEquals("Bomber_bomb", bomb.name());
    }
}
