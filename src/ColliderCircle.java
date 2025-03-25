/** Collider em forma de circulo
    @author     Miguel Alvito
    @version    1.0 (2024-03-25)
    @inv        o raio do circulo é maior que 0
*/
public class ColliderCircle extends Collider 
{
    private double radius;
    
    /** Construtor do Collider em circulo
        @param center   o centro do circulo, em Point
        @param radius   o raio do circulo, em double
    */
    public ColliderCircle(Point center, double radius)
    {
        checkRadius(radius);
        this.centroid = center.clone();
        this.radius = radius;
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

    /** Devolve uma representação em String do Collider, no formato: "<centroid> <radius>" */
    @Override
    public String toString()
    {
        return this.centroid + " " + this.radius;
    }
}
