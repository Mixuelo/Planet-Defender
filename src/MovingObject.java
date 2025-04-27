public class MovingObject extends GameObject
{
    protected Point velocity;
    protected double topVelocity;
    protected double friction;

    public MovingObject(String n, Transform t, Collider c, Point velocity, double topVelocity, double friction)
    {
        super(n, t, c);
        this.velocity = velocity;
        this.topVelocity = topVelocity;
        this.friction = friction;
    }

    void updateMovement()
    {
        //TODO
    }

    void addVelocity(Point dVelocity)
    {
        //TODO
    }

    void setVelocity(Point dVelocity)
    {
        //TODO
    }
}
