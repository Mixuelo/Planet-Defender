package GUI;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Queue;

public class KeyListenerImpl implements KeyListener
{
    private final Queue<InputEvent> inputQueue;

    public KeyListenerImpl(Queue<InputEvent> inputQueue)
    {
        this.inputQueue = inputQueue;
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        inputQueue.add(e);
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        inputQueue.add(e);
    }

    @Override
    public void keyTyped(KeyEvent e)
    {

    }
}
