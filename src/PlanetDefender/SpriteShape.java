package PlanetDefender;

import Engine.Point;
import Engine.Shape;

import java.awt.*;

/**
 * Subclasse de Shape para o SpriteShape.
 * @author Miguel Alvito, Nicole Reis e Pedro Pinto
 * @version 1.0 (17/05/2025)
 */
public class SpriteShape extends Shape
{
    /**
     * Cosntrutor.
     * @param imageFile {@code String}
     * @param pos {@code Point}
     */
    public SpriteShape(String imageFile, Point pos)
    {
        super(imageFile, pos);
    }

    /**
     * Desenha a imagem no ecrã.
     * @param g {@code Graphics2d}
     */
    @Override
    public void draw(Graphics2D g)
    {
        if (image != null)
        {
            g.drawImage(image, (int) Math.round(position.x()), (int) Math.round(position.y()), null);
        }
    }
}
