/**
 *
 */
public class GameObject implements IGameObject
{
    private String name;
    private Transform transform;
    private Collider collider;

    public GameObject(String n, Transform t, Collider c)
    {
        name = n;
        transform = t;
        collider = c;
    }

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
