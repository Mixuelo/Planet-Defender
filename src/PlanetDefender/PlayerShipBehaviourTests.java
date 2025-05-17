package PlanetDefender;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import Engine.*;

public class PlayerShipBehaviourTests
{
    @Test
    public void testOnEnabled()
    {
        PlayerShipBehaviour behaviour = new PlayerShipBehaviour();
        behaviour.health = 0;

        behaviour.onEnabled();
        assertEquals(25, behaviour.health);
    }

    @Test
    public void testOnDefeat()
    {
        GameEngine engine = new GameEngine();
        PlayerShipBehaviour behaviour = new PlayerShipBehaviour();
        GameObject planet = new GameObject("planet", new Transform(new Point(0, 0), 0, 0, 0), null, new PlanetBehaviour());
        GameObject player = new GameObject("player", new Transform(new Point(0, 0), 0, 0, 0), null, behaviour);
        player.engine(engine);
        planet.engine(engine);
        behaviour.planet(planet);

        behaviour.onDefeat();

        assertFalse(engine.isEnabled(player));
    }
}
