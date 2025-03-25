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
        checkVertices(pontos);
        this.vertices = new ArrayList<Point>();

        for(Point p : pontos)
        {
            this.vertices.add(p.clone());
        }
    }

    private static void checkVertices(ArrayList<Point> pontos) throws IllegalArgumentException
    {
        int n = pontos.size();

        if(n < 3) 
        {
            throw new IllegalArgumentException("O poligono tem menos de 3 pontos: " + n);
        }

        ArrayList<LineSegment> edges = new ArrayList<LineSegment>();

        for(int i = 0; i < n; i++)
        {
            LineSegment e = new LineSegment(pontos.get(i), pontos.get((i+1)%n));

            if(e.pointCollinear(pontos.get((i+2)%n)))
            {
                throw new IllegalArgumentException("O poligono tem duas arestas colineares");
            }

            for(LineSegment other : edges)
            {
                if(e.segmentIntersect(other))
                {
                    throw new IllegalArgumentException("O poligono tem duas arestas que intersetam");
                }
            }

            edges.add(e);
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
