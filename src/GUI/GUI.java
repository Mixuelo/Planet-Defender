package GUI;

import Engine.*;
import java.awt.event.InputEvent;
import javax.swing.*;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

public class GUI extends JFrame
{
    private final GamePanel gamePanel;
    private final Queue<InputEvent> inputQueue;

    public GUI()
    {
        super("Planet Defender");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 600);

        this.inputQueue = new ConcurrentLinkedQueue<>();
        this.gamePanel = new GamePanel();

        this.add(gamePanel);
        this.addKeyListener(new KeyListenerImpl(inputQueue));
        this.setVisible(true);
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
    public void putOnScreen(GameEngine engine)
    {
        gamePanel.engine(engine);
    }
}
