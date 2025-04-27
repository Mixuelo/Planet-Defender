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
        //TODO
    }

    void onDefeat()
    {
        //TODO
    }
}
