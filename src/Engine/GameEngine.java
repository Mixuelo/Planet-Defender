package Engine;

import GUI.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Timer;

/** Classe para o GameEngine, definido por uma lista de GameObjects.
 *  @author Miguel Alvito, Nicole Reis e Pedro Pinto
 *  @version 1.0 (2025-04-02)
 */
public class GameEngine implements IGameEngine
{
    private ArrayList<GameObject> enabled;
    private ArrayList<GameObject> disabled;
    private GUI gui;
    private double lastTime;

    /**
     * Construtor para o GameEngine.
     */
    public GameEngine()
    {
        enabled = new ArrayList<>();
        disabled = new ArrayList<>();
    }

    /**
     * Define um GUI (setter)
     * @param g {@code GUI}
     */
    public void gui(GUI g)
    {
        this.gui = g;
    }

    /**
     * Remove um {@code GameObject} do GameEngine.
     * @param go {@code GameObject}
     */
    public void destroy(IGameObject go)
    {
        if(go.behaviour() != null) { go.behaviour().onDestroy(); }
        if(this.enabled.remove(go)) { return; }
        this.disabled.remove(go);
    }

    /**
     * Verifica se um IGameObject existe.
     * @param go {@code IGameObject}
     * @return true se existir, false se não {@code boolean}
     */
    private boolean exists(IGameObject go)
    {
        return this.enabled.contains(go) || this.disabled.contains(go);
    }

    /**
     * Adiciona um novo IGameObject à lista de ativos.
     * @param go {@code IGameObject}
     */
    public void addEnabled(IGameObject go)
    {
        if(!this.exists(go)) 
        {
            this.enabled.add((GameObject) go);
            ((GameObject) go).engine(this);
            if(go.behaviour() != null) go.behaviour().onInit();
        }
    }

    /**
     * Adiciona um novo IGameObject à lista de inativos.
     * @param go {@code IGameObject}
     */
    public void addDisabled(IGameObject go)
    {
        if(!this.exists(go))
        {
            this.disabled.add((GameObject) go);
            ((GameObject) go).engine(this);
            if(go.behaviour() != null) go.behaviour().onInit();
        }
    }

    /**
     * Ativa um IGameObject da GameEngine.
     * @param go {@code IGameObject}
     */
    public void enable(IGameObject go)
    {
        if(this.disabled.remove(go)) 
        { 
            this.enabled.add((GameObject) go); 
            if(go.behaviour() != null) go.behaviour().onEnabled();
        }
    }

    /**
     * Desativa um IGameObject da GameEngine.
     * @param go {@code IGameObject}
     */
    public void disable(IGameObject go)
    {
        if(this.enabled.remove(go)) 
        {
            this.disabled.add((GameObject) go); 
            if(go.behaviour() != null) go.behaviour().onDisabled();
        }
    }

    /**
     * Verifica se um IGameObject está ativo.
     * @param go {@code IGameObject}
     * @return true se estiver ativo, false se não {@code boolean}
     */
    public boolean isEnabled(IGameObject go)
    {
        if(this.enabled.contains(go)) { return true; }
        return false;
    }

    /**
     * Verifica se um IGameObject está inativo.
     * @param go {@code IGameObject}
     * @return true se estiver inativo, false se não {@code boolean}
     */
    public boolean isDisabled(IGameObject go)
    {
        if(this.disabled.contains(go)) { return true; }
        return false;
    }

    /**
     * Devolve todos os IGameObject's ativos.
     * @return {@code List<IGameObject>}
     */
    public List<IGameObject> getEnabled()
    {
        List<IGameObject> list = new ArrayList<>();

        for(GameObject go : enabled)
        {
            list.add((IGameObject) go);
        }
        return list;
    }

    /**
     * Devolve todos os IGameObject's inativos.
     * @return {@code List<IGameObject>}
     */
    public List<IGameObject> getDisabled()
    {
        List<IGameObject> list = new ArrayList<>();

        for(GameObject go : disabled)
        {
            list.add((IGameObject) go);
        }

        return list;
    }

    /**
     * Destroy all IGameObjects
     * pos: calls onDestroy() for each IGameObject
     */
    public void destroyAll()
    {
        for(IGameObject go : enabled)
        {
            if(go.behaviour() != null) { go.behaviour().onDestroy(); }
        }
        for(IGameObject go : disabled)
        {
            if(go.behaviour() != null) { go.behaviour().onDestroy(); }
        }

        enabled = new ArrayList<>();
        disabled = new ArrayList<>();
    }

    /**
     * Generates a new frame:
     * Get user input from UI
     * update all the enabled GameObjects
     * check collisions and send info to GameObjects
     * update UI
     * pos: UI.input() &&
     *      calls Behaviour.onUpdate() for all enabled objects &&
     *      Behaviour.checkCollisions() &&
     *      UI.draw()
     */
    public void run()
    {
        BackgroundMusic music = new BackgroundMusic();
        music.playMusic("music/backMusic.wav");
        final int fps = 60;
        final double frameTime = 1000.0 / fps;

        lastTime = System.nanoTime() * 1e-9;

        Timer timer = new Timer((int) frameTime, new ActionListener() {
            public void actionPerformed(ActionEvent evt) { tick(); }
        });
        timer.start();

        for(;;)
        {
            //this.tick();
        }
    }

    /**
     * Executa um ciclo de atualização do engine.
     */
    private void tick()
    {
        double now = System.nanoTime() * 1e-9;
        double dt = now - lastTime;
        lastTime = now;

        InputEvent ie = gui.getUserInput();

        ArrayList<IGameObject> enabledClone = new ArrayList<>(this.enabled.size());

        for(IGameObject go : enabled)
        {
            enabledClone.add(go);
        }
        
        for(IGameObject go : enabledClone) 
        {
            if(go instanceof MovingObject) ((MovingObject) go).updateMovement(dt);
            if(go.collider() != null)      go.collider().onUpdate();
            if(go.shape() != null)         go.shape().onUpdate();
            if(go.behaviour() != null)     go.behaviour().onUpdate(dt, ie);
        }

        // envia lista de colisões para todos os IGameObject em enabled
        this.checkCollisions();
        //if (panel != null) panel.repaint();
        // envia a lista de IGameObjects em enabled para o GUI
        if (gui != null) gui.putOnScreen(this.getEnabled());
    }

    /**
     * Check collisions for all the enabled objects
     * pos: calls Behaviour.onCollision(gol) for all enabled GameObjects
     *      passing in the list of all the objects that collided with each IGameObject
     */
    public void checkCollisions()
    {
        ArrayList<GameObject> enabledClone = new ArrayList<>(this.enabled.size());

        for(IGameObject go : enabled)
        {
            enabledClone.add((GameObject) go);
        }

        for(GameObject go : enabledClone)
        {
            ArrayList<IGameObject> list = new ArrayList<>();

            for(GameObject other : enabledClone)
            {
                if(go != other && go.transform().layer() == other.transform().layer() && go.checkCollision(other))
                {
                    list.add(other);
                }
            }
            if (go.behaviour() != null) { go.behaviour().onCollision(list); }
        }
    }
}

