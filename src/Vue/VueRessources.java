package Vue;

import Model.Map;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Clase permettant d'afficher les ressources, elle est stockée dans un JPanel
 */
public class VueRessources extends JPanel {
    private final Map map;
    private final int hauteur = 30;
    private final int longueur = Map.taille;

    public VueRessources(Map map){
        this.map = map;
        setBackground(Color.RED);

        setPreferredSize(new Dimension(longueur,hauteur));
    }

    /**
     * Méthode permettant de paint les 3 ressources
     * @param g  the <code>Graphics</code> context in which to paint
     */
    @Override
    public void paint(Graphics g) {
        g.drawImage(BanqueImage.imgFondRessources,0,0,longueur,hauteur,null);
        paintFood(g);
        paintWood(g);
        paintRocks(g);

    }

    /**
     * Le bois, ici avec l'image correspondante
     * @param g
     */
    private void paintWood(Graphics g){
        g.setColor(Color.decode("#A52A2A"));
       //g.fillRect(10,0,20,20);
        g.drawImage(BanqueImage.imgWood,50,0,20,20,null);
        g.drawString("" + map.getWood(),70,15);

    }
    /**
     * La nourriture, ici avec l'image correspondante
     * @param g
     */
    private void paintFood(Graphics g){
       // g.setColor(Color.GREEN);
        //g.fillRect(10,10,10,10);
        g.drawImage(BanqueImage.imgWheat,100,0,20,20,null);
        g.drawString("" + map.getFood(),120,15);
    }

    /**
     * La pierre, ici avec l'image correspondante
     * @param g
     */
    private void paintRocks(Graphics g){
        g.setColor(Color.GRAY);
        //g.fillRect(110,0,20,20);
        g.drawImage(BanqueImage.imgStone,150,0,20,20,null);
        g.drawString("" + map.getStone(),170,15);
    }
}