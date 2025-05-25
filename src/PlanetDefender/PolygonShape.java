package PlanetDefender;

import Engine.Point;
import Engine.Shape;
import Engine.Transform;

import java.awt.*;
import java.util.ArrayList;

/**
 * Subclasse de Shape para o PolygonShape.
 * @author Miguel Alvito, Nicole Reis e Pedro Pinto
 * @version 1.0 (17/05/2025)
 */
public class PolygonShape extends Shape
{
    private ArrayList<Point> vertices;
    private Color color;

    /**
     * Construtor.
     */
    public PolygonShape(ArrayList<Point> pontos, Color c, Transform trans)
    {
        this.vertices = new ArrayList<Point>();

        for(Point p : pontos)
        {
            this.vertices.add(p.clone());
        }

        this.position = this.calculateCentroid();

        this.angle = 0;
        this.scale = 1;

        Point pDiff = trans.position().subNew(this.position);
        double aDiff = trans.angle();
        double sDiff = trans.scale();
        this.position = trans.position().clone();

        for(Point v : this.vertices)
        {
            v.addThis(pDiff);
            v.rotateThis(this.position, aDiff);
            v.scaleThis(this.position, sDiff);
        }

        this.angle = aDiff;
        this.scale = sDiff;
        this.transform = trans;
        this.color = c;
        this.drawOrder = 0;
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
     * Este método calcula a posição do polígono.
     * @return position {@code Point}
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
    @Override
    public void scale(double dScale)
    {
        double finalScale = this.scale + dScale;
        double mult = finalScale / this.scale;
        for(Point v : this.vertices)
        {
            v.scaleThis(this.position, mult);
        }
        this.scale += dScale;
    }

    /**
     * Este método move o Collider.
     * @param dPos {@code Point}
     */
    @Override
    public void move(Point dPos)
    {
        for(Point v : this.vertices)
        {
            v.addThis(dPos);
        }
        this.position.addThis(dPos);
    }

    /**
     * Este método faz a rotação do Collider em determinados graus.
     * @param dAngle {@code double}
     */
    @Override
    public void rotate(double dAngle)
    {
        for(Point v : this.vertices)
        {
            v.rotateThis(this.position, dAngle);
        }
        this.angle += dAngle;
        this.angle %= 360;
    }

    /**
     * Desenha a imagem no ecrã.
     * @param g {@code Graphics2d}
     */
    @Override
    public void draw(Graphics2D g)
    {
        g.setColor(this.color);
        int n = this.vertices.size();
        int[] x = new int[n];
        int[] y = new int[n];
        for(int i = 0; i < n; i++)
        {
            x[i] = (int) this.vertices.get(i).x();
            y[i] = (int) this.vertices.get(i).y();
        }
        g.fillPolygon(x, y, n);
    }
}
