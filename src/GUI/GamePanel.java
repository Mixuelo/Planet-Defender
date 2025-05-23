package GUI;
import Engine.*;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class GamePanel extends JPanel
{
    private static final long serialVersionUID = 1L;
    private CopyOnWriteArrayList<IGameObject> gameObjects;

    public void gameObjects(CopyOnWriteArrayList<IGameObject> list)
    {
        this.gameObjects = new CopyOnWriteArrayList<>(list);
        repaint();
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        if (gameObjects == null)     return;
        if (gameObjects.size() <= 0) return;

        Graphics2D g2d = (Graphics2D) g;

        for (IGameObject obj : gameObjects)
        {
            if (obj.shape() != null) obj.shape().draw(g2d);
        }
    }
}
