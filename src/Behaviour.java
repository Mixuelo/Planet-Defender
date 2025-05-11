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
     * Esta funçao é chamada quando o GameObject é adicionado à GameEngine
     */
    public void onInit()
    {

    }

    /**
     * Esta funçao é chamada quando o GameObject é ativado pela GameEngine
     */
    public void onEnabled()
    {

    }

    /**
     * Esta funçao é chamada quando o GameObject é desativado pela GameEngine
     */
    public void onDisabled()
    {

    }

    /**
     * Esta funçao é chamada quando o GameObject é destruído pela GameEngine
     */
    public void onDestroy()
    {

    }

    /**
     * Esta função é chamada a cada frame pela GameEngine
     * @param dT {@code double}
     * @param ie {@code InputEvent}
     */
    public void onUpdate(double dT, InputEvent ie)
    {

    }

    /**
     * Reage a colisões.
     * @param gol {@code List<IGameObject>}
     */
    public void onCollision(List<IGameObject> gol)
    {

    }
}
