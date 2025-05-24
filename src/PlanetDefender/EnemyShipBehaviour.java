package PlanetDefender;

import java.awt.*;
import java.awt.event.InputEvent;
import java.util.Random;
import Engine.*;
import Engine.Point;

/**
 * Subclasse de EnenmyBehaviour responsável pelo comportamento das naves dos inimigos.
 * @author Miguel Alvito, Nicole Reis e Pedro Pinto
 * @version 1.0 (08/05/2025)
 */
public class EnemyShipBehaviour extends EnemyBehaviour
{
    enum actionState
    {
        CHASE_PLAYER,
        CHASE_PLANET,
        ATTACK_PLAYER,
        ATTACK_PLANET
    }

    private GameObject player;
    private double boredom;
    private double cooldown;
    private actionState state;
    private Random rng;
    private int bulletID;
    private static final int HEALTH = 3;
    private static final double ACCELERATION = 30;
    private static final double PATIENCE = 20;
    private static final double FIRE_COOLDOWN = 0.75;
    private static final double START_COOLDOWN = 5;
    private static final double PLAYER_DIST_TRIGGER = 200;
    private static final double PLAYER_DIST_CHASE = 250;
    private static final double PLANET_DIST_TRIGGER = 200;
    private static final double MAX_ROTATION_SPEED = 180;
    private static final double MIN_ROTATION_SPEED = 45;
    private static final double ROTATION_SPEED = 1.5;
    private static final double LOOK_ANGLE_THRESHOLD = 5;
    private static final double BULLET_SPEED = 100;

    /**
     * Construtor.
     */
    public EnemyShipBehaviour()
    {
        super(HEALTH);
        this.boredom = 0;
        this.cooldown = START_COOLDOWN;
        this.rng = new Random();
        this.boredom = 0;
    }

    /**
     * Define um player para este EnemyShipBehaviour (setter).
     * @param p {@code GameObject}
     */
    public void player(GameObject p)
    {
        this.player = p;
    }

    /**
     * Inicializa este EnemyShipBehaviour.
     */
    @Override
    public void onInit()
    {
        super.onInit();

        int firstState = rng.nextInt(4);
        if(firstState == 0)
        {
            this.state = actionState.CHASE_PLANET;
        }
        else
        {
            this.state = actionState.CHASE_PLAYER;
        }
    }

    /**
     * Roda em direção a um determinado GameObject.
     * @param go {@code GameObject}
     * @param dT {@code double}
     */
    private void rotateTowards(GameObject go, double dT)
    {
        Point dist = go.transform().position().subNew(this.parent.transform().position());
        double desiredAngle = Math.toDegrees(Math.atan2(dist.y(), dist.x())) - 90;
        desiredAngle %= 360;
        if(desiredAngle < 0) { desiredAngle += 360; }

        double dAngle = desiredAngle - this.parent.transform().angle();

        if(dAngle > 180)
        {
            dAngle -= 360;
        }
        else if(dAngle < -180)
        {
            dAngle += 360;
        }

        dAngle *= ROTATION_SPEED;

        if(dAngle > MAX_ROTATION_SPEED) { dAngle = MAX_ROTATION_SPEED; }
        else if(dAngle < -MAX_ROTATION_SPEED) { dAngle = -MAX_ROTATION_SPEED; }

        if(!lookingTowards(go)) {
            if(dAngle > 0 && dAngle < MIN_ROTATION_SPEED) { dAngle = MIN_ROTATION_SPEED; }
            else if(dAngle < 0 && dAngle > -MIN_ROTATION_SPEED) { dAngle = -MIN_ROTATION_SPEED; }
        }

        this.parent.rotate(dAngle * dT);
    }

    /**
     * Indica se está apontado a um determinado GameObject.
     * @param go {@code GameObject}
     * @return true se estiver, false se não {@code boolean}
     */
    private boolean lookingTowards(GameObject go)
    {
        double thisAngle = this.parent.transform().angle();
        Point dist = go.transform().position().subNew(this.parent.transform().position());
        double desiredAngle = Math.toDegrees(Math.atan2(dist.y(), dist.x())) - 90;
        desiredAngle %= 360;
        if(desiredAngle < 0) { desiredAngle += 360; }

        if(Math.abs(thisAngle - desiredAngle) <= LOOK_ANGLE_THRESHOLD) { return true; }
        if(Math.abs(thisAngle - (desiredAngle - 360)) <= LOOK_ANGLE_THRESHOLD) { return true; }
        return false;
    }



    /**
     * Acelera a nave inimiga.
     * @param dT {@code double}
     */
    private void accelerate(double dT)
    {
        double ang = Math.toRadians(this.parent.transform().angle());

        ((MovingObject) this.parent).addVelocity(
                new Point(
                        -Math.sin(ang) * ACCELERATION * dT,
                        Math.cos(ang) * ACCELERATION * dT
                )
        );
    }

    /**
     * Faz com que a nave inimiga vá em direção e ataque um determinado GameObject.
     * @param go {@code GameObject}
     * @param dT {@code double}
     */
    private void chase(GameObject go, double dT)
    {
        boolean chasePlayer = (go.equals(this.player));
        if(chasePlayer) { this.boredom += dT; }

        // rodar
        if(!lookingTowards(go)) { rotateTowards(go, dT); }

        // avançar
        if(chasePlayer || lookingTowards(go)) { accelerate(dT); }

        // verificar distancia
        if(this.parent.transform().position().distFrom(go.transform().position()) < PLANET_DIST_TRIGGER && !chasePlayer)
        {
            this.state = actionState.ATTACK_PLANET;
        }
        else if(this.parent.transform().position().distFrom(go.transform().position()) < PLAYER_DIST_TRIGGER && chasePlayer)
        {
            this.state = actionState.ATTACK_PLAYER;
        }

        // verificar boredom
        if(boredom >= PATIENCE)
        {
            this.state = actionState.CHASE_PLANET;
        }
    }

    /**
     * Faz com que a nave inimiga dispare uma bala.
     */
    private void shoot()
    {
        double ang = Math.toRadians(this.parent.transform().angle());

        // TODO: definir raio universal para balas, inimigos podem ter balas com colisores menor que o jogador, para facilitar o jogo
        Transform bulletTrans = this.parent.transform().clone();
        MovingObject bullet = new MovingObject(this.parent.name() + "_bullet" + Integer.toString(this.bulletID++), bulletTrans, new ColliderCircle(new Point(0,0), 3), new BulletBehaviour(this.parent), new CircleShape(5, Color.YELLOW, bulletTrans, -1), new Point(0,0), BULLET_SPEED, 1);

        bullet.setVelocity(
                new Point(
                        -Math.sin(ang) * BULLET_SPEED,
                        Math.cos(ang) * BULLET_SPEED
                )
        );

        this.parent.engine().addEnabled(bullet);

        this.cooldown = FIRE_COOLDOWN;
    }

    /**
     * Faz com que a nave inimiga ataque um determinado GameObject.
     * @param go {@code GameObject}
     * @param dT {@code double}
     */
    private void attack(GameObject go, double dT)
    {
        if(!lookingTowards(go)) { rotateTowards(go, dT); }

        if(this.cooldown <= 0 && (lookingTowards(go) || go == this.player) ) { shoot(); }

        if(go == this.player && this.parent.transform().position().distFrom(go.transform().position()) > PLAYER_DIST_CHASE)
        {
            this.state = actionState.CHASE_PLAYER;
        }
    }

    /**
     * Atualiza este EnemyShipBehaviour com base no tempo e input.
     * @param dT {@code double}
     * @param ie {@code InputEvent}
     */
    @Override
    public void onUpdate(double dT, InputEvent ie)
    {
        switch (this.state) {
            case CHASE_PLAYER:
                chase(this.player, dT);
                break;

            case CHASE_PLANET:
                chase(this.target, dT);
                break;

            case ATTACK_PLAYER:
                attack(this.player, dT);
                break;

            case ATTACK_PLANET:
                attack(this.target, dT);
                break;

            default:
                break;
        }

        if(cooldown > 0) { this.cooldown -= dT; }
    }
}
