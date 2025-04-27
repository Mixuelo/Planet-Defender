import java.awt.event.InputEvent;
import java.util.List;

public interface IBehaviour
{
    public IGameObject gameObject();

    public void gameObject(IGameObject go);

    void onInit();

    void onEnabled();

    void onDisabled();

    void onDestroy();

    void onUpdate(double dT, InputEvent ie);

    void onCollision(List<IGameObject> gol);
}
