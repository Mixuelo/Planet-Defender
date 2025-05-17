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
public class CircleShape extends Shape
{
    /**
     * Construtor.
     * @param imageFile {@code String}
     * @param pos {@code Point}
     */
    public CircleShape(String imageFile, Point pos)
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

    }
}
