/** Classe abstrata para o Collider.
    @author     Miguel Alvito, Nicole Reis e Pedro Pinto
    @version    1.0 (2024-03-25)
*/
public abstract class Collider implements ICollider
{
    protected Point centroid;
    protected double scale;
    protected double angle;
    protected Transform transform;

    /** Devolve o centroide do Collider.
     * @return centroid {@code Point}
     * */
    public Point centroid()
    {
        return this.centroid;
    }

    /**
     * Este método altera a escala, adicionando dScale ao valor da escala.
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

    /** Verificar se existe uma colisão entre este colisor e outro colisor
        @param that o colisor a verificar
        @return     return == that.checkCollision(this);
    */
    public abstract boolean checkCollision(Collider that);

    /** Verificar se existe uma colisão entre este colisor e outro colisor em circulo
        @param that o colisor em circulo a verificar
    */
    public boolean checkCollisionCircle(ColliderCircle that)
    {
        return this.checkCollision(that);
    }

    /** Verificar se existe uma colisão entre este colisor e outro colisor em poligono
        @param that o colisor em poligono a verificar
    */
    public boolean checkCollisionPolygon(ColliderPolygon that)
    {
        return this.checkCollision(that);
    }

    /** Devolve uma representação em String do Collider
     * @return {@code String}
     */
    @Override
    public abstract String toString(); 
}
