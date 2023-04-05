package Model.Personnages;

import Model.Map;
import Model.ThreadScanEnnemies;

public class Ennemy extends Personnage {
    private static final int health_points = 1000;
    private static final int attack_points = 150;
    private static final int rayon = 50;

    public Ennemy(int x, int y, Map map){
        super(health_points,x,y,rayon, attack_points, map);
        this.hpMax = this.getHealth_points();
        //new ThreadScanEnnemies(map,this);
    }

}