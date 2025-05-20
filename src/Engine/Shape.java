package Engine;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

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

    public void transform(Transform t) 
    {
        this.transform = t;
    }

    /**
     * Desenha a imagem no ecrã.
     * @param g {@code Graphics2d}
     */
    public abstract void draw(Graphics2D g);

    public void onUpdate()
    {
        Point dPos = this.transform.position().subNew(this.position); 
        double dScale = this.transform.scale() - this.scale;
        double dAngle = this.transform.angle() - this.angle;

        this.move(dPos);
        this.rotate(dAngle);
        this.scale(dScale);
    }

    public abstract void scale(double dScale);

    public abstract void move(Point dPos);

    public abstract void rotate(double dAngle);
}
