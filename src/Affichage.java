import Batiments.Batiment;
import Batiments.Nexus;
import Obstacles.Obstacle;
import Personnages.Personnage;

import javax.swing.*;
import java.awt.*;

public class Affichage extends JPanel {
    public Map map;
    /**
     * Création d'un Affichage
     * @param map de type Map
     */
    public Affichage(Map map){
        this.map = map;
        setPreferredSize(new Dimension(map.windowWidth,map.windowHeight));
        setBackground(Color.GREEN);
    }
    /**
     * Méthode pour dessiner la map, les obstacles, les personnages et les batiments.
     * @param g Instance de la classe Graphics
     */

    public void paint(Graphics g){
        g.setColor(Color.GRAY);
        for(Batiment b : map.getBatiments()){
            g.drawRect(b.getX(), b.getY(), 100, -100);
        }
        g.setColor(Color.YELLOW);
        for(Obstacle o : map.getObstacles()){
            g.drawRect(o.getX(), o.getY(), 10, -10);
        }
        g.setColor(Color.BLUE);
        for(Personnage p: map.getPersonnages()){
            g.drawRect(p.getX(), p.getY(), 10, -10);
        }
    }
}
