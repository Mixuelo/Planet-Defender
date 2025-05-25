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
     * @param imagePath {@code String}
     * @param imageExt {@code String}
     * @param imageScale {@code double}
     * @param frames {@code int}
     * @param fps {@code double}
     */
    public EffectObject(String n, Transform t, String imagePath, String imageExt, double imageScale, int frames, double fps)
    {
        super(n, t, (Collider) null, (Behaviour) new EffectBehaviour(imagePath, imageExt, imageScale, frames, fps));
    }
}
