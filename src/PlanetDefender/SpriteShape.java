package PlanetDefender;

import Engine.Point;
import Engine.Shape;
import Engine.Transform;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.io.File;

import javax.imageio.ImageIO;

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
     * @param imagePath {@code String}
     */
    public SpriteShape(String imagePath, double imageScale, Transform t)
    {
        this.imageScale = imageScale;
        this.transform = t;
        this.position = t.position().clone();
        this.angle = t.angle();
        this.scale = t.scale();
        this.drawOrder = 0;

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

    public SpriteShape(String imagePath, double imageScale, Transform t, int z)
    {
        this(imagePath, imageScale, t);
        this.drawOrder = z;
    }

    public void sprite(String imagePath)
    {
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
            int w = sprite.getWidth(null);
            int h = sprite.getHeight(null);

            AffineTransform trans = new AffineTransform();
            trans.translate(this.position.x(), this.position.y());
            trans.scale(this.imageScale * this.scale, this.imageScale * this.scale);
            trans.translate(-w/2, -h/2);
            trans.rotate(Math.toRadians(this.angle), w/2, h/2);
            g.drawImage(this.sprite, trans, null);
        }
    }

    /**
     * Atualizar a escala do circulo, adicionando sScale ao valor da escala.
     * @param dScale {@code double}
     */
    public void scale(double dScale)
    {
        this.scale += dScale;
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
