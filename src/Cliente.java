import Engine.*;
import Engine.Point;
import GUI.*;
import PlanetDefender.*;

import java.awt.*;


public class Cliente
{
    public static void main(String[] args)
    {
        GameEngine game = new GameEngine();

        GUI gui = new GUI();
        game.gui(gui);

        Transform t1 = new Transform(new Point(400, 250), 0, 0, 1);
        UIObject title = new UIObject("PlanetDefender", t1, new TitleBehaviour(), new SpriteShape("imgs/titulo.png", 1, t1));

        Transform t2 = new Transform(new Point(400, 400), 0, 0, 1);
        UIObject info = new UIObject("Info", t2, new TitleBehaviour(), new TextShape("Aperte ESPAÇO para jogar", new Font("Arial", Font.PLAIN, 24), Color.WHITE, t2));

        Transform backTrans = new Transform(new Point(400, 300), -1, 0, 1);
        UIObject background = new UIObject("back",backTrans, null, new SpriteShape("imgs/fundo.png", 1, backTrans));

        game.addEnabled(background);
        game.addEnabled(title);
        game.addEnabled(info);

        game.run();

        return;
    }
}
