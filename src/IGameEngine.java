import java.util.List;

public interface IGameEngine
{
    public void addEnabled(IGameObject go);

    public void addDisabled(IGameObject go);

    public void enable(IGameObject go);

    public void disable(IGameObject go);

    public boolean isEnabled(IGameObject go);

    public boolean isDisabled(IGameObject go);

    public List<IGameObject> getEnabled();

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
