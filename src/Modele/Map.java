package Modele;

import Modele.Obstacle;
import Modele.Personnage;

import java.util.ArrayList;

public class Map {
    private ArrayList<Obstacle> obstacles;
    private ArrayList<Personnage> characters;
    private ArrayList<Batiment> batiments;
    public static final int taille=600;
    private int food;
    private int stone;
    private int wood;


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

    //Food a 0 mais pourrait etre set a une autre valeur au début
    public Map(){
        this.batiments = new ArrayList<>();
        this.characters = new ArrayList<>();
        this.obstacles = new ArrayList<>();
        Nexus chateau = new Nexus(500, 50,400);
        batiments.add(chateau);
        this.food=0;
        this.wood=0;
        this.stone=0;
    }
}
