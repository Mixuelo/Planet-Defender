import java.util.ArrayList;
import java.util.Scanner;

public class Cliente
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        String s;
        String [] aos;

        GameEngine gameEngine = new GameEngine();
        int frames = Integer.parseInt(sc.nextLine());
        int n = Integer.parseInt(sc.nextLine());

        for(int i = 0; i < n; i++)
        {
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
                for(int j = 0; j < aos.length; j+=2)
                {
                    points.add(new Point(Double.parseDouble(aos[j]), Double.parseDouble(aos[j+1])));
                }
                collider = new ColliderPolygon(transform, points);
            }
            else
            {
                sc.close();
                throw new IllegalArgumentException("Número de valores inválido: " + aos.length);
            }

            GameObject go = new GameObject(name, transform, collider);

            s = sc.nextLine();
            aos = s.split(" ");
            go.move(new Point(Double.parseDouble(aos[0]), Double.parseDouble(aos[1])), Integer.parseInt(aos[2]));
            go.rotate(Double.parseDouble(aos[3]));
            go.scale(Double.parseDouble(aos[4]));

            gameEngine.add(go);
        }

        for (int i = 0; i < n; i++)
        {
            ArrayList<GameObject> collidingObjects = new ArrayList<>();
            for (int j = 0; j < n; j++)
            {
                if (j == i) continue;
                if (gameEngine.objects().get(i).collider().checkCollision(gameEngine.objects().get(j).collider()))
                {
                    collidingObjects.add(gameEngine.objects().get(j));
                }
            }

            if (!collidingObjects.isEmpty())
            {
                StringBuilder names = new StringBuilder();
                for (GameObject g : collidingObjects)
                {
                    names.append(g.name() + " ");
                }
                names.deleteCharAt(names.length() - 1);
                System.out.println(gameEngine.objects().get(i).name() + " " + names.toString());
            }
        }

        sc.close();
        return;
    }
}

