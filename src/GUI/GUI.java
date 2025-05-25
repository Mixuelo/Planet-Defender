package GUI;

import Engine.*;
import java.awt.event.InputEvent;
import javax.swing.*;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Classe responsável pela interface gráfica do jogo.
 * @author Miguel Alvito, Nicole Reis, Pedro Pinto
 * @version 1.0 (25/05/2025)
 */
public class GUI
{
    private final JFrame frame;
    private final GamePanel gamePanel;
    private final transient Queue<InputEvent> inputQueue;

    /**
     * Construtor.
     */
    public GUI()
    {
        this.frame = new JFrame("Planet Defender");
        frame.setResizable(false);

        this.inputQueue = new ConcurrentLinkedQueue<>();
        this.gamePanel = new GamePanel();

        frame.add(gamePanel);
        frame.addKeyListener(new KeyListenerImpl(inputQueue));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setVisible(true);
    }

    /**
     * Retorna o 1º InputEvent de uma fila de espera que existe no GUI.
     * @return {@code InputEvent}
     */
    public InputEvent getUserInput()
    {
        return inputQueue.poll();
    }

    /**
     * Aceita uma lista de GameObjects enabled para colocar no ecrã.
     */
    public void putOnScreen(List<IGameObject> objects)
    {
        CopyOnWriteArrayList<IGameObject> gol = new CopyOnWriteArrayList<>();
        gol.addAll(objects);
        gamePanel.gameObjects(gol);
    }
}
