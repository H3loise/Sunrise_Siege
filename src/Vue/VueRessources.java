package Vue;

import Model.Map;

import javax.swing.*;
import java.awt.*;

/**
 * Classe permettant d'afficher les ressources, elle est stockée dans un JPanel
 */
public class VueRessources extends JPanel {
    private final Map map;
    private final int hauteur = 30;
    private final int longueur = Map.taille;


    private int widthBarre = 200;
    /**
     * Création d'un Vue.Affichage pour les ressources (bois,blé,pierre).
     * @param map de type Modele.Map
     */
    public VueRessources(Map map){
        this.map = map;
        setBackground(Color.GRAY);
        setPreferredSize(new Dimension(longueur,hauteur));
    }

    /**
     * Méthode pour dessiner la map, les obstacles, les personnages et les batiments.
     * @param g Instance de la classe Graphics
     */
    @Override
    public void paint(Graphics g) {
        super.repaint();
        g.drawImage(BanqueImage.imgFondRessources,0,0,longueur,hauteur,null);
        paintFood(g);
        paintWood(g);
        paintRocks(g);
        paintAstre(g);
        timerRect(g);
        paintScore(g);
    }

    /**
     * Méthode pour dessiner le bois (ressource).
     * Récupération des images depuis BanqueImage.
     * @param g Instance de la classe Graphics
     */
    private void paintWood(Graphics g){
        g.setColor(Color.decode("#A52A2A"));
        //g.fillRect(10,0,20,20);
        g.setColor(Color.BLACK);
        g.drawImage(BanqueImage.imgWood,50,-1,30,30,null);
        g.drawString("" + map.getWood(),80,18);

    }
    /**
     * Méthode pour dessiner le blé/nourriture (ressource).
     * Récupération des images depuis BanqueImage.
     * @param g Instance de la classe Graphics
     */
    private void paintFood(Graphics g){
        g.setColor(Color.BLACK);
        g.drawImage(BanqueImage.imgWheat,100,-1,30,30,null);
        g.drawString("" + map.getFood(),130,18);
    }

    /**
     * Méthode pour dessiner la pierre (ressource).
     * Récupération des images depuis BanqueImage.
     * @param g Instance de la classe Graphics
     */
    private void paintRocks(Graphics g){
        g.setColor(Color.BLACK);
        g.drawImage(BanqueImage.imgStone,150,1,30,30,null);
        g.drawString("" + map.getStone(),180,18);
    }

    /**
     * Méthode pour dessiner le soleil ou la lune en fonction du jour ou de la nuit.
     * Récupération des images depuis BanqueImage.
     * @param g Instance de la classe Graphics
     */
    private void paintAstre(Graphics g){
        if(map.getDay()) {
            g.drawImage(BanqueImage.imgSoleil, map.taille/2, 0, 30, 30, null);
        }
        if(!map.getDay()) {
            g.drawImage(BanqueImage.imgLune, map.taille/2, 0, 30, 30, null);
        }
    }

    /**
     * Méthode pour dessiner le rectangle de progression du jour/nuit.
     * @param g
     */
    private void timerRect(Graphics g){
        int delai = map.getDelaiJourNuit(); // récupère la durée d'un cycle jour/nuit
        long elapsedTime = System.currentTimeMillis() - map.getStartTime(); // calcule le temps écoulé depuis le début du cycle actuel
        double ratio = (double) elapsedTime / delai; // calcule le ratio de progression (entre 0 et 1)
        int barWidth = (int) (ratio * (widthBarre - 3)); // calcule la largeur du rectangle représentant la progression

        // dessine le rectangle noir de la barre de progression
        g.setColor(Color.BLACK);
        g.drawRect(map.taille/2+39, 4, widthBarre, 21);

        // dessine le rectangle vert ou rouge représentant la progression
        if (map.getDay()) {
            g.setColor(Color.GREEN);
        } else {
            g.setColor(Color.RED);
        }
        g.fillRect(map.taille/2+40, 5, barWidth, 20);
    }

    private void paintScore(Graphics g) {
        g.setColor(Color.BLACK);
        long score = map.getScore();
        g.drawString("Score : " + score, 800, 20);
    }
}