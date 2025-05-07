import java.awt.event.InputEvent;

public class EffectBehaviour extends Behaviour
{
    private double time;

    public EffectBehaviour(double t)
    {
        this.time = t;
    }

    @Override
    public void onUpdate(double dT, InputEvent ie) {
        super.onUpdate(dT, ie);

        this.time -= dT;
        if(this.time <= 0) 
        {
            this.parent.engine().destroy(this.parent);
        }
    } 
}
