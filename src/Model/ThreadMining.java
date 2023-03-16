package Model;

import Model.Obstacles.Obstacle;
import Model.Personnages.Personnage;

import java.awt.*;
import java.util.ArrayList;

public class ThreadMining extends Thread {

    private Map m;
    private Personnage p;
    private int finalX;
    private int finalY;
    private Obstacle o;
    private final int delai = 20;

    public ThreadMining(Map m, Personnage p, int finalX, int finalY,Obstacle o) {
        this.m = m;
        this.p = p;
        this.finalX = finalX;
        this.finalY = finalY;
        this.o = o;
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
        m.obstacleMined(o);
    }
}

