package Model.Personnages;

import Model.Map;

/**
 * Classe Archer, permettant de modéliser un Archer, unité controlable qui puisse attaquer les ennemis avec une range
 * précise. Un archer peut être améliorer grâce à @upgrade().
 * Sous-classe de Personnage.
 */
public class Archer extends Personnage {

    private static int health_points = 1000;

    private static int attack_points = 100;

    private static final int rayon = 200;

    public static final int woodPrice = 4;
    public static final int wheatPrice = 3;
    public static final int stonePrice = 0;


    public Archer(int x, int y) {
        super(health_points,x,y,rayon,attack_points);
        this.hpMax = this.getHealth_points();
        level = 1;
    }

    /**
     * Permet l'amélioration de archer, de ses caractéristiques comme l'attaque et les pv.
     */

    public void upgrade(){
        level ++;
        hpMax = (hpMax *  level) + (15*level);
        attack_points = (attack_points * level) + (50*level);
        health_points = hpMax;
        setHealth_points(getHpMax());
    }

}
