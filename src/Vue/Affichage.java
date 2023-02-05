package Vue;

import Model.Map;
import javax.swing.*;
import java.awt.*;

public class Affichage extends JFrame {
    private final VueRessources vueRessources;
    private final VueJeu vueJeu;
    private final VueInfo vueInfo;

    /**
     * Création d'un Vue.Affichage
     * @param map de type Modele.Map
     */
    public Affichage(Map map){
        JFrame window = new JFrame("Sunrise Siege");
        window.setPreferredSize(new Dimension(Map.taille, Map.taille));
        window.getContentPane().setLayout(new BorderLayout());
        //this.setBackground(Color.GREEN);

        this.vueRessources = new VueRessources(map);
        this.vueJeu = new VueJeu(map);
        this.vueInfo = new VueInfo(map);
        this.vueRessources.setBorder(BorderFactory.createLineBorder(Color.black));

        window.add(this.vueInfo, BorderLayout.EAST);
        window.add(this.vueJeu);
        window.add(this.vueRessources, BorderLayout.SOUTH);

        window.pack();
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    /**
     * Méthode pour dessiner la map, les obstacles, les personnages et les batiments.
     * @param g Instance de la classe Graphics
     */

    public void paint(Graphics g){
        super.repaint();
        vueInfo.paint(g);
        vueRessources.paint(g);
        vueJeu.paint(g);
    }

}