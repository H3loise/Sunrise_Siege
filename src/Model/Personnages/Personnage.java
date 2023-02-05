package Model.Personnages;


public abstract class Personnage {
    private int x;
    private int y;
    private int health_points;
    private boolean isAlive = true;

    public Personnage(int health_points, int x, int y){
        this.health_points = health_points;
        this.x = x;
        this.y = y;
    }

    /**
     * perso qui se fait attaquer
     * @param damage
     */
    public void attackedPersonnage(int damage){
        if(this.health_points <= 0){
            this.isAlive = false;
        }
        else{
            this.health_points -= damage;
        }
    }

    /**
     * get si en vie pour retirer perso du jeu
     * @return
     */
    public boolean getIsAlive(){
        return this.isAlive;
    }

    public int getHealth_points(){return this.health_points;}

    public int getX(){return this.x;}

    public int getY(){return this.y;}

    public void setX(int new_x){this.x = new_x;}

    public void setY(int new_y){this.y = new_y;}

}
