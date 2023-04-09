package Model.Obstacles;

import java.awt.*;
import java.util.Random;

/**
 * Super classe obstacle, permettant d'encoder les rochers, arbres et blé
 * Le type de la ressource est un enum class, son image en dépendra, mais aussi du moment de la journée ( nuit/jour)
 * Un obstacle rapporte un nombre de ressource définit par sa taille.
 *
 */

public class Obstacle {
    private int ressource;
    private Taille size;
    private int x;
    private int y;
    private final int few = 3;
    private final int medium = 5;
    private final int many = 10;
    private final int taille = 50;
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
        this.size = taille;
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
        this.x = x;
        this.y = y;
        Random r = new Random();
        int nb = r.nextInt(3);
        switch (nb) {
            case 0 -> size = Taille.Small;
            case 1 -> size = Taille.Average;
            case 2 -> size = Taille.Big;
        }
        switch (size){
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
        return size;
    }

    /**
     * Permet de modifier la taille, non utilisable normalement.
     * @param size
     */
    private void setSize(Taille size) {
        this.size = size;
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


    public Type getType() {
        return type;
    }




    /**
     * Permet d'affiche les informations liées à l'obstacle, sera utile pour l'affichage swing
     * @return String
     */
    @Override
    public String toString() {
        return "Cest un " +  type +
                " , de taille " + size +
                " et qui rapporte " + ressource +
                "à sa destruction";
    }

    public int getTaille() {
        return taille;
    }



    /**
     * Permet d'augmenter la taille de l'obstacle, pourra être utile prochainement
     * unused
     */
    public void upgrade(){
        switch (size){
            case Small -> {
                size = Taille.Average;
                ressource =medium;
            }
            case Average ->{
                size = Taille.Big;
                ressource = many;

            }
        }
    }
}
