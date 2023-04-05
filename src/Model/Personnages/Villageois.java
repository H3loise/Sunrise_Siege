package Model.Personnages;

import Model.Map;

public class Villageois extends Personnage{
    private static final int health_points = 1;

    public static final int woodPrice = 2;
    public static final int wheatPrice = 2;

    public static final int stonePrice = 0;


    public Villageois(int x, int y, Map map){
        super(health_points,x,y,0,0,map);
        this.hpMax = this.getHealth_points();
    }


}
