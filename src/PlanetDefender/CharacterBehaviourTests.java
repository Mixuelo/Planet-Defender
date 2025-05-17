package PlanetDefender;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import Engine.*;

public class CharacterBehaviourTests
{
    @Test
    public void testTakeDamage()
    {
        PlayerShipBehaviour ship1 = new PlayerShipBehaviour();
        PlayerShipBehaviour ship2 = new PlayerShipBehaviour();

        int initialHealth = ship1.health();

        ship1.takeDamage(3);
        ship2.takeDamage(10);

        assertEquals(initialHealth - 3, ship1.health());
        assertEquals(initialHealth - 10, ship2.health());
    }

    @Test
    public void testOnDefeat()
    {
        GameEngine eng = new GameEngine();
        GameObject ship1 = new GameObject("s1", null, null, new PlayerShipBehaviour());
        GameObject ship2 = new GameObject("s2", null, null, new PlayerShipBehaviour());
        eng.addEnabled(ship1);
        eng.addEnabled(ship2);

        int initialHealth = ((CharacterBehaviour) ship1.behaviour()).health();

        ((PlayerShipBehaviour) ship1.behaviour()).takeDamage(initialHealth);
        ((PlayerShipBehaviour) ship2.behaviour()).takeDamage(initialHealth + 5);

        assertFalse(eng.isEnabled(ship1));
        assertFalse(eng.isEnabled(ship2));
    }
}
