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

        UIObject title = new UIObject("title", new Transform(new Point(0, 0), 0, 0, 1), new TitleBehaviour(), null);
        Transform backTrans = new Transform(new Point(0, 0), -1, 0, 1);
        UIObject background = new UIObject("back",backTrans, null, new SpriteShape("imgs/fundo.png", 2.5, backTrans));

        game.addEnabled(title);
        //game.addEnabled(background);

        game.run();

        return;
    }
}

