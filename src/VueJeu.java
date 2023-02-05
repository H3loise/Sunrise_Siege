import Batiments.Batiment;
import Obstacles.Obstacle;
import Personnages.Personnage;

import javax.swing.*;
import java.awt.*;

public class VueJeu extends JPanel {
    private Map map;
    private VueRessources vueRessources;
    /**
     * Création d'un Affichage
     * @param map de type Map
     */
    public VueJeu(Map map){
        this.map = map;
        setPreferredSize(new Dimension(Map.taille,Map.taille));
        this.vueRessources = new VueRessources(map);
        setBackground(Color.GREEN);
    }
    /**
     * Méthode pour dessiner la map, les obstacles, les personnages et les batiments.
     * @param g Instance de la classe Graphics
     */

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
