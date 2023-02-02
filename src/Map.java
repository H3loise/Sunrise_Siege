import Batiments.Batiment;
import Obstacles.Obstacle;
import Personnages.Personnage;

import java.util.ArrayList;

public class Map {
    private ArrayList<Obstacle> obstacles;
    private ArrayList<Personnage> personnages;
    private ArrayList<Batiment> batiments;
    public static final int taille=600;



    public Map(ArrayList<Obstacle> o, ArrayList<Personnage> p, ArrayList<Batiment> b){
        this.batiments=b;
        this.obstacles=o;
        this.personnages=p;
    }
}
