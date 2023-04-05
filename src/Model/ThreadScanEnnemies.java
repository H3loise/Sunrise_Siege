package Model;

import Model.Personnages.Ennemy;
import Model.Personnages.Personnage;

public class ThreadScanEnnemies extends Thread{
    private final Map map;
    private final Personnage perso;
    private final int cooldown = 50;

    public ThreadScanEnnemies(Map map, Personnage perso){
        this.map = map;
        this.perso = perso;
    }


    /**
     * sleep au tout début pour donner le temps aux persos d'être bien instanciés et bien rentré dans les arrays
     * boucle while qui tourne tant que le perso est en vie
     * check si perso est un "monstre" il attaque les alliés (guerrier archers)
     * si perso "allié" (guerrier archer) il attaque les monstres
     */

    @Override
    public void run() {
        try {
            sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while(perso.getIsAlive()) {
            if(this.perso instanceof Ennemy){
                for(Personnage gentil : map.getPersonnages()){
                    if(map.scanFightRange(this.perso,gentil) && !this.perso.isAttacking()){
                        System.out.println("lezgo");
                        new ThreadAttack(this.perso,gentil).start();
                    }
                }
            }
            else{
                for (Ennemy ennemy : map.getEnnemies()) {
                    if (map.scanFightRange(this.perso, ennemy) && !this.perso.isAttacking()) {
                        //System.out.println("lezgo");
                        new ThreadAttack(this.perso, ennemy).start();
                    }
                }
            }
            try {
                sleep(this.cooldown);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
