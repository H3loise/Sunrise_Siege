package Vue;

import Model.Batiments.Batiment;
import Model.Map;
import Model.Obstacles.Obstacle;
import Model.Personnages.Personnage;


import javax.swing.*;
import java.awt.*;

public class VueJeu extends JPanel {
    private Map map;
    private VueRessources vueRessources;
    private final int largeur = map.taille;
    private final int hauteur = map.taille;

    /**
     * Création d'un Vue.Affichage
     * @param map de type Modele.Map
     */
    public VueJeu(Map map){
        this.map = map;
        setPreferredSize(new Dimension(largeur, hauteur));
        //this.vueRessources = new VueRessources(map);
        setBackground(Color.GREEN);
    }
    /**
     * Méthode pour dessiner la map, les obstacles, les personnages et les batiments.
     * @param g Instance de la classe Graphics
     */

    @Override
    public void paint(Graphics g){
        paintBatiments(g);
        paintObstacles(g);
        paintPersonnages(g);
    }
    private void paintBatiments(Graphics g){
        g.setColor(Color.GRAY);
        for(Batiment b : map.getBatiments()){
            g.drawRect(b.getX(), b.getY(), 100, -100);
        }
    }
    private void paintObstacles(Graphics g){
        g.setColor(Color.YELLOW);
        for(Obstacle o : map.getObstacles()){
            g.drawRect(o.getX(), o.getY(), 10, -10);
        }
    }

    private void paintPersonnages(Graphics g){
        g.setColor(Color.BLUE);
        for(Personnage p: map.getPersonnages()){
            g.drawRect(p.getX(), p.getY(), 10, -10);
        }
    }

}