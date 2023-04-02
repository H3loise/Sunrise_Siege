package Model.Personnages;

public class Guerrier extends Personnage {
    private  static int health_points = 150;// apparemment faut mettre static pour que ça rentre dans le super

    public static final int woodPrice = 0;
    public static final int wheatPrice = 3;

    public static final int stonePrice = 4;


    public Guerrier(int x, int y){
        super(health_points,x,y);
        this.hpMax = this.getHealth_points();
        level = 1;
    }

    public void upgrade(){
        level ++;
        hpMax = (hpMax *  level) + (50*level);
        this.setAttack_points((getAttack_points() * level) + (15*level));
        health_points = hpMax;
    }



    public void attack(Personnage p){
        p.attackedPersonnage(this.getAttack_points());
    }
}
