import java.util.ArrayList;
import java.util.List;

/** Classe para o GameEngine, definido por uma lista de GameObjects.
 *  @author Miguel Alvito, Nicole Reis e Pedro Pinto
 *  @version 1.0 (2025-04-02)
 */
public class GameEngine implements IGameEngine
{
    private ArrayList<GameObject> objects;

    /**
     * Construtor para o GameEngine.
     */
    public GameEngine()
    {
        objects = new ArrayList<>();
    }

    public ArrayList<GameObject> objects()
    {
        return this.objects;
    }

    /**
     * Adiciona um {@code GameObject} ao GameEngine.
     * @param go {@code GameObject}
     */
    public void add(GameObject go)
    {
        objects.add(go);
    }

    /**
     * Remove um {@code GameObject} do GameEngine.
     * @param go {@code GameObject}
     */
    public void destroy(GameObject go)
    {
        objects.remove(go);
    }

    public void addEnable(GameObject go)
    {
        //TODO
    }

    public void addDisable(GameObject go)
    {
        //TODO
    }

    public void enable()
    {
        //TODO
    }

    public void disable()
    {
        //TODO
    }

    public boolean isEnabled(GameObject go)
    {
        //TODO
        return false;
    }

    public boolean isDisabled(GameObject go)
    {
        //TODO
        return false;
    }

    public List<IGameObject> getEnabled()
    {
        //TODO
        return null;
    }

    public List<IGameObject> getDisabled()
    {
        //TODO
        return null;
    }

    public void destroyAll()
    {
        //TODO
    }

    public void run()
    {
        //TODO
    }

    public void checkCollisions()
    {
        //TODO
    }
}
