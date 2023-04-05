package Model.Batiments;
/**
 * Nexus est le batiment principal, le chateau, le coeur de notre base. La destruction du Nexus entraînera le défaite du
 * joueur immédiatement.Un nexus a de l'attaque et peut donc se défendre.
 */

public class Nexus extends Batiment {
    //Canon qui attaque
    private int attack;
    private final int minimumOfEach = 5;
    private final double range = 300;

    /**
     * Création d'un Nexus, appel au super constructeur
     * @param x
     * @param y
     */
    public Nexus(int x, int y) {
        super(x, y);
        setPv(getPvMax());
        System.out.println(pv);
        this.attack = 50;
    }

    /**
     * Getter de l'attribut attack
     * @return
     */
    public int getAttack() {
        return attack;
    }

    public double getRange() {
        return range;
    }

    /**
     * Modifie l'attaque, utile pour les tests.
     * @param attack
     */
    public void setAttack(int attack) {
        this.attack = attack;
    }

    /**
     * permet d'améliorer le Nexus, pour l'instant on met 50 en attaque et 100 en pv pour chaque niveau obtenu.
     * l'amélioration du nexus se fera pas matériaux, que l'on gèrera dans le modèle.
     *
     */
    public void upgrade(){
        setLevel(getLevel()+1);
        this.attack+=50;
        setPvMax(pvMax+100);
        setPv(pvMax);
    }



    public int getMinimumOfEach() {
        return minimumOfEach;
    }

    /**
     * Vue.Affichage des informations sur le Nexus, nous sera utile pour l'affichage sur swing aussi
     * @return String
     */
    @Override
    public String toString(){
        return "Le nexus a " + attack +
                " d'attaque, " + getPv() +
                " points de vie, niveau :" + getLevel();
    }


    /**---------------------------------------------------------------------------------------
     * Vestiges :
     -----------------------------------------------------------------------------------------*/




    /**
     * fonction développeur, permet de cheat, sera utile pour les tests
     * @param level
     */
    public void cheat(int level){
        setPv(level * 100);
        setAttack(50*level);
        setLevel(level);
    }

}
