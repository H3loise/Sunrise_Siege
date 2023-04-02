package Model;

import Model.Personnages.Ennemy;
import Model.Personnages.Personnage;

public class ThreadAttack extends Thread{
    private final Personnage perso;
    private final Ennemy ennemi;
    private final int cooldown = 50000000;

    public ThreadAttack(Personnage perso, Ennemy ennemi){
        this.perso = perso;
        this.ennemi = ennemi;
    }

    @Override
    public void run() {
        while(this.ennemi.getIsAlive() && this.perso.getIsAlive()){
            this.perso.attack(this.ennemi);
            try {
                sleep(cooldown);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
