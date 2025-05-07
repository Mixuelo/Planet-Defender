public abstract class CharacterBehaviour extends Behaviour
{
    private int health;

    public CharacterBehaviour(int health)
    {
        this.health = health;
    }
    public int health()
    {
        return this.health;
    }

    void takeDamage(int damage)
    {
        this.health -= damage;
        if(this.health <= 0) { onDefeat(); }
    }

    void onDefeat()
    {
        this.parent.engine().destroy(this.parent);
    }
}
