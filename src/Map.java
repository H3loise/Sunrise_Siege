import Batiments.Batiment;
import Obstacles.Obstacle;
import Personnages.Personnage;

import java.util.ArrayList;

public class Map {
    private ArrayList<Obstacle> obstacles;
    private ArrayList<Personnage> characters;
    private ArrayList<Batiment> batiments;
    public static final int taille=600;
    public static final int windowWidth = 1480;
    public static final int windowHeight = 920;

    public ArrayList<Obstacle> getObstacles() {
        return obstacles;
    }

    public ArrayList<Batiment> getBatiments() {
        return batiments;
    }

    public ArrayList<Personnage> getPersonnages() {
        return characters;
    }

    public Map(ArrayList<Obstacle> o, ArrayList<Personnage> c, ArrayList<Batiment> b){
        this.batiments=b;
        this.obstacles=o;
        this.characters=c;
    }


}
