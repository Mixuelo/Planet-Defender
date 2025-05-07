public class BaseEnemy extends MovingObject
{
    public BaseEnemy(String n, Transform t, Collider c, Point velocity, double topVelocity, double friction)
    {
        super(n, t, c, velocity, topVelocity, friction);
    }
}
