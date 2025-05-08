import java.awt.event.InputEvent;
import java.util.List;

/**
 * Subclasse de Behaviour responsável pelo comportamento dos tiros disparados.
 * @author Miguel Alvito, Nicole Reis e Pedro Pinto
 * @version 1.0 (08/05/2025)
 */
public class BulletBehaviour extends Behaviour
{
    /**
     * Método para atualizar o estado da bala a cada frame.
     * @param dT {@code double}
     * @param ie {@code InputEvent}
     */
    @Override
    public void onUpdate(double dT, InputEvent ie)
    {
        super.onUpdate(dT, ie);

        Point pos = this.gameObject().transform().position();

        if(pos.x() < -50 || pos.x() > 850 || pos.y() < -50 || pos.y() > 650)
        {
            this.gameObject().engine().destroy(this.gameObject());
        }
    }

    /**
     * Método usado para reagir a colisões.
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
                cb.takeDamage(1);
            }
            else if(b instanceof BombBehaviour)
            {
                ((BombBehaviour) b).explode();
            }
        }
        this.gameObject().engine().destroy(this.gameObject());
    }
}
