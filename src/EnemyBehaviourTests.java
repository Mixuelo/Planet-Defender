import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class EnemyBehaviourTests
{
    @Test
    public void constructorTest()
    {
        EnemyBehaviour enemy1 = new EnemyBehaviour(100);
        EnemyBehaviour enemy2 = new EnemyBehaviour(40);

        assertEquals(100, enemy1.health());
        assertEquals(40, enemy2.health());
    }

    @Test
    public void targetTest()
    {
        EnemyBehaviour enemy = new EnemyBehaviour(100);

        Transform transform = new Transform(new Point(0, 0), 0, 1, 0);
        GameObject target = new GameObject("Object", transform, new ColliderCircle(transform, new Point(0, 0), 10), enemy);

        enemy.target(target);

        assertEquals(enemy.target, target);
    }
}
