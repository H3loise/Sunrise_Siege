import Personnages.Archer;
import Personnages.Guerrier;

public class Main {
    public static void main(String[] args) {
        Archer a = new Archer(0,0);
        Guerrier g = new Guerrier(0,0);
        System.out.println(g.getHealth_points());
        a.attack(g);
        System.out.println(g.getHealth_points());
    }
}
