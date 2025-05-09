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

    @Override
    public void onUpdate(double dT, InputEvent ie) {
        super.onUpdate(dT, ie);

        // TODO: atualizar UIObject que apresenta a vida do planeta
    }

    @Override
    void onDefeat() {
        // TODO: criar efeito de destruiçao do planeta
        this.gameOver();
        this.parent.engine().destroy(this.parent);
    }

    /**
     * Destroi todos os elementos da engine quando o planeta fica sem saúde.
     */
    protected void gameOver()
    {
        // TODO: criar UIObject com mensagem de Game Over
    }
}
