package Model;

//import Model.Batiments.Batiment;
//import Model.Batiments.Nexus;
import Model.Batiments.*;
import Model.Batiments.Nexus;
import Model.Obstacles.Obstacle;
import Model.Personnages.Archer;
import Model.Personnages.Personnage;
import Model.Personnages.Villageois;
//import Model.Personnages.Archer;
//import Model.Personnages.Guerrier;
//import Model.Personnages.Personnage;
//import Model.Personnages.Villageois;

import java.util.ArrayList;
import java.util.Random;

public class Map {
    private ArrayList<Obstacle> obstacles;
    private ArrayList<Personnage> characters;
    private ArrayList<Batiment> batiments;
    public static final int taille=1000;
    //a enlever
    private Nexus nexus;
    private int score = 0;
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

    //Food a 0 mais pourrait etre set a une autre valeur au dÃ©but
    public Map(){
        this.batiments = new ArrayList<>();
        this.characters = new ArrayList<>();
        this.obstacles = new ArrayList<>();
        Nexus chateau = new Nexus( 50,400);
        this.nexus = chateau;
        batiments.add(chateau);
        this.food=0;
        this.wood=0;
        this.stone=0;
        //this.characters.add(new Villageois(300, 300));
        //this.characters.add(new Guerrier( 350, 300));
        //this.characters.add(new Archer(400,300));
        this.characters.add(new Archer(10,10));
        this.obstacles.add(new Obstacle(300, 350));
        this.obstacles.add(new Obstacle(350, 350));
        this.obstacles.add(new Obstacle(400,350));
    }
    public Map(ArrayList<Obstacle> o, ArrayList<Personnage> c, ArrayList<Batiment> b){
        this.batiments=b;
        this.obstacles=o;
        this.characters=c;
    }

    /**
     * Procédure permettant de miner une ressource, le matériau est récupéré et le minerai est détruit.
     * @param v
     * @param o
     */
    public void mining(Villageois v,Obstacle o){
        if(voisin(v.getX(),v.getY(),o.getX(),o.getY())) {
            switch (o.getType()) {
                case Rock -> stone += o.getRessource();
                case Tree -> wood += o.getRessource();
                case Wheat -> food += o.getRessource();
            }
            obstacles.remove(o);
            System.out.println("Vous avez récupéré " + o.getRessource() + " " + o.getType());
        }
    }


    /**
     * Procédure permettant d'incrémenter le score, ici on a choisi 150, mais on changera
     */
    public void upScore(){
        this.score +=150;
    }

    /**
     * Fonction permettant de calculer si le point a et b sont voisins à près, on pourra la modifier jusqu'a n proche
     * pour les archers par exemple
     * @param x
     * @param y
     * @param x2
     * @param y2
     * @return boolean
     */
    private boolean voisin(int x, int y,int x2, int y2){
        return (x2>= x-1 || x2<= x+1) && (y2>=y-1 || y2<=y +1);
    }

    /**
     * Procédure qui se lance au moment de l'update, elle permet de vider l'ArrayList characters des personnages morts
     */
    private void eraseDeadPeople(){
        for(Personnage p : characters){
            if (!p.getIsAlive()){
                characters.remove(p);
            }
        }
    }
    /**
     * Procédure qui se lance au moment de l'update, elle permet de vider l'ArrayList batiments des batiments détruits
     */
    private void eraseDestroyedBuildings(){
        for(Batiment b : batiments){
            if(b.getPv() <= 0 ){
                batiments.remove(b);
            }
        }
    }

    /**
     * Fonction calculant si la partie est perdu, autrement dit si le nexus a été détruit.
     * @return boolean
     */
    public boolean testLoose(){
        return nexus.getPv() <= 0;
    }

    /**
     * On génère de nouveaux obstacles aléatoires, bien sûr leur nombre varie selon la nuit à laquelle nous sommes
     * puisque le jeu est sensé devenir de plus en plus dur
     */
    private void generateNewObstacles(){
        Random random = new Random();
        int n = random.nextInt((score%150) + 4);
        for (int i = 0; i < n; i++) {
            this.obstacles.add(new Obstacle(random.nextInt(0,taille),random.nextInt(0,taille)));
        }
    }

    /**
     * Procédure permettant d'améliorer le nexus, on vérifie que la personne a les matériaux necessaire, on
     * les déduis puis on l'upgrade, le score est alors augmenté de 400
     */

    public void upgradeNexus(){
        int n = nexus.getMinimumOfEach();
        if(wood >= n * nexus.getLevel() && food >= n * nexus.getLevel() && stone >= n * nexus.getLevel()){
            wood -= n * nexus.getLevel();
            food -= n * nexus.getLevel();
            stone -= n * nexus.getLevel();
            nexus.upgrade();
            score += 400;
            System.out.println("Bravo le nexus a été amélioré, voici ses stats actuelles : ");
            System.out.println(nexus.toString());
        }else{
            System.out.println("t'as pas assez clochard va, au travail !!!!!");
        }
    }

    /**
     * Procédure permettant l'update du modèle, on lance les fonctions créees pour cela.
     */
    public void update(){
        eraseDeadPeople();
        eraseDestroyedBuildings();
        generateNewObstacles();

    }
}
