package Model.Personnages;

public class Archer extends Personnage {

    private static int health_points = 1000;

    private static int attack_points = 5;

    private static final int rayon = 200;

    public static final int woodPrice = 4;
    public static final int wheatPrice = 3;
    public static final int stonePrice = 0;


    public Archer(int x, int y) {
        super(health_points,x,y,rayon,attack_points);
        this.hpMax = this.getHealth_points();
        level = 1;
    }

    public void upgrade(){
        level ++;
        hpMax = (hpMax *  level) + (15*level);
        attack_points = (attack_points * level) + (50*level);
        health_points = hpMax;
    }

}
