package Engine;

import java.awt.*;

/**
 * Interface para a classe abstrata Shape
 * @author Miguel Alvito, Nicole Reis e Pedro Pinto
 * @version 1.0 (17/05/2025)
 */
public interface IShape
{
    /**
     * Desenha a imagem no ecrã.
     * @param g {@code Graphics2D}
     */
    void draw(Graphics2D g);

    /**
     * Atualiza este shape.
     */
    void onUpdate();
}
