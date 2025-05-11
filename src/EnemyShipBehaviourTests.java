import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class EnemyShipBehaviourTests
{
    @Test
    public void onUpdateTest()
    {
        GameEngine engine = new GameEngine();
        EnemyShipBehaviour behaviour = new EnemyShipBehaviour();

        MovingObject enemyShip = new MovingObject("enemy", new Transform(new Point(100, 100), 0, 1, 0), null, behaviour, new Point(0, 0), 0, 0);
        enemyShip.engine(engine);

        GameObject planet = new GameObject("planet", new Transform(new Point(400, 300), 0, 0, 0), null, null);
        GameObject player = new GameObject("player", new Transform(new Point(200, 200), 0, 0, 0), null, null);

        behaviour.target = planet;
        behaviour.player(player);

        behaviour.onInit();
        behaviour.onUpdate(0.1, null);

        assertNotEquals(0, enemyShip.transform().angle());
    }
}