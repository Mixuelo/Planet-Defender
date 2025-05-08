/**
 * Subclasse de MovingObject que representa inimigos em movimento.
 * @author Miguel Alvito, Nicole Reis e Pedro Pinto
 * @version 1.0 (08/05/2025)
 */
public class BaseEnemy extends MovingObject
{
    /**
     * Construtor.
     * @param n {@code String}
     * @param t {@code Transform}
     * @param c {@code Collider}
     * @param velocity {@code Point}
     * @param topVelocity {@code double}
     * @param friction {@code double}
     */
    public BaseEnemy(String n, Transform t, Collider c, Point velocity, double topVelocity, double friction)
    {
        super(n, t, c, velocity, topVelocity, friction);
    }
}
