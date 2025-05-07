public class MovingObject extends GameObject
{
    protected Point velocity;
    protected double topVelocity;
    protected double friction;

    public MovingObject(String n, Transform t, Collider c, Point velocity, double topVelocity, double friction)
    {
        super(n, t, c);
        this.velocity = velocity.clone();
        this.topVelocity = topVelocity;
        this.friction = friction;
    }

    public void updateMovement()
    {
        this.transform().position().addThis(velocity);
        this.velocity.multThis(this.friction);
    }

    public void addVelocity(Point dVelocity)
    {
        this.velocity.addThis(dVelocity);
        this.capVelocity();
    }

    public void setVelocity(Point velocity)
    {
        this.velocity = velocity.clone();
        this.capVelocity();
    }

    private void capVelocity()
    {
        double curr = this.velocity.distFrom(new Point(0, 0));
        if(curr <= this.topVelocity) return;

        double scale = this.topVelocity / curr;
        this.velocity.multThis(scale);
    }
}
