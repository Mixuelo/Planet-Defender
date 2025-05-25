package PlanetDefender;

import Engine.*;
import Engine.Point;
import Engine.Shape;

import java.awt.*;

/**
 * Subclasse de Shape para círculos.
 * @author Miguel Alvito, Nicole Reis e Pedro Pinto
 * @version 1.0 (17/05/2025)
 */
public class CircleShape extends Shape
{
    private double radius;
    private Color color;

    /**
     * Construtor.
     */
    public CircleShape(double r, Color c, Transform t)
    {
        this.transform = t;
        this.position = t.position();
        this.angle = t.angle();
        this.scale = t.scale();
        this.radius = r;
        this.color = c;
        this.drawOrder = 0;
    }

    /**
     * Construtor.
     */
    public CircleShape(double r, Color c, Transform t, int z)
    {
        this(r, c, t);
        this.drawOrder = z;
    }

    /**
     * Desenha a imagem no ecrã.
     * @param g {@code Graphics2d}
     */
    @Override
    public void draw(Graphics2D g)
    {
        g.setColor(this.color);
        g.fillOval((int) (this.position.x()-this.radius), (int) (this.position.y()-this.radius), (int) this.radius*2, (int) this.radius*2);
    }

    /**
     * Atualizar a escala do circulo, adicionando dScale ao valor da escala.
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
