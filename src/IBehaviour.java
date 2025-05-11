import java.awt.event.InputEvent;
import java.util.List;

/**
 * Interface implementada pela classe Behaviour.
 * @author Miguel Alvito, Nicole Reis e Pedro Pinto
 * @version 1.0 (08/05/2025)
 */
public interface IBehaviour
{
    /**
     * Retorna o IGameObject associado a este IBehaviour (getter).
     * @return parent {@code IGameObject}
     */
    public IGameObject gameObject();

    /**
     * Define um IGameObject para estar associado a este IBehaviour (setter).
     * @param go {@code IGameObject}
     */
    public void gameObject(IGameObject go);

    /**
     * Este método é chamado quando o GameObject é adicionado à GameEngine
     */
    void onInit();

    /**
     * Este método é chamado quando o GameObject é ativado pela GameEngine
     */
    void onEnabled();

    /**
     * Este método é chamado quando o GameObject é desativado pela GameEngine
     */
    void onDisabled();

    /**
     * Este método é chamado quando o GameObject é destruído pela GameEngine
     */
    void onDestroy();

    /**
     * Este método é chamado a cada frame pela GameEngine
     * @param dT {@code double}
     * @param ie {@code InputEvent}
     */
    void onUpdate(double dT, InputEvent ie);

    /**
     * Reage a colisões.
     * @param gol {@code List<IGameObject>}
     */
    void onCollision(List<IGameObject> gol);
}
