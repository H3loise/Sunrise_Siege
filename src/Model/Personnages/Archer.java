package Model.Personnages;
public class Archer extends Personnage {

    private  static int health_points = 150;// apparemment faut mettre static pour que ça rentre dans le super

    private  int attack_points = 50;

    private final int min_attack_distance = 0;

    public static final int woodPrice = 4;
    public static final int wheatPrice = 3;

    public static final int stonePrice = 0;


    /**
     * Comme archer unité de combat a distance, donner limite
     */
    private final int max_attack_distance = 20;


    public Archer(int x, int y) {
        super(health_points,x,y);
        level = 1;
    }

    public void upgrade(){
        level ++;
        hpMax = (hpMax *  level) + (15*level);
        attack_points = (attack_points * level) + (50*level);
        health_points = hpMax;
    }

    public void attack(Personnage p) {
        p.attackedPersonnage(this.attack_points);
    }



}
