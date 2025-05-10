import java.awt.event.InputEvent;
import java.util.Random;

/**
 * Subclasse de CharacterBehaviour responsável pelo comportamento dos inimigos.
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
        ATTACK_PLANET,
    }

    private GameObject player;
    private double boredom;
    private double cooldown;
    private actionState state;
    private Random rng;
    private int bulletID;
    private static final double ACCELERATION = 5;
    private static final double PATIENCE = 7;
    private static final double FIRE_COOLDOWN = 0.5;
    private static final double START_COOLDOWN = 5;
    private static final double PLAYER_DIST_TRIGGER = 150;
    private static final double PLAYER_DIST_CHASE = 350;
    private static final double PLANET_DIST_TRIGGER = 250;
    private static final double MAX_ROTATION_SPEED = 180;
    private static final double ROTATION_SPEED = 0.5;
    private static final double LOOK_ANGLE_THRESHOLD = 10;
    private static final double BULLET_SPEED = 10;

    /**
     * Construtor.
     * @param health {@code int}
     */
    public EnemyShipBehaviour(int health)
    {
        super(health);
        this.boredom = 0;
        this.cooldown = START_COOLDOWN;
        this.rng = new Random();
        this.boredom = 0;
    }

    public void player(GameObject p)
    {
        this.player = p;
    }

    @Override
    public void onInit() {
        super.onInit();

        int firstState = rng.nextInt(3);
        if(firstState == 0) 
        {
            this.state = actionState.CHASE_PLANET;
        }
        else 
        {
            this.state = actionState.CHASE_PLAYER;
        }
    }

    private void rotateTowards(GameObject go, double dT)
    {
        double dAngle = go.transform().angle() - this.parent.transform().angle();
        
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

        this.parent.rotate(dAngle * dT);
    }

    private boolean lookingTowards(GameObject go)
    {
        double goAngle = go.transform().angle();
        double thisAngle = this.parent.transform().angle();

        if(Math.abs(thisAngle - goAngle) <= LOOK_ANGLE_THRESHOLD) { return true; }
        if(Math.abs(thisAngle - (goAngle - 360)) <= LOOK_ANGLE_THRESHOLD) { return true; }
        return false;
    }

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

    private void chase(GameObject go, double dT)
    {
        boolean chasePlayer = (go == this.player);
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

    private void shoot()
    {
        double ang = Math.toRadians(this.parent.transform().angle());

        // TODO: definir raio universal para balas, inimigos podem ter balas com colisores menor que o jogador, para facilitar o jogo
        MovingObject bullet = new MovingObject(this.parent.name() + "_bullet" + Integer.toString(this.bulletID++), this.parent.transform().clone(), new ColliderCircle(new Point(0,0), 10), new BulletBehaviour(), new Point(0,0), BULLET_SPEED, 1);

        bullet.setVelocity(
            new Point(
                -Math.sin(ang) * BULLET_SPEED,
                Math.cos(ang) * BULLET_SPEED
            )
        );

        this.parent.engine().addEnabled(bullet);

        this.cooldown = FIRE_COOLDOWN;
    }

    private void attack(GameObject go, double dT)
    {
        if(!lookingTowards(go)) { rotateTowards(go, dT); }

        if(this.cooldown <= 0 && (lookingTowards(go) || go == this.player) ) { shoot(); }

        if(go == this.player && this.parent.transform().position().distFrom(go.transform().position()) > PLAYER_DIST_CHASE)
        {
            this.state = actionState.CHASE_PLAYER;
        }
    }

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
