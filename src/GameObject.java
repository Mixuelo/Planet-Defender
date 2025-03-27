/**
 *  Classe para o GameObject.
 *  @author Miguel Alvito, Nicole Reis e Pedro Pinto
 *  @version 1.0 (2024-03-25)
 */
public class GameObject implements IGameObject
{
    private String name;
    private Transform transform;
    private Collider collider;

    /**
     * Contrutor de um GameObject.
     * @param n {@code String}
     * @param t {@code Transform}
     * @param c {@code Collider}
     */
    public GameObject(String n, Transform t, Collider c)
    {
        name = n;
        transform = t;
        collider = c;
    }

    /**
     * Este método retorna o nome do GameObject.
     * @return the name of the GameObject {@code String}
     */
    public String name()
    {
        return name;
    }

    /**
     * Este método devolve uma string com os dados do GameObject.
     * @return {@code String}
     */
    public String toString()
    {
        return name + "\n" + transform.toString() + "\n" + collider.toString();
    }

    /**
     * @return the Transform of the GameObject {@code Transform}
     */
    public Transform transform()
    {
        return (Transform) transform;
    }

    /**
     * @return the Collider of the GameObject with its centroid at this.transform().position() {@code Collider}
     */
    public Collider collider()
    {
        return (Collider) collider;
    }
}
