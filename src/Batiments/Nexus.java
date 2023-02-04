
package Batiments;

/**
 * Nexus est le batiment principal, le chateau, le coeur de notre base. La destruction du Nexus entraînera le défaite du
 * joueur immédiatement.Un nexus a de l'attaque et peut donc se défendre.
 */

public class Nexus extends Batiment {
    //Canon qui attaque
    private int attack;
    private final int pvBase = 100;
    private final int attackBase = 50;
    private final int upAttackPerLevel = 50;
    private final int upHpPerLevel = 100;

    /**
     * Création d'une Nexus, appel au super constructeur
     * @param x
     * @param y
     */
    public Nexus(int x, int y) {
        super( x, y);
        this.setPv(pvBase);
        this.attack = attackBase;
    }

    /**
     * Getter de l'attribut attack
     * @return
     */
    public int getAttack() {
        return attack;
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
        setPv(getLevel()+1);
        this.attack+=upAttackPerLevel;
        setPv(getPv()+upHpPerLevel);
    }

    /**
     * fonction développeur, permet de cheat, sera utile pour les tests
     * @param level
     */
    public void cheat(int level){
        setPv(level * pvBase);
        setAttack(attackBase*level);
        setLevel(level);
    }


    /**
     * Affichage des informations sur le Nexus, nous sera utile pour l'affichage sur swing aussi
     * @return String
     */
    @Override
    public String toString(){
        return "Le nexus a " + attack +
                " d'attaque, " + getPv() +
                " points de vie, niveau :" + getLevel();
    }
}
