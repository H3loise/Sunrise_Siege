package Vue;

import Model.Map;

import javax.print.attribute.standard.PrinterName;
import javax.swing.*;
import java.awt.*;

public class Affichage extends JFrame {
    private Map map;
    private VueRessources vueRessources;
    private VueJeu vueJeu;
    /**
     * Création d'un Vue.Affichage
     * @param map de type Modele.Map
     */
    public Affichage(Map map){
        this.map = map;
        JFrame window = new JFrame("Sunrise Siege");
        setPreferredSize(new Dimension(Map.taille, Map.taille));
         this.vueRessources = new VueRessources(map);
         this.vueJeu = new VueJeu(map);
         vueJeu.setLayout(new GridLayout(1,1));
        vueJeu.setBackground(Color.BLUE);
        vueRessources.setLayout(new GridLayout(1,1));
        window.add(vueRessources);

        window.add(vueJeu);
        window.pack();
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    /**
     * Méthode pour dessiner la map, les obstacles, les personnages et les batiments.
     * @param g Instance de la classe Graphics
     */

    public void paint(Graphics g){
       vueJeu.paint(g);
       //vueRessources.paint(g);
    }


}
