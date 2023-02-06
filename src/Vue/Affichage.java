package Vue;

import Model.Map;
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
        setPreferredSize(new Dimension(map.taille, map.taille));
        this.vueRessources = new VueRessources(map);
        //this.vueRessources.setBackground(Color.blue);
        this.vueJeu = new VueJeu(map);

        JPanel j = new VueRessources(map);

        //j.setBackground(Color.blue);
        //window.setBackground(Color.GREEN);
        //this.vueRessources.setBackground(Color.blue);
        window.add(vueJeu);
        window.add(j, BorderLayout.SOUTH);
        window.pack();
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    /**
     * Méthode pour dessiner la map, les obstacles, les personnages et les batiments.
     * On appelle les sous-classes VueRessources et vueJeu, chargées chacun d'afficher le jeu ou les ressources
     * @param g Instance de la classe Graphics
     */

    public void paint(Graphics g){
        super.repaint();
        vueRessources.paint(g);
        vueJeu.paint(g);
    }


}