package GUI;
import Engine.*;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel
{
    private GameEngine engine;

    public void engine(GameEngine engine)
    {
        this.engine = engine;
        repaint();
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        if (engine.getEnabled().size() <= 0) return;

        Graphics2D g2d = (Graphics2D) g;

        for (IGameObject obj : engine.getEnabled())
        {
            if (obj.shape() != null) obj.shape().draw(g2d);
        }
    }
}