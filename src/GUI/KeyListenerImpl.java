package GUI;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Queue;

/**
 * Subclasse de KeyListener responsável por capturar eventos de teclado relevantes.
 * @author Miguel Alvito, Nicole Reis, Pedro Pinto
 * @version 1.0 (25/05/2025)
 */
public class KeyListenerImpl implements KeyListener
{
    private final Queue<InputEvent> inputQueue;

    /**
     * Construtor.
     * @param inputQueue {@code Queue<InputEvent>}
     */
    public KeyListenerImpl(Queue<InputEvent> inputQueue)
    {
        this.inputQueue = inputQueue;
    }

    /**
     * Este método é chamado quando uma tecla (espaço, cima, esquerda ou direita) é pressionada.
     * @param e the event to be processed
     */
    @Override
    public void keyPressed(KeyEvent e)
    {
        if(e.getKeyCode() == KeyEvent.VK_SPACE ||
           e.getKeyCode() == KeyEvent.VK_UP ||
           e.getKeyCode() == KeyEvent.VK_LEFT ||
           e.getKeyCode() == KeyEvent.VK_RIGHT)
            inputQueue.add(e);
    }

    /**
     * Este método é chamado quando uma tecla (espaço, cima, esquerda ou direita) é libertada.
     * @param e the event to be processed
     */
    @Override
    public void keyReleased(KeyEvent e)
    {
        if(e.getKeyCode() == KeyEvent.VK_SPACE ||
           e.getKeyCode() == KeyEvent.VK_UP ||
           e.getKeyCode() == KeyEvent.VK_LEFT ||
           e.getKeyCode() == KeyEvent.VK_RIGHT)
            inputQueue.add(e);
    }

    /**
     * Este método é chamado quando uma tecla é digitada (não aplicável).
     * @param e the event to be processed
     */
    @Override
    public void keyTyped(KeyEvent e)
    {

    }
}
