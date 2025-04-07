import java.util.ArrayList;

/** Classe para o GameEngine, definido por uma lista de GameObjects.
 *  @author Miguel Alvito, Nicole Reis e Pedro Pinto
 *  @version 1.0 (2025-04-02)
 */
public class GameEngine
{
    private ArrayList<GameObject> objects;

    /**
     * Construtor para o GameEngine.
     */
    public GameEngine()
    {
        objects = new ArrayList<>();
    }

    public ArrayList<GameObject> objects()
    {
        return this.objects;
    }

    /**
     * Adiciona um {@code GameObject} ao GameEngine.
     * @param go {@code GameObject}
     */
    public void add(GameObject go)
    {
        objects.add(go);
    }

    /**
     * Remove um {@code GameObject} do GameEngine.
     * @param go {@code GameObject}
     */
    public void destroy(GameObject go)
    {
        objects.remove(go);
    }
}
