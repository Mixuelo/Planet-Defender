import Engine.*;
import PlanetDefender.*;
import javax.swing.*;

public class Cliente
{
    public static void main(String[] args)
    {
        JFrame frame = new JFrame("Planet Defender");
        frame.setSize(800, 600);

        JPanel panel = new JPanel();

        GameEngine game = new GameEngine();
        UIObject title = new UIObject("title", new Transform(new Point(0, 0), 0, 0, 1), new TitleBehaviour());
        UIObject background = new UIObject("back", new Transform(new Point(0, 0), -1, 0, 1), null);

        game.addEnabled(title);
        game.addEnabled(background);

        game.frame(frame);

        game.run();

        return;
    }
}

