import Engine.*;
import GUI.*;
import PlanetDefender.*;
import javax.swing.*;

public class Cliente
{
    public static void main(String[] args)
    {
        GameEngine game = new GameEngine();
        UIObject title = new UIObject("title", new Transform(new Point(0, 0), 0, 0, 1), new TitleBehaviour());
        UIObject background = new UIObject("back", new Transform(new Point(0, 0), -1, 0, 1), null);

        game.addEnabled(title);
        game.addEnabled(background);

        JFrame window = new JFrame("Planet Defender");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(800, 600);
        window.setResizable(false);

        GamePanel panel = new GamePanel();
        panel.engine(game);
        window.add(panel);
        window.setVisible(true);

        panel.requestFocusInWindow();

        game.run();

        return;
    }
}

