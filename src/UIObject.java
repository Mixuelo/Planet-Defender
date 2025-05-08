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
     * @param c {@code Collider}
     */
    public UIObject(String n, Transform t, Collider c)
    {
        super(n, t, c);
    }
}
