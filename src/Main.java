import Model.Batiments.Batiment;
import Model.Batiments.Nexus;
import Model.Map;
import Model.Obstacles.Obstacle;
import Model.Personnages.Archer;
import Model.Personnages.Personnage;
import Vue.Affichage;

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
        Map map = new Map(obs,per,bat);
        System.out.println(map.getObstacles());
        Affichage aff = new Affichage(map);
        window.add(aff);
        window.pack();
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
