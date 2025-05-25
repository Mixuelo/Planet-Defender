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
    private Image sprite;
    private double imageScale;

    /**
     * Construtor.
     * @param imagePath {@code String}
     * @param imageScale {@code double}
     * @param t {@code Transform}
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

    /**
     * Construtor.
     * @param imagePath {@code String}
     * @param imageScale {@code double}
     * @param t {@code Transform}
     * @param z {@code int}
     */
    public SpriteShape(String imagePath, double imageScale, Transform t, int z)
    {
        this(imagePath, imageScale, t);
        this.drawOrder = z;
    }

    /**
     * Carrega uma imagem a partir de um path e define-a como sprite do objeto.
     * @param imagePath {@code String}
     */
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
     * Atualizar a escala do shape, adicionando dScale ao valor da escala.
     * @param dScale {@code double}
     */
    @Override
    public void scale(double dScale)
    {
        this.scale += dScale;
    }

    /**
     * Este método move este shape.
     * @param dPos {@code Point}
     */
    @Override
    public void move(Point dPos)
    {
        this.position.addThis(dPos);
    }

    /**
     * Este método faz a rotação deste shape em determinados graus.
     * @param dAngle {@code double}
     */
    @Override
    public void rotate(double dAngle)
    {
        this.angle = (this.angle + dAngle) % 360;
    }
}
