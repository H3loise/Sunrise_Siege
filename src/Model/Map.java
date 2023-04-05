package Model;

import Model.Batiments.Batiment;
import Model.Batiments.Caserne;
import Model.Batiments.Nexus;
import Model.Obstacles.Obstacle;
import Model.Personnages.*;

import java.awt.*;
import java.util.*;

public class Map {
    private ArrayList<Obstacle> obstacles;
    private ArrayList<Personnage> characters;
    private ArrayList<Batiment> batiments;
    public static final int taille=1000;
    //a enlever
    private Nexus nexus;
    private Personnage actionner;

    private int score = 0;
    public static final int windowWidth = 1480;
    public static final int windowHeight = 920;
    //private int food;

    private int food;
    private int stone;
    private int wood;
    private boolean day;


    private int xActionner;
    private int yActionner;

    public void setxActionner(int x){
        this.xActionner=x;
    }

    public int getxActionner(){
        return this.xActionner;
    }

    public void setyActionner(int y){
        this.yActionner=y;
    }

    public int getyActionner(){
        return this.yActionner;
    }

    public void setActionner(Personnage actionner) {
        this.actionner = actionner;
    }

    public int getActionnerHealth_Points(){
        return this.actionner.getHealth_points();
    }
    public int getActionnerHpMax(){
        return this.actionner.getHpMax();
    }
    public int getActionnerAttack(){
        return this.actionner.getAttack_points();
    }

    private final ArrayList<Ennemy> ennemies = new ArrayList<>();

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

    private int delaiJourNuit = 50000;

    private long startTime = 0;
    public Map(){
        this.batiments = new ArrayList<>();
        this.characters = new ArrayList<>();
        this.obstacles = new ArrayList<>();
        Nexus chateau = new Nexus( 50,400);
        this.nexus = chateau;
        batiments.add(chateau);
        this.food=50;
        this.wood=50;
        this.stone=50;
        //this.obstacles.add(new Obstacle(700, 500));
       // this.obstacles.add(new Obstacle(700,500));

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
        new ThreadAttackNexusAuto(this);
    }
    public Map(ArrayList<Obstacle> o, ArrayList<Personnage> c, ArrayList<Batiment> b){
        this.batiments=b;
        this.obstacles=o;
        this.characters =c;
    }

    /**
     * Fonction permettant d'initialiser tout les noeuds, on leur transmet leur position.
     */
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
     * La place occupée par le matériaux dans les noeuds est alors libérée
     * @param v
     * @param o
     */
    public void mining(Villageois v,Obstacle o){
        rendreCasePossibleObstacles(o);
        deplacementPersoMiner(v,o);
    }

    /**
     * Getter du delaiJourNuit
     * @return delaiJourNuit
     */

    public int getDelaiJourNuit(){
        return this.delaiJourNuit;
    }

    /**
     * Getter de startTime qui défini depuis quand le cycle a commencé
     * @return startTime
     */
    public long getStartTime(){
        return startTime;
    }
    /**
     * Setter de startTime qui défini depuis quand le cycle a commencé
     */
    public void setStartTime(long millis){
        startTime = millis;
    }

    /**
     * Donne les ressources liées à l'obstacle passé en param, l'obstacle est alors remove de l'ArrayList obstacles
     * @param o
     */
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
    public void eraseDeadPeople(){
        characters.removeIf(p -> !p.getIsAlive());
    }

    public void eraseMonsters(){
        this.ennemies.removeIf(ennemy -> !ennemy.getIsAlive());
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
            int x = random.nextInt(0,taille);
            int y = random.nextInt(0,taille);
            while(isOneSolid(x,y)){
                 x = random.nextInt(0,taille);
                y = random.nextInt(0,taille);
            }
            Obstacle o = new Obstacle(x,y);
            this.obstacles.add(o);
            rendreCaseImpossibleObstacles(o);
        }
    }

    private boolean isOneSolid(int x,int y){
        for(int i = x;i< x+ nexus.getTaille() + 5;i++){
            if(i<1000) {
                for (int j = y; j < y + nexus.getTaille() +5; j++) {
                    if (j < 1000) {
                        if (nodes[i][j].isSolid()) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
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
     * Procédure permettant de remettre les pv du Nexus au pvMax, cela déduit des ressources liés au cout de la reparation
     * Le cout pour le réparer est enfait le prix que le Nexus a couté pour être amélioré à son niveau actuel
     */
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

    /**
     * Permettant de mettre directement des personnages, ceci est une fonction pour les dev
     * @param characters
     */
    public void setCharacters(ArrayList<Personnage> characters) {
        this.characters = characters;
    }


    /**
     * Je prends en compte la taille du batiment ainsi que la hitbox du joueur, et je rends les cases corresepondantes
     * solides afin que l'algorithme empêche mon joueur de passer par là
     * @param b
     */
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

    /**
     * Après destruction d'un batiment, il faut libérer la place.
     * Cette fonction rends les cases qui étaient solides libres à nouveau, permettant au joueur de les traverser.
     * @param b
     */
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

    /**
     * Les obstacles sont intraversables, à part au moment d'être minés, ainsi je les rends "opaques" l'autre partie
     * du temps
     * @param b
     */
    private void rendreCaseImpossibleObstacles(Obstacle b ){
        int x = b.getX();
        int y = b.getY();
        int taille = b.getTaille() + Personnage.taille ;
        for(int i = x- Personnage.taille ;i <x + b.getTaille();i++) {
            if (i < Map.taille && i>=0) {
                for (int j = y - Personnage.taille; j < y + b.getTaille() ; j++) {
                    if (j < Map.taille && j>=0) {
                        nodes[i][j].setAsSolid();
                    }
                }
            }
        }

    }

    /**
     * Je libère la place que prenais l'obstacles dans les noeuds, ainsi les cases redeviennent traversables
     * @param b
     */
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

    /**
     * Fun test dev
     */
    private void testCaseImpossible(){
        for(int i = 0;i<taille-200;i++){
            nodes[i][400].setAsSolid();
        }
    }


    /** ------------------------------------------------------------------
     *                                              ADD
     *  ------------------------------------------------------------------
     */

    /**
     * Pour ajouter un personnage depuis l'exterieur, fonction utilisée notamment dans le main pour des test
     * @param p
     */
    public void addCharacter(Personnage p ){
        characters.add(p);
        //new ThreadScanEnnemies(this,p).start();
    }

    public void addEnnemy(Ennemy e){
        this.ennemies.add(e);
        //new ThreadScanEnnemies(this,e).start();
    }

    /**
     * same
     * @param o
     */
    public void addObstacle(Obstacle o){
        obstacles.add(o);
    }

    /**
     * Fonction  de deplacement du personnage, on prends un personnage, la destination souhaitée, on calcule le 
     * cheminLePluscourt puis on lance le thread de déplacement, si chemin vide ou non trouvé le thread ne fait rien.
     * De plus on remet à l'état initial les noeuds modifiés pour la recherche de ce parcours
     * ----------------------------
     * Note Yanis :
     * @param p
     * @param x
     * @param y
     */
    /*public void deplacementPerso(Personnage p ,int x,int y){
        if(!p.isMoving()) {
            p.setMoving(true);
            ArrayList<Point> points = cheminLePluscourt(p, x, y);
            resetNoeudsAprèsUtilisation();
            new ThreadDeplacement(this, p, points).start();
        }
    }*/
    public void deplacementPerso(Personnage p ,int x,int y){
        if(!p.isMoving()) {
            ArrayList<Point> points = cheminLePluscourt(p, x, y);
            ThreadDeplacement thread_dep = new ThreadDeplacement(this, p, points);
            p.setMoving(true);
            p.setThread_deplacement(thread_dep);
            resetNoeudsAprèsUtilisation();
            thread_dep.start();
        }
    }

    /**
     * Après utilisation, les champs Checked,Open,Start,Goal doivent etre remis à 0 et ce pour chaque neouds, on parcourt
     * donc tout les noeuds et on reset chacun d'entre eux
     */
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

    /**
     * Pour miner un Obstacle il nous faut un obstacle,
     * @param p
     * @param o
     */
    public void deplacementPersoMiner(Personnage p ,Obstacle o ){
        if(!p.isMoving()) {
            p.setMoving(true);
            rendreCasePossibleObstacles(o);
            ArrayList<Point> points = cheminLePluscourt(p, o.getX(), o.getY());
            resetNoeudsAprèsUtilisation();
            new ThreadMining(this, p, o, points).start();
            System.out.println("coucou");
        }
    }

    /**
     * Lance la fonction recherche, qui retourne le path sous forme d'ArrayList de noeud et le convertit sous forme
     * d'arrayList de Point, plus facile à manipuler par la suite
     * @param p
     * @param x
     * @param y
     * @return
     */
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

    /**
     * Mets le fcost,hcost et gcost au noeud selon la distance par rapport au départ et à la fin
     * @param node
     */
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


    /**
     * Permet d'attribuer à chaque Noeud son cout, on parcourt donc tout le double tableau nodes et on lance
     * getCost pour chacun Noeud
     */
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


    /**
     * Méthode permettant d'ouvrir un Noeud, cela dit que le noeud a été exploré et est retenu dans la liste des noeuds sur le chemin, de plus on initialise son parent,
     * de sort à remonter le chemin une fois fini avec Model.Map#trackThePath();
     * @param node
     */
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

    /**
     * On récupère le path jusqu'au GoalNode, on doit retourner la liste car on par du GoalNode pour aller jusqu'au StartNode
     * @return
     */
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

    /**
     *  On fait revenir chacun des guerriers/archer à la caserne, on rendre la caserne tangible le temps de calculer le
     *  chemin, puis on déplace chacun des guerrier/archer
     */
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

    /**
     * Prend tous les ennemis de la partie et les déplace vers le nexus
     */
    private void moveEnnemiesToNexus(){
        for (Ennemy ennemi : this.ennemies){
            deplacementPerso(ennemi,this.nexus.getX(),this.nexus.getY());
        }
    }


    /**
     * Procedure pour acheter des villageois, les villageois ont un coût, on  déduit alors les ressources corresepondantes
     * au joueur et on place le villageois sur la première case libre, si le joueur n'a pas assez de ressource, rien n'est
     * effectué
     */
    public void acheterVillageois() {
        if (wood >= Villageois.woodPrice && stone > Villageois.stonePrice && food >= Villageois.wheatPrice) {
            stone -= Villageois.stonePrice;
            wood -= Villageois.woodPrice;
            food -= Villageois.wheatPrice;
            int x = 10;
            int y = 10;
            boolean libre = false;
            Villageois p = new Villageois(10, 10,this);
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

    /**
     * On peut acheter des guerriers, ils ont un coût, une fois acheté le guerrier spawn sur la caserne.
     */
    public void acheterGuerrier(){
        if (wood >= Guerrier.woodPrice && stone > Guerrier.stonePrice && food >= Guerrier.wheatPrice) {
            stone -= Guerrier.stonePrice;
            wood -= Guerrier.woodPrice;
            food -= Guerrier.wheatPrice;
            Guerrier p = new Guerrier(caserne.getX(), caserne.getY(),this);
            for (int i = 1; i < caserne.getLevel(); i++) {
                p.upgrade();
            }
            addCharacter(p);
            //petit message sur le panel please
        }
            else{
                System.out.println("Vous n'avez pas assez de ressources");
                //on pourra afficher la différence de ce qu'il manque
            }
    }

    /**
     * On peut acheter des archers, ils ont un coût, une fois acheté l'archer spawn sur la caserne.
     */
    public void acheterArcher(){
        if (wood >= Archer.woodPrice && stone > Archer.stonePrice && food >= Archer.wheatPrice) {
            stone -= Archer.stonePrice;
            wood -= Archer.woodPrice;
            food -= Archer.wheatPrice;
            Archer p = new Archer(caserne.getX(), caserne.getY(),this);
            addCharacter(p);
            //petit message sur le panel please
        }
        else{
            System.out.println("Vous n'avez pas assez de ressources");
            //on pourra afficher la différence de ce qu'il manque
        }
    }

    /**
     * Procédure pour soigner tout les guerriers au levé du jour, on parcourt l'arraylist de personnage et on
     * lance proc heal() sur chaque
     */
    public void healEveryone(){
        for(Personnage p :characters){
            p.heal();
        }
    }

    public ArrayList<Ennemy> getEnnemies () {
        return ennemies;
    }

    /**
     * Proc permet d'upgrade un guerrier, on va la modifier pour upgrade le niveau de création des guerriers.
     * Function deprecated, unused
     * @param g
     */
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

    /**
     * idem
     *
     * @param g
     */
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


    public Personnage getActionner(){
        return this.actionner;
    }

    /** ------------------------------------------------------------------
     *                                              ENNEMIES
     *  ------------------------------------------------------------------
     */

    /**
     * Génère des ennemis avec un minimum de 4 ennemis
     * les ennemis sont générés obligatoirement soit dans la partie haute soit dans la partie droite pour des soucis d'équilibrage
     * donc x_or_y représente une sorte de "pile ou face" qui determinera si l'ennemi apparaitera en haut ou a droite
     * ensuite on donne une valeur aléatoire a la coordonée x ou y du monstre dépendemment de la ou il apparait
     */
    private void generateEnnemies(){
        Random random = new Random();
        int ennemy_number = random.nextInt((score%150+1))+4;
        int x_or_y = random.nextInt(2);
        for (int i = 0; i < ennemy_number; i++) {
            if (x_or_y == 0) {
                addEnnemy(new Ennemy(random.nextInt(taille), 0,this));
            } else {
                addEnnemy(new Ennemy(900, random.nextInt(taille),this));
            }
        }
    }

    /**
     * Initialisation  du déplacement de tout les ennemis, on calcule le point le plus proche d'eux pour frapper le nexus
     * grâce à closestNexusPoint(Ennmy) et on lance le déplacement grâce au threadDéplacement et A*;
     */
    private void moveEnnemies(){
        for(Ennemy ennemi : this.ennemies){
            deplacementPerso(ennemi,closestNexusPoint(ennemi).x,closestNexusPoint(ennemi).y);
        }
    }

    /**
     * On calcule les points libre par rapport au CENTRE du nexus ( et non en haut à gauche), puis on transmet les liste
     * à la méthode searchMinDistArray(ArrayList<Point>,Ennemy) pour prendre le point le plus proche de l'ennemi.
     * On prend une distance maximale de 100, totalement arbitraire
     * @param ennemi
     * @return
     */
    private Point closestNexusPoint(Ennemy ennemi){
        double max = 100;
        Point middleNexus = new Point(nexus.getX() + nexus.getTaille()/2, nexus.getY()+nexus.getTaille()/2);
        ArrayList<Point> res_array = new ArrayList<>();
        for (Node[] n : nodes) {
            for (Node res : n) {
                if(!res.isSolid()){
                    if(Math.hypot((middleNexus.getX()-res.getRow()),(middleNexus.getY()- res.getCol())) < max){
                        res_array.add(new Point(res.getRow(),res.getCol()));
                    }
                }
            }
        }

        return searchMinDistArray(res_array, ennemi);
    }

    /**
     * Cherche le point à distance minimal de l'ennemi dans l'ArrayList, fonction classique de recherche de minimum,
     * on instancie min à 10000 de façon arbitraire.
     * @param points
     * @param ennemi
     * @return
     */
    private Point searchMinDistArray(ArrayList<Point> points, Ennemy ennemi){
        Point res = new Point();
        double min = 10000;
        for(Point p : points){
            if(min > Math.hypot((ennemi.getX()-p.x),(ennemi.getY()- p.y))){
                res = p;
                min = Math.hypot((ennemi.getX()-p.x),(ennemi.getY()- p.y));
            }
        }
        return res;
    }

    /** ------------------------------------------------------------------
     *                                              BAGARRE
     *  ------------------------------------------------------------------
     */

    /*public boolean scanFightRange(Personnage perso, Personnage ennemi){
        double diam = Math.sqrt((perso.getX() - ennemi.getX()) * (perso.getX() - ennemi.getX()) + (perso.getY() - ennemi.getY()) * (perso.getY() - ennemi.getY()));
        int r1 = perso.getRayon();
        int r2 = ennemi.getRayon();
        if(diam <= r1-r2 || diam <= r2-r1 || diam < r1+r2 || diam == r1+r2){
            return true;
        }
        return false;
    }*/

    /**
     * Lorsque la zone du perso touche le centre de "ennemi" la fonction renvoie true
     */
    public boolean scanFightRange(Personnage perso, Personnage ennemi) {
        double distance = Math.sqrt((perso.getX() - ennemi.getX()) * (perso.getX() - ennemi.getX()) + (perso.getY() - ennemi.getY()) * (perso.getY() - ennemi.getY()));
        double sumRadius = perso.getRayon() + ennemi.getRayon();
        if (distance < perso.getRayon() || distance <= sumRadius - ennemi.getRayon()) {
            return true;
        }
        return false;
    }


    /** ------------------------------------------------------------------
     *                                              UPGRADE
     *  ------------------------------------------------------------------
     */

    /**
     * Procédure permettant l'update du modèle, on lance les fonctions créees pour cela.
     */
    public void update(){
        if(day){
            resetPositionWarriors();
            rendreCasesImpossibleBats(caserne);
            eraseDeadPeople();
            //eraseDestroyedBuildings();
            generateNewObstacles();
            upScore();
            healEveryone();
        }
        else {
            rendreCasePossibleBatiment(caserne);
            //generateEnnemies();
            moveEnnemies();
        }
    }

    /**
     * On upgrade la caserne, faisant en sorte d'upgrade tout les Archer et Guerrier ayant été créee jusque là,
     * de plus cela nous coûte des ressources, donc nous soustrayons les ressources si cela est possible avec un if
     * faisant le calcul. Cette méthode se raprooche de Modele.Map.upgradeNexus();
     */
    public void upgradeCaserne() {
        if(caserne.getLevel()< 3 ) {
            int n = caserne.getMinimumOfEach();
            if (wood >= n * caserne.getLevel() && food >= n * caserne.getLevel() && stone >= n * caserne.getLevel()) {
                wood -= n * caserne.getLevel();
                food -= n * caserne.getLevel();
                stone -= n * caserne.getLevel();
                caserne.upgrade();
                for (Personnage p : characters) {
                    if (p instanceof Guerrier) {
                        Guerrier g = (Guerrier) p;
                        for (int i = 1; i < caserne.getLevel(); i++) {
                            if(g.getLevel()<caserne.getLevel()) {
                                g.upgrade();

                            }
                        }
                    } else if (p instanceof Archer) {
                        Archer a = (Archer) p;
                        for (int i = 1; i < caserne.getLevel(); i++) {
                            if(a.getLevel()<caserne.getLevel()) {
                                a.upgrade();
                            }
                        }
                    }
                }

            }
        }
    }

    public Caserne getCaserne() {
        return this.caserne;
    }
}
