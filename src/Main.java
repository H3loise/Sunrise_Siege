import Personnages.Archer;
import Personnages.Guerrier;

public class Main {
    public static void main(String[] args) {
        Archer a = new Archer();
        Guerrier g = new Guerrier();
        System.out.println(g.getHealth_points());
        a.attack(g);
        System.out.println(g.getHealth_points());
    }
}
