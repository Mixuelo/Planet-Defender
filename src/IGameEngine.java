import java.util.List;

/**
 * Interface implementada pela classe GameEngine.
 * @author Miguel Alvito, Nicole Reis e Pedro Pinto
 * @version 1.0 (08/05/2025)
 */
public interface IGameEngine
{
    /**
     * Adiciona um novo IGameObject à lista de ativos.
     * @param go {@code IGameObject}
     */
    public void addEnabled(IGameObject go);

    /**
     * Adiciona um novo IGameObject à lista de inativos.
     * @param go {@code IGameObject}
     */
    public void addDisabled(IGameObject go);

    /**
     * Ativa um IGameObject da IGameEngine.
     * @param go {@code IGameObject}
     */
    public void enable(IGameObject go);

    /**
     * Desativa um IGameObject da IGameEngine.
     * @param go {@code IGameObject}
     */
    public void disable(IGameObject go);

    /**
     * Verifica se um IGameObject está ativo.
     * @param go {@code IGameObject}
     * @return true se estiver ativo, false se não {@code boolean}
     */
    public boolean isEnabled(IGameObject go);

    /**
     * Verifica se um IGameObject está inativo.
     * @param go {@code IGameObject}
     * @return true se estiver inativo, false se não {@code boolean}
     */
    public boolean isDisabled(IGameObject go);

    /**
     * Devolve todos os IGameObject's ativos.
     * @return {@code List<IGameObject>}
     */
    public List<IGameObject> getEnabled();

    /**
     * Devolve todos os IGameObject's inativos.
     * @return {@code List<IGameObject>}
     */
    public List<IGameObject> getDisabled();

    /**
     * Destroy IGameObject go whether it is enabled or disabled
     * pre: go != null
     * pos: go.onDestroy()
     */
    public void destroy(IGameObject go);

    /**
     * Destroy all IGameObjects
     * pos: calls onDestroy() for each IGameObject
     */
    public void destroyAll();

    /**
     * Generates a new frame:
     * Get user input from UI
     * update all the enabled GameObjects
     * check collisions and send info to GameObjects
     * update UI
     * pos: UI.input() &&
     *      calls Behaviour.onUpdate() for all enabled objects &&
     *      Behaviour.checkCollisions() &&
     *      UI.draw()
     */
    public void run();

    /**
     * Check collisions for all the enabled objects
     * pos: calls Behaviour.onCollision(gol) for all enabled GameObjects
     *      passing in the list of all the objects that collided with each IGameObject
     */
    public void checkCollisions();
}
