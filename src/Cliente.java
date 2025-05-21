import Engine.*;
import GUI.*;
import PlanetDefender.*;

public class Cliente
{
    public static void main(String[] args)
    {
        GameEngine game = new GameEngine();

        GUI gui = new GUI();
        game.gui(gui);

        UIObject title = new UIObject("title", new Transform(new Point(0, 0), 0, 0, 1), new TitleBehaviour());
        UIObject background = new UIObject("back", new Transform(new Point(0, 0), -1, 0, 1), null);

        game.addEnabled(title);
        game.addEnabled(background);

        game.run();

        return;
    }
}

