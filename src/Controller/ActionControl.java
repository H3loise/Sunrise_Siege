package Controller;

import Model.Map;
import Model.Personnages.Archer;
import Model.Personnages.Guerrier;
import Model.Personnages.Personnage;
import Vue.Affichage;
import Vue.Controller.ArcherController;
import Vue.Controller.VueController;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ActionControl implements MouseListener {
        private Map map;


        public ActionControl(Map m){
            this.map=m;
            System.out.println("crée le listener");
        }


    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("clique");
        /**
        int x = e.getX();
        int y=e.getY();
        System.out.println("clique sur la page");
        for(Personnage p:map.getPersonnages()){
            if(x>= p.getX() && x<=p.getX()+50 && y>= p.getY() && y<=p.getY()+60){
                if(p instanceof Guerrier){
                    affichage.setControllerGuerrier();
                }else{
                    if(p instanceof Archer){
                        System.out.println("clique d'archer");
                        affichage.setControllerArcher();
                    }else {
                        affichage.setControllerNone();
                    }
                }
            }
        }
         **/

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
