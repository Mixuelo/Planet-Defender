import java.awt.event.InputEvent;
import java.util.Random;
import java.util.Arrays;
import java.util.ArrayList;

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
    //TODO: ajustar valores com base nas dimensoes do ecra
    private static final int LEFT_BORDER = -20;
    private static final int RIGHT_BORDER = 820;
    private static final int UP_BORDER = -20;
    private static final int DOWN_BORDER = 620;
    private static final double INITIAL_TIMER = 5;
    private static final double MIN_TIMER = 2.5;
    private static final double TIME_DECREASE = 0.05;

    public EnemySpawnerBehaviour()
    {
        this.enemyID = 0;
        this.timer = INITIAL_TIMER;
        this.rng = new Random();
    }

    public void planet(GameObject p)
    {
        this.planet = p;
    }

    @Override
    public void onUpdate(double dT, InputEvent ie) {
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
        double maxv;
        double f;

        int type = this.rng.nextInt(6);

        //TODO: ajustar valores
        if(type < 3) // asteroide (1/2 de chance)
        {
            c = null;
    
            double size = this.rng.nextGaussian(8, 0.6);
            size = Math.max(size, 5);
            size = Math.min(size, 10);
            b = new AsteroidBehaviour(size);

            v = this.planet.transform().position().subNew(t.position());
            v.multThis(0.05);
            v.rotateThis(new Point(0,0), this.rng.nextGaussian() * 30);

            maxv = 40; 

            f = 1;
        }
        else if(type < 6) // nave (1/3 de chance)
        {
            c = new ColliderPolygon(new ArrayList<>(Arrays.asList(new Point(0, 20), new Point(-10, 0), new Point(10, 0))));

            b = new EnemyShipBehaviour();

            v = new Point(0,0);
            
            maxv = 40;

            f = 0.5;
        }
        else // bombardeiro (1/6 de chance)
        {
            Point dist = this.planet.transform().position().subNew(t.position());
            double ang = Math.toDegrees(Math.atan2(dist.y(), dist.x())) - 90;
            ang %= 360;

            double rang = this.rng.nextDouble(-15, 15);
            if(rang >= 0)   { ang += 15 + rang; }
            else            { ang += -15 + rang; }

            t.rotate(ang);

            c = new ColliderPolygon(new ArrayList<>(Arrays.asList(new Point(10, 15), new Point(10, -15), new Point(-10, -15), new Point(-10, 15))));

            b = new EnemyBomberBehaviour();

            v = new Point(0,0);
            
            maxv = 100; 

            f = 1;
        }

        MovingObject enemy = new MovingObject("enemy" + Integer.toString(this.enemyID), t, c, b, v, maxv, f);

        ((EnemyBehaviour) enemy.behaviour()).target(this.planet);

        this.parent.engine().addEnabled(enemy);

        this.timer = Math.max(INITIAL_TIMER - enemyID * TIME_DECREASE, MIN_TIMER);
    }
}
