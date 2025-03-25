/** Classe abstrata para o Collider
    @author     Miguel Alvito
    @version    1.0 (2024-03-25)
*/
public abstract class Collider implements ICollider
{
    protected Point centroid;

    /** Devolve o centroide do Collider */
    public Point centroid()
    {
        return this.centroid;
    }

    /** Devolve uma representação em String do Collider */
    @Override
    public abstract String toString(); 
}
