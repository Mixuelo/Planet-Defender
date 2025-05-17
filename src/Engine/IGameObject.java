package Engine;

/**
 * Interface implementada pela classe GameObject.
 * @author Miguel Alvito, Nicole Reis e Pedro Pinto
 * @version 1.0 (08/05/2025)
 */
public interface IGameObject
{
    /**
    * @return the name of the GameObject
    */
    String name();

    /**
    * @return the Transform of the GameObject
    */
    ITransform transform();

    /**
    * @return the Collider of the GameObject with its centroid at this.transform().position()
    */
    ICollider collider();

    /**
     * @return the Behaviour of the GameObject
     */
    IBehaviour behaviour();

    /**
     * @return the Shape of the GameObject
     */
    IShape shape();

}
