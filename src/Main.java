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
        Guerrier TestDeplacement1 = new Guerrier(70,70);
        map.addCharacter(TestDeplacement1);
        map.deplacementPerso(TestDeplacement1,300,300);
        System.out.println(TestDeplacement1.getX());
        System.out.println(TestDeplacement1.getY());

    }
}
