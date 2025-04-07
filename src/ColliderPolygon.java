import java.util.ArrayList;

/** Collider em forma de polígono.
    @author     Miguel Alvito, Nicole Reis e Pedro Pinto
    @version    1.0 (2024-03-25)
*/
public class ColliderPolygon extends Collider
{
    private ArrayList<Point> vertices;
    private ArrayList<LineSegment> edges;

    /** Construtor do Collider em polígono.
        @param pontos   um {@code ArrayList} contendo os vértices do poligono, {@code Point}, estes têm que estar ordenados.
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

        int n = this.vertices.size();
        this.edges = new ArrayList<LineSegment>();

        for(int i = 0; i < n; i++)
        {
            LineSegment e = new LineSegment(this.vertices.get(i), this.vertices.get((i+1)%n));
            this.edges.add(e);
        }

        this.angle = 0;
        this.scale = 1;
    }

    /**
     * Outro construtor do Collider em polígono.
     * @param trans {@code Transform}
     * @param pontos {@code ArrayList<Point>}
     */
    public ColliderPolygon(Transform trans, ArrayList<Point> pontos)
    {
        this(pontos);

        Point pDiff = trans.position().subNew(this.centroid);
        double aDiff = trans.angle();
        double sDiff = trans.scale();
        this.centroid = trans.position().clone();

        for(Point v : this.vertices)
        {
            v.addThis(pDiff);
            v.rotateThis(this.centroid, aDiff);
            v.scaleThis(this.centroid, sDiff);
        }

        int n = this.vertices.size();
        this.edges = new ArrayList<LineSegment>(n);

        for(int i = 0; i < n; i++)
        {
            LineSegment e = new LineSegment(this.vertices.get(i), this.vertices.get((i+1)%n));
            this.edges.add(e);
        }

        this.angle = aDiff;
        this.scale = sDiff;
    }

    /**
     * Este método verifica se os vértices dados formam um polígono.
     * @param pontos {@code ArrayList<Point>}
     * @throws IllegalArgumentException
     */
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

    /** Devolve uma cópia do vetor de vértices do polígono.
     *  @return {@code ArrayList<Point>}
     */
    public ArrayList<Point> vertices()
    {
        ArrayList<Point> res = new ArrayList<Point>(this.vertices.size());
        for(Point v : this.vertices)
        {
            res.add(v.clone());
        }
        return res;
    }

    /** Devolve uma cópia do vetor de arestas do polígono.
     *  @return {@code ArrayList<LineSegment>}
     */
    public ArrayList<LineSegment> edges()
    {
        int n = this.vertices.size();
        ArrayList<LineSegment> res = new ArrayList<LineSegment>(n);

        for(int i = 0; i < n; i++)
        {
            LineSegment e = new LineSegment(this.vertices.get(i), this.vertices.get((i+1)%n));
            res.add(e);
        }

        return res;
    }

    /**
     * Este método calcula a área do polígono.
     * @return área {@code double}
     */
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

    /**
     * Este método calcula o centroid do polígono.
     * @return centroid {@code Point}
     */
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

    /**
     * Este método altera a escala, adicionando dScale ao valor da escala.
     * @param dScale {@code double}
     */
    public void scale(double dScale)
    {
        double finalScale = this.scale + dScale;
        double mult = finalScale / this.scale;
        for(Point v : this.vertices)
        {
            v.scaleThis(this.centroid, mult);
        }
        for(LineSegment e : this.edges)
        {
            e.scale(this.centroid, mult);
        }
        this.scale += dScale;
    }

    /**
     * Este método move o Collider.
     * @param dPos {@code Point}
     */
    public void move(Point dPos)
    {

        for(Point v : this.vertices)
        {
            v.addThis(dPos);
        }
        for(LineSegment s : this.edges)
        {
            s.move(dPos);
        }
        this.centroid.addThis(dPos);
    }

    /**
     * Este método faz a rotação do Collider em determinados graus.
     * @param dAngle {@code double}
     */
    public void rotate(double dAngle)
    {
        for(Point v : this.vertices)
        {
            v.rotateThis(this.centroid, dAngle);
        }
        for(LineSegment e : this.edges)
        {
            e.rotate(this.centroid, dAngle);
        }
        this.angle += dAngle;
        this.angle %= 360;
    }

    /** Verifica se o ponto that, esta inserido dentro do poligono
        @param that o ponto a verificar
        @return     devolve true caso o ponto estiver dentro do poligono, false caso contrario
    */
    private boolean checkPointInside(Point that)
    {
        double maxX = that.x();

        for(Point v : this.vertices)
        {
            maxX = v.x() > maxX ? v.x() : maxX;
        }
        maxX++;

        LineSegment s = new LineSegment(that, new Point(maxX, that.y()));

        int count = 0;
        for(LineSegment a : this.edges)
        {
            if(a.segmentIntersect(s)) { count++; }
        }

        return (count % 2 != 0);
    }

    /** Verificar se existe uma colisão entre este poligono e outro colisor
        @param that o colisor a verificar
        @return     return == that.checkCollisionPolygon(this);
    */
    public boolean checkCollision(Collider that)
    {
        return that.checkCollisionPolygon(this);
    }

    /** Verificar se existe uma colisão entre este poligono e outro colisor em poligono
        @param that o colisor em poligono a verificar
        @return     return == that.checkCollisionPolygon(this)
    */
    public boolean checkCollisionPolygon(ColliderPolygon that)
    {
        for(Point p : this.vertices)
        {
            if(that.checkPointInside(p)) { return true; }
        }

		for(LineSegment i : this.edges)
		{
            for(LineSegment j : that.edges())
            {
			   if(i.segmentIntersect(j)) { return true; }
            }
		}
        return false;
    }

    /** Verificar se existe uma colisão entre este poligono e outro colisor em circulo
        @param that o colisor em circulo a verificar
        @return     return == that.checkCollisionPolygon(this)
    */
    public boolean checkCollisionCircle(ColliderCircle that)
    {
        if(this.checkPointInside(that.centroid())) { return true; }

        for(LineSegment i : this.edges)
        {
            if(that.segmentIntersect(i)) { return true; }
        }

        return false;
    }

    /**
     * Devolve uma representação em String to Collider, no formato: "[<vertice0>,<vertice1>,...,<verticeN>]"
     * @return {@code String}
     */
    @Override
    public String toString()
    {
        String str = "";
        int n = this.vertices.size();
        for(int i = 0; i < n; i++)
        {
            str += this.vertices.get(i).toString();
            if(i < n-1) { str += " "; }
        }
        return str;
    }
}
