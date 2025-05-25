package PlanetDefender;

import Engine.*;

/**
 * Subclasse abstrata de Behaviour responsável pelo comportamento dos “personagens” do jogo.
 * @author Miguel Alvito, Nicole Reis, Pedro Pinto
 * @version 1.0 (08/05/2025)
 */
public abstract class CharacterBehaviour extends Behaviour
{
    protected int health;
    protected GameObject statusGauge;

    /**
     * Construtor.
     * @param health {@code int}
     */
    public CharacterBehaviour(int health)
    {
        this.health = health;
        this.statusGauge = null;
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
     * Setter para a saúde deste CharacterBehaviour.
     * @param hp {@code int}
     */
    public void health(int hp)
    {
        this.health = hp;
    }

    /**
     * Reduz a saúde deste CharacterBehaviour de acordo com um determinado dano.
     * @param damage {@code int}
     */
    public void takeDamage(int damage)
    {
        this.health -= damage;
        if(this.statusGauge != null) { ((ArcGaugeBehaviour) statusGauge.behaviour()).refresh(); }
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

    /**
     * Setter para o statusGauge.
     * @param sg {@code GameObject}
     */
    public void statusGauge(GameObject sg)
    {
        this.statusGauge = sg;
    }

    /**
     * Getter para o statusGauge.
     * @return sg {@code GameObject}
     */
    public GameObject statusGauge()
    {
        return this.statusGauge;
    }
}
