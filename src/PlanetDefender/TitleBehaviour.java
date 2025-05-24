package PlanetDefender;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.ArrayList;
import Engine.*;
import Engine.Point;

/**
 * Subclasse de Behaviour responsável pelo comportamento do ecrã inicial do jogo.
 * @author Miguel Alvito, Nicole Reis, Pedro Pinto
 * @version 1.0 (08/05/2025)
 */
public class TitleBehaviour extends Behaviour
{
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static boolean firstGame = true;

    /**
     * Atualiza com base em tempo e input.
     * @param dT {@code double}
     * @param ie {@code InputEvent}
     */
    @Override
    public void onUpdate(double dT, InputEvent ie)
    {
        if(firstGame)
        {
            Transform t1 = new Transform(new Point(400, 250), 0, 0, 1);
            UIObject title = new UIObject("PlanetDefender", t1, new TitleBehaviour(), new SpriteShape("imgs/titulo.png", 1, t1));

            Transform t2 = new Transform(new Point(400, 400), 0, 0, 1);
            UIObject info = new UIObject("Info", t2, new TitleBehaviour(), new TextShape("Aperte ESPAÇO para jogar", new Font("Arial", Font.PLAIN, 24), Color.WHITE, t2));

            this.parent.engine().addEnabled(title);
            this.parent.engine().addEnabled(info);

            if (ie != null && ((KeyEvent) ie).getKeyCode() == KeyEvent.VK_SPACE)
            {
                this.parent.engine().destroy(title);
                this.parent.engine().destroy(info);
                startGame();
            }
        }
        else if (ie != null && ((KeyEvent) ie).getKeyCode() == KeyEvent.VK_SPACE)
        {
            startGame();
        }
    }

    /**
     * Começa o jogo.
     */
    private void startGame()
    {
        Transform plyTrans = new Transform(new Point(WIDTH/2, HEIGHT/4), 1, 0, 1);
        MovingObject player = new MovingObject(
            "player", 
            plyTrans,
            new ColliderPolygon(new ArrayList<>(Arrays.asList(
                new Point(13, -35), 
                new Point(37, -35), 
                new Point(50, -28),
                new Point(40, -9),
                new Point(6, -9),
                new Point(0, -28)
            ))),
            new PlayerShipBehaviour(),
            new SpriteShape("imgs/nave_jogador_parada.png", 0.1, plyTrans, 1),
            new Point(0, 0),
            125,
            0.7
        );

        Transform pntTrans = new Transform(new Point(WIDTH/2, HEIGHT/2), 1, 0, 1);
        GameObject planet = new GameObject(
            "planet", 
            pntTrans,
            new ColliderCircle(new Point(0, 0), 50),
            new PlanetBehaviour(),
            new SpriteShape("imgs/planeta.png", 0.0625, pntTrans)
        );

        GameObject enemy = new GameObject(
            "enemy", 
            new Transform(new Point(0, 0), 0, 0, 1), 
            null,
            new EnemySpawnerBehaviour()
        );

        GameObject planetStatus = new GameObject(
            "planet_status",
            new Transform(new Point(0, 0), 5, 0, 1),
            null,
            new ArcGaugeBehaviour(Color.GREEN, 25, 8)
        );

        GameObject playerStatus = new GameObject(
            "player_status",
            new Transform(new Point(0, 0), 5, 0, 1),
            null,
            new ArcGaugeBehaviour(Color.CYAN, 20, 5)
        );

        ((PlayerShipBehaviour) player.behaviour()).planet(planet);
        ((PlanetBehaviour) planet.behaviour()).player(player);

        ((EnemySpawnerBehaviour) enemy.behaviour()).planet(planet);
        ((EnemySpawnerBehaviour) enemy.behaviour()).player(player);

        ((PlanetBehaviour) planet.behaviour()).statusGauge(planetStatus);
        ((ArcGaugeBehaviour) planetStatus.behaviour()).target(planet);
        ((PlayerShipBehaviour) player.behaviour()).statusGauge(playerStatus);
        ((ArcGaugeBehaviour) playerStatus.behaviour()).target(player);

        this.parent.engine().addEnabled(planet);
        this.parent.engine().addEnabled(player);
        this.parent.engine().addEnabled(enemy);
        this.parent.engine().addEnabled(planetStatus);
        this.parent.engine().addEnabled(playerStatus);

        this.parent.engine().destroy(this.parent);
        if (firstGame) firstGame = false;
    }
}
