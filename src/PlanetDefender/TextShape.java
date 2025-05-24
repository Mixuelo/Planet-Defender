package PlanetDefender;
import Engine.*;
import Engine.Point;
import Engine.Shape;

import java.awt.*;

public class TextShape extends Shape
{
    private String text;
    private Font font;
    private Color color;

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

    @Override
    public void scale(double dScale)
    {

    }

    @Override
    public void move(Point dPos)
    {

    }

    @Override
    public void rotate(double dAngle)
    {

    }
}

