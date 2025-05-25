package PlanetDefender;
import Engine.*;
import Engine.Point;
import Engine.Shape;

import java.awt.*;

/**
 * Subclasse de Shape responsável pelos textos.
 * @author Miguel Alvito, Nicole Reis, Pedro Pinto
 * @version 1.0 (25/05/2025)
 */
public class TextShape extends Shape
{
    private String text;
    private Font font;
    private Color color;

    /**
     * Construtor.
     * @param text {@code String}
     * @param font {@code Font}
     * @param color {@code Color}
     * @param t {@code Transform}
     */
    public TextShape(String text, Font font, Color color, Transform t)
    {
        this.text = text;
        this.font = font;
        this.color = color;
        this.transform = t;
        this.position = t.position();
        this.angle = t.angle();
        this.scale = t.scale();
    }

    /**
     * Desenha o texto no ecrã.
     * @param g {@code Graphics2d}
     */
    @Override
    public void draw(Graphics2D g)
    {
        g.setFont(font);
        g.setColor(color);
        FontMetrics fm = g.getFontMetrics();
        int x = (int)(this.transform.position().x() - fm.stringWidth(text) / 2);
        int y = (int)(this.transform.position().y() + fm.getHeight() / 4);
        g.drawString(text, x, y);
    }

    /**
     * Altera a escala do texto (não aplicável).
     * @param dScale {@code double}
     */
    @Override
    public void scale(double dScale)
    {

    }

    /**
     * Move o texto (não aplicável).
     * @param dPos {@code Point}
     */
    @Override
    public void move(Point dPos)
    {

    }

    /**
     * Roda o texto (não aplicável).
     * @param dAngle {@code double}
     */
    @Override
    public void rotate(double dAngle)
    {

    }
}

