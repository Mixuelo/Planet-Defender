/**
 * Interface implementada pela classe abstrata Collider
 * @author Miguel Alvito, Nicole Reis e Pedro Pinto
 * @version 1.0 (08/05/2025)
 */
public interface ICollider
{
    /**
     * Devolve o centroid deste ICollider (getter).
     * @return centroid {@code Point}
     */
    public Point centroid();

    /**
     * Atualiza as informações deste ICollider de acordo com a sua Transform.
     */
    public void onUpdate();
}
