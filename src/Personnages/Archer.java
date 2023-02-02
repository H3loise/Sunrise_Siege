package Personnages;

public class Archer extends Personnage {
    private static final String name = "Personnages.Archer";        // apparemment faut mettre static pour que ça rentre dans le super
    private static final int health_points = 150;       // apparemment faut mettre static pour que ça rentre dans le super
    private final int attack_points = 50;
    private final int min_attack_distance = 0;
    /**
     * Comme archer unité de combat a distance, donner limite
     */
    private final int max_attack_distance = 20;


    public Archer() {
        super(health_points, name);
    }

    public void attack(Personnage p) {
        p.attackedPersonnage(this.attack_points);
    }
}
