/**
 * Subclasse de CharacterBehaviour responsável pelo comportamento do planeta.
 * @author Miguel Alvito, Nicole Reis, Pedro Pinto
 * @version 1.0 (08/05/2025)
 */
public class PlanetBehaviour extends CharacterBehaviour
{
    private static final int INITIAL_HEALTH = 100;

    /**
     * Construtor.
     */
    public PlanetBehaviour()
    {
        super(INITIAL_HEALTH);
    }

    /**
     * Destroi todos os elementos da engine quando o planeta fica sem saúde.
     */
    protected void gameOver()
    {
        parent.engine().destroyAll();
    }
}
