import Batiments.Nexus;
import Obstacles.Obstacle;
import Personnages.Archer;
import Personnages.Guerrier;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
public class Main {
    public static void main(String[] args) {
        JFrame fenetre = new JFrame("Sunrise Siege");
        //Map map = new Map(map.getObstacles(),map.getPersonnages(),map.getBatiments());
        //Affichage aff = new Affichage(map);
        //fenetre.add(aff);
        fenetre.pack();
        fenetre.setVisible(true);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
