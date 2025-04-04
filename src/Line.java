/**
 * Classe para retas.
 * @author Miguel Alvito, Nicole Reis e Pedro Pinto
 * @version 1.0 (2024-03-25)
 */
public class Line
{
    protected Point p1;
    protected Point p2;

    /** Construtor da reta.
        @param p1   o primeiro Point
        @param p2   o segundo Point
    */
    public Line(Point p1, Point p2)
    {
        checkEquals(p1, p2);
        this.p1 = p1.clone();
        this.p2 = p2.clone();
    }

    /** Verifica se os dois pontos são iguais.
        @param p1   o primeiro Point
        @param p2   o segundo Point
    */
    public static void checkEquals(Point p1, Point p2) throws IllegalArgumentException
    {
        if(p1.equals(p2))
        {
            throw new IllegalArgumentException("Os dois pontos dados são iguais");
        }
    }

    /** enumerador usado como tipo de retorno do método orientation(Point that) */
    enum orientation
    {
        CLOCKWISE,
        COUNTERCLOCKWISE,
        COLLINEAR
    }

    /** Descobre a orientação do tuplo de pontos ordenados (this.p1, this.p2, that)
        @param that o outro Point
        @return     devolve a orientação do tuplo, atravéz de um enumerador do tipo orientation
        @see        <a href = "https://www.geeksforgeeks.org/check-if-two-given-line-segments-intersect/" a>
     */
    protected orientation pointOrientation(Point that)
    {
        double val = (this.p2.y() - this.p1.y()) * (that.x() - this.p2.x()) - (this.p2.x() - this.p1.x()) * (that.y() - this.p2.y());

        if (val <= 1e-9 && val >= -1e-9) return orientation.COLLINEAR;

        return (val > 0) ? orientation.CLOCKWISE : orientation.COUNTERCLOCKWISE;
    }

    /** Verifica se o Point that é colinear à reta
        @param that o Point a verificar
        @return     devolve true caso o Point that for colinear à reta, devolve false caso contrario
     */
    public boolean pointCollinear(Point that)
    {
        return this.pointOrientation(that) == orientation.COLLINEAR;
    }

    /** Calcula o ponto inserido na reta, mais proximo do ponto that 
        @param that o ponto a verificar
        @post       return esta inserido na reta
    */
    public Point closestPointFromPoint(Point that)
    {
        Point d = this.p2.subNew(this.p1);
        double dmag = Math.sqrt(d.x()*d.x() + d.y()*d.y());
        d.multThis(1/dmag);

        Point lhs = that.subNew(this.p1);

        double dot = lhs.x() * d.x() + lhs.y() * d.y();
        
        double px = this.p1.x() + d.x() * dot;
        double py = this.p1.y() + d.y() * dot;

        return new Point(px, py);
    }
}
