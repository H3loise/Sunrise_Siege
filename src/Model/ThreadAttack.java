package Model;

import Model.Personnages.Personnage;

/**
 * ThreadAttack, permet d'attaquer en boucle un ennemi jusqu'à que mort s'en suive, ou que l'attaquant est lui-même mort.
 * Ce Thread se lance grâce au ThreadScanEnnemies.
 * Attribut attackNexus permettant de savoir si le personnage doit attaquer le nexus ou pas
 */
public class ThreadAttack extends Thread{
    private final Personnage perso;
    private final Personnage ennemi;
    private final Map map;
    private boolean attackNexus = false;
    private final int cooldown = 500;

    public ThreadAttack(Map map,Personnage perso, Personnage ennemi, boolean attackNexus){
        this.perso = perso;
        this.ennemi = ennemi;
        this.map = map;
        this.attackNexus = attackNexus;
    }


    /**
     * Si perso est un monstre il attaque "l'ennemi" (donc guerrier/archer) jusqu'a ce que l'un des deux meurt
     * Si attackNexus est true le perso attaque le nexus
     * Si perso est un allié il attaque l'ennemi (monstre) jusqu'a ce que l'un des deux meurt
     * A la fin de chaque "algo" on fait "setAttacking" a false
     */
    @Override
    public void run() {
        if(this.attackNexus){
            while(this.perso.getIsAlive() && !this.map.testLoose()){
                this.perso.attackNexus(map.getNexus());
                try {
                    sleep(cooldown);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        else {
            while (this.ennemi.getIsAlive() && this.perso.getIsAlive() && map.scanFightRange(perso, ennemi)) {
                this.perso.attack(this.ennemi);
                try {
                    sleep(cooldown);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        this.perso.setAttacking(false);
    }
}
