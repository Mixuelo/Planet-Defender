package PlanetDefender;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import Engine.*;

public class CharacterBehaviourTests
{
    @Test
    public void testTakeDamage()
    {
        GameEngine eng = new GameEngine();
        PlayerShipBehaviour ship1 = new PlayerShipBehaviour();
        GameObject shipObject1 = new GameObject("ship1", new Transform(new Point(0,0), 1, 0, 1), null, ship1);
        PlayerShipBehaviour ship2 = new PlayerShipBehaviour();
        GameObject shipObject2 = new GameObject("ship2", new Transform(new Point(0,0), 1, 0, 1), null, ship2);
        eng.addEnabled(shipObject1);
        eng.addEnabled(shipObject2);

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
        GameObject ship1 = new GameObject("s1", new Transform(new Point(0,0), 1, 0, 1), null, new PlayerShipBehaviour());
        GameObject ship2 = new GameObject("s2", new Transform(new Point(0,0), 1, 0, 1), null, new PlayerShipBehaviour());
        eng.addEnabled(ship1);
        eng.addEnabled(ship2);

        int initialHealth = ((CharacterBehaviour) ship1.behaviour()).health();

        ((PlayerShipBehaviour) ship1.behaviour()).takeDamage(initialHealth);
        ((PlayerShipBehaviour) ship2.behaviour()).takeDamage(initialHealth + 5);

        assertFalse(eng.isEnabled(ship1));
        assertFalse(eng.isEnabled(ship2));
    }
}
