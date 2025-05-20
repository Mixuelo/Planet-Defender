package PlanetDefender;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import Engine.*;

public class AsteroidBehaviourTests
{
    @Test
    public void onInitTest()
    {
        GameEngine engine = new GameEngine();
        AsteroidBehaviour asteroidBehaviour = new AsteroidBehaviour(50.0);
        Transform transform = new Transform(new Point(0, 0), 1, 1, 1);
        GameObject asteroid = new GameObject("Asteroid", transform, new ColliderCircle(new Point(0, 0), 5), asteroidBehaviour);

        assertEquals(1, asteroid.transform().scale());

        engine.addEnabled(asteroid);

        assertEquals(50.0 * 10, asteroid.transform().scale());
    }

    @Test
    public void onUpdateTest()
    {
        GameEngine engine = new GameEngine();
        AsteroidBehaviour asteroidBehaviour = new AsteroidBehaviour(50.0);
        Transform asteroidTransform = new Transform(new Point(100, 100), 1, 1, 0);
        MovingObject asteroid = new MovingObject("Asteroid", asteroidTransform, new ColliderCircle(new Point(0, 0), 5), asteroidBehaviour,  new Point(0, 0), 200, 0);

        PlayerShipBehaviour playerBehaviour = new PlayerShipBehaviour();
        Transform targetTransform = new Transform(new Point(200, 200), 1, 1, 0);
        GameObject target = new GameObject("Target", targetTransform, new ColliderCircle(new Point(0, 0), 5), playerBehaviour);

        engine.addEnabled(asteroid);
        engine.addEnabled(target);

        asteroidBehaviour.target(target);

        assertTrue(asteroid.velocity().x() == 0);
        assertTrue(asteroid.velocity().y() == 0);

        asteroidBehaviour.onUpdate(0.1, null);

        assertTrue(asteroid.velocity().x() > 0);
        assertTrue(asteroid.velocity().y() > 0);
    }

    @Test
    public void onCollisionTest()
    {
        GameEngine engine = new GameEngine();
        AsteroidBehaviour asteroidBehaviour = new AsteroidBehaviour(50.0);
        Transform transform = new Transform(new Point(0, 0), 1, 1, 0);
        GameObject asteroid = new GameObject("Asteroid", transform, new ColliderCircle(new Point(0, 0), 5), asteroidBehaviour);

        PlayerShipBehaviour playerBehaviour = new PlayerShipBehaviour();
        GameObject character = new GameObject("Character", transform, new ColliderCircle(new Point(0, 0), 5), playerBehaviour);

        engine.addEnabled(asteroid);
        engine.addEnabled(character);

        int initialHealth = playerBehaviour.health();

        asteroidBehaviour.onCollision(new ArrayList<>(Arrays.asList(character)));

        int expectedDamage = (int)(50.0 * 5);
        assertEquals(initialHealth - expectedDamage, playerBehaviour.health());
        assertFalse(engine.getEnabled().contains(asteroid));
    }

    @Test
    public void onDefeatTest()
    {
        GameEngine engine = new GameEngine();
        AsteroidBehaviour asteroidBehaviour = new AsteroidBehaviour(50.0);
        Transform transform = new Transform(new Point(0, 0), 1, 1, 0);
        GameObject asteroid = new GameObject("Asteroid", transform, new ColliderCircle(new Point(0, 0), 5), asteroidBehaviour);
        engine.addEnabled(asteroid);

        asteroidBehaviour.onDefeat();

        assertFalse(engine.getEnabled().contains(asteroid));
        assertEquals(2, engine.getEnabled().size());
    }

    @Test
    public void divideTest()
    {
        GameEngine engine = new GameEngine();
        AsteroidBehaviour asteroidBehaviour = new AsteroidBehaviour(50.0);
        Transform transform = new Transform(new Point(0, 0), 1, 1, 0);
        GameObject asteroid = new GameObject("Asteroid", transform, new ColliderCircle(new Point(0, 0), 5), asteroidBehaviour);
        engine.addEnabled(asteroid);

        asteroidBehaviour.divide();

        assertEquals(3, engine.getEnabled().size());

        GameObject child1 = null;
        GameObject child2 = null;
        for (IGameObject go : engine.getEnabled())
        {
            if (go.name().equals("Asteroid_child1")) child1 = (GameObject) go;
            if (go.name().equals("Asteroid_child2")) child2 = (GameObject) go;
        }
        assertNotNull(child1);
        assertNotNull(child2);
    }
}
