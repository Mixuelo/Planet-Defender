package PlanetDefender;

import java.awt.event.InputEvent;
import Engine.*;

/**
 * Subclasse abstrata de Behaviour responsável pelos efeitos.
 * @author Miguel Alvito, Nicole Reis e Pedro Pinto
 * @version 1.0 (08/05/2025)
 */
public class EffectBehaviour extends Behaviour
{
    private double timer;
    private String imagePath;
    private String imageExt;
    private double imageScale;
    private int frames;
    private double fps;
    private double animDT;
    private int lastFrame;

    /**
     * Construtor.
     * @param t {@code double}
     */
    public EffectBehaviour(String ip, String ie, double is, int f, double fps)
    {
        this.imagePath = ip;
        this.imageExt = ie;
        this.imageScale = is;
        this.frames = f;
        this.fps = fps;
        this.animDT = 1/fps;
        this.lastFrame = 0;
    }

    /**
     * Atualiza este EffectBehaviour com base no tempo e no input.
     * @param dT {@code double}
     * @param ie {@code InputEvent}
     */
    @Override
    public void onUpdate(double dT, InputEvent ie)
    {
        this.timer += dT;

        if(this.timer >= this.frames * animDT) 
        {
            this.parent.shape(null);
            this.parent.engine().destroy(this.parent);
            return;
        }

        int currFrame = (int) (this.timer / this.animDT) + 1;

        if(this.lastFrame != currFrame) 
        {
            this.parent.shape(new SpriteShape(this.imagePath + currFrame + this.imageExt, this.imageScale, this.parent.transform()));
        }

        this.lastFrame = currFrame;
    } 
}
