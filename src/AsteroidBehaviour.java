import java.awt.event.InputEvent;
import java.util.List;

/**
 * Subclasse de CharacterBehaviour responsável pelo comportamento dos asteróides.
 * @author Miguel Alvito, Nicole Reis, Pedro Pinto
 * @version 1.0 (08/05/2025)
 */
public class AsteroidBehaviour extends EnemyBehaviour
{
    private double size;
    private static final double SCALE_FACTOR = 0.1;
    private static final double HEALTH_FACTOR = 5;
    private static final double DAMAGE_FACTOR = 5;
    private static final double DIVIDE_FACTOR = 0.4;
    private static final double SIZE_CUTOFF = 20;
    private static final double DISTANCE_CUTOFF = 1000;
    private static final double ACCELERATION = 5;
    private static final double COLLIDER_RADIUS = 10;

    public AsteroidBehaviour(double size)
    {
        super((int) (size * HEALTH_FACTOR));
        this.size = size;
    }

    @Override
    public void onInit() {
        super.onInit();

        double wantedScale = size * SCALE_FACTOR;
        this.parent.scale(wantedScale - this.parent.transform().scale());
    }

    @Override
    public void onUpdate(double dT, InputEvent ie) {
        MovingObject p = (MovingObject) this.parent;

        Point dist = this.target.transform().position().subNew(this.parent.transform().position());
        double mag = dist.distFrom(new Point(0,0));

        double speed = (DISTANCE_CUTOFF - mag) * ACCELERATION * dT;
        if(speed <= 0) { return; } 

        Point norm = dist.multNew(1 / mag);
        Point res = norm.multNew(speed);
        p.addVelocity(res);
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
            if(b instanceof CharacterBehaviour)
            {
                CharacterBehaviour cb = (CharacterBehaviour) b;
                cb.takeDamage((int) (size * DAMAGE_FACTOR));
                this.gameObject().engine().destroy(this.gameObject());
            }
        }
    }

    @Override
    void onDefeat() {
        this.divide();
        this.parent.engine().destroy(this.parent);
    }

    /**
     * Se este asteróide for grande, divide-o em dois novos asteróides pequenos (apaga o original).
     */
    private void divide()
    {
        double childSize = this.size * DIVIDE_FACTOR;
        if(childSize < SIZE_CUTOFF) { return; }

        Transform parentTransform = this.gameObject().transform();

        Transform t1 = parentTransform.clone();
        t1.move(new Point(Math.random()*20 - 10, Math.random()*20 - 10), 0);
        GameObject asteroid1 = new GameObject(this.parent.name() + "_child1", t1, new ColliderCircle(t1, t1.position(), COLLIDER_RADIUS), new AsteroidBehaviour(childSize));

        Transform t2 = parentTransform.clone();
        t2.move(new Point(Math.random()*20 - 10, Math.random()*20 - 10), 0);
        GameObject asteroid2 = new GameObject(this.parent.name() + "_child2", t2, new ColliderCircle(t2, t2.position(), COLLIDER_RADIUS), new AsteroidBehaviour(childSize));

        this.gameObject().engine().addEnabled(asteroid1);
        this.gameObject().engine().addEnabled(asteroid2);
    }
}
