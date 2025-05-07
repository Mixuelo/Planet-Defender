import java.util.List;

public class AsteroidBehaviour extends CharacterBehaviour
{
    private boolean large;
    public AsteroidBehaviour(int health, boolean large)
    {
        super(health);
        this.large = large;
    }

    @Override
    public void onCollision(List<IGameObject> gol)
    {
        super.onCollision(gol);

        for(IGameObject go : gol)
        {
            IBehaviour b = go.behaviour();
            if(b instanceof CharacterBehaviour)
            {
                CharacterBehaviour cb = (CharacterBehaviour) b;
                cb.takeDamage(5);
                this.gameObject().engine().destroy(this.gameObject());
            }
            else if(b instanceof BulletBehaviour)
            {
                divide();
                this.gameObject().engine().destroy(go);
                this.gameObject().engine().destroy(this.gameObject());
            }
        }
    }

    private void divide()
    {
        if(!this.large)
        {
            Transform parentTransform = this.gameObject().transform();
            double parentX = parentTransform.position().x();
            double parentY = parentTransform.position().y();

            for (int i = 0; i < 2; i++)
            {
                //TODO: acabar
            }
        }
    }
}
