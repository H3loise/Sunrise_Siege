import java.awt.*;
import java.util.Random;

/**
 * Super classe obstacle, permettant d'encoder les rochers, arbres et blé
 * Le type de la ressource est un enum class, son image en dépendra, mais aussi du moment de la journée ( nuit/jour)
 * Un obstacle rapporte un nombre de ressource définit par sa taille.
 *
 */

public  class Obstacle {
    private int ressource;
    private Taille Size;
    private int x;
    private int y;
    private final int few = 3;
    private final int medium = 5;
    private final int many = 10;
    private final Image imageJour = null;
    private final Image imageNuit = null;
    private Type type;

    /**
     * Constructeur complet, on doit y entrer toute les informations
     * @param ressource
     * @param taille
     * @param x
     * @param y
     * @param type
     */
    public Obstacle(int ressource, Taille taille, int x, int y,Type type) {
        this.ressource = ressource;
        this.type = type;
        this.Size = taille;
        this.x = x;
        this.y = y;
    }

    /**
     * Constructeur presque à vide, on a seulement besoin des coordonnées auquel on veut le créer,les coordonnées seront
     * peut-êtres supprimés plus tard. La taille et la nature de l'obstacle est générée aléatoirement.
     * @param x
     * @param y
     */
    public Obstacle(int x, int y){
        Random r = new Random();
        int nb = r.nextInt(3);
        switch (nb) {
            case 0 -> Size = Taille.Small;
            case 1 -> Size = Taille.Average;
            case 2 -> Size = Taille.Big;
            case default -> Size = Taille.Big;
        }
        switch (Size){
            case Small -> ressource = few;
            case Average -> ressource = medium;
            case Big -> ressource = many;
        }
        nb = r.nextInt(3);

        switch (nb){
            case 0 -> type  = Type.Rock;
            case 1 -> type = Type.Tree;
            case 2 -> type = Type.Wheat;
        }
    }

    /**
     * Getter du nombre de ressource que rapporte l'obstacle lors de sa destruction
     * @return int
     */
    public int getRessource() {
        return ressource;
    }

    /**
     * Permet de modifier combien rapporte l'obstacle,non utilisable normalement.
     * @param ressource
     */
    private void setRessource(int ressource) {
        this.ressource = ressource;
    }

    /**
     * Permet de connaître la taille de l'obstacle
     * @return int
     */
    public Taille getSize() {
        return Size;
    }

    /**
     * Permet de modifier la taille, non utilisable normalement.
     * @param size
     */
    private void setSize(Taille size) {
        this.Size = size;
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

    public int getFew() {
        return few;
    }

    public int getMedium() {
        return medium;
    }

    public int getMany() {
        return many;
    }

    public Image getImageJour() {
        return imageJour;
    }

    public Image getImageNuit() {
        return imageNuit;
    }

    public Type getType() {
        return type;
    }

    /**
     * Permet d'augmenter la taille de l'obstacle, pourra être utile prochainement
     */
    public void upgrade(){
        switch (Size){
            case Small -> {
                Size = Taille.Average;
                ressource =medium;
            }
            case Average ->{
                Size = Taille.Big;
                ressource = many;

            }
        }
    }
    /**
     * Permet d'affiche les informations liées à l'obstacle, sera utile pour l'affichage swing
     * @return String
     */
    @Override
    public String toString() {
        return "Cest un " +  type +
                " , de taille " + Size +
                " et qui rapporte " + ressource +
                "à sa destruction";
    }
}
