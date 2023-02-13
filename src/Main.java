import Model.Batiments.Batiment;
import Model.Batiments.Nexus;
import Model.Map;
import Model.Obstacles.Obstacle;
import Model.Personnages.Archer;
import Model.Personnages.Personnage;
import Vue.Affichage;
import Vue.ThreadAfficheur;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame("Sunrise Siege");
        Map map = new Map();
        Affichage aff = new Affichage(map);
        new ThreadAfficheur(aff).start();
    }
}
