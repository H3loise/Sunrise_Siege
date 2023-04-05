package Model.Personnages;

import Model.Map;

public class Guerrier extends Personnage {
    private  static int health_points = 1000;        // apparemment faut mettre static pour que ça rentre dans le super

    private static int attack_points = 50;               // apparemment faut mettre static pour que ça rentre dans le super
    private static final int rayon = 50;

    public static final int woodPrice = 0;
    public static final int wheatPrice = 3;

    public static final int stonePrice = 4;


    public Guerrier(int x, int y){
        super(health_points,x,y,rayon, attack_points);
        this.hpMax = this.getHealth_points();
        level = 1;
    }

    public void upgrade(){
        level ++;
        hpMax = (hpMax *  level) + (50*level);
        attack_points = (attack_points * level) + (15*level);
        health_points = hpMax;
    }
}
