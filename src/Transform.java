/**
 *
 */
public class Transform implements ITransform
{
    private Point position;
    private int layer;
    private double angle;
    private double scale;

    public Transform(Point p, int l, double a, double s)
    {
        position = p;
        layer = l;
        angle = a;
        scale = s;
    }

    /**
     * Este método devolve uma string na forma "(x,y) layer angle scale", onde x e y correspondem às coordenadas cartesianas de position.
     * @return {@code String}
     */
    public String toString()
    {
        return position.toString() + " " + layer + " " + angle + " " + scale;
    }

    /**
     * Move this ITransform by dPos.x(), dPos.y() and dlayer
     * @param dPos the 2D differential to move
     * @param dlayer the layer differential
     */
    public void move(Point dPos, int dlayer)
    {
        position.translation(dPos.x(), dPos.y());
        layer += dlayer;
    }

    /**
     * Rotate this ITransform from current orientation by dTheta
     * @param dTheta
     * @pos 0 <= this.angle() < 360
     */
    public void rotate(double dTheta)
    {
        this.angle %= 360;
    }

    /**
     * increment the ITransform scale by dscale
     * @param dScale the scale increment
     */
    public void scale(double dScale)
    {
        this.scale += dScale;
    }

    /**
     * @return the (x,y) coordinates
     */
    public Point position()
    {
        return this.position;
    }

    /**
     * @return the layer
     */
    public int layer()
    {
        return this.layer;
    }

    /**
     * @return the angle in degrees
     */
    public double angle()
    {
        return this.angle;
    }

    /**
     * @return the current scale factor
     */
    public double scale()
    {
        return scale;
    }
}
