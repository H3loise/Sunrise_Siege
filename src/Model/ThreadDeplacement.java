package Model;

import Model.Personnages.Personnage;

import java.awt.*;
import java.util.ArrayList;

public class ThreadDeplacement extends Thread {

    Map m;
    Personnage p;
    int finalX;
    int finalY;
    private final int delai = 20;
    ArrayList<Point> points;

    public ThreadDeplacement(Map m, Personnage p, int finalX, int finalY,ArrayList<Point> points) {
        this.m = m;
        this.p = p;
        this.finalX = finalX;
        this.finalY = finalY;
        this.points = points;
    }

    @Override
    public void run() {
            System.out.println(p.toString());
            /**
             * Probleme de paraléllisme dans le lancement
             */
            for (Point point : points) {
                p.setPosition((int) point.getX(), (int) point.getY());
                try {
                    sleep(delai);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
    }
}

