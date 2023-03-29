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
    private final int delai = 10;
    ArrayList<Point> points;


    public ThreadMining(Map m, Personnage p,Obstacle o, ArrayList<Point> points) {
        this.points = points;
        this.m = m;
        this.p = p;
        this.finalX = o.getX();
        this.finalY = o.getY();
        this.o = o;
    }

    @Override
    public void run() {
        try {
            sleep(300);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        for (Point point :
                points) {
            p.setPosition( (int)point.getX(), (int) point.getY());


            try {
                sleep(delai);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        p.setMoving(false);
        m.obstacleMined(o);
    }
}
