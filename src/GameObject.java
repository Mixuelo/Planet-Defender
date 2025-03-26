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
     * Este método devolve uma string com os dados do game object".
     * @return {@code String}
     */
    public String toString()
    {
        return name + "\n" + transform.toString() + "\n" + collider.toString();
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
