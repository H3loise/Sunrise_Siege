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
        Archer archer = new Archer(50,50);
        map.addCharacter(TestDeplacement1);
        map.addCharacter(archer);
        Obstacle v = new Obstacle(700,700);
        map.addObstacle(v);
        //map.mining(TestDeplacement1,v);
        //map.deplacementPerso(archer,50,200);
        Archer archer2 = new Archer(50,50);
        //map.addCharacter(archer2);
       // map.deplacementPerso(archer2,50,800);


        //map.deplacementPerso(TestDeplacement1,400,401);

    }
}
