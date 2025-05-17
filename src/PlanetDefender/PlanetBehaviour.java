package PlanetDefender;

import java.awt.event.InputEvent;
import Engine.*;

/**
 * Subclasse de CharacterBehaviour responsável pelo comportamento do planeta.
 * @author Miguel Alvito, Nicole Reis, Pedro Pinto
 * @version 1.0 (08/05/2025)
 */
public class PlanetBehaviour extends CharacterBehaviour
{
    private MovingObject player;
    private boolean playerAlive;
    private double playerRecovery;
    private static final int HEALTH = 150;
    private static final double PLAYER_RECOVERY_TIME = 7;
    private static final int DESTRUCTION_EFFECT_TIME = 5;

    /**
     * Construtor.
     */
    public PlanetBehaviour()
    {
        super(HEALTH);
        this.playerAlive = true;
        this.playerRecovery = 0;
    }

    /**
     * Define um objeto como player (setter).
     * @param p {@code MovingObject}
     */
    public void player(MovingObject p) 
    {
        this.player = p;
    }

    /**
     * Atualiza este PlanetBehaviour com base no tempo e input.
     * @param dT {@code double}
     * @param ie {@code InputEvent}
     */
    @Override
    public void onUpdate(double dT, InputEvent ie)
    {
        if(!this.playerAlive) { this.playerRecovery -= dT; }
        if(this.playerRecovery <= 0) { this.recoverPlayer(); }

        // TODO: atualizar UIObject que apresenta a vida do planeta
    }

    /**
     * Declara Game Over e apaga este PlanetBehaviour.
     */
    @Override
    public void onDefeat()
    {
        Transform effectTransform = this.parent.transform().clone();
        effectTransform.move(new Point(0,0), 2);
        // TODO: definir tempo com base na duraçao da animaçao
        EffectObject effect = new EffectObject(this.parent.name() + "_effect", effectTransform, DESTRUCTION_EFFECT_TIME);
        this.parent.engine().addEnabled(effect);

        this.gameOver();
        this.parent.engine().destroy(this.parent);
    }

    /**
     * Declara Game Over.
     */
    protected void gameOver()
    {
        UIObject uiObject = new UIObject("GameOver", this.parent.transform().clone(), new TitleBehaviour());
        this.parent.engine().addEnabled(uiObject);
    }

    /**
     * Método chamado quando a nave do jogador é derrotada.
     */
    public void onPlayerDefeat()
    {
        this.playerAlive = false;
        this.playerRecovery = PLAYER_RECOVERY_TIME;
    }

    /**
     * Revive a nave do jogador.
     */
    private void recoverPlayer()
    {
        this.player.move(this.parent.transform().position().subNew(this.player.transform().position()), 0);
        this.player.setVelocity(new Point(0,0));
        this.player.transform().rotate(-this.player.transform().angle());
        this.playerAlive = true;
        this.parent.engine().enable(this.player);
    }
}
