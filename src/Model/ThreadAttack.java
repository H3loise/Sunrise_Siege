package Model;

import Model.Personnages.Personnage;

public class ThreadAttack extends Thread{
    private final Personnage perso;
    private final Personnage ennemi;
    private final Map map;
    private final int cooldown = 500;

    public ThreadAttack(Map map, Personnage perso, Personnage ennemi){
        this.perso = perso;
        this.ennemi = ennemi;
        this.map = map;
    }


    /**
     * Si perso est un monstre il attaque "l'ennemi" (donc guerrier/archer) jusqu'a ce que l'un des deux meurt
     * Si perso est un allié il attaque l'ennemi (monstre) jusqu'a ce que l'un des deux meurt
     * A la fin de chaque "algo" on fait "setAttacking" a false
     */
    @Override
    public void run() {
        while (this.ennemi.getIsAlive() && this.perso.getIsAlive() && map.scanFightRange(perso,ennemi)) {
            this.perso.attack(this.ennemi);
            try {
                sleep(cooldown);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.perso.setAttacking(false);
    }
}
