package Model.Personnages;

import Model.Batiments.Nexus;
import Model.Map;
import Model.ThreadScanEnnemies;

/**
 * Classe Ennemy permettant de modéliser un ennemi, celui-ci est une sous-classe de Personnage.
 * Un ennemy est non controlable
 */
public class Ennemy extends Personnage {
    private static final int health_points = 1000;
    private static final int attack_points = 50;
    private static final int rayon = 50;



    public Ennemy(int x, int y){
        super(health_points,x,y,rayon, attack_points);
        this.hpMax = this.getHealth_points();
    }



}