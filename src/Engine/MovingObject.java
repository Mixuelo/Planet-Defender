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
     * Construtor.
     * @param n {@code String}
     * @param t {@code Transform}
     * @param c {@code Collider}
     * @param s {@code Shape}
     * @param velocity {@code Point}
     * @param topVelocity {@code double}
     * @param friction {@code double}
     */
    public MovingObject(String n, Transform t, Collider c, Behaviour b, Shape s, Point velocity, double topVelocity, double friction)
    {
        super(n, t, c, b, s);
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
     * Devolve a velocidade máxima deste MovingObject (getter)
     * @return topVelocity {@code double}
     */
    public double topVelocity()
    {
        return this.topVelocity;
    }

    /**
     * Devolve a fricção deste MovingObject (getter)
     * @return friction {@code double}
     */
    public double friction()
    {
        return this.friction;
    }

    /**
     * Atualiza o movimento deste MovingObject com base no tempo.
     * @param dt {@code double}
     */
    public void updateMovement(double dt)
    {
        this.transform().position().addThis(velocity.multNew(dt));

        double mag = this.velocity.distFrom(new Point(0,0));
        if(mag < 1e-6) { return; }
        double target = Math.max(mag - this.friction * dt, 0);
        this.velocity.multThis(target / mag);
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
