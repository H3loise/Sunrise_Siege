import Controller.ActionControl;
import Model.Batiments.Batiment;
import Model.Batiments.Nexus;
import Model.Map;
import Model.Obstacles.Obstacle;
import Model.Personnages.Archer;
import Model.Personnages.Personnage;
import Vue.Affichage;
import Vue.Controller.NoneController;
import Vue.Controller.VueController;
import Vue.VueJeu;
import Vue.VueRessources;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame("Sunrise Siege");
        Map map = new Map();
        window.setPreferredSize(new Dimension(map.taille+300, map.taille));
        new TimeChanger(map).start();
        //Affichage aff = new Affichage(map);
        //new RepaintThread(aff,map).start();
        VueController vueController=new NoneController(map);
        VueJeu vueJeu=new VueJeu(map);
        VueRessources vueRessources = new VueRessources(map);
        ActionControl controller = new ActionControl(map, vueController);
        new RepaintThread(vueJeu,vueRessources,map).start();
        window.add(vueRessources,BorderLayout.SOUTH);
        window.add(vueJeu,BorderLayout.CENTER);
        window.add(vueController,BorderLayout.EAST);
        window.addMouseListener(controller);
        window.pack();
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}