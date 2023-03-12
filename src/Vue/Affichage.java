package Vue;

import Controller.ActionControl;
import Model.Map;
import Vue.Controller.ArcherController;
import Vue.Controller.GuerrierController;
import Vue.Controller.NoneController;
import Vue.Controller.VueController;

import javax.swing.*;
import java.awt.*;

public class Affichage extends JFrame {
    private Map map;
    private VueRessources vueRessources;
    private VueJeu vueJeu;
    private VueController controller;
    private GuerrierController gc;
    private ArcherController ac;
    private NoneController nc;
    private ActionControl actionControl;

    /**
     * Création d'un Vue.Affichage
     * @param map de type Modele.Map
     */
    public Affichage(Map map){
        this.map = map;
        JFrame window = new JFrame("Sunrise Siege");
        setPreferredSize(new Dimension(map.taille+300, map.taille));
        this.vueRessources = new VueRessources(map);
        this.vueJeu = new VueJeu(map);
        JPanel j = new VueRessources(map);
        NoneController noneController = new NoneController(map);
        nc=noneController;
        GuerrierController guerrierController= new GuerrierController(map);
        gc=guerrierController;
        ArcherController archerController= new ArcherController(map);
        ac=archerController;
        controller=noneController;
        ActionControl actionControl = new ActionControl(map);
        window.addMouseListener(actionControl);
        window.add(controller,BorderLayout.EAST);
        window.add(vueJeu, BorderLayout.CENTER);
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
        controller.paint(g);
    }

    public void setControllerGuerrier() {
        this.controller = gc;
    }

    public void setControllerArcher(){
        this.controller=ac;
    }

    public void setControllerNone(){
        this.controller=nc;
    }
}