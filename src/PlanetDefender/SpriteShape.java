package PlanetDefender;

import java.awt.*;
import java.io.File;

import javax.imageio.ImageIO;
import Engine.*;

/**
 * Subclasse de Shape para o SpriteShape.
 * @author Miguel Alvito, Nicole Reis e Pedro Pinto
 * @version 1.0 (17/05/2025)
 */
public class SpriteShape extends Shape
{
    Image sprite;
    double imageScale;

    /**
     * Cosntrutor.
     * @param imageFile {@code String}
     */
    public SpriteShape(String imagePath, double imageScale, Transform t, Point p)
    {
        this.position = p.clone();
        this.imageScale = imageScale;
        this.transform = t;

        try 
        {
            File imageFile = new File(imagePath);
            this.sprite = ImageIO.read(imageFile);
        }
        catch(Exception e)
        {
            System.out.println("ERRO: " + imagePath);
            e.printStackTrace();
        }
    }

    /**
     * Desenha a imagem no ecrã.
     * @param g {@code Graphics2d}
     */
    @Override
    public void draw(Graphics2D g)
    {
        if (this.sprite != null)
        {
            int w = (int) Math.round(sprite.getWidth(null) * this.imageScale);
            int h = (int) Math.round(sprite.getHeight(null) * this.imageScale);
            g.drawImage(sprite, (int) Math.round(position.x()), (int) Math.round(position.y()), w, h, null);
        }
    }

    /**
     * Atualizar a escala do circulo, adicionando sScale ao valor da escala.
     * @param dScale {@code double}
     */
    public void scale(double dScale)
    {
        double finalScale = this.scale + dScale;
        double mult = finalScale / this.scale;
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
