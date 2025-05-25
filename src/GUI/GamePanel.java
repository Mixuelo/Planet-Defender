package GUI;
import Engine.*;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Subclasse de JPanel responsável por desenhar os objetos do jogo no ecrã.
 * @author Miguel Alvito, Nicole Reis, Pedro Pinto
 * @version 1.0 (25/05/2025)
 */
public class GamePanel extends JPanel
{
    private static final long serialVersionUID = 1L;
    private CopyOnWriteArrayList<IGameObject> gameObjects;

    /**
     * Define a lista de GameObject's.
     * @param list {@code CopyOnWriteArrayList<IGameObject>}
     */
    public void gameObjects(CopyOnWriteArrayList<IGameObject> list)
    {
        this.gameObjects = new CopyOnWriteArrayList<>(list);
        repaint();
    }

    /**
     * Desenha os GameObjects no ecrã.
     * @param g the <code>Graphics</code> object to protect
     */
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        if (gameObjects == null)     return;
        if (gameObjects.size() <= 0) return;

        Graphics2D g2d = (Graphics2D) g;

        gameObjects.sort((a,b) -> { 
            int d = a.transform().layer() - b.transform().layer();
            if(d != 0) return d;
            if(a.shape() == null || b.shape() == null) return 0; 
            return ((Engine.Shape) a.shape()).drawOrder() - ((Engine.Shape) b.shape()).drawOrder(); 
        });

        for (IGameObject obj : gameObjects)
        {
            if (obj.shape() != null) obj.shape().draw(g2d);
        }
    }
}
