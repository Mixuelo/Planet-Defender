import java.util.ArrayList;

/** Collider em forma de poligono
    @author     Miguel Alvito
    @version    1.0 (2024-03-25)
*/
public class ColliderPolygon extends Collider
{
    private ArrayList<Point> vertices;

    /** Contrutor do Collider em poligono
        @param pontos   um ArrayList contendo os vertices do poligono, em Point, estes têm que estar ordenados 
    */
    public ColliderPolygon(ArrayList<Point> pontos) 
    {
        this.vertices = new ArrayList<Point>();

        for(Point p : pontos)
        {
            this.vertices.add(p.clone());
        }
    }

    /** Devolve uma copia do vetor de vertices do poligono */
    public ArrayList<Point> vertices()
    {
        ArrayList<Point> res = new ArrayList<Point>(this.vertices.size());
        for(Point v : this.vertices)
        {
            res.add(v.clone());
        }
        return res;
    }

    /** Devolve uma representação em String to Collider, no formato: "[<vertice0>,<vertice1>,...,<verticeN>]" */
    @Override
    public String toString()
    {
        return this.vertices.toString();
    }
}
