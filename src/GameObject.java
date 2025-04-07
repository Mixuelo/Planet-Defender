import java.util.Objects;

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
     * Verifica se este {@code GameObject} é igual a outro objeto.
     * @param obj {@code Object}
     * @return true se for, false se não {@code boolean}
     */
    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        GameObject that = (GameObject) obj;
        return name.equals(that.name);
    }

    /**
     * Retorna um valor de hash único para este {@code GameObject}.
     * @return {@code int}
     */
    @Override
    public int hashCode()
    {
        return Objects.hash(name, transform, collider);
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
        return transform;
    }

    /**
     * @return the Collider of the GameObject with its centroid at this.transform().position() {@code Collider}
     */
    public Collider collider()
    {
        return collider;
    }

    /**
     * Este método move o GameObject, pelo vetor dPos.
     * @param dPos {@code Point}
     * @param dlayer {@code int}
     */
    public void move(Point dPos, int dlayer)
    {
        this.transform.move(dPos, dlayer);
        this.collider.move(dPos);
    }

    /**
     * Este método roda o GameObject, por dTheta graus.
     * @param dTheta {@code double}
     */
    public void rotate(double dTheta)
    {
        this.transform.rotate(dTheta);
        this.collider.rotate(dTheta);
    }

    /**
     * Este método atualiza a escala do GameObject, adicionando dScale ao valor da escala.
     * @param dScale {@code double}
     */
    public void scale(double dScale)
    {
        this.transform.scale(dScale);
        this.collider.scale(dScale);
    }

    /**
     * Verifica se existe colisão entre este GameObject e outro GameObject.
     * @param go {@code GameObject}
     * @return true, se houver colisão, false, se não houver colisão {@code boolean}
     */
    public boolean checkCollision(GameObject go)
    {
        if(go.transform.layer() != this.transform.layer()) return false;
        if(this.equals(go))                                return false;
        return collider.checkCollision(go.collider);
    }
}
