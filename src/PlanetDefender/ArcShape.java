package PlanetDefender;

import Engine.*;
import Engine.Point;
import Engine.Shape;

import java.awt.*;

/**
 * Subclasse de Shape para o CircleShape.
 * @author Miguel Alvito, Nicole Reis e Pedro Pinto
 * @version 1.0 (17/05/2025)
 */
public class ArcShape extends Shape
{
    private double radius;
    private Color color;
    private double startAngle;
    private double arcAngle;
    private double strokeWidth;

    /**
     * Construtor.
     */
    public ArcShape(double r, Color c, double sw, double sa, double aa, Transform t)
    {
        this.transform = t;
        this.position = t.position();
        this.angle = t.angle();
        this.scale = t.scale();
        this.radius = r;
        this.color = c;
        this.startAngle = sa;
        this.arcAngle = aa;
        this.strokeWidth = sw;
        this.drawOrder = 0;
    }

    /**
     * Desenha a imagem no ecrã.
     * @param g {@code Graphics2d}
     */
    @Override
    public void draw(Graphics2D g)
    {
        g.setColor(this.color);
        g.setStroke(new BasicStroke((float) this.strokeWidth, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND));
        //g.fillOval((int) (this.position.x()-this.radius), (int) (this.position.y()-this.radius), (int) this.radius*2, (int) this.radius*2);
        g.drawArc((int) (this.position.x()-this.radius), (int) (this.position.y()-this.radius), (int) this.radius*2, (int) this.radius*2, (int) startAngle, (int) arcAngle);

        //g.setStroke(null);
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
        this.position.addThis(dPos);
    }

    /**
     * Este método faz a rotação do Collider em determinados graus.
     * @param dAngle {@code double}
     */
    public void rotate(double dAngle)
    {
        this.angle = (this.angle + dAngle) % 360;
    }
}
