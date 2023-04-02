package Model.Personnages;

public class Ennemy extends Personnage {
    private static final int health_points = 10000;
    private static final int attack_points = 50;
    private static int rayon = 30;

    public Ennemy(int x, int y){
        super(health_points,x,y,rayon, attack_points);
        this.hpMax = this.getHealth_points();
    }

}