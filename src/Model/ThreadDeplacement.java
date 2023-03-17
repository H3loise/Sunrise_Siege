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

    public ThreadDeplacement(Map m, Personnage p, int finalX, int finalY) {
        this.m = m;
        this.p = p;
        this.finalX = finalX;
        this.finalY = finalY;
    }

    @Override
    public void run() {
        try {
            sleep(300);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        ArrayList<Point> chemin = m.cheminLePluscourt(p, finalX, finalY);
        for (Point point :
                chemin) {
            p.setPosition( (int)point.getX(), (int) point.getY());


            try {
                sleep(delai);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

