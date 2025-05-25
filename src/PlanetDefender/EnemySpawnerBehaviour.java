package PlanetDefender;

import java.awt.event.InputEvent;
import java.util.Random;
import java.util.Arrays;
import java.util.ArrayList;
import Engine.*;

/**
 * Subclasse de Behaviour responsável pela geração de inimigos ao longo do tempo.
 * @author Miguel Alvito, Nicole Reis, Pedro Pinto
 * @version 1.0 (08/05/2025)
 */
public class EnemySpawnerBehaviour extends Behaviour
{
    private int enemyID;
    private double timer;
    private Random rng;
    private GameObject planet;
    private GameObject player;
    private static final int SCREEN_WIDTH = 800;
    private static final int SCREEN_HEIGHT = 600;
    private static final int MARGIN = 50;
    private static final int LEFT_BORDER = -MARGIN;
    private static final int RIGHT_BORDER = SCREEN_WIDTH + MARGIN;
    private static final int UP_BORDER = -MARGIN;
    private static final int DOWN_BORDER = SCREEN_HEIGHT + MARGIN;
    private static final double INITIAL_TIMER = 7;
    private static final double MIN_TIMER = 2.5;
    private static final double TIME_DECREASE = 0.1;

    /**
     * Construtor.
     */
    public EnemySpawnerBehaviour()
    {
        this.enemyID = 0;
        this.timer = INITIAL_TIMER;
        this.rng = new Random();
    }

    /**
     * Define um GameObject como planeta (setter).
     * @param p {@code GameObject}
     */
    public void planet(GameObject p)
    {
        this.planet = p;
    }

    public void player(GameObject p)
    {
        this.player = p;
    }

    /**
     * Atualiza este EnemySpawnerBehaviour com base em tempo e input.
     * @param dT {@code double}
     * @param ie {@code InputEvent}
     */
    @Override
    public void onUpdate(double dT, InputEvent ie)
    {
        this.timer -= dT;
        if(this.timer > 0) { return; }

        Transform t = new Transform(new Point(0, 0), 1, 0, 1);

        int side = this.rng.nextInt(4);
        switch(side)
        {
            case 0:     // cima
                t.move(new Point(rng.nextInt(LEFT_BORDER, RIGHT_BORDER), UP_BORDER), 0);
                break;

            case 1:     // esquerda
                t.move(new Point(LEFT_BORDER, rng.nextInt(UP_BORDER, DOWN_BORDER)), 0);
                break;

            case 2:     // baixo
                t.move(new Point(rng.nextInt(LEFT_BORDER, RIGHT_BORDER), DOWN_BORDER), 0);
                break;

            case 3:     // direita
                t.move(new Point(RIGHT_BORDER, rng.nextInt(UP_BORDER, DOWN_BORDER)), 0);
                break;

            default:
                break;
        }

        Collider c;
        Behaviour b;
        Point v;
        SpriteShape s = null;
        double maxv;
        double f;

        int type = this.rng.nextInt(6);

        if(type < 3) // asteroide (1/2 de chance)
        {
            c = (Collider) new ColliderCircle(t, new Point(0,0), 0.5);
    
            double size = this.rng.nextGaussian(7, 0.6);
            size = Math.max(size, 5);
            size = Math.min(size, 9);
            b = new AsteroidBehaviour(size);

            s = new SpriteShape("imgs/asteroide.png", 0.01, t);

            v = this.planet.transform().position().subNew(t.position());
            v.multThis(0.03);
            v.rotateThis(new Point(0,0), this.rng.nextDouble(-55, 55));

            maxv = 40; 

            f = 0;
        }
        else if(type < 5) // nave (1/3 de chance)
        {
            c = new ColliderPolygon(t, new ArrayList<>(Arrays.asList(new Point(0, 40), new Point(-25, 0), new Point(25, 0))));

            b = new EnemyShipBehaviour();
            ((EnemyShipBehaviour) b).player(this.player);

            s = new SpriteShape("imgs/nave_inimiga.png", 0.1, t, 1);

            //v = new Point(0,0);
            v = this.planet.transform().position().subNew(t.position());
            v.multThis(0.05);
            v.rotateThis(new Point(0,0), this.rng.nextGaussian() * 30);
            
            maxv = 40;

            f = 15;
        }
        else // bombardeiro (1/6 de chance)
        {
            Point dist = this.planet.transform().position().subNew(t.position());
            double ang = Math.toDegrees(Math.atan2(dist.y(), dist.x())) - 90;
            ang %= 360;

            double angDiffMax = 5;
            double angDiffMin = 15;

            double rang = this.rng.nextDouble(-angDiffMax, angDiffMax);
            if(rang >= 0)   { ang += angDiffMin + rang; }
            else            { ang += -angDiffMin + rang; }

            t.rotate(ang);

            c = new ColliderPolygon(t, new ArrayList<>(Arrays.asList(new Point(8, 25), new Point(8, -25), new Point(-8, -25), new Point(-8, 25))));

            b = new EnemyBomberBehaviour();

            s = new SpriteShape("imgs/bombardeiro.png", 0.05, t, 1);

            v = new Point(0,0);
            
            maxv = 100; 

            f = 0;
        }

        MovingObject enemy = new MovingObject(this.parent.name() + Integer.toString(this.enemyID++), t, c, b, s, v, maxv, f);

        ((EnemyBehaviour) enemy.behaviour()).target(this.planet);

        this.parent.engine().addEnabled(enemy);

        this.timer = Math.max(INITIAL_TIMER - enemyID * TIME_DECREASE, MIN_TIMER);
    }
}
