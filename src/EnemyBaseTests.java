import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class EnemyBaseTests
{
    @Test
    public void testConstructor()
    {
        Transform t = new Transform(new Point(0, 0), 0, 0, 1);
        Collider c = new ColliderCircle(t, new Point(0, 0), 2);
        EnemyBase enemyBase = new EnemyBase("teste", t, c, new Point(0, 0), 0, 0);

        assertEquals("teste", enemyBase.name());
        assertEquals(t.toString(), enemyBase.transform().toString());
        assertEquals(c.toString(), enemyBase.collider().toString());
        assertEquals(new Point(0, 0), enemyBase.velocity);
        assertEquals(0, enemyBase.topVelocity);
        assertEquals(new Point(0, 0), enemyBase.friction);
    }
}
