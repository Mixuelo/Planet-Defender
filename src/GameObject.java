/**
 *
 */
public class GameObject implements IGameObject
{
    private Transform transform;
    private Collider collider;
    private String name;

    /**
     * @return the name of the GameObject
     */
    public String name()
    {
        return name;
    }

    /**
     * Este método devolve uma string na forma "name".
     * @return {@code String}
     */
    public String toString()
    {
        return name;
    }

    /**
     * @return the Transform of the GameObject
     */
    public ITransform transform()
    {
        return (ITransform) transform;
    }

    /**
     * @return the Collider of the GameObject with its centroid at this.transform().position()
     */
    public ICollider collider()
    {
        return (ICollider) collider;
    }
}
