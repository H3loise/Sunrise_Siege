package Model.Personnages;
import Model.Obstacles.Obstacle;

public class Villageois extends Personnage{
    private static final int health_points = 1;

    public Villageois(int x, int y){
        super(health_points,x,y);
    }

    /**
     * Extrait une ressource d'un coup et envoi les ressources instantanement au chateau
     * @param obs
     */
    public void extractRessource(Obstacle obs){
        
    }

}
