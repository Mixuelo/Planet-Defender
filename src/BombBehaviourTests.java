import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class BombBehaviourTests
{
    @Test
    public void explodeTest()
    {
        GameEngine engine = new GameEngine();
        Transform transform = new Transform(new Point(0, 0), 0, 1, 1);
        BombBehaviour bombBehaviour = new BombBehaviour();
        GameObject bomb = new GameObject("Bomb", transform, new ColliderCircle(transform, new Point(0, 0), 10), bombBehaviour);

        engine.addEnabled(bomb);

        bombBehaviour.explode();

        assertEquals(5, bomb.transform().scale());
        assertEquals(2, engine.getEnabled().size());

        bombBehaviour.onCollision(new ArrayList<>());

        IGameObject effect = engine.getEnabled().get(0);
        assertEquals("Bomb_effect", effect.name());
        assertEquals(2, effect.transform().layer());
    }

    @Test
    public void onCollisionTest()
    {
        GameEngine engine = new GameEngine();

        Transform bombTransform = new Transform(new Point(0, 0), 0, 1, 0);
        BombBehaviour bombBehaviour = new BombBehaviour();
        GameObject bomb = new GameObject("Bomb", bombTransform, new ColliderCircle(bombTransform, new Point(0, 0), 10), bombBehaviour);

        Transform playerTransform = new Transform(new Point(0, 0), 0, 1, 0);
        Collider playerCollider = new ColliderCircle(playerTransform, new Point(0, 0), 10);
        PlayerShipBehaviour playerBehaviour = new PlayerShipBehaviour();
        GameObject player = new GameObject("Player", playerTransform, playerCollider, playerBehaviour);

        engine.addEnabled(bomb);
        engine.addEnabled(player);

        int playerHealth = playerBehaviour.health();

        List<IGameObject> collisionList = new ArrayList<>();
        collisionList.add(player);

        bombBehaviour.onCollision(collisionList);
        assertEquals(playerHealth, playerBehaviour.health());

        bombBehaviour.onCollision(collisionList);
        assertEquals(playerHealth - 7, playerBehaviour.health());
        
        assertFalse(engine.getEnabled().contains(bomb));
    }
}
