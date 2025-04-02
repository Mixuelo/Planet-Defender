/*
- ICollider:
    - a deteção de colisões é feita por estes objetos
	- os IColliders devem estar centrados na ITransform.position do gameobject
	- ICollider deve ter referência para a mesma ITransform que o gameobject e movimentar-se de acordo com esta
*/

public interface ICollider
{
    Point centroid();

    // ...
}
