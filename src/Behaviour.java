import java.awt.event.InputEvent;
import java.util.List;

public abstract class Behaviour implements IBehaviour
{
    protected GameObject parent;

    public GameObject gameObject()
    {
        return parent;
    }

    public void gameObject(IGameObject go)
    {
        this.parent = (GameObject) go;
    }

    public void onInit()
    {
        //TODO
    }

    public void onEnabled()
    {
        //TODO
    }

    public void onDisabled()
    {
        //TODO
    }

    public void onDestroy()
    {
        //TODO
    }

    public void onUpdate(double dT, InputEvent ie)
    {
        //TODO
    }

    public void onCollision(List<IGameObject> gol)
    {
        //TODO
    }
}
