import Batiments.Nexus;
import Personnages.Archer;
import Personnages.Guerrier;
import java.awt.*;
import javax.swing.*;
public class Main {
    public static void main(String[] args) {
        JFrame fenetre = new JFrame("Sunrise Siege");
        Archer a = new Archer();
        Guerrier g = new Guerrier();
        System.out.println(g.getHealth_points());
        a.attack(g);
        System.out.println(g.getHealth_points());
        Affichage aff = new Affichage();
        fenetre.add(aff);
        fenetre.pack();
        fenetre.setVisible(true);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
