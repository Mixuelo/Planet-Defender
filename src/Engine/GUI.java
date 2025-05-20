package Engine;

import java.awt.event.InputEvent;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class GUI
{
    List<IGameObject> gol = new CopyOnWriteArrayList<>();

    /**
     * Retorna o 1º InputEvent de uma fila de espera que existe no GUI e
     * os listeners implementados no GUI adicionam as teclas ou info do rato ao fim desta fila, quando pressionadas.
     * Esta fila pode ser implementada com uma lista
     * @return {@code InputEvent}
     */
    public InputEvent getUserInput()
    {

    }

    /**
     * Aceita uma lista de GameObjects enabled para colocar no ecrã
     * Estas listas, no GameEngine e no GUI tem de ser thread safe, pq o Java AWT/Swing corre numa thread diferente do resto do código Java.
     */
    public void putOnScreen(List<IGameObject> gol)
    {

    }
}
