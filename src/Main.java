import Model.Batiments.Batiment;
import Model.Batiments.Nexus;
import Model.Map;
import Model.Obstacles.Obstacle;
import Model.Personnages.Archer;
import Model.Personnages.Guerrier;
import Model.Personnages.Personnage;
import Model.Personnages.Villageois;
import Vue.Affichage;

import java.util.ArrayList;
import javax.swing.*;
public class Main {
    public static void main(String[] args) {
        Map map = new Map();
        new TimeChanger(map).start();
        Affichage aff = new Affichage(map);
        new RepaintThread(aff,map).start();
        Villageois TestDeplacement1 = new Villageois(50,50);
        map.addCharacter(TestDeplacement1);
        Obstacle v = new Obstacle(700,700);
        map.addObstacle(v);
        map.mining(TestDeplacement1,v);
       // map.deplacementPerso(TestDeplacement1,400,401);

    }
}
