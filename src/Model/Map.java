package Model;

import Model.Batiments.Batiment;
import Model.Batiments.Nexus;
import Model.Obstacles.Obstacle;
import Model.Personnages.Archer;
import Model.Personnages.Guerrier;
import Model.Personnages.Personnage;
import Model.Personnages.Villageois;

import java.util.ArrayList;

public class Map {
    private ArrayList<Obstacle> obstacles;
    private ArrayList<Personnage> characters;
    private ArrayList<Batiment> batiments;
    public static final int taille=600;
    //a enlever
    public static final int windowWidth = 1480;
    public static final int windowHeight = 920;
    //
    private int food;
    private int stone;
    private int wood;
    private boolean day;


    public ArrayList<Obstacle> getObstacles() {
        return obstacles;
    }

    public ArrayList<Batiment> getBatiments() {
        return batiments;
    }

    public ArrayList<Personnage> getPersonnages() {
        return characters;
    }

    public int getFood() {
        return food;
    }

    public int getStone() {
        return stone;
    }

    public int getWood() {
        return wood;
    }

    public boolean getDay(){
        return day;
    }

    public void setDay(boolean d){
        this.day=d;
    }

    //Food a 0 mais pourrait etre set a une autre valeur au début
    public Map(){
        this.batiments = new ArrayList<>();
        this.characters = new ArrayList<>();
        this.obstacles = new ArrayList<>();
        Nexus chateau = new Nexus( 50,400);
        batiments.add(chateau);
        this.food=0;
        this.wood=0;
        this.stone=0;
        this.characters.add(new Villageois(300, 300));
        this.characters.add(new Guerrier( 350, 300));
        this.characters.add(new Archer(400,300));
        this.obstacles.add(new Obstacle(300, 350));
        this.obstacles.add(new Obstacle(350, 350));
        this.obstacles.add(new Obstacle(400,350));
    }
    public Map(ArrayList<Obstacle> o, ArrayList<Personnage> c, ArrayList<Batiment> b){
        this.batiments=b;
        this.obstacles=o;
        this.characters=c;
    }
}