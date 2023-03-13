package Controller;

import Model.Map;
import Model.Personnages.Archer;
import Model.Personnages.Guerrier;
import Model.Personnages.Personnage;
import Vue.Affichage;
import Vue.Controller.ArcherController;
import Vue.Controller.GuerrierController;
import Vue.Controller.NoneController;
import Vue.Controller.VueController;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ActionControl implements MouseListener {
        private Map map;
        Affichage affichage;
        NoneController noneController;
        ArcherController archerController;
        GuerrierController guerrierController;
        VueController controller;


        public ActionControl(Map m, VueController c){
            this.controller=c;
            this.map=m;
            System.out.println("crée le listener");
            noneController=new NoneController(map);
            archerController=new ArcherController(map);
            guerrierController=new GuerrierController(map);
        }


    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("listend");
        controller.type();
        System.out.println("listenerf");
        int x = e.getX();
        int y=e.getY();
        System.out.println("clique sur la page");
        for(Personnage p:map.getPersonnages()) {
            if (x >= p.getX() && x <= p.getX() + 50 && y >= p.getY() && y <= p.getY() + 60) {
                if (p instanceof Guerrier) {
                    controller=guerrierController;
                } else {
                    if (p instanceof Archer) {
                        System.out.println("clique d'archer");
                        controller=archerController;
                        controller.repaint();
                    } else {
                        controller=noneController;
                    }
                }
            }
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
