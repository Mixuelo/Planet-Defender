import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class EnemySpawnerBehaviourTests
{
    @Test
    public void onUpdateTest()
    {
        GameEngine engine = new GameEngine();
        EnemySpawnerBehaviour spawner = new EnemySpawnerBehaviour();
        GameObject spawnerObject = new GameObject("Spawner", new Transform(new Point(0, 0), 0, 0, 0), new ColliderCircle(new Point(0 ,0), 5), spawner);
        spawnerObject.engine(engine);

        PlayerShipBehaviour playerBehaviour = new PlayerShipBehaviour(100);
        GameObject planet = new GameObject("Planet", new Transform(new Point(400, 300), 0, 0, 0), new ColliderCircle(new Point(0 ,0), 5), playerBehaviour);
        spawner.planet(planet);

        assertEquals(0, engine.getEnabled().size());

        spawner.onUpdate(1.0, null);
        spawner.onUpdate(5 + 0.1, null);

        assertEquals(1, engine.getEnabled().size());
        GameObject enemy = (GameObject) engine.getEnabled().get(0);
        assertTrue(enemy.name().startsWith("enemy0"));
    }
}
