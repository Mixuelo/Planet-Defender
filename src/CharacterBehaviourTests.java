import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class CharacterBehaviourTests
{
    @Test
    public void testTakeDamage()
    {
        PlayerShipBehaviour ship1 = new PlayerShipBehaviour(10);
        PlayerShipBehaviour ship2 = new PlayerShipBehaviour(14);

        ship1.takeDamage(3);
        ship2.takeDamage(10);

        assertEquals(7, ship1.health());
        assertEquals(4, ship2.health());
    }

    @Test
    public void testOnDefeat()
    {
        GameEngine eng = new GameEngine();
        GameObject ship1 = new GameObject("s1", null, null, new PlayerShipBehaviour(10));
        GameObject ship2 = new GameObject("s2", null, null, new PlayerShipBehaviour(12));
        eng.addEnabled(ship1);
        eng.addEnabled(ship2);

        ((PlayerShipBehaviour) ship1.behaviour()).takeDamage(10);
        ((PlayerShipBehaviour) ship2.behaviour()).takeDamage(14);

        assertFalse(eng.isEnabled(ship1));
        assertFalse(eng.isEnabled(ship2));
    }
}
