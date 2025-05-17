package PlanetDefender;

import Engine.*;

/**
 * Subclasse de GameObject que representa efeitos visuais.
 * @author Miguel Alvito, Nicole Reis e Pedro Pinto
 * @version 1.0 (08/05/2025)
 */
public class EffectObject extends GameObject
{
    /**
     * Construtor.
     * @param n {@code String}
     * @param t {@code Transform}
     * @param time {@code int}
     */
    public EffectObject(String n, Transform t, int time)
    {
        super(n, t, (Collider) null, (Behaviour) new EffectBehaviour(time));
    }
}
