import Controller.ActionPanel;
import Model.Map;
import Model.Personnages.Guerrier;
import Model.TimeChanger;
import Vue.Affichage;
import Vue.ControllerView.ArcherController;
import Vue.ThreadAfficheur;
import Vue.VueJeu;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        Map map = new Map();
        /**JFrame fenetre=new JFrame("Sunrise Siege");
        //fenetre.setPreferredSize(new Dimension(1500,1000));
        VueJeu vueJeu=new VueJeu(map);
        ArcherController archerController= new ArcherController(map);
        fenetre.add(vueJeu,BorderLayout.CENTER);
        fenetre.add(archerController,BorderLayout.EAST);
        //new ThreadAfficheur(vueJeu).start();
        //vueJeu.repaint();
        //new TimeChanger(map).start();
        fenetre.setPreferredSize(new Dimension(1300,1000));
        fenetre.pack();
        fenetre.setVisible(true);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         **/
        Guerrier testDeplacement = new Guerrier( 200,200);
        map.addCharacter(testDeplacement);
        map.deplacementPerso(testDeplacement,50,700);
        Affichage affichage = new Affichage(map);
        ActionPanel actionPanel = new ActionPanel(map,affichage);
        affichage.addMouseListener(actionPanel);
        new TimeChanger(map).start();
    }
}
