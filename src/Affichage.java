import Batiments.Batiment;
import Batiments.Nexus;
import Obstacles.Obstacle;
import Personnages.Personnage;

import javax.swing.*;
import java.awt.*;

public class Affichage extends JPanel {
    public Map map;
    public Affichage(Map map){
        this.map = map;
        setPreferredSize(new Dimension(map.windowWidth,map.windowHeight));
        setBackground(Color.LIGHT_GRAY);
    }

    public void paint(Graphics g){
        g.setColor(Color.GRAY);
        for(Batiment b : map.getBatiments()){
            g.drawRect(b.getX(), b.getY(), 100, -100);
        }
        g.setColor(Color.RED);
        for(Obstacle o : map.getObstacles()){
            g.drawRect(o.getX(), o.getY(), 10, -10);
        }
    }
}
