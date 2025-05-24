package PlanetDefender;

import java.awt.event.InputEvent;
import java.util.List;
import Engine.*;

/**
 * Subclasse de CharacterBehaviour responsável pelo comportamento dos asteróides.
 * @author Miguel Alvito, Nicole Reis, Pedro Pinto
 * @version 1.0 (08/05/2025)
 */
public class AsteroidBehaviour extends EnemyBehaviour
{
    private double size;
    private static final double SCALE_FACTOR = 10;
    private static final double HEALTH_FACTOR = 0.5;
    private static final double DAMAGE_FACTOR = 1;
    private static final double DIVIDE_FACTOR = 0.6;
    private static final double DIVIDE_MAX_ANGLE = 45;
    private static final double SIZE_CUTOFF = 2;
    private static final double DISTANCE_CUTOFF = 700;
    private static final double ACCELERATION = 0.0065;
    private static final double COLLIDER_RADIUS = 0.5;
    private static final double IMAGE_SCALE = 0.01;
    private static final String IMAGE_PATH = "imgs/asteroide.png";
    private static final double OUT_OF_BOUNDS_DIST = 700;

    /**
     * Construtor.
     * @param size {@code double}
     */
    public AsteroidBehaviour(double size)
    {
        super((int) (size * HEALTH_FACTOR));
        this.size = size;
    }

    /**
     * Inicializa este AsteroidBehaviour.
     */
    @Override
    public void onInit()
    {
        super.onInit();

        double wantedScale = size * SCALE_FACTOR;
        this.parent.scale(wantedScale - this.parent.transform().scale());
    }

    /**
     * Atualiza este AsteroidBehaviour com base no tempo e input.
     * @param dT {@code double}
     * @param ie {@code InputEvent}
     */
    @Override
    public void onUpdate(double dT, InputEvent ie)
    {
        MovingObject p = (MovingObject) this.parent;

        Point dist = this.target.transform().position().subNew(this.parent.transform().position());
        double mag = dist.distFrom(new Point(0,0));

        double speed = (DISTANCE_CUTOFF - mag) * ACCELERATION * dT;
        if(speed <= 0) { return; } 

        Point norm = dist.multNew(1 / mag);
        Point res = norm.multNew(speed);
        p.addVelocity(res);

        this.checkOutOfBounds();
    }

    /**
     * Reage a colisões.
     * @param gol {@code List<IGameObject>}
     */
    @Override
    public void onCollision(List<IGameObject> gol)
    {
        super.onCollision(gol);

        for(IGameObject go : gol)
        {
            IBehaviour b = go.behaviour();

            if(b instanceof AsteroidBehaviour) { continue; }

            if(b instanceof CharacterBehaviour)
            {
                CharacterBehaviour cb = (CharacterBehaviour) b;
                cb.takeDamage((int) (size * DAMAGE_FACTOR));
                this.gameObject().engine().destroy(this.gameObject());
            }
        }
    }

    /**
     * Destroi o GameObject associado a este CharacterBehaviour.
     */
    @Override
    public void onDefeat()
    {
        this.divide();
        this.parent.engine().destroy(this.parent);
    }

    /**
     * Divide o asteróide em dois novos asteróides pequenos.
     */
    protected void divide()
    {
        double childSize = this.size * DIVIDE_FACTOR;
        if(childSize < SIZE_CUTOFF) { return; }

        Transform parentTransform = this.gameObject().transform();

        Transform t1 = parentTransform.clone();
        Point v1 = ((MovingObject) this.parent).velocity().clone();
        v1.rotateThis(new Point(0,0), (Math.random()-0.5)*2 * DIVIDE_MAX_ANGLE);
        t1.move(new Point(Math.random()*50 - 25, Math.random()*50 - 25), 0);
        GameObject asteroid1 = new MovingObject(this.parent.name() + "_child1", t1, new ColliderCircle(t1, t1.position(), COLLIDER_RADIUS), new AsteroidBehaviour(childSize), new SpriteShape(IMAGE_PATH, IMAGE_SCALE, t1), v1, ((MovingObject) this.parent).topVelocity(), ((MovingObject) this.parent).friction());

        Transform t2 = parentTransform.clone();
        Point v2 = ((MovingObject) this.parent).velocity().clone();
        v2.rotateThis(new Point(0,0), (Math.random()-0.5)*2 * DIVIDE_MAX_ANGLE);
        t2.move(new Point(Math.random()*50 - 25, Math.random()*50 - 25), 0);
        GameObject asteroid2 = new MovingObject(this.parent.name() + "_child2", t2, new ColliderCircle(t2, t2.position(), COLLIDER_RADIUS), new AsteroidBehaviour(childSize), new SpriteShape(IMAGE_PATH, IMAGE_SCALE, t2), v2, ((MovingObject) this.parent).topVelocity(), ((MovingObject) this.parent).friction());

        ((AsteroidBehaviour) asteroid1.behaviour()).target(this.target);
        ((AsteroidBehaviour) asteroid2.behaviour()).target(this.target);

        this.gameObject().engine().addEnabled(asteroid1);
        this.gameObject().engine().addEnabled(asteroid2);
    }

    /**
     * Verifica se a distância entre o asteroid e o target é maior que a distância definida por OUT_OF_BOUNDS_DIST.
     */
    private void checkOutOfBounds()
    {
        if(this.parent.transform().position().distFrom(this.target.transform().position()) > OUT_OF_BOUNDS_DIST)
        {
            this.takeDamage(999);
        }
    }
}
