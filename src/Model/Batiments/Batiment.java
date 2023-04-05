package Model.Batiments;

import java.awt.*;


/**
 * SuperClasse abstract Batiment, vous ne pouvez pas manipuler directement des objets de cette classe /!\,
 * un batiment a toujours des pv, une position, une image de nuit et de jour, et un niveau.
 * un batiment est un Nexus pour l'instant, mais pas un obstacle attention.
 */
public abstract class Batiment {
    protected int pv;
    protected int pvMax = 2000;
    private int x;
    private int y;
    protected int level;
    private final int taille = 150;

    /**
     * Le niveau d'un batiment est à 1 lors de sa création, on peut l'augmenter avec la sous_fonction cheat.
     * @param x
     * @param y
     * @return Batiment
     */
    public Batiment(int x, int y) {
        this.x = x;
        this.y = y;
        this.level = 1;
    }


    public int getPv() {
        return pv;
    }
    public void setPv(int pv) {
        this.pv = pv;
    }
    public int getPvMax() {
        return pvMax;
    }
    public void setPvMax(int pvMax) {
        this.pvMax = pvMax;
    }

    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }

    public int getLevel() {
        return level;
    }
    public void setLevel(int level) {
        this.level = level;
    }

    public int getTaille() {
        return taille;
    }
}
