import Model.Batiments.Batiment;
import Model.Batiments.Nexus;
import Model.Map;
import Model.Obstacles.Obstacle;
import Model.Personnages.Archer;
import Model.Personnages.Guerrier;
import Model.Personnages.Personnage;
import Vue.Affichage;

import java.util.ArrayList;
import javax.swing.*;
public class Main {
    public static void main(String[] args) {
        Map map = new Map();
        new TimeChanger(map).start();
        Affichage aff = new Affichage(map);
        new RepaintThread(aff,map).start();
        Guerrier TestDeplacement1 = new Guerrier(400,401);
        map.addCharacter(TestDeplacement1);
        map.deplacementPerso(TestDeplacement1,350,300);
       // map.deplacementPerso(TestDeplacement1,400,401);

    }
}
