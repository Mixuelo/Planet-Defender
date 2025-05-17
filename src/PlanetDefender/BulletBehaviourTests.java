package PlanetDefender;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import Engine.*;

public class BulletBehaviourTests
{
    @Test
    public void onUpdateTest()
    {
        GameEngine engine = new GameEngine();
        BulletBehaviour bulletBehaviour = new BulletBehaviour(null);

        Transform insideTransform = new Transform(new Point(100, 100), 0, 1, 0);
        GameObject insideBullet = new GameObject("InsideBullet", insideTransform, new ColliderCircle(insideTransform, new Point(0, 0), 2), bulletBehaviour);
        engine.addEnabled(insideBullet);
        bulletBehaviour.onUpdate(0.1, null);
        assertTrue(engine.getEnabled().contains(insideBullet));

        // x < -50
        Transform leftTransform = new Transform(new Point(-51, 100), 0, 1, 0);
        GameObject leftBullet = new GameObject("LeftBullet", leftTransform, new ColliderCircle(leftTransform, new Point(0, 0), 2), bulletBehaviour);
        engine.addEnabled(leftBullet);
        bulletBehaviour.onUpdate(0.1, null);
        assertFalse(engine.getEnabled().contains(leftBullet));

        // x > 850
        Transform rightTransform = new Transform(new Point(851, 100), 0, 1, 0);
        GameObject rightBullet = new GameObject("RightBullet", rightTransform, new ColliderCircle(rightTransform, new Point(0, 0), 2), bulletBehaviour);
        engine.addEnabled(rightBullet);
        bulletBehaviour.onUpdate(0.1, null);
        assertFalse(engine.getEnabled().contains(rightBullet));

        // y < -50
        Transform topTransform = new Transform(new Point(100, -51), 0, 1, 0);
        GameObject topBullet = new GameObject("TopBullet", topTransform, new ColliderCircle(topTransform, new Point(0, 0), 2), bulletBehaviour);
        engine.addEnabled(topBullet);
        bulletBehaviour.onUpdate(0.1, null);
        assertFalse(engine.getEnabled().contains(topBullet));

        // y > 650
        Transform bottomTransform = new Transform(new Point(100, 651), 0, 1, 0);
        GameObject bottomBullet = new GameObject("BottomBullet", bottomTransform, new ColliderCircle(bottomTransform, new Point(0, 0), 2), bulletBehaviour);
        engine.addEnabled(bottomBullet);
        bulletBehaviour.onUpdate(0.1, null);
        assertFalse(engine.getEnabled().contains(bottomBullet));
    }

    @Test
    public void onCollisionWithPlayerTest()
    {
        GameEngine engine = new GameEngine();
        BulletBehaviour bulletBehaviour = new BulletBehaviour(null);
        Transform bulletTransform = new Transform(new Point(100, 100), 0, 1, 0);
        GameObject bullet = new GameObject("Bullet", bulletTransform, new ColliderCircle(bulletTransform, new Point(0, 0), 2), bulletBehaviour);
        engine.addEnabled(bullet);

        Transform playerTransform = new Transform(new Point(100, 100), 0, 1, 1);
        PlayerShipBehaviour playerBehaviour = new PlayerShipBehaviour();
        GameObject player = new GameObject("Player", playerTransform, new ColliderCircle(playerTransform, new Point(0, 0), 5), playerBehaviour);
        engine.addEnabled(player);

        List<IGameObject> collisionList = new ArrayList<>();
        collisionList.add(player);

        int initialHealth = playerBehaviour.health();
        bulletBehaviour.onCollision(collisionList);
        assertEquals(initialHealth - 1, playerBehaviour.health());
        assertFalse(engine.getEnabled().contains(bullet));
    }

    @Test
    public void onCollisionWithBombTest()
    {
        GameEngine engine = new GameEngine();
        BulletBehaviour bulletBehaviour = new BulletBehaviour(null);
        Transform bulletTransform = new Transform(new Point(100, 100), 0, 1, 0);
        GameObject bullet = new GameObject("Bullet", bulletTransform, new ColliderCircle(bulletTransform, new Point(0, 0), 2), bulletBehaviour);
        engine.addEnabled(bullet);

        Transform bombTransform = new Transform(new Point(100, 100), 0, 1, 1);
        BombBehaviour bombBehaviour = new BombBehaviour(null);
        GameObject bomb = new GameObject("Bomb", bombTransform, new ColliderCircle(bombTransform, new Point(0, 0), 5), bombBehaviour);
        engine.addEnabled(bomb);

        List<IGameObject> collisionList = new ArrayList<>();
        collisionList.add(bomb);

        bulletBehaviour.onCollision(collisionList);
        assertFalse(engine.getEnabled().contains(bullet));
    }
}
