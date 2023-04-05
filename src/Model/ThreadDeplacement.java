package Model;

import Model.Personnages.Ennemy;
import Model.Personnages.Personnage;

import java.awt.*;
import java.util.ArrayList;

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
            this.delai = 10;
        }
    }

    @Override
    public void run() {
        System.out.println(p.toString());
        /**
         * Probleme de paraléllisme dans le lancement
         */
            for (Point point : points) {
                while(this.p.isAttacking()){
                    try {
                        sleep(10);
                        this.p.setMoving(false);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
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