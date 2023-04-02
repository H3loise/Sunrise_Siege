package Model.Personnages;

public class Ennemy extends Personnage {
    private static int health_points = 100;
    private final int attack_points = 50;
    private static int rayon = 30;


    public Ennemy(int x, int y){
        super(health_points,x,y,rayon);
    }

    public void attack(Personnage p){
        p.attackedPersonnage(this.attack_points);
    }
}