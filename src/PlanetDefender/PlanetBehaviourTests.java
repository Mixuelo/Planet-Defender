package PlanetDefender;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import Engine.*;

public class PlanetBehaviourTests
{
    @Test
    public void testOnDefeat()
    {
        GameEngine engine = new GameEngine();
        PlanetBehaviour planetBehaviour = new PlanetBehaviour();
        GameObject planet = new GameObject("planet", new Transform(new Point(0, 0), 0, 0, 0), null, planetBehaviour);
        planet.engine(engine);

        planetBehaviour.onDefeat();
        assertFalse(engine.getEnabled().contains(planet));
    }

    @Test
    public void testGameOver()
    {
        GameEngine engine = new GameEngine();
        PlanetBehaviour planetBehaviour = new PlanetBehaviour();
        GameObject planet = new GameObject("planet", new Transform(new Point(0, 0), 0, 0, 0), null, planetBehaviour);
        planet.engine(engine);

        planetBehaviour.gameOver();

        assertEquals(1, engine.getEnabled().size());
        assertEquals("GameOver", engine.getEnabled().get(0).name());
    }
}
