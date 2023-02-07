package Model;
import Model.Batiments.*;

import Model.Obstacles.*;
import Model.Personnages.*;

import java.util.ArrayList;

public class Map {
    private ArrayList<Obstacle> obstacles;
    private ArrayList<Personnage> characters;
    private ArrayList<Batiment> batiments;
    public static final int taille=1000;
    private int food;
    private int stone;
    private int wood;
    private boolean day = true;


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
        this.batiments = new ArrayList<Batiment>();
        this.characters = new ArrayList<>();
        this.obstacles = new ArrayList<>();
        Nexus chateau = new Nexus(50, taille-400);
        chateau.setLevel(1);
        batiments.add(chateau);
        this.food=0;
        this.wood=0;
        this.stone=0;
        this.characters.add(new Villageois(200, 200));
        this.characters.add(new Guerrier( 300, 200));
        this.characters.add(new Archer(400,200));
        this.obstacles.add(new Obstacle(200, 350));
        this.obstacles.add(new Obstacle(300, 350));
        this.obstacles.add(new Obstacle(400,350));
    }
}