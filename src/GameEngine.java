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

    public void addEnabled(IGameObject go)
    {
        //TODO
    }

    public void addDisabled(IGameObject go)
    {
        //TODO
    }

    public void enable(IGameObject go)
    {
        //TODO
    }

    public void disable(IGameObject go)
    {
        //TODO
    }

    public boolean isEnabled(IGameObject go)
    {
        //TODO
        return false;
    }

    public boolean isDisabled(IGameObject go)
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

    public void destroy(IGameObject go)
    {
        //TODO
    }

    public void destroyAll()
    {
        //TODO
    }

    public void run()
    {
        /*
        for(;;)
        {
            ie = getUserInput();
            for(IGameObject go : enabled)
            {
                go.behaviour().onUpdate(dt, ie);
                go.collisor().onUpdate();
            }

            // envia lista de colisões para todos os
            // IGameObject em enabled

            // envia a lista de IGameObjects em enabled para o GUI
        }
        */
    }

    public void checkCollisions()
    {
        //TODO
    }
}
