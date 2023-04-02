package Model;

import Model.Personnages.Ennemy;
import Model.Personnages.Personnage;

public class ThreadScanEnnemies extends Thread{
    private final Map map;
    private final Personnage perso;

    public ThreadScanEnnemies(Map map, Personnage perso){
        this.map = map;
        this.perso = perso;
    }

    @Override
    public void run() {
        while(perso.getIsAlive()) {
            for (Ennemy ennemy : map.getEnnemies()) {
                if (map.scanFightRange(this.perso, ennemy) && !this.perso.isMoving()) {
                    //map.attack(this.perso,ennemy);
                    new ThreadAttack(this.perso, ennemy).start();
                }
            }
            try {
                sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
