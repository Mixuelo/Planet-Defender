import java.util.ArrayList;
import java.util.Scanner;

/*
- Cliente:
ENTRADA:
	-> n*4+2 linhas:
	nº de frames a simular f
	nº de GameObjects a criar (n)
	-> +4 linhas para cada GameObject:
	   1) name                                      (GameObject)
	   2) x y layer rotação fator                   (Transform)
	   3) x y r (círculo) ou 6/+ doubles (polígono) (Collider)
	   4) double double int double double -> 3 1ªs informação sobre a velocidade constante do GameObject em cada frame nos eixos x, y e na layer;
	                                         4º valor é a velocidade de rotação constante no sentido anti-horário em graus (por cada frame);
	                                         5º valor é um diferencial a ser somado à escala corrente em cada frame

SAÍDA:
	-> mín 0 e máx n linhas (1 para cada GameObject que esteja em colisão), ordenadas pela ordem dos objetos na entrada
	-> cada linha tem:
	nome (GameObject) nome1 nome 2 ... (GameObjects em colisão, após o nº de frames f, indicado da 1ª linha de entrada, ordenados pela ordem de entrada)
*/

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

            s = sc.nextLine();
            aos = s.split(" ");
            double velX = Double.parseDouble(aos[0]);
            double velY = Double.parseDouble(aos[1]);
            int velLayer = Integer.parseInt(aos[2]);
            double velAngle = Double.parseDouble(aos[3]);
            double diferential = Double.parseDouble(aos[4]);

            gameEngine.add(new GameObject(name, transform, collider));
        }

        //ArrayList<GameObject> collidingObjects = new ArrayList<>();
        //ArrayList<GameObject> collidingObjects2 = new ArrayList<>();
        // boolean colision = gameEngine.objects().get(i).collider().checkCollision(gameEngine.objects().get(j).collider());


        sc.close();
        return;
    }
}

