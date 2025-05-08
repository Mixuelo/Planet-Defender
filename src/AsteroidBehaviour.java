import java.util.List;

/**
 * Subclasse de CharacterBehaviour responsável pelo comportamento dos asteróides.
 * @author Miguel Alvito, Nicole Reis, Pedro Pinto
 * @version 1.0 (08/05/2025)
 */
public class AsteroidBehaviour extends CharacterBehaviour
{
    private boolean large;

    /**
     * Construtor.
     * @param health {@code int}
     * @param large {@code boolean}
     */
    public AsteroidBehaviour(int health, boolean large)
    {
        super(health);
        this.large = large;
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
                cb.takeDamage(5);
                this.gameObject().engine().destroy(this.gameObject());
            }
            else if(b instanceof BulletBehaviour)
            {
                divide();
                this.gameObject().engine().destroy(go);
            }
        }
    }

    /**
     * Se este asteróide for grande, divide-o em dois novos asteróides pequenos (apaga o original).
     */
    private void divide()
    {
        if(this.large)
        {
            Transform parentTransform = this.gameObject().transform();
            Point parentPos = parentTransform.position();

            int layer = parentTransform.layer();
            double angle = parentTransform.angle();
            double scale = parentTransform.scale() * 0.5f;

            Transform t1 = new Transform(
                    new Point(parentPos.x() + (Math.random()*20 - 10), parentPos.y() + (Math.random()*20 - 10)),
                    layer, angle, scale
            );
            //TODO: definir um raio para os asteroids pequenos
            GameObject asteroid1 = new GameObject("Small Asteroid 1", t1, new ColliderCircle(t1, t1.position(), 2), new AsteroidBehaviour(this.health()/2, false));

            Transform t2 = new Transform(
                    new Point(parentPos.x() + (Math.random()*20 - 10), parentPos.y() + (Math.random()*20 - 10)),
                    layer, angle, scale
            );
            //TODO: definir um raio para os asteroids pequenos
            GameObject asteroid2 = new GameObject("Small Asteroid 2", t2, new ColliderCircle(t2, t2.position(), 2), new AsteroidBehaviour(this.health()/2, false));

            this.gameObject().engine().addEnabled(asteroid1);
            this.gameObject().engine().addEnabled(asteroid2);
            this.gameObject().engine().destroy(this.gameObject());
        }
    }
}
