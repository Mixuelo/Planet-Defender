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
        PlayerShipBehaviour ship1 = new PlayerShipBehaviour(10);
        PlayerShipBehaviour ship2 = new PlayerShipBehaviour(14);

        ship1.takeDamage(10);
        ship2.takeDamage(14);

        assertNull(ship1.gameObject());
        assertNull(ship2.gameObject());
    }
}
