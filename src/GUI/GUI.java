package GUI;

import Engine.*;
import java.awt.event.InputEvent;
import javax.swing.*;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;

public class GUI
{
    private final JFrame frame;
    private final GamePanel gamePanel;
    private final transient Queue<InputEvent> inputQueue;

    public GUI()
    {
        this.frame = new JFrame("Planet Defender");

        this.inputQueue = new ConcurrentLinkedQueue<>();
        this.gamePanel = new GamePanel();

        frame.add(gamePanel);
        frame.addKeyListener(new KeyListenerImpl(inputQueue));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setVisible(true);
    }

    /**
     * Retorna o 1º InputEvent de uma fila de espera que existe no GUI e
     * os listeners implementados no GUI adicionam as teclas ou info do rato ao fim desta fila, quando pressionadas.
     * Esta fila pode ser implementada com uma lista
     * @return {@code InputEvent}
     */
    public InputEvent getUserInput()
    {
        return inputQueue.poll();
    }

    /**
     * Aceita uma lista de GameObjects enabled para colocar no ecrã
     * Estas listas, no GameEngine e no GUI tem de ser thread safe, pq o Java AWT/Swing corre numa thread diferente do resto do código Java como List<IGameObject> gol = new CopyOnWriteArrayList<>();
     */
    public void putOnScreen(List<IGameObject> objects)
    {
        CopyOnWriteArrayList<IGameObject> gol = new CopyOnWriteArrayList<>();
        gol.addAll(objects);
        gamePanel.gameObjects(gol);
    }
}
