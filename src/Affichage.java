import Batiments.Nexus;

import javax.swing.*;
import java.awt.*;

public class Affichage extends JPanel {
    public int windowWidth = 1480;
    public int windowHeight = 920;
    public Nexus nexus;
    public Affichage(){
        nexus = new Nexus(50,windowHeight-50);
        setPreferredSize(new Dimension(windowWidth,windowHeight));
        setBackground(Color.LIGHT_GRAY);
    }

    public void paint(Graphics g){
        g.setColor(Color.GRAY);
        g.drawRect(nexus.getX(),nexus.getY(),100 ,-100);
    }
}
