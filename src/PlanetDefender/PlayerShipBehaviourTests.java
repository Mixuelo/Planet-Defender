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
        assertEquals(10, behaviour.health);
    }

    @Test
    public void testOnDefeat()
    {
        GameEngine engine = new GameEngine();
        PlayerShipBehaviour behaviour = new PlayerShipBehaviour();
        GameObject planet = new GameObject("planet", new Transform(new Point(0, 0), 0, 0, 0), null, new PlanetBehaviour());
        MovingObject player = new MovingObject("player", new Transform(new Point(0, 0), 0, 0, 0), null, behaviour, new Point(0,0), 0, 0);
        player.engine(engine);
        planet.engine(engine);
        ((PlanetBehaviour) planet.behaviour()).player(player);
        behaviour.planet(planet);

        behaviour.onDefeat();

        assertFalse(engine.isEnabled(player));
    }
}
