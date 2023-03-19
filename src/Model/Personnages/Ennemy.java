package Model.Personnages;

public class Ennemy extends Personnage {
    private static final int health_points = 100;
    private final int attack_points = 50;
    private final int range = 50;
    public Ennemy(int x, int y){
        super(health_points,x,y);
    }

    public void attack(Personnage p) {
        p.attackedPersonnage(this.attack_points);
    }

}
