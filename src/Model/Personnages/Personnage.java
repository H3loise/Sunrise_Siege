package Model.Personnages;

public abstract class Personnage {
    private int x;
    private int y;
    private final int rayon;
    private boolean isAlive = true;
    protected int health_points;
    protected int hpMax = health_points;
    private int attack_points;
    protected int level;
    public static final int taille = 60;

    private boolean moving = false;
    public Personnage(int health_points, int x, int y, int rayon, int attack_points){
        this.health_points = health_points;
        this.x = x;
        this.y = y;
        this.rayon = rayon;
        this.attack_points = attack_points;
    }

    public int getAttack_points(){
        return this.attack_points;
    }


    /**
     * perso qui se fait attaquer
     * @param damage
     */
    public void receivesDamage(int damage){
        if(this.health_points <= 0){
            this.isAlive = false;
        }
        else{
            this.health_points -= damage;
        }
    }

    public void attack(Personnage perso){
        perso.receivesDamage(this.getAttack_points());
    }

    /**
     * get si en vie pour retirer perso du jeu
     * @return
     */
    public boolean getIsAlive(){
        return this.isAlive;
    }

    public int getHealth_points(){return this.health_points;}

    public int getHpMax(){return this.hpMax;}

    public int getX(){return this.x;}

    public int getY(){return this.y;}

    public int getRayon(){return this.rayon;}

    public void setPosition(int x, int y) {
        setX(x);
        setY(y);
    }

    public synchronized  void  setX(int new_x){
        this.x = new_x;
    }

    public synchronized void setY(int new_y){
        this.y = new_y;
    }

    public void heal(){
        health_points = hpMax;
    }

    public int getLevel(){
        return this.level;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public boolean isMoving(){
        return this.moving;
    }
}
