package Model;

import Model.Personnages.Ennemy;
import Model.Personnages.Personnage;
import Model.Personnages.Villageois;

/**
 * Thread permettant de scanner les ennemis au alentours, selon la nature de l'ennemi et de l'attaquant, cela lance
 * le threadAttack.
 */
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
     * check si perso est un "monstre" il attaque les alliés (guerrier archers) et si le monstre est arrivé au nexus il attaque le nexus
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
                    if(map.scanFightRange(this.perso,gentil) && !this.perso.isAttacking() && !(gentil instanceof Villageois)){
                        new ThreadAttack(this.map,this.perso,gentil,false).start();
                    }
                }
                if(map.ennemyArrivedToNexus(this.perso) && !this.perso.isAttacking()){
                    new ThreadAttack(this.map,this.perso,null,true).start();
                }
            }
            else{
                for (Ennemy ennemy : map.getEnnemies()) {
                    if (map.scanFightRange(this.perso, ennemy) && !this.perso.isAttacking()) {
                        new ThreadAttack(this.map,this.perso, ennemy,false).start();
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
