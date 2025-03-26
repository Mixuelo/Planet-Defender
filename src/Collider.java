/** Classe abstrata para o Collider
    @author     Miguel Alvito
    @version    1.0 (2024-03-25)
*/
public abstract class Collider implements ICollider
{
    protected Point centroid;
    protected double scale;
    protected double angle;

    /** Devolve o centroide do Collider */
    public Point centroid()
    {
        return this.centroid;
    }

    //TODO: DOCUMENTAR
    public abstract void scale(double dScale);

    //TODO: DOCUMENTAR
    public abstract void move(Point dPos);

    //TODO: DOCUMENTAR
    public abstract void rotate(double dAngle);

    //TODO: DOCUMENTAR
    public abstract void setScale(double scale);

    //TODO: DOCUMENTAR
    public abstract void setCentroid(Point pos);

    //TODO: DOCUMENTAR
    public abstract void setAngle(double angle);

    /** Devolve uma representação em String do Collider */
    @Override
    public abstract String toString(); 
}
