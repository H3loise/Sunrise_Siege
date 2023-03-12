package Vue;

import Control.Control;
import Model.Map;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Affichage extends JFrame {
    private final VueRessources vueRessources;
    private final VueJeu vueJeu;
    private final VueInfo vueInfo;

    /**
     * Création d'un Vue.Affichage
     * @param map de type Modele.Map
     */
    public Affichage(Map map) throws IOException {
        JFrame window = new JFrame("Sunrise Siege");
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        window.setPreferredSize(new Dimension(Map.taille, Map.taille));
//        this.setPreferredSize(new Dimension(Map.taille,Map.taille));
//        this.getContentPane().setLayout(new BorderLayout());

        //window.getContentPane().setLayout(new BorderLayout());
        //window.getContentPane().setLayout(new BorderLayout());
        panel.addMouseListener(new Control());
        window.add(panel);

        this.vueRessources = new VueRessources(map);
        this.vueJeu = new VueJeu(map);
        this.vueInfo = new VueInfo(map);
        this.vueRessources.setBorder(BorderFactory.createLineBorder(Color.black));

//        window.add(this.vueInfo, BorderLayout.EAST);
//        window.add(this.vueJeu);
//        window.add(this.vueRessources, BorderLayout.SOUTH);

        panel.add(this.vueInfo, BorderLayout.EAST);
        panel.add(this.vueJeu);
        panel.add(this.vueRessources, BorderLayout.SOUTH);

//        this.add(this.vueInfo, BorderLayout.EAST);
//        this.add(this.vueJeu);
//        this.add(this.vueRessources, BorderLayout.SOUTH);
        window.pack();
        window.setVisible(true);
//        this.pack();
//        this.setVisible(true);
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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