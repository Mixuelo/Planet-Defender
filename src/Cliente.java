import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Cliente
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        String s;
        String [] aos;

        String name = sc.nextLine();

        s = sc.nextLine();
        aos = s.split(" ");
        Transform transform = new Transform(new Point(Double.parseDouble(aos[0]), Double.parseDouble(aos[1])),
                Integer.parseInt(aos[2]), Double.parseDouble(aos[3]), Double.parseDouble(aos[4]));
        Collider collider = null;

        s = sc.nextLine();
        aos = s.split(" ");
        if(aos.length == 3)
        {
            collider = new ColliderCircle(transform, new Point(Double.parseDouble(aos[0]), Double.parseDouble(aos[1])), Double.parseDouble(aos[2]));
        }
        else if (aos.length >= 6)
        {
            ArrayList<Point> points = new ArrayList<Point>();
            for(int i = 0; i < aos.length; i+=2)
            {
                points.add(new Point(Double.parseDouble(aos[i]), Double.parseDouble(aos[i+1])));
            }
            collider = new ColliderPolygon(transform, points);
        }
        else
        {
            sc.close();
            throw new IllegalArgumentException("Número de valores inválido: " + aos.length);
        }

        GameObject gameObject = new GameObject(name, transform, collider);
        while(sc.hasNextLine())
        {
            s = sc.nextLine();
            if (s.isEmpty()) break;
            aos = s.split(" ");
            if(aos[0].equals("move"))
            {
                gameObject.move(new Point(Double.parseDouble(aos[1]), Double.parseDouble(aos[2])), Integer.parseInt(aos[3]));
            }
            else if(aos[0].equals("rotate"))
            {
                gameObject.rotate(Integer.parseInt(aos[1]));
            }
            else if(aos[0].equals("scale"))
            {
                gameObject.scale(Double.parseDouble(aos[1]));
            }
            else
            {
                sc.close();
                throw new IllegalArgumentException("Comando inválido");
            }
        }
        System.out.println(gameObject.toString());
        sc.close();
        return;
    }
}

