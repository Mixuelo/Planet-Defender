package PlanetDefender;

import java.awt.Color;
import java.awt.event.InputEvent;

import Engine.*;
import PlanetDefender.CharacterBehaviour;

public class ArcGaugeBehaviour extends Behaviour
{
    private GameObject target;
    private double timer;
    private double value;
    private double maxValue;
    private Color color;
    private double radius;
    private double strokeWidth;
    private static final double SUSTAIN_TIME = 1;
    private static final double RELEASE_TIME = 1;

    public ArcGaugeBehaviour(Color c, double r, double sw)
    {
        this.target = null;
        this.timer = SUSTAIN_TIME + RELEASE_TIME + 1;
        this.value = 0;
        this.maxValue = 0;
        this.color = c;
        this.radius = r;
        this.strokeWidth = sw;
    }

    public void target(GameObject t)
    {
        this.target = t;
    }

    public void onInit() 
    {
        if(this.target == null) { return; }    

        this.maxValue = ((CharacterBehaviour) this.target.behaviour()).health();
    }

    public void onUpdate(double dT, InputEvent ie) {
        Point dPos = this.target.transform().position().subNew(this.parent.transform().position());
        this.parent.move(dPos, 0);

        if(this.timer > SUSTAIN_TIME + RELEASE_TIME) { this.parent.shape(null); return; }
        this.timer += dT;

        double alpha;
        if(this.timer < SUSTAIN_TIME) { alpha = 1; }
        else { alpha = (SUSTAIN_TIME + RELEASE_TIME - this.timer) / RELEASE_TIME; }

        alpha = Math.clamp(alpha, 0, 1);

        Color c = new Color(this.color.getRed(), this.color.getGreen(), this.color.getBlue(), (int) (alpha*255));

        double arcAngle = (this.value * 360) / this.maxValue;
        Shape s = new ArcShape(this.radius, c, this.strokeWidth, 90, arcAngle, this.parent.transform());

        this.parent.shape(s);
    }

    public void refresh()
    {
        this.value = ((CharacterBehaviour) this.target.behaviour()).health();
        if(this.value < 0) { this.value = 0; }
        if(this.value > this.maxValue) { this.value = this.maxValue; }

        this.timer = 0;
    }
}
