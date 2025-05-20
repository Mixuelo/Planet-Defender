package Engine;

/**
 * Subclasse de GameObject que representa objetos que se podem mover.
 * @author Miguel Alvito, Nicole Reis e Pedro Pinto
 * @version 1.0 (08/05/2025)
 */
public class MovingObject extends GameObject
{
    protected Point velocity;
    protected double topVelocity;
    protected double friction;

    /**
     * Construtor.
     * @param n {@code String}
     * @param t {@code Transform}
     * @param c {@code Collider}
     * @param velocity {@code Point}
     * @param topVelocity {@code double}
     * @param friction {@code double}
     */
    public MovingObject(String n, Transform t, Collider c, Behaviour b, Point velocity, double topVelocity, double friction)
    {
        super(n, t, c, b);
        this.velocity = velocity.clone();
        this.topVelocity = topVelocity;
        this.friction = friction;
    }

    /**
     * Devolve a velocidade deste MovingObject (getter)
     * @return velocity {@code Point}
     */
    public Point velocity()
    {
        return this.velocity;
    }

    /**
     * Atualiza o movimento deste MovingObject.
     */
    public void updateMovement()
    {
        this.transform().position().addThis(velocity);
        this.velocity.multThis(this.friction);
    }

    /**
     * Adiciona velocidade em x e em y a este MovingObject.
     * @param dVelocity {@code Point}
     */
    public void addVelocity(Point dVelocity)
    {
        this.velocity.addThis(dVelocity);
        this.capVelocity();
    }

    /**
     * Define uma velocidade para este MovingObject.
     * @param velocity {@code Point}
     */
    public void setVelocity(Point velocity)
    {
        this.velocity = velocity.clone();
        this.capVelocity();
    }

    /**
     * Limita a velocidade do MovingObject para que não ultrapasse o valor máximo definido.
     */
    private void capVelocity()
    {
        double curr = this.velocity.distFrom(new Point(0, 0));
        if(curr <= this.topVelocity) return;

        double scale = this.topVelocity / curr;
        this.velocity.multThis(scale);
    }
}
