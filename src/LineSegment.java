/**
 * Classe para segmentos.
 * @author Miguel Alvito, Nicole Reis e Pedro Pinto
 * @version 1.0 (2024-03-25)
 */
public class LineSegment extends Line
{
    /** Construtor do Segmento 
        @param p1 o primeiro Point
        @param p2 o segundo Point
    */
    public LineSegment(Point p1, Point p2)
    {
        super(p1, p2);
    }

    /** Retorna o primeiro ponto */
    public Point p1()
    {
        return super.p1;
    }

    /** Retorna o segundo ponto */
    public Point p2()
    {
        return super.p2;
    }

    /** Verifica se o Point colinear that esta no segmento
        @param that o Point a verificar
        @pre        o Point that é colinear ao segmento
        @return     devolve true caso o Point that esteja no segmento, devolve false caso contrário
        @see        <a href = https://www.geeksforgeeks.org/check-if-two-given-line-segments-intersect/ a>
     */
    public boolean pointOnSegment(Point that)
    {
        if(that.x() <= Math.max(this.p1.x(), this.p2.x()) && that.x() >= Math.min(this.p1.x(), this.p2.x()) &&
           that.y() <= Math.max(this.p1.y(), this.p2.y()) && that.y() >= Math.min(this.p1.y(), this.p2.y()))
        {
            return true;
        }

        return false;
    }

    /** Verifica se o Segmento that interseta este segmento
        @param that o outro Segmento
        @return     devolve true caso o Segmento that intersetar este segmento, devolve false caso contrario
        @see        <a href = https://www.geeksforgeeks.org/check-if-two-given-line-segments-intersect/ a>
     */
    public boolean segmentIntersect(LineSegment that)
    {
        orientation o1 = this.pointOrientation(that.p1());
        orientation o2 = this.pointOrientation(that.p2());
        orientation o3 = that.pointOrientation(this.p1);
        orientation o4 = that.pointOrientation(this.p2);

        if(o1 != o2 && o3 != o4)
        {
            if(o1 == orientation.COLLINEAR && this.pointOnSegment(that.p1())) { return false; }
            if(o2 == orientation.COLLINEAR && this.pointOnSegment(that.p2())) { return false; }
            if(o3 == orientation.COLLINEAR && that.pointOnSegment(this.p1)) { return false; }
            if(o4 == orientation.COLLINEAR && that.pointOnSegment(this.p2)) { return false; }

            return true;
        }

        return false;
    }

    // TODO: DOCUMENTAR
    public void move(Point dPos)
    {
        this.p1.addThis(dPos);
        this.p2.addThis(dPos);
    }

    // TODO: DOCUMENTAR
    public void rotate(Point axis, double dAngle)
    {
        this.p1.rotateThis(axis, dAngle);
        this.p2.rotateThis(axis, dAngle);
    }

    // TODO: DOCUMENTAR
    public void scale(Point axis, double scale)
    {
        this.p1.scaleThis(axis, scale);
        this.p2.scaleThis(axis, scale);
    }

    /** Devolve uma representação, em String, do segmento, no formato: "[<ponto1>,<ponto2>]"*/
    public String toString()
    {
        return String.format("[%s,%s]", this.p1, this.p2);
    }
}
