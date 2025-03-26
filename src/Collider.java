/** Classe abstrata para o Collider.
    @author     Miguel Alvito, Nicole Reis e Pedro Pinto
    @version    1.0 (2024-03-25)
*/
public abstract class Collider implements ICollider
{
    protected Point centroid;
    protected double scale;
    protected double angle;

    /** Devolve o centroide do Collider.
     * @return centroid {@code Point}
     * */
    public Point centroid()
    {
        return this.centroid;
    }

    /**
     * Este método altera a escala.
     * @pre s > 0
     * @param dScale {@code double}
     */
    public abstract void scale(double dScale);

    /**
     * Este método move o Collider.
     * @param dPos {@code Point}
     */
    public abstract void move(Point dPos);

    /**
     * Este método faz a rotação do Collider em determinados graus.
     * @param dAngle {@code double}
     */
    public abstract void rotate(double dAngle);

    /** Devolve uma representação em String do Collider
     * @return {@code String}
     */
    @Override
    public abstract String toString(); 
}
