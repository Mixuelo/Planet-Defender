import java.util.List;

public class BombBehaviour extends Behaviour
{
    private boolean explode;
    private static final double explodeScale = 5;

    public BombBehaviour() 
    {
        this.explode = false;
    }

    public void explode() 
    {
        if(this.explode) { return; }

        this.explode = true;
        if(this.parent instanceof MovingObject) ((MovingObject) this.parent).setVelocity(new Point(0,0));
        //this.parent.shape = null; TODO: ADICIONAR ESTA LINHA
        Transform effectTransform = this.parent.transform().clone();
        effectTransform.move(new Point(0,0), 2);
        //TODO: definir tempo com base no tamanho da animaçao
        EffectObject effect = new EffectObject(this.parent.name() + "_effect", effectTransform, 10); 
        this.parent.engine().addEnabled(effect);

        this.parent.scale(explodeScale - this.parent.transform().scale());
    }

    @Override
    public void onCollision(List<IGameObject> gol)
    {
        super.onCollision(gol);

        for(IGameObject go : gol)
        {
            if(go.behaviour() instanceof CharacterBehaviour)
            {
                if(this.explode) 
                {
                    ((CharacterBehaviour) go.behaviour()).takeDamage(7);
                }
                else 
                {
                    this.explode();
                }
            }
        }
        if(this.explode) { this.parent.engine().destroy(this.parent); }
    }

}
