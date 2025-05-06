/** Collider em forma de círculo
    @author     Miguel Alvito, Nicole Reis e Pedro Pinto
    @version    1.0 (2024-03-25)
    @inv        o raio do circulo é maior que 0
*/
public class ColliderCircle extends Collider 
{
    private double radius;
    
    /** Construtor do Collider em círculo. TODO: DEPRECATED
        @param center   o centro do circulo, em Point
        @param radius   o raio do circulo, em double
    */
    public ColliderCircle(Point center, double radius)
    {
        checkRadius(radius);
        this.centroid = center.clone();
        this.radius = radius;
        this.scale = 1;
        this.angle = 0;
    }

    /**
     * Outro construtor do Collider em círculo.
     * @param trans {@code Transform}
     * @param center {@code Point}
     * @param radius {@code double}
     */
    public ColliderCircle(Transform trans, Point center, double radius)
    {
        this(trans.position(), radius);

        this.radius *= trans.scale();
        this.scale *= trans.scale();

        this.angle = trans.angle();
        this.transform = trans;
    }

    /** Verificar se o raio é maior que 0, caso não for dá-se uma exceção.
        @param radius   o valor a verificar
    */
    private static void checkRadius(double radius) throws IllegalArgumentException
    {
        if(radius <= 0) { throw new IllegalArgumentException("O raio dado não é maior que 0: " + radius); }
    }

    /** Devolve o raio do círculo.
     * @return radius {@code double}
     */
    public double radius()
    {
        return this.radius;
    }

    /**
     * Atualizar a escala do circulo, adicionando sScale ao valor da escala.
     * @param dScale {@code double}
     */
    public void scale(double dScale)
    {
        double finalScale = this.scale + dScale;
        double mult = finalScale / this.scale;
        this.radius *= mult;
        this.scale *= mult;
    }

    /**
     * Este método move o Collider.
     * @param dPos {@code Point}
     */
    public void move(Point dPos)
    {
        this.centroid.addThis(dPos);
    }

    /**
     * Este método faz a rotação do Collider em determinados graus.
     * @param dAngle {@code double}
     */
    public void rotate(double dAngle)
    {
        this.angle = (this.angle + dAngle) % 360;
    }

    /** Verifica se existe uma interseçao deste circulo com o segmento that
        @param that o segmento a verificar
        @return     true se existir uma interseçao, false caso contrario.
        @see        <a href = https://mathworld.wolfram.com/Circle-LineIntersection.html a>
    */
    public boolean segmentIntersect(LineSegment that)
    {
        Point close = that.closestPointFromPoint(this.centroid);
        if (close.distFrom(this.centroid) < this.radius)    { return true; }
        else                                                { return false; }
    }

    /** Verificar se existe uma colisão entre este circulo e outro colisor
        @param that o colisor a verificar
        @return     return == that.checkCollisionCircle(this);
    */
    public boolean checkCollision(Collider that)
    {
        return that.checkCollisionCircle(this);
    }

    /** Verificar se existe uma colisão entre este circulo e outro colisor em circulo
        @param that o colisor em circulo a verificar
        @return     return == that.checkCollisionCircle(this)
    */
    public boolean checkCollisionCircle(ColliderCircle that)
    {
        return this.centroid.distFrom(that.centroid()) <= this.radius + that.radius();
    }

    /** Devolve uma representação em String do Collider, no formato: "<centroid> <radius>"
     * @return {@code String}
     */
    @Override
    public String toString()
    {
        return this.centroid + " " + String.format("%.2f", this.radius);
    }
}
