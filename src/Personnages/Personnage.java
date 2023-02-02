package Personnages;


public abstract class Personnage {
    private int health_points;
    private boolean isAlive = true;

    public Personnage(int health_points){
        this.health_points = health_points;
    }

    /**
     * get si en vie pour retirer perso du jeu
     * @return
     */
    public boolean getIsAlive(){
        return this.isAlive;
    }

    public int getHealth_points(){return this.health_points;}

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
}
