import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Cliente
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);

        String name = sc.nextLine();

        Transform transform = new Transform(new Point(sc.nextDouble(), sc.nextDouble()), sc.nextInt(), sc.nextDouble(), sc.nextDouble());
        Collider collider = null;

        String buffer = sc.nextLine();
        String line = sc.nextLine();
        String [] aos = line.split(" ");
        if(aos.length == 3)
        {
            collider = new ColliderCircle(new Point(Double.parseDouble(aos[0]), Double.parseDouble(aos[1])), Double.parseDouble(aos[2]));
        }
        else if (aos.length >= 6)
        {
            ArrayList<Point> points = new ArrayList<Point>();
            for(int i = 0; i < aos.length-1; i++)
            {
                points.add(new Point(Double.parseDouble(aos[i]), Double.parseDouble(aos[i+1])));
            }
            collider = new ColliderPolygon(points);
        }
        else
        {
            // lançar exceção
        }

        GameObject gameObject = new GameObject(name, transform, collider);



        sc.close();
        return;
    }
}
