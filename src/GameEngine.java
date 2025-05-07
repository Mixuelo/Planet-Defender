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
    public void destroy(IGameObject go)
    {
        if(go.behaviour() != null) { go.behaviour().onDestroy(); }
        if(this.enabled.remove(go)) { return; }
        this.disabled.remove(go);
    }

    private boolean exists(IGameObject go)
    {
        return this.enabled.contains(go) || this.disabled.contains(go);
    }

    public void addEnabled(IGameObject go)
    {
        if(!this.exists(go)) 
        {
            this.enabled.add((GameObject) go);
            ((GameObject) go).engine(this);
        }
    }

    public void addDisabled(IGameObject go)
    {
        if(!this.exists(go))
        {
            this.disabled.add((GameObject) go);
            ((GameObject) go).engine(this);
        }
    }

    public void enable(IGameObject go)
    {
        if(this.disabled.remove(go)) { this.enabled.add((GameObject) go); }
    }

    public void disable(IGameObject go)
    {
        if(this.enabled.remove(go)) { this.disabled.add((GameObject) go); }
    }

    public boolean isEnabled(IGameObject go)
    {
        if(this.enabled.contains(go)) { return true; }
        return false;
    }

    public boolean isDisabled(IGameObject go)
    {
        if(this.disabled.contains(go)) { return true; }
        return false;
    }

    public List<IGameObject> getEnabled()
    {
        List<IGameObject> list = new ArrayList<>();

        for(GameObject go : enabled)
        {
            list.add((IGameObject) go);
        }

        return list;
    }

    public List<IGameObject> getDisabled()
    {
        List<IGameObject> list = new ArrayList<>();

        for(GameObject go : disabled)
        {
            list.add((IGameObject) go);
        }

        return list;
    }

    public void destroyAll()
    {
        for(IGameObject go : enabled)
        {
            if(go.behaviour() != null) { go.behaviour().onDestroy(); }
        }
        for(IGameObject go : disabled)
        {
            if(go.behaviour() != null) { go.behaviour().onDestroy(); }
        }

        enabled = new ArrayList<>();
        disabled = new ArrayList<>();
    }

    public void run()
    {
        for(;;)
        {
            // ie = getUserInput();
            
            for(IGameObject go : enabled)
            {
                if(go instanceof MovingObject) { ((MovingObject) go).updateMovement(); }
                go.collider().onUpdate();
                //go.behaviour().onUpdate(dt, ie);
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
<<<<<<< HEAD
            go.behaviour().onCollision(list);
=======

            if (go.behaviour() != null) { go.behaviour().onCollision(list); }
>>>>>>> 67e55f285421673062c81764e84bb35d4cf398b1
        }
    }
}
