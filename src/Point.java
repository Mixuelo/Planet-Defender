/** Ponto representado por coordenadas cartesianas
    @author     Miguel Alvito
    @version    1.0 (2024-03-25)
*/
public class Point implements Cloneable
{
    private double x;
    private double y;

    /** Construtor do Point
        @param x    o valor da abcissa do ponto
        @param y    o valor da ordenada do ponto
    */
    public Point(double x, double y)
    {
        this.x = x;
        this.y = y;
    }

    /** Devolve o valor da abcissa do ponto */
    public double x()
    {
        return this.x;
    }

    /** Devolve o valor da ordenada do ponto */
    public double y()
    {
        return this.y;
    }

    //TODO: DOCUMENTAR e TESTAR
    public Point subNew(Point that)
    {
        return new Point(this.x - that.x(), this.y - that.y());
    }

    //TODO: DOCUMENTAR e TESTAR
    public Point addNew(Point that)
    {
        return new Point(this.x + that.x(), this.y + that.y());
    }

    //TODO: DOCUMENTAR e TESTAR
    public Point multNew(Point that)
    {
        return new Point(this.x * that.x(), this.y * that.y());
    }

    //TODO: DOCUMENTAR e TESTAR
    public Point multNew(double factor)
    {
        return new Point(this.x * factor, this.y * factor);
    }

    //TODO: DOCUMENTAR e TESTAR
    public void subThis(Point that)
    {
        this.x -= that.x();
        this.y -= that.y();
    }

    //TODO: DOCUMENTAR e TESTAR
    public void addThis(Point that)
    {
        this.x += that.x();
        this.y += that.y();
    }

    //TODO: DOCUMENTAR e TESTAR
    public void multThis(Point that)
    {
        this.x *= that.x();
        this.y *= that.y();
    }

    //TODO: DOCUMENTAR e TESTAR
    public void multThis(double factor)
    {
        this.x *= factor;
        this.y *= factor;
    }

    //TODO: DOCUMENTAR e TESTAR
    public void rotateThis(Point axis, double dAngle)
    {
        this.subThis(axis);

        double rads = Math.toRadians(dAngle);
        double newX = this.x * Math.cos(rads) - this.y * Math.sin(rads);
        double newY = this.x * Math.sin(rads) + this.y * Math.cos(rads);
        this.x = newX;
        this.y = newY;

        this.addThis(axis);
    }

    //TODO: DOCUMENTAR e TESTAR
    public void scaleThis(Point axis, double dScale)
    {
        Point diff = this.subNew(axis);
        diff.multThis(dScale);
        Point res = axis.addNew(diff);
        
        this.x = res.x();
        this.x = res.y();
    }

    /** Devolve uma representação em String do ponto, no formato: "(<x>,<y>)" */
    @Override
    public String toString()
    {
        return String.format("(%.2f,%.2f)", this.x, this.y);
    }

    /** Verifica se este ponto e o objeto that são iguais
        @param that o objeto a verificar
    */
    @Override
    public boolean equals(Object that)
    {
        if(that == this) { return true; }
        if(that == null) { return false; }
        if(this.getClass() != that.getClass()) { return false; }   
        return this.equals((Point) that);
    }

    /** Verifica se este ponto e o ponto that têm coordenadas iguais
        @param that o outro ponto, em Point
        @post       return == that.equals(this)
    */
    public boolean equals(Point that)
    {
        if(this.x <= that.x() + 1e-9 && this.x >= that.x() - 1e-9 &&
           this.y <= that.y() + 1e-9 && this.y >= that.y() - 1e-9)
        {
            return true;
        }
        return false;
    }

    /** Clona este objeto, copiando as coordenadas */
    @Override
    public Point clone()
    {
        try
        {
            Point res = (Point) super.clone();
            res.x = this.x;
            res.y = this.y;
            return res;
        }
        catch(CloneNotSupportedException e)
        {
            throw new InternalError();
        }
    }

    /** Devolver hash code */ 
    @Override
    public int hashCode()
    {
        return super.hashCode();
    }

    /**
     * Este método aplica um deslocamento em x e y ao ponto.
     * @param dx {@code double}
     * @param dy {@code double}
     */
    public void translation(double dx, double dy)
    {
        x += dx;
        y += dy;
    }
}
