package PlanetDefender;

import java.awt.event.InputEvent;
import java.util.List;
import Engine.*;

/**
 * Subclasse de Behaviour responsável pelo comportamento dos tiros disparados.
 * @author Miguel Alvito, Nicole Reis e Pedro Pinto
 * @version 1.0 (08/05/2025)
 */
public class BulletBehaviour extends Behaviour
{
    private GameObject owner;

    private static final int SCREEN_WIDTH = 800;
    private static final int SCREEN_HEIGHT = 600;
    private static final int MARGIN = 50;

    private static final int LEFT_BORDER = -MARGIN;
    private static final int RIGHT_BORDER = SCREEN_WIDTH + MARGIN;
    private static final int UP_BORDER = -MARGIN;
    private static final int DOWN_BORDER = SCREEN_HEIGHT + MARGIN;

    /**
     * Construtor.
     * @param owner {@code GameObject}
     */
    public BulletBehaviour(GameObject owner)
    {
        this.owner = owner;
    }

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

        if(pos.x() < LEFT_BORDER || pos.x() > RIGHT_BORDER || pos.y() < UP_BORDER || pos.y() > DOWN_BORDER)
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
            if(b instanceof CharacterBehaviour && !go.equals(this.owner))
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
