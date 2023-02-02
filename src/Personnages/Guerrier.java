package Personnages;

public class Guerrier extends Personnage {
    private static final int health_points = 150;           // apparemment faut mettre static pour que ça rentre dans le super
    private final int attack_points = 50;               // apparemment faut mettre static pour que ça rentre dans le super

    public Guerrier(){
        super(health_points);
    }

    public void attack(Personnage p){
        p.attackedPersonnage(this.attack_points);
    }
}
