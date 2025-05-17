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
    protected BufferedImage image;
    protected Point position;

    /**
     * Construtor
     * @param imageFile {@code String}
     * @param pos {@code Point}
     */
    public Shape(String imageFile, Point pos)
    {
        this.position = pos.clone();
        try
        {
            this.image = ImageIO.read(new File(imageFile));
        }
        catch (IOException e)
        {
            System.err.println("Erro ao carregar imagem: " + imageFile);
            this.image = null;
        }
    }

    /**
     * Desenha a imagem no ecrã.
     * @param g {@code Graphics2d}
     */
    public abstract void draw(Graphics2D g);
}
