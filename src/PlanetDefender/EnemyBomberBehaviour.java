package PlanetDefender;

import java.awt.Color;
import java.awt.event.InputEvent;
import Engine.*;

/**
 * Subclasse de CharacterBehaviour responsável pelo comportamento dos bombardeiros.
 * @author Miguel Alvito, Nicole Reis e Pedro Pinto
 * @version 1.0 (08/05/2025)
 */
public class EnemyBomberBehaviour extends EnemyBehaviour
{

    private double lastDist;
    private boolean hasBomb;
    private static final int HEALTH = 5;
    private static final double SPEED = 30;
    private static final double BOMB_SPEED = 20;
    private static final double BOMB_RADIUS = 12;
    private static final double OUT_OF_BOUNDS_DIST = 700;

    /**
     * Construtor.
     */
    public EnemyBomberBehaviour()
    {
        super(HEALTH);
        this.lastDist = Integer.MAX_VALUE;
        this.hasBomb = true;
    }

    /**
     * Inicializa este EnemyBomberBehaviour.
     */
    @Override
    public void onInit() 
    {
        double ang = Math.toRadians(this.parent.transform().angle());

        ((MovingObject) this.parent).setVelocity(
            new Point(
                -Math.sin(ang) * SPEED,
                Math.cos(ang) * SPEED
            )
        );
    }

    /**
     * Faz com que este bombardeiro "largue" uma bomba.
     */
    private void dropBomb()
    {
        Point dist = this.target.transform().position().subNew(this.parent.transform().position());
        double ang = Math.toDegrees(Math.atan2(dist.y(), dist.x())) - 90;
        ang %= 360;
        
        Transform t = this.parent.transform().clone();
        MovingObject bomb = new MovingObject(this.parent.name() + "_bomb", t, new ColliderCircle(t, new Point(0,0), BOMB_RADIUS), new BombBehaviour(this.parent), new CircleShape(BOMB_RADIUS, Color.BLACK, t, -1), new Point(0,0), BOMB_SPEED, 0);

        bomb.setVelocity(
            new Point(
                -Math.sin(Math.toRadians(ang)) * BOMB_SPEED,
                Math.cos(Math.toRadians(ang)) * BOMB_SPEED
            )
        );

        this.parent.engine().addEnabled(bomb);
    }

    /**
     * Atualiza este EnemyBomberBehaviour com base em tempo e input.
     * @param dT {@code double}
     * @param ie {@code InputEvent}
     */
    @Override
    public void onUpdate(double dT, InputEvent ie) 
    {
        double currDist = this.parent.transform().position().distFrom(this.target.transform().position());

        if(this.hasBomb && currDist > lastDist)
        {
            this.hasBomb = false;
            this.dropBomb();
        }

        this.lastDist = currDist;

        this.checkOutOfBounds();
    }

    /**
     * Verifica se a distância entre o bombardeiro e o target ultrapassa os limites definidos.
     */
    private void checkOutOfBounds()
    {
        if(this.parent.transform().position().distFrom(this.target.transform().position()) > OUT_OF_BOUNDS_DIST)
        {
            this.takeDamage(999);
        }
    }

    /**
     * Destroi o GameObject associado a este EnemyBomberBehaviour.
     */
    @Override
    protected void onDefeat()
    {
        super.onDefeat();

        Transform effectTransform = this.parent.transform().clone();
        effectTransform.move(new Point(0,0), 2);
        effectTransform.rotate(-effectTransform.angle());
        EffectObject effectExplosion = new EffectObject(this.parent.name() + "_defeat_explosion", effectTransform, "imgs/boom", ".png", 0.4, 17, 17); 
        Transform effectTransformShip = this.parent.transform().clone();
        effectTransformShip.move(new Point(0,0), 2);
        EffectObject effectShip = new EffectObject(this.parent.name() + "_defeat_ship", effectTransformShip, "imgs/bombardeiro_derrota", ".png", 0.05, 1, 2); 
        this.parent.engine().addEnabled(effectExplosion);
        this.parent.engine().addEnabled(effectShip);
    }
}
