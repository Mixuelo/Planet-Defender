import java.util.ArrayList;
import java.util.List;

/** Classe para o GameEngine, definido por uma lista de GameObjects.
 *  @author Miguel Alvito, Nicole Reis e Pedro Pinto
 *  @version 1.0 (2025-04-02)
 */
public class GameEngine implements IGameEngine
{
    private ArrayList<GameObject> enabled;
    private ArrayList<GameObject> disabled;

    private final double dt = 0.0166666f;

    /**
     * Construtor para o GameEngine.
     */
    public GameEngine()
    {
        enabled = new ArrayList<>();
        disabled = new ArrayList<>();
    }

    /** TODO: REMOVER ESTA FUNÇAO */
    public ArrayList<GameObject> objects()
    {
        return this.enabled;
    }

    /** TODO: REMOVER ESTA FUNÇAO
     * Adiciona um {@code GameObject} ao GameEngine.
     * @param go {@code GameObject}
     */
    public void add(GameObject go)
    {
        this.addEnabled(go);
    }

    /**
     * Remove um {@code GameObject} do GameEngine.
     * @param go {@code GameObject}
     */
    public void destroy(GameObject go)
    {
        this.enabled.remove(go);
        this.disabled.remove(go);
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
        for(;;)
        {
            // ie = getUserInput();
            
            for(IGameObject go : enabled)
            {
                //go.behaviour().onUpdate(dt, ie);
                go.collider().onUpdate();
            }

            // envia lista de colisões para todos os
            // IGameObject em enabled
            this.checkCollisions();

            // envia a lista de IGameObjects em enabled para o GUI
        }
    }

    public void checkCollisions()
    {
        // TODO: ver se existe uma forma melhor de fazer isto em causar um desperdicio de memoria de O(n²)
        for(GameObject go : enabled)
        {
            ArrayList<IGameObject> list = new ArrayList<>();

            for(GameObject other : enabled)
            {
                if(go != other && go.transform().layer() == other.transform().layer() && go.checkCollision(other))
                {
                    list.add(other);
                }
            }

            go.behaviour().onCollision(list);
        }
    }
}
