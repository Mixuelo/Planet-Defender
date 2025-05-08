import java.awt.event.InputEvent;
import java.util.List;

/**
 * Classe abstrata que representa o que cada objeto pode fazer no jogo e implementa a interface IBehaviour.
 * @author Miguel Alvito, Nicole Reis e Pedro Pinto
 * @version 1.0 (08/05/2025)
 */
public abstract class Behaviour implements IBehaviour
{
    protected GameObject parent;

    /**
     * Retorna o GameObject associado a este Behaviour (getter).
     * @return parent {@code GameObject}
     */
    public GameObject gameObject()
    {
        return parent;
    }

    /**
     * Define um GameObject para estar associado a este Behaviour (setter).
     * @param go {@code GameObject}
     */
    public void gameObject(IGameObject go)
    {
        this.parent = (GameObject) go;
    }

    /**
     * Inicializa este Behaviour.
     */
    public void onInit()
    {
        //TODO
    }

    /**
     * Ativa este Behaviour.
     */
    public void onEnabled()
    {
        //TODO
    }

    /**
     * Desativa este Behaviour.
     */
    public void onDisabled()
    {
        //TODO
    }

    /**
     * Destroi este Behaviour.
     */
    public void onDestroy()
    {
        //TODO
    }

    /**
     * Atualiza este Behaviour com base no tempo e no input.
     * @param dT {@code double}
     * @param ie {@code InputEvent}
     */
    public void onUpdate(double dT, InputEvent ie)
    {
        //TODO
    }

    /**
     * Reage a colisões.
     * @param gol {@code List<IGameObject>}
     */
    public void onCollision(List<IGameObject> gol)
    {
        //TODO
    }
}
