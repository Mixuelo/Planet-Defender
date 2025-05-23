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
        if(e.getKeyCode() == KeyEvent.VK_SPACE ||
           e.getKeyCode() == KeyEvent.VK_UP ||
           e.getKeyCode() == KeyEvent.VK_LEFT ||
           e.getKeyCode() == KeyEvent.VK_RIGHT)
            inputQueue.add(e);
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        if(e.getKeyCode() == KeyEvent.VK_SPACE ||
           e.getKeyCode() == KeyEvent.VK_UP ||
           e.getKeyCode() == KeyEvent.VK_LEFT ||
           e.getKeyCode() == KeyEvent.VK_RIGHT)
            inputQueue.add(e);
    }

    @Override
    public void keyTyped(KeyEvent e)
    {

    }
}
