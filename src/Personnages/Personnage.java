package Personnages;


public abstract class Personnage {
    private int health_points;
    private boolean isAlive = true;

    public Personnage(int health_points, String name){
        this.health_points = health_points;
    }

    /**
     * get si en vie pour retirer perso du jeu
     * @return
     */
    public boolean getIsAlive(){
        return this.isAlive;
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

    public void extractRessources(){}
}
