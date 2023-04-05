package Model.Personnages;


public class Villageois extends Personnage{
    private static final int health_points = 1;

    public static final int woodPrice = 2;
    public static final int wheatPrice = 2;

    public static final int stonePrice = 0;


    public Villageois(int x, int y){
        super(health_points,x,y,0,0);
        this.hpMax = this.getHealth_points();
    }


}
