/**
 * Subclasse de CharacterBehaviour responsável pelo comportamento dos inimigos.
 * @author Miguel Alvito, Nicole Reis e Pedro Pinto
 * @version 1.0 (08/05/2025)
 */
public class EnemyBehaviour extends CharacterBehaviour
{
    protected GameObject target;

    /**
     * Construtor.
     * @param health {@code int}
     */
    public EnemyBehaviour(int health)
    {
        super(health);
    }

    /**
     * Define um target para este EnemyBehaviour (setter).
     * @param t {@code GameObject}
     */
    public void target(GameObject t) 
    {
        this.target = t;
    }
}
