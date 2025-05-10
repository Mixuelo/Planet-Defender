import java.awt.event.InputEvent;
import java.util.Arrays;
import java.util.ArrayList;

/**
 * Subclasse de Behaviour responsável pelo comportamento do ecrã inicial do jogo.
 * @author Miguel Alvito, Nicole Reis, Pedro Pinto
 * @version 1.0 (08/05/2025)
 */
public class TitleBehaviour extends Behaviour
{
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    @Override
    public void onUpdate(double dT, InputEvent ie) {
        //TODO: quando receber input chamar a funçao startGame()
    }

    private void startGame()
    {
        MovingObject player = new MovingObject(
            "player", 
            new Transform(new Point(WIDTH/2, HEIGHT/4), 1, 0, 1), 
            new ColliderPolygon(new ArrayList<>(Arrays.asList(new Point(0, 20), new Point(-10, 0), new Point(10, 0)))),
            new PlayerShipBehaviour(),
            new Point(0, 0),
            45,
            0.5
        );

        GameObject planet = new GameObject(
            "planet", 
            new Transform(new Point(WIDTH/2, HEIGHT/2), 1, 0, 1), 
            new ColliderCircle(new Point(0, 0), 50),
            new PlanetBehaviour()
        );

        GameObject enemy = new GameObject(
            "enemy", 
            new Transform(new Point(0, 0), 0, 0, 1), 
            null,
            new EnemySpawnerBehaviour()
        );

        ((PlayerShipBehaviour) player.behaviour()).planet(planet);
        ((PlanetBehaviour) planet.behaviour()).player(player);

        ((EnemySpawnerBehaviour) enemy.behaviour()).planet(planet);
        ((EnemySpawnerBehaviour) enemy.behaviour()).player(player);

        this.parent.engine().addEnabled(planet);
        this.parent.engine().addEnabled(player);
        this.parent.engine().addEnabled(enemy);

        //TODO: criar objectos de UI

        this.parent.engine().destroy(this.parent);
    }
}
