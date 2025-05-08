public class PlanetBehaviour extends CharacterBehaviour
{
    private static final int INITIAL_HEALTH = 100;

    public PlanetBehaviour()
    {
        super(INITIAL_HEALTH);
    }

    protected void gameOver()
    {
        parent.engine().destroyAll();
    }
}
