package Engine;

import java.util.Objects;

/**
 *  Classe para o GameObject.
 *  @author Miguel Alvito, Nicole Reis e Pedro Pinto
 *  @version 1.1 (2025-05-05)
 */
public class GameObject implements IGameObject
{
    private String name;
    private Transform transform;
    private Collider collider;
    private Behaviour behaviour;
    private Shape shape;
    private GameEngine engine;

    /**
     * Contrutor de um GameObject.
     * @param n {@code String}
     * @param t {@code Transform}
     * @param c {@code Collider}
     * @param b {@code Behaviour}
     */
    @Deprecated
    public GameObject(String n, Transform t, Collider c, Behaviour b)
    {
        this.name = n;
        this.transform = t;
        this.collider = c;
        if (this.collider != null && this.collider.transform == null) this.collider.transform = t;
        this.behaviour = b;
        if (this.behaviour != null) this.behaviour.parent = this;
    }

    /**
     * Contrutor de um GameObject.
     * @param n {@code String}
     * @param t {@code Transform}
     * @param c {@code Collider}
     * @param b {@code Behaviour}
     * @param s {@code Shape}
     */
    public GameObject(String n, Transform t, Collider c, Behaviour b, Shape s)
    {
        this.name = n;
        this.transform = t;
        this.collider = c;
        if (this.collider != null && this.collider.transform == null) this.collider.transform = t;
        this.behaviour = b;
        if (this.behaviour != null) this.behaviour.parent = this;
        this.shape = s;
    }

    /** Retorna a GameEngine ao qual o GameObject pertence */
    public IGameEngine engine() 
    {
        return this.engine;
    }

    /** Define a GameEngine ao qual o GameObject pertence */
    public void engine(GameEngine e) 
    {
        this.engine = e;
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

    /** Retorna o objeto Behaviour do GameObject */
    public Behaviour behaviour()
    {
        return behaviour;
    }

    /** Retorna o objeto Shape do GameObject */
    public Shape shape()
    {
        return shape;
    }

    /**
     * Este método move o GameObject, pelo vetor dPos.
     * @param dPos {@code Point}
     * @param dlayer {@code int}
     */
    public void move(Point dPos, int dlayer)
    {
        this.transform.move(dPos, dlayer);
        //this.collider.move(dPos);
        this.shape.move(dPos);
    }

    /**
     * Este método roda o GameObject, por dTheta graus.
     * @param dTheta {@code double}
     */
    public void rotate(double dTheta)
    {
        this.transform.rotate(dTheta);
        //this.collider.rotate(dTheta);
        this.shape.rotate(dTheta);
    }

    /**
     * Este método atualiza a escala do GameObject, adicionando dScale ao valor da escala.
     * @param dScale {@code double}
     */
    public void scale(double dScale)
    {
        this.transform.scale(dScale);
        //this.collider.scale(dScale);
        this.shape.scale(dScale);
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
