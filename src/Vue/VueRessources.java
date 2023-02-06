package Vue;

import Model.Map;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Classe permettant d'afficher les ressources, elle est stockée dans un JPanel
 */
public class VueRessources extends JPanel {
    private final Map map;
    private final int hauteur = 30;
    private final int longueur = Map.taille;
    /**
     * Création d'un Vue.Affichage pour les ressources (bois,blé,pierre).
     * @param map de type Modele.Map
     */
    public VueRessources(Map map){
        this.map = map;
        setBackground(Color.RED);
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
            g.drawImage(BanqueImage.imgSoleil, 965, 0, 30, 30, null);
        }
        if(!map.getDay()) {
            g.drawImage(BanqueImage.imgLune, 965, 0, 30, 30, null);
        }
    }
}