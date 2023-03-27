package Model.Personnages;

import java.awt.event.*;

import javax.swing.*;
import java.awt.event.ActionListener;

public abstract class Personnage {
    private int x;
    private int y;
    protected int health_points;
    private boolean isAlive = true;
    protected int hpMax = health_points;
    private final Object lock = new Object();
    protected int level;
    public static final int taille = 60;

    private boolean moving = false;
    public Personnage(int health_points, int x, int y){
        this.health_points = health_points;
        this.x = x;
        this.y = y;

    }


    /**
     * perso qui se fait attaquer
     * @param damage
     */
    public void attackedPersonnage(int damage){
        if(this.health_points <= 0){
            this.isAlive = false;
        }
        else{
            this.health_points -= damage;
        }
    }

    /**
     * get si en vie pour retirer perso du jeu
     * @return
     */
    public boolean getIsAlive(){
        return this.isAlive;
    }

    public int getHealth_points(){return this.health_points;}

    public int getX(){return this.x;}

    public int getY(){return this.y;}
    public void setPosition(int x, int y) {
        synchronized (lock) {
            setX(x);
            setY(y);
        }
    }

    public synchronized  void  setX(int new_x){
        this.x = new_x;
    }

    public synchronized void setY(int new_y){
        this.y = new_y;
    }

    public void heal(){
        health_points = hpMax;
    }

    public int getLevel(){
        return this.level;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public boolean isMoving(){
        return this.moving;
    }
}
