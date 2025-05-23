package PlanetDefender;

import java.awt.Color;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import Engine.*;

/**
 * Subclasse de CharacterBehaviour responsável pelo comportamento da nave do jogador.
 * @author Miguel Alvito, Nicole Reis e Pedro Pinto
 * @version 1.0 (08/05/2025)
 */
public class PlayerShipBehaviour extends CharacterBehaviour
{
    private double cooldown;
    private int rotationDir = 0;
    private boolean accel = false;
    private int bulletID;
    private GameObject planet;
    private static final int HEALTH = 25;
    private static final double ACCELERATION = 50;
    private static final double FIRE_COOLDOWN = 0.5;
    private static final double ROTATION_SPEED = 180;
    private static final double BULLET_SPEED = 10;
    private static final double OUT_OF_BOUNDS_DIST = 600;

    /**
     * Construtor.
     */
    public PlayerShipBehaviour()
    {
        super(HEALTH);
        this.cooldown = 0;
        this.bulletID = 0;
        this.rotationDir = 0;
        this.accel = false;
    }

    /**
     * Define um planeta associado à nave (setter).
     * @param p {@code GameObject}
     */
    public void planet(GameObject p)
    {
        this.planet = p;
    }

    /**
     * Roda a nave.
     * @param direction {@code int}
     * @param dT {@code double}
     */
    private void rotate(int direction, double dT)
    {
        double dAngle = ROTATION_SPEED * direction;
        this.parent.rotate(dAngle * dT);
    }

    /**
     * Acelera a nave.
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
     * Faz com que a nave dispare.
     */
    private void shoot()
    {
        double ang = Math.toRadians(this.parent.transform().angle());

        // TODO: definir raio universal para balas, inimigos podem ter balas com colisores menor que o jogador, para facilitar o jogo
        Transform bulletTrans = this.parent.transform().clone();
        MovingObject bullet = new MovingObject(this.parent.name() + "_bullet" + Integer.toString(this.bulletID++), bulletTrans , new ColliderCircle(new Point(0,0), 5), new BulletBehaviour(this.parent), new SpriteShape("imgs/bala.png",1, bulletTrans), new Point(0,0), BULLET_SPEED, 1);

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
     * Atualiza com base em tempo e input.
     * @param dT {@code double}
     * @param ie {@code InputEvent}
     */
    @Override
    public void onUpdate(double dT, InputEvent ie)
    {
        boolean fire = false;

        if(ie != null)
        {
            if(((KeyEvent) ie).getKeyCode() == KeyEvent.VK_SPACE)
            {
                if(((KeyEvent) ie).getID() == KeyEvent.KEY_PRESSED) { fire = true; }
            }
            else if(((KeyEvent) ie).getKeyCode() == KeyEvent.VK_UP)
            {
                if(((KeyEvent) ie).getID() == KeyEvent.KEY_PRESSED) { accel = true; }
                if(((KeyEvent) ie).getID() == KeyEvent.KEY_RELEASED) { accel = false; }
            }
            else if(((KeyEvent) ie).getKeyCode() == KeyEvent.VK_LEFT)
            {
                if(((KeyEvent) ie).getID() == KeyEvent.KEY_PRESSED) { rotationDir += 1; }
                if(((KeyEvent) ie).getID() == KeyEvent.KEY_RELEASED) { rotationDir -= 1; }
            }
            else if(((KeyEvent) ie).getKeyCode() == KeyEvent.VK_RIGHT)
            {
                if(((KeyEvent) ie).getID() == KeyEvent.KEY_PRESSED) { rotationDir -= 1; }
                if(((KeyEvent) ie).getID() == KeyEvent.KEY_RELEASED) { rotationDir += 1; }
            }
        }

        if(rotationDir > 1) { rotationDir = 1; }
        if(rotationDir < -1) { rotationDir = -1; }

        if(rotationDir != 0) { this.rotate(-rotationDir, dT); }
        if(accel) { this.accelerate(dT); }
        if(fire && this.cooldown <= 0) { this.shoot(); }

        if(this.cooldown > 0) { this.cooldown -= dT; }
        this.checkOutOfBounds();
    }

    /**
     * Método chamado quando a nave do jogador é derrotada.
     */
    @Override
    public void onDefeat()
    {
        if(this.planet == null) { this.parent.engine().destroy(this.parent); return; }

        this.parent.engine().disable(this.parent);
        ((PlanetBehaviour) this.planet.behaviour()).onPlayerDefeat();
    }

    /**
     * Ativa este PlayerShipBehaviour.
     */
    @Override
    public void onEnabled()
    {
        this.health = HEALTH;
        this.cooldown = 0;
    }

    /**
     * Verifica se a distância entre a nave e o planeta ultrapassa os limites definidos.
     */
    private void checkOutOfBounds()
    {
        if(this.parent.transform().position().distFrom(this.planet.transform().position()) > OUT_OF_BOUNDS_DIST)
        {
            this.takeDamage(999);
        }
    }
}
