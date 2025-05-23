package PlanetDefender;

import Engine.*;

/**
 * Subclasse de GameObject que representa elementos de interface de utilizador.
 * @author Miguel Alvito, Nicole Reis e Pedro Pinto
 * @version 1.0 (08/05/2025)
 */
public class UIObject extends GameObject
{
    /**
     * Construtor.
     * @param n {@code String}
     * @param t {@code Transform}
     * @param b {@code Behaviour}
     * @param s {@code Shape}
     */
    public UIObject(String n, Transform t, Behaviour b, Shape s)
    {
        super(n, t, (Collider) null, b, s);
    }
}
