import java.awt.event.InputEvent;

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
     * Atualiza este PlanetBehaviour com base no tempo e input.
     * @param dT {@code double}
     * @param ie {@code InputEvent}
     */
    @Override
    public void onUpdate(double dT, InputEvent ie)
    {
        super.onUpdate(dT, ie);

        // TODO: atualizar UIObject que apresenta a vida do planeta
    }

    /**
     * Declara Game Over e apaga este PlanetBehaviour.
     */
    @Override
    void onDefeat()
    {
        // TODO: criar efeito de destruiçao do planeta
        this.gameOver();
        this.parent.engine().destroy(this.parent);
    }

    /**
     * Declara Game Ove.
     */
    protected void gameOver()
    {
        // TODO: criar UIObject com mensagem de Game Over
    }
}
