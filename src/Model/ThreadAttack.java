package Model;

import Model.Personnages.Ennemy;
import Model.Personnages.Personnage;

/**
 * ThreadAttack, permet d'attaquer en boucle un ennemi jusqu'à que mort s'en suive, ou que l'attaquant est lui-même mort.
 * Ce Thread se lance grâce au ThreadScanEnnemies.
 */
public class ThreadAttack extends Thread{
    private final Personnage perso;
    private final Personnage ennemi;
    private final int cooldown = 500;

    public ThreadAttack(Personnage perso, Personnage ennemi){
        this.perso = perso;
        this.ennemi = ennemi;
    }


    /**
     * Si perso est un monstre il attaque "l'ennemi" (donc guerrier/archer) jusqu'a ce que l'un des deux meurt
     * Si perso est un allié il attaque l'ennemi (monstre) jusqu'a ce que l'un des deux meurt
     * A la fin de chaque "algo" on fait "setAttacking" a false
     */
    @Override
    public void run() {
        while (this.ennemi.getIsAlive() && this.perso.getIsAlive()) {
            this.perso.attack(this.ennemi);
            try {
                sleep(cooldown);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.perso.setAttacking(false);
        System.out.println("qsdjklqjdhfalkzjdfhlakjzdhlakjzdhljk"+this.perso.isAttacking());
    }
}
