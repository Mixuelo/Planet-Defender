import java.util.List;

public interface IGameEngine
{
    public void addEnable();

    public void addDisable();

    public void enable();

    public void disable();

    public boolean isEnabled();

    public boolean isDisabled();

    public List<IGameObject> getEnabled();

    public List<IGameObject> getDisabled();

    public void destroy();

    public void destroyAll();

    public void run();

    public void checkCollisions();
}
