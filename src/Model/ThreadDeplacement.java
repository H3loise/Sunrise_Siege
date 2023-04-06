package Model;

import Model.Personnages.Ennemy;
import Model.Personnages.Personnage;
import Model.Personnages.Villageois;

import java.awt.*;
import java.util.ArrayList;

/**
 * Thread permettant le déplacement des personnages avec animations, on lui transmet juste la liste des points
 * à parcourir.
 */
public class ThreadDeplacement extends Thread {

    Map m;
    Personnage p;
    private int delai = 10;
    ArrayList<Point> points;

    public ThreadDeplacement(Map m, Personnage p,ArrayList<Point> points) {
        this.m = m;
        this.p = p;
        this.points = points;
        if(this.p instanceof Ennemy){
            this.delai = 25;
        }
    }

    /**
     * Lancement du run, il parcourt les points avec un sleep. Il met l'attribut à true pour éviter qu'on ne puisse
     * redéplacer le personnage pendant son déplacement, de plus un personnage en train d'attaquer ne se deplace plus.
     */
    @Override
    public void run() {
        System.out.println(p.toString());
            for (Point point : points) {
                while(this.p.isAttacking()){
                    try {
                        sleep(10);
                        this.p.setMoving(false);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if(!m.getDay() && p instanceof Villageois){
                    p.setMoving(false);
                    interrupt();
                }
                this.p.setMoving(true);
                p.setPosition((int) point.getX(), (int) point.getY());
                try {
                    sleep(delai);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            p.setMoving(false);
    }
}