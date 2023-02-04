import Batiments.Batiment;
import Batiments.Nexus;
import Obstacles.Obstacle;
import Personnages.Archer;
import Personnages.Personnage;

import java.util.ArrayList;
import javax.swing.*;
public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame("Sunrise Siege");
        ArrayList<Personnage> per = new ArrayList<>();
        ArrayList<Obstacle> obs = new ArrayList<>();
        ArrayList<Batiment> bat = new ArrayList<>();
        per.add(new Archer(50,50));
        obs.add(new Obstacle(70,70));
        bat.add(new Nexus(50,870));
        Map map = new Map();
        System.out.println(map.getObstacles());
        Affichage aff = new Affichage(map);
        window.add(aff);
        window.pack();
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
