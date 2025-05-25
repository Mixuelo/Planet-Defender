package Engine;

import java.awt.*;

/** Classe abstrata para o Shape.
    @author     Miguel Alvito, Nicole Reis e Pedro Pinto
    @version    1.0 (17/05/2025)
*/
public abstract class Shape implements IShape
{
    protected Point position;
    protected double scale;
    protected double angle;
    protected Transform transform;
    protected int drawOrder;

    /**
     * Define uma transform para este Shape (setter).
     * @param t {@code Transform}
     */
    public void transform(Transform t) 
    {
        this.transform = t;
    }

    /***
     * Define a ordem de desenho (setter).
     * @param i {@code int}
     */
    public void drawOrder(int i) 
    {
        this.drawOrder = i;
    }

    /**
     * Devolve a ordem de desenho (getter).
     * @return drawOrder {@code int}
     */
    public int drawOrder() 
    {
        return this.drawOrder;
    }

    /**
     * Desenha a imagem no ecrã.
     * @param g {@code Graphics2d}
     */
    public abstract void draw(Graphics2D g);

    /**
     * Atualiza este Shape.
     */
    public void onUpdate()
    {
        Point dPos = this.transform.position().subNew(this.position); 
        double dScale = this.transform.scale() - this.scale;
        double dAngle = this.transform.angle() - this.angle;

        this.move(dPos);
        this.rotate(dAngle);
        this.scale(dScale);
    }

    /**
     * Altera a escala.
     * @param dScale {@code double}
     */
    public abstract void scale(double dScale);

    /**
     * Move este Shape.
     * @param dPos {@code Point}
     */
    public abstract void move(Point dPos);

    /**
     * Roda este Shape.
     * @param dAngle {@code double}
     */
    public abstract void rotate(double dAngle);
}
