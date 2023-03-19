package Model;

//import Model.Batiments.Batiment;
//import Model.Batiments.Nexus;
import Model.Batiments.Batiment;
import Model.Batiments.Caserne;
import Model.Batiments.Nexus;
import Model.Obstacles.Obstacle;
import Model.Personnages.*;
//import Model.Personnages.Archer;
//import Model.Personnages.Guerrier;
//import Model.Personnages.Personnage;
//import Model.Personnages.Villageois;

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

    private ArrayList<Ennemy> ennemies = new ArrayList<>();

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

    public Nexus getNexus() {
        return nexus;
    }

    //Pour le calcul des chemins:
    private int maxCol = taille;
    private int maxRow = taille;
    private Node[][] nodes = new Node[maxCol][maxRow];
    private Node startNode,goalNode,currentNode;
    private ArrayList<Node> openList = new ArrayList<>();
    private ArrayList<Node> checkedList = new ArrayList<>();
    boolean goalReached = false;
    private Caserne caserne;

    public Map(){
        this.batiments = new ArrayList<>();
        this.characters = new ArrayList<>();
        this.obstacles = new ArrayList<>();
        Nexus chateau = new Nexus( 60,400);
        chateau.setPv(100);
        this.nexus = chateau;
        batiments.add(chateau);
        this.food=20;
        this.wood=20;
        this.stone=20;

        //this.characters.add(new Villageois(300, 300));
        //this.characters.add(new Guerrier( 350, 300));
        //this.characters.add(new Archer(400,300));
        this.characters.add(new Archer(50,50));
        this.characters.add(new Ennemy(500,200));
        this.obstacles.add(new Obstacle(300, 350));
        this.obstacles.add(new Obstacle(350, 350));
        this.obstacles.add(new Obstacle(400,350));

        initializeNodes();
        rendreCasesImpossibleBats(nexus);
        for (Obstacle b:
                obstacles ) {
            rendreCaseImpossibleObstacles(b);
        }
        new ThreadAttackNexusAuto(this).start();
        this.caserne = new Caserne(500,600);
        rendreCasesImpossibleBats(caserne);
        batiments.add(caserne);
    }

    public Map(ArrayList<Obstacle> o, ArrayList<Personnage> c, ArrayList<Batiment> b){
        this.batiments=b;
        this.obstacles=o;
        this.characters =c;
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
        rendreCasePossibleObstacles(o);
        deplacementPersoMiner(v,o.getX(),o.getY(),o);
    }

    public void obstacleMined(Obstacle o){
        switch (o.getType()) {
            case Rock -> stone += o.getRessource();
            case Tree -> wood += o.getRessource();
            case Wheat -> food += o.getRessource();
        }
        obstacles.remove(o);
        System.out.println("Vous avez récupéré " + o.getRessource() + " " + o.getType());
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
    /*private void eraseDestroyedBuildings(){
        for(Batiment b : batiments){
            if(b.getPv() <= 0 ){
                batiments.remove(b);
            }
        }
    }*/

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
        int taille = b.getTaille() + Personnage.taille ;
        for(int i = x- Personnage.taille ;i<x + b.getTaille();i++) {
            if (i < Map.taille && i>=0) {
                for (int j = y - Personnage.taille; j < y + b.getTaille() ; j++) {
                    if (j < Map.taille && j>=0) {
                        nodes[i][j].setAsSolid();
                    }
                }
            }
        }
    }

    private void rendreCasePossibleBatiment(Batiment b){
        int x = b.getX();
        int y = b.getY();
        int taille = b.getTaille() + Personnage.taille ;
        for(int i = x- Personnage.taille ;i<x + b.getTaille();i++) {
            if (i < Map.taille && i>=0) {
                for (int j = y - Personnage.taille; j < y + b.getTaille() ; j++) {
                    if (j < Map.taille && j>=0) {
                        nodes[i][j].setAsFree();
                    }
                }
            }
        }
    }
    private void rendreCaseImpossibleObstacles(Obstacle b ){
        int x = b.getX();
        int y = b.getY();
        int taille = b.getTaille() + Personnage.taille ;
        for(int i = x- Personnage.taille ;i<x + b.getTaille();i++) {
            if (i < Map.taille && i>=0) {
                for (int j = y - Personnage.taille; j < y + b.getTaille() ; j++) {
                    if (j < Map.taille && j>=0) {
                        nodes[i][j].setAsSolid();
                    }
                }
            }
        }

    }

    private void rendreCasePossibleObstacles(Obstacle b ){
        int x = b.getX();
        int y = b.getY();
        int taille = b.getTaille()  + Personnage.taille ;
        for(int i = x- Personnage.taille ;i<x + b.getTaille();i++) {
            if (i < Map.taille && i>=0) {
                for (int j = y - Personnage.taille; j < y + b.getTaille() ; j++){
                    if (j < Map.taille && j>=0) {
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

    public void addObstacle(Obstacle o){
        obstacles.add(o);
    }

    public void deplacementPerso(Personnage p ,int x,int y){
        ArrayList<Point> points= cheminLePluscourt(p,x,y);
        resetNoeudsAprèsUtilisation();
        new ThreadDeplacement(this,p,x,y,points).start();
    }

    private void resetNoeudsAprèsUtilisation(){
        int col = 0;
        int row =0;
        while (col < maxCol && row < maxRow) {
            nodes[col][row].resetState();
            col++;
            if (col == maxCol) {
                col =0;
                row++;
            }
        }
    }
    public void deplacementPersoMiner(Personnage p ,int x,int y,Obstacle o ){
        new ThreadMining(this,p,x,y,o).start();
        System.out.println("coucou");
    }
    public  ArrayList<Point> cheminLePluscourt(Personnage p, int x, int y){
        if(nodes[x][y].isSolid()){
            System.out.println("solide");
            return new ArrayList<>();

        }
        if(p.getY() == y && p.getX() == x){
            System.out.println("Vous êtes déjà sur cette case");
            return new ArrayList<>();
        }
        ArrayList<Point>res = new ArrayList<>();
        ArrayList<Node> chemin = recherche(p,x,y);
        System.out.println(chemin);
        for (Node n : chemin){
            res.add(new Point(n.getCol(),n.getRow()));
        }

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

    private void afficheNodesDifferents(){
        for (Node[] n:
                nodes) {
            for (Node res:
                    n) {
                if (res.isStart() /*|| res.isChecked() || res.isOpen() || res.isGoal()*/){
                    System.out.println("probleme ici");
                }
            }
        }
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
        Node tempStart = startNode;
        ArrayList<Node> tempOpen = openList;

        ArrayList<Node>tempChecked = checkedList;
        startNode = nodes[p.getX()][p.getY()];
        nodes[p.getX()][p.getY()].setAsStart();
        currentNode = startNode;
        goalNode = nodes[x][y];
        nodes[x][y].setAsGoal();
        setCostOnNodes();
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
        goalReached =false;
        openList = tempOpen;
        checkedList = tempChecked;
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

    public void setFood(int food) {
        this.food += food;
    }

    public void setStone(int stone) {
        this.stone += stone;
    }

    public void setWood(int wood) {
        this.wood += wood;
    }

    private void resetPositionWarriors(){
        rendreCasePossibleBatiment(caserne);
        for (Personnage p:
                characters) {
            if(p instanceof Guerrier || p instanceof Archer){
                System.out.println("oui");
                deplacementPerso(p, caserne.getX(), caserne.getY());
            }

        }
    }

    public ArrayList<Ennemy> getEnnemies () {
        return ennemies;
    }

    private void killEnnemies(){
        this.ennemies.clear();
    }

    private void generateEnnemies(){
        //TODO
        this.ennemies.add(new Ennemy(700,10));
        this.ennemies.add(new Ennemy(600,10));
        this.ennemies.add(new Ennemy(500,10));
    }

    private void moveEnnemeies(){
        for (Ennemy e : this.ennemies){
            deplacementPerso(e,this.nexus.getX()-Personnage.taille-1,this.nexus.getY()-Personnage.taille-1);
        }
    }


    public void acheterVillageois() {
        if (wood >= Villageois.woodPrice && stone > Villageois.stonePrice && food >= Villageois.wheatPrice) {
            stone -= Villageois.stonePrice;
            wood -= Villageois.woodPrice;
            food -= Villageois.wheatPrice;
            int x = 10;
            int y = 10;
            boolean libre = false;
            Villageois p = new Villageois(10, 10);
            for (Node[] n :
                    nodes) {
                if (libre) {
                    break;
                }
                for (Node res :
                        n) {
                    if(!res.isSolid()){
                        libre = true;
                        x = res.getRow();
                        y = res.getCol();
                        break;
                    }
                }
            }
            p.setPosition(x,y);
            addCharacter(p);
        }
        else{
            System.out.println("Vous n'avez pas assez de ressources");
        }
    }

    public void acheterGuerrier(){
        if (wood >= Guerrier.woodPrice && stone > Guerrier.stonePrice && food >= Guerrier.wheatPrice) {
            stone -= Guerrier.stonePrice;
            wood -= Guerrier.woodPrice;
            food -= Guerrier.wheatPrice;
            Guerrier p = new Guerrier(caserne.getX(), caserne.getY());
            addCharacter(p);
            //petit message sur le panel please
        }
            else{
                System.out.println("Vous n'avez pas assez de ressources");
                //on pourra afficher la différence de ce qu'il manque
            }
    }
    public void acheterArcher(){
        if (wood >= Archer.woodPrice && stone > Archer.stonePrice && food >= Archer.wheatPrice) {
            stone -= Archer.stonePrice;
            wood -= Archer.woodPrice;
            food -= Archer.wheatPrice;
            Archer p = new Archer(caserne.getX(), caserne.getY());
            addCharacter(p);
            //petit message sur le panel please
        }
        else{
            System.out.println("Vous n'avez pas assez de ressources");
            //on pourra afficher la différence de ce qu'il manque
        }
    }

    public void healEveryone() {
        for (Personnage p : characters) {
            p.heal();
        }
    }

    public void upgradeGuerrier(Guerrier g){
        if(stone >= Guerrier.stonePrice * g.getLevel() && food >= Guerrier.wheatPrice * g.getLevel() & wood >= Guerrier.woodPrice * g.getLevel() ){
            stone -= Guerrier.stonePrice * g.getLevel();
            food -= Guerrier.wheatPrice * g.getLevel();
            wood -= Guerrier.woodPrice * g.getLevel();
            g.upgrade();
        }else{
            System.out.println("Vous n'avez pas assez de ressources");
        }
    }

    public void upgradeArcher(Guerrier g){
        if(stone >= Archer.stonePrice * g.getLevel() && food >= Archer.wheatPrice * g.getLevel() & wood >= Archer.woodPrice * g.getLevel() ){
            stone -= Archer.stonePrice * g.getLevel();
            food -= Archer.wheatPrice * g.getLevel();
            wood -= Archer.woodPrice * g.getLevel();
            g.upgrade();
        }else{
            System.out.println("Vous n'avez pas assez de ressources");
        }
    }



    /**
     * Procédure permettant l'update du modèle, on lance les fonctions créees pour cela.
     */
    public void update(){
        if(day){
            resetPositionWarriors();
            rendreCasesImpossibleBats(caserne);
            eraseDeadPeople();
            // eraseDestroyedBuildings();
            generateNewObstacles();
            upScore();
            healEveryone();
            //killEnnemies();
            generateEnnemies();
            moveEnnemeies();
        }
        else {
            rendreCasePossibleBatiment(caserne);

        }
    }
}
