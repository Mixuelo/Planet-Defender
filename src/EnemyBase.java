public class EnemyBase extends MovingObject
{
    public EnemyBase(String n, Transform t, Collider c, Point velocity, double topVelocity, double friction)
    {
        super(n, t, c, velocity, topVelocity, friction);
    }
}
