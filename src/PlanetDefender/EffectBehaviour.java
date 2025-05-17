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
    private double time;

    /**
     * Construtor.
     * @param t {@code double}
     */
    public EffectBehaviour(double t)
    {
        this.time = t;
    }

    /**
     * Atualiza este EffectBehaviour com base no tempo e no input.
     * @param dT {@code double}
     * @param ie {@code InputEvent}
     */
    @Override
    public void onUpdate(double dT, InputEvent ie)
    {
        super.onUpdate(dT, ie);

        this.time -= dT;
        if(this.time <= 0) 
        {
            this.parent.engine().destroy(this.parent);
        }
    } 
}
