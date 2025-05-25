package PlanetDefender;

import java.util.List;
import Engine.*;

/**
 * Subclasse de Behaviour responsável pelo comportamento das bombas.
 * @author Miguel Alvito, Nicole Reis, Pedro Pinto
 * @version 1.0 (08/05/2025)
 */
public class BombBehaviour extends Behaviour
{
    private boolean explode;
    private GameObject owner;
    private static final double EXPLODE_SCALE = 5;
    private static final double BOMB_EFFECT_FPS = 15;
    private static final int DAMAGE = 10;

    /**
     * Construtor (inicializa a bomba como "não explodida").
     * @param owner {@code EnemyBomberBehaviour}
     */
    public BombBehaviour(GameObject owner)
    {
        this.owner = owner;
        this.explode = false;
    }

    /**
     * Executa uma explosão.
     */
    public void explode() 
    {
        if(this.explode) { return; }

        this.explode = true;
        this.owner = null;
        if(this.parent instanceof MovingObject) ((MovingObject) this.parent).setVelocity(new Point(0,0));
        this.parent.shape(null);
        Transform effectTransform = this.parent.transform().clone();
        effectTransform.move(new Point(0,0), 2);
        effectTransform.rotate(-effectTransform.angle());
        EffectObject effect = new EffectObject(this.parent.name() + "_effect", effectTransform, "imgs/boom", ".png", 0.5, 17, BOMB_EFFECT_FPS); 
        this.parent.engine().addEnabled(effect);

        this.parent.scale(EXPLODE_SCALE - this.parent.transform().scale());
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
            if(go.behaviour() instanceof CharacterBehaviour && !go.equals(this.owner))
            {
                if(this.explode) 
                {
                    ((CharacterBehaviour) go.behaviour()).takeDamage(DAMAGE);
                }
                else 
                {
                    this.explode();
                    return;
                }
            }
        }
        if(this.explode) { this.parent.engine().destroy(this.parent); }
    }
}
