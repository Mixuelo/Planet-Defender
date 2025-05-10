import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class EnemyBomberBehaviourTests
{
    @Test
    public void constructorTest()
    {
        EnemyBomberBehaviour enemy1 = new EnemyBomberBehaviour(100);
        EnemyBomberBehaviour enemy2 = new EnemyBomberBehaviour(40);

        assertEquals(100, enemy1.health());
        assertEquals(40, enemy2.health());
    }

}
