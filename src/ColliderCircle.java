/** Collider em forma de circulo
    @author     Miguel Alvito
    @version    1.0 (2024-03-25)
    @inv        o raio do circulo é maior que 0
*/
public class ColliderCircle extends Collider 
{
    private double radius;
    private double scale;
    
    /** Construtor do Collider em circulo
        @param center   o centro do circulo, em Point
        @param radius   o raio do circulo, em double
    */
    public ColliderCircle(Point center, double radius)
    {
        checkRadius(radius);
        this.centroid = center.clone();
        this.radius = radius;
        this.scale = 1;
    }

    //TODO: DOCUMENTAR e TESTAR
    public ColliderCircle(Transform trans, Point center, double radius)
    {
        this(center, radius);
        this.scale(trans.scale());
    }

    /** Verificar se o raio é maior que 0, caso nao for dá-se uma exceção
        @param radius   o valor a verificar
    */
    private static void checkRadius(double radius) throws IllegalArgumentException
    {
        if(radius <= 0) { throw new IllegalArgumentException("O raio dado não é maior que 0: " + radius); }
    }

    /** Devolve o raio do circulo */ 
    public double radius()
    {
        return this.radius;
    }

    //TODO: DOCUMENTAR e TESTAR
    //@pre  s > 0
    public void scale(double dScale)
    {
        this.radius *= dScale;
        this.scale *= dScale;
    }

    //TODO: DOCUMENTAR e TESTAR
    public void move(Point dPos)
    {
        this.centroid.addThis(dPos);
    }

    //TODO: DOCUMENTAR
    public void rotate(double dAngle)
    {
        this.angle += dAngle;
    }

    //TODO: DOCUMENTAR e TESTAR
    //@pre  s > 0
    public void setScale(double s)
    {
        double d = s / this.scale;
        this.scale(d);
    }

    //TODO: DOCUMENTAR e TESTAR
    public void setCentroid(Point pos)
    {
        Point diff = pos.subNew(this.centroid);
        this.centroid.addThis(diff);
    }

    //TODO: DOCUMENTAR e TESTAR
    public void setAngle(double a)
    {
        this.angle = a;
    }

    /** Devolve uma representação em String do Collider, no formato: "<centroid> <radius>" */
    @Override
    public String toString()
    {
        return this.centroid + " " + String.format("%.2f", this.radius);
    }
}
