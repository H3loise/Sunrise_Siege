package Model;

//import Model.Batiments.Batiment;
//import Model.Batiments.Nexus;
import Model.Batiments.Batiment;
import Model.Batiments.Nexus;
import Model.Obstacles.Obstacle;
import Model.Personnages.Archer;
import Model.Personnages.Personnage;
import Model.Personnages.Villageois;
//import Model.Personnages.Archer;
//import Model.Personnages.Guerrier;
//import Model.Personnages.Personnage;
//import Model.Personnages.Villageois;

import javax.swing.plaf.synth.SynthTextAreaUI;
import java.awt.*;
import java.util.*;

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

    //Pour le calcul des chemins:
    private int maxCol = taille;
    private int maxRow = taille;
    private Node[][] nodes = new Node[maxCol][maxRow];
    private Node startNode,goalNode,currentNode;
    private ArrayList<Node> openList = new ArrayList<>();
    private ArrayList<Node> checkedList = new ArrayList<>();
    boolean goalReached = false;


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

        initializeNodes();
        rendreCasesImpossibleBats(nexus);
        for (Obstacle b:
            obstacles ) {
            rendreCaseImpossibleObstacles(b);
        }
    }
    public Map(ArrayList<Obstacle> o, ArrayList<Personnage> c, ArrayList<Batiment> b){
        this.batiments=b;
        this.obstacles=o;
        this.characters=c;
    }
    private void initializeNodes() {
        int col = 0;
        int row =0;
        while (col < maxCol && row < maxRow) {
            nodes[col][row] = new Node(col, row);
            col++;
            if (col == maxCol) {
                col = 0;
                row++;
            }
        }
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
            rendreCasePossibleObstacles(o);
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
            Obstacle o = new Obstacle(random.nextInt(0,taille),random.nextInt(0,taille));
            this.obstacles.add(o);
            rendreCaseImpossibleObstacles(o);
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




    public void healingNexus() {
        int n = nexus.getMinimumOfEach();
        if (wood >= n * (nexus.getLevel()-1) && food >= n * (nexus.getLevel()-1) && stone >= n * (nexus.getLevel()-1)) {
             nexus.setPv(nexus.getPvMax());
            wood -= n * (nexus.getLevel()-1);
            food -= n * (nexus.getLevel()-1);
            stone -= n * (nexus.getLevel()-1);
            System.out.println(nexus.toString());

        }
        else{
            System.out.println("Pas assez d'argent pour réparer le Nexus");
        }
    }


    public void setCharacters(ArrayList<Personnage> characters) {
        this.characters = characters;
    }

    private void rendreCasesImpossibleBats(Batiment b){
        int x = b.getX();
        int y = b.getY();
        int taille = b.getTaille();
        for(int i = x;i<x+taille;i++) {
            if (i < Map.taille) {
                for (int j = y; j < y + taille; j++) {
                    if (j < Map.taille) {
                        nodes[i][j].setAsSolid();
                    }
                }
            }
        }
    }

    private void rendreCaseImpossibleObstacles(Obstacle b ){
        int x = b.getX();
        int y = b.getY();
        int taille = b.getTaille();
        for(int i = x;i<x+taille;i++) {
            if (i < Map.taille) {
                for (int j = y; j < y + taille; j++) {
                    if (j < Map.taille) {
                        nodes[i][j].setAsSolid();
                    }
                }
            }
        }
    }

    private void rendreCasePossibleObstacles(Obstacle b ){
        int x = b.getX();
        int y = b.getY();
        int taille = b.getTaille();
        for(int i = x;i<x+taille;i++) {
            if (i < Map.taille) {
                for (int j = y; j < y + taille; j++) {
                    if (j < Map.taille) {
                        nodes[i][j].setAsFree();
                    }
                }
            }
        }
    }

    private void testCaseImpossible(){
        for(int i = 0;i<taille-200;i++){
            nodes[i][400].setAsSolid();
        }
    }

    public void addCharacter(Personnage p ){
        characters.add(p);
    }

    public void deplacementPerso(Personnage p ,int x,int y){
        new ThreadDeplacement(this,p,x,y).start();
        System.out.println("coucou");

    }
    public ArrayList<Point> cheminLePluscourt(Personnage p, int x, int y){
        if(p.getY() == y && p.getX() == x){
            System.out.println("Vous êtes déjà sur cette case");
            return null;
        }
        ArrayList<Point>res = new ArrayList<>();
        ArrayList<Node> chemin = recherche(p,x,y);
        for (Node n : chemin){
            res.add(new Point(n.getCol(),n.getRow()));
        }
        currentNode = null;
        goalNode = null;
        startNode = null;
        checkedList = new ArrayList<>();
        openList = new ArrayList<>();
        return res;
    }

    private void getCost(Node node){

        //Gcost = distance depuis le point de depart
        int xDistance = Math.abs(node.getCol() - startNode.getCol());
        int yDistance = Math.abs(node.getRow()  -startNode.getRow());
        node.setgCost(xDistance + yDistance) ;

        xDistance = Math.abs(node.getCol() - goalNode.getCol());
        yDistance = Math.abs(node.getRow()  -goalNode.getRow());
        node.sethCost( xDistance + yDistance);
        node.setfCost(node.getgCost() + node.gethCost());
    }

    private void setCostOnNodes(){
        for (Node[] n:
                nodes) {
            for (Node res:
                    n) {
                getCost(res);
            }
        }
    }
    public ArrayList<Node> recherche(Personnage p,int x,int y){
        startNode = nodes[p.getX()][p.getY()];
        nodes[p.getX()][p.getY()].setAsStart();
        currentNode = startNode;
        goalNode = nodes[x][y];
        nodes[x][y].setAsGoal();

        ArrayList<Node> res = new ArrayList<>();
        while(!goalReached){
            int col = currentNode.getCol();
            int row = currentNode.getRow();
            currentNode.setChecked();
            checkedList.add(currentNode);
            openList.remove(currentNode);

            //Ouvre les voisins
            if(col-1>=0) {
                openNode(nodes[col - 1][row]);
            }
            if(row+1<maxRow) {
                openNode(nodes[col][row + 1]);
            }
            if(row-1>=0) {
                openNode(nodes[col][row - 1]);
            }
            if(col+1<maxCol) {
                openNode(nodes[col + 1][row]);
            }
            int bestNodeIndex = 0;
            int bestNodefCost = 1100000000;
            for(int i =0;i<openList.size(); i++){
                //On vérifie si on a pas un meilleur chemin
                if(openList.get(i).getfCost() < bestNodefCost){
                    bestNodeIndex = i;
                    bestNodefCost = openList.get(i).getfCost();
                }
                else if(openList.get(i).getfCost() == bestNodefCost)
                    if(openList.get(i).getgCost() < openList.get(bestNodeIndex).getgCost()){
                        bestNodeIndex = i;
                    }
            }
            currentNode = openList.get(bestNodeIndex);
            if(currentNode == goalNode){
                goalReached = true;
               res = trackThePath();
            }
        }
        return res;
    }

    private void openNode(Node node){
        if(!node.isOpen() && !node.isChecked() && !node.isSolid()){
            node.setAsOpen();
            node.parent = currentNode;
            openList.add(node);

        }
    }

    public Node[][] getNodes() {
        return nodes;
    }

    private ArrayList<Node> trackThePath(){
        ArrayList<Node> res =new ArrayList<>();
        Node current = goalNode;
        while(current != startNode){
            current = current.parent;
            if(current != startNode){
                res.add(current);
            }
        }
        Collections.reverse(res);
        return res;
    }

    /**
     * Procédure permettant l'update du modèle, on lance les fonctions créees pour cela.
     */
    public void update(){
        eraseDeadPeople();
        eraseDestroyedBuildings();
        generateNewObstacles();
        upScore();

    }
}
