public class EffectObject extends GameObject
{
    public EffectObject(String n, Transform t, int time)
    {
        super(n, t, (Collider) null, (Behaviour) new EffectBehaviour(time));
    }
}
