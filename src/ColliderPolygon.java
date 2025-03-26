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

        this.centroid = this.calculateCentroid();
        
        this.angle = 0;
        this.scale = 1;
    }

    public ColliderPolygon(Transform trans, ArrayList<Point> pontos)
    {
        this(pontos);

        Point pDiff = trans.position().subNew(this.centroid);
        double aDiff = trans.angle();
        double sDiff = trans.scale();

        for(Point v : this.vertices)
        {
            v.addThis(pDiff);
            v.rotateThis(this.centroid, aDiff);
            v.scaleThis(this.centroid, sDiff);
        }
        
        this.angle = aDiff;
        this.scale = sDiff;
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

    //TODO: DOCUMENTAR
    private double calculateArea()
    {
        double res = 0;
        int n = this.vertices.size();

        for(int i = 0; i < n; i++)
        {
            int j = (i+1)%n;
            Point pi = this.vertices.get(i);
            Point pj = this.vertices.get(j);
            res += pi.x() * pj.y() - pj.x() * pi.y();
        }

        return res / 2;
    }

    //TODO: DOCUMENTAR
    private Point calculateCentroid()
    {
        double A = this.calculateArea();
        int n = this.vertices.size();
        double x = 0;
        double y = 0;

        for(int i = 0; i < n; i++)
        {
            double r;
            int j = (i+1)%n;
            Point pi = this.vertices.get(i);
            Point pj = this.vertices.get(j);
            r = pi.x() * pj.y() - pj.x() * pi.y();

            x += (pi.x() + pj.x()) * r;
            y += (pi.y() + pj.y()) * r;
        }

        Point c = new Point(x / (6*A), y / (6*A));
        return c;
    }

    //TODO: DOCUMENTAR e TESTAR
    public void scale(double dScale)
    {
        for(Point v : this.vertices)
        {
            v.scaleThis(this.centroid, dScale);
        }
        this.scale *= dScale;
    }

    //TODO: DOCUMENTAR e TESTAR
    public void move(Point dPos)
    {
        for(Point v : this.vertices)
        {
            v.addThis(dPos);
        }
    }

    //TODO: DOCUMENTAR e TESTAR
    public void rotate(double dAngle)
    {
        for(Point v : this.vertices)
        {
            v.rotateThis(this.centroid, dAngle);
        }
        this.angle += dAngle;
    }

    /** Devolve uma representação em String to Collider, no formato: "[<vertice0>,<vertice1>,...,<verticeN>]" */
    @Override
    public String toString()
    {
        return this.vertices.toString();
    }
}
