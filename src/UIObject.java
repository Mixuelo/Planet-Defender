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
     */
    public UIObject(String n, Transform t, Behaviour b)
    {
        super(n, t, (Collider) null, b);
    }
}
