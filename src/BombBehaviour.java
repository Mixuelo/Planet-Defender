import java.util.List;

/**
 * Subclasse de Behaviour responsável pelo comportamento das bombas.
 * @author Miguel Alvito, Nicole Reis, Pedro Pinto
 * @version 1.0 (08/05/2025)
 */
public class BombBehaviour extends Behaviour
{
    private boolean explode;
    private static final double explodeScale = 5;

    /**
     * Construtor (inicializa a bomba como "não explodida").
     */
    public BombBehaviour() 
    {
        this.explode = false;
    }

    /**
     * Executa uma explosão.
     */
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

    /**
     * Método usado para reagir a colisões.
     * @param gol {@code List<IGameObject>}
     */
    @Override
    public void onCollision(List<IGameObject> gol)
    {
        super.onCollision(gol);

        for(IGameObject go : gol)
        {
            //TODO: fazer com que bombas nao colidem com o proprio pai nos primeiros instantes da sua criaçao
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
