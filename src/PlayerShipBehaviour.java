import java.awt.event.InputEvent;

/**
 * Subclasse de CharacterBehaviour responsável pelo comportamento da nave do jogador.
 * @author Miguel Alvito, Nicole Reis e Pedro Pinto
 * @version 1.0 (08/05/2025)
 */
public class PlayerShipBehaviour extends CharacterBehaviour
{
    private double cooldown;
    private int bulletID;
    private GameObject planet;
    private static final int HEALTH = 25;
    private static final double ACCELERATION = 5;
    private static final double FIRE_COOLDOWN = 0.5;
    private static final double ROTATION_SPEED = 90;
    private static final double BULLET_SPEED = 10;

    /**
     * Construtor.  TODO: APAGAR ESTA FUNÇAO
     * @param health {@code int}
     */
    @Deprecated
    public PlayerShipBehaviour(int health)
    {
        super(health);
        this.cooldown = 0;
        this.bulletID = 0;
    }

    /**
     * Construtor.
     * @param health {@code int}
     */
    public PlayerShipBehaviour()
    {
        super(HEALTH);
        this.cooldown = 0;
        this.bulletID = 0;
    }

    public void planet(GameObject p)
    {
        this.planet = p;
    }

    private void rotate(int direction, double dT)
    {
        double dAngle = ROTATION_SPEED * direction;
        this.parent.rotate(dAngle * dT);
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

    private void shoot()
    {
        double ang = Math.toRadians(this.parent.transform().angle());

        // TODO: definir raio universal para balas, inimigos podem ter balas com colisores menor que o jogador, para facilitar o jogo
        MovingObject bullet = new MovingObject(this.parent.name() + "_bullet" + Integer.toString(this.bulletID++), this.parent.transform().clone(), new ColliderCircle(new Point(0,0), 5), new BulletBehaviour(), new Point(0,0), BULLET_SPEED, 1);

        bullet.setVelocity(
            new Point(
                -Math.sin(ang) * BULLET_SPEED,
                Math.cos(ang) * BULLET_SPEED
            )
        );

        this.parent.engine().addEnabled(bullet);

        this.cooldown = FIRE_COOLDOWN;
    }

    @Override
    public void onUpdate(double dT, InputEvent ie) {
        // TODO: verficar input
        int rotationDir = 0;
        boolean accel = false;
        boolean fire = false;

        if(rotationDir != 0) { this.rotate(rotationDir, dT); }
        if(accel) { this.accelerate(dT); }
        if(fire && this.cooldown <= 0) { this.shoot(); }

        if(this.cooldown > 0) { this.cooldown -= dT; }
    }

    @Override
    void onDefeat() {
        if(this.planet == null) { this.parent.engine().destroy(this.parent); return; }

        this.parent.engine().disable(this.parent);
        ((PlanetBehaviour) this.planet.behaviour()).onPlayerDefeat();
    }

    @Override
    public void onEnabled() {
        this.health = HEALTH;
        this.cooldown = 0;
    }
}
