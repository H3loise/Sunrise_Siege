package Model.Personnages;

import Model.Map;

/**
 * Classe Guerrier, permettant de modéliser un Guerrier, unité controlable qui puisse attaquer les ennemis avec une range
 * précise. Un guerrier peut être améliorer grâce à @upgrade().
 * Sous-classe de Personnage.
 */
public class Guerrier extends Personnage {
    private  static int health_points = 1000;        // apparemment faut mettre static pour que ça rentre dans le super

    private static int attack_points = 50;               // apparemment faut mettre static pour que ça rentre dans le super
    private static final int rayon = 50;

    public static final int woodPrice = 0;
    public static final int wheatPrice = 3;

    public static final int stonePrice = 4;


    public Guerrier(int x, int y, Map map){
        super(health_points,x,y,rayon, attack_points, map);
        this.hpMax = this.getHealth_points();
        level = 1;
    }
    /**
     * Permet l'amélioration de guerrier, de ses caractéristiques comme l'attaque et les pv.
     */
    public void upgrade(){
        level ++;
        hpMax = (hpMax *  level) + (50*level);
        attack_points = (attack_points * level) + (15*level);
        health_points = hpMax;
    }
}
