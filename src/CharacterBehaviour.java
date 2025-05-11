/**
 * Subclasse abstrata de Behaviour responsável pelo comportamento dos “personagens” do jogo.
 * @author Miguel Alvito, Nicole Reis, Pedro Pinto
 * @version 1.0 (08/05/2025)
 */
public abstract class CharacterBehaviour extends Behaviour
{
    public int health;

    /**
     * Construtor.
     * @param health {@code int}
     */
    public CharacterBehaviour(int health)
    {
        this.health = health;
    }

    /**
     * Getter para a saúde deste CharacterBehaviour.
     * @return health {@code int}
     */
    public int health()
    {
        return this.health;
    }

    /**
     * Reduz a saúde deste CharacterBehaviour de acordo com um determinado dano.
     * @param damage {@code int}
     */
    public void takeDamage(int damage)
    {
        this.health -= damage;
        if(this.health <= 0)
        {
            onDefeat();
        }
    }

    /**
     * Destroi o GameObject associado a este CharacterBehaviour.
     */
    protected void onDefeat()
    {
        this.parent.engine().destroy(this.parent);
    }
}
