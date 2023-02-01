import java.awt.*;

/**
 * SuperClasse abstract Batiment, vous ne pouvez pas manipuler directement des objets de cette classe /!\,
 * un batiment a toujours des pv, une position, une image de nuit et de jour, et un niveau.
 * un batiment est un Nexus pour l'instant, mais pas un obstacle attention.
 */
public abstract class Batiment {
    private int pv;
    private int x;
    private int y;
    private final Image imageJour = null;
    private final Image imageNuit = null;
    private int level;

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

    public Image getImageJour() {
        return imageJour;
    }

    public Image getImageNuit() {
        return imageNuit;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
