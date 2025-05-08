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
     * Inicializa este IBehaviour.
     */
    void onInit();

    /**
     * Ativa este IBehaviour.
     */
    void onEnabled();

    /**
     * Desativa este IBehaviour.
     */
    void onDisabled();

    /**
     * Destroi este IBehaviour.
     */
    void onDestroy();

    /**
     * Atualiza este IBehaviour com base no tempo e no input.
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
