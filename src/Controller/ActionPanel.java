package Controller;

import Model.Map;
import Model.Personnages.Archer;
import Model.Personnages.Guerrier;
import Model.Personnages.Personnage;
import Model.Personnages.Villageois;
import Vue.Affichage;
import Vue.ControllerView.NoneController;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class ActionPanel implements MouseListener {

    private Map map;
    private Affichage affichage;

    public ActionPanel (Map m, Affichage a){
        this.map=m;
        this.affichage=a;
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("clique carte");
        NoneController none = new NoneController(map);
        ArrayList<Personnage> personnages = map.getPersonnages();
        boolean changed =true;

        System.out.println("coordonnées chateau : x= "+this.map.getNexus().getX()+" y="+this.map.getNexus().getY());
        System.out.println("coordonnées click souris : x="+e.getX()+" y = "+e.getY());

        if(e.getX()>=map.getNexus().getX() && e.getX()<= (map.getNexus().getX()+150) && e.getY()>=map.getNexus().getY() && e.getY()<= (map.getNexus().getY()+150)){
            System.out.println("change pour le chateau");
            this.affichage.card.show(this.affichage.getController(),"nexus");
            changed=false;
        }
        for(Personnage p:personnages){
            if(e.getX()>=p.getX() && e.getX()<=p.getX()+80 && e.getY()>=p.getY() && e.getY()<=p.getY()+80){
                if(p instanceof Archer){
                    this.affichage.card.show(this.affichage.getController(),"archer");
                }if(p instanceof Guerrier){
                    this.affichage.card.show(this.affichage.getController(),"guerrier");
                }if(p instanceof Villageois){
                    this.affichage.card.show(this.affichage.getController(),"villageois");
                }
                changed=false;
            }
        }
        if(changed) {
            this.affichage.card.show(this.affichage.getController(), "none");
        }
        //this.affichage.setNone();
        //this.affichage.getController().revalidate();
        //this.affichage.getController().repaint();
        //this.affichage.repaint();
        //this.affichage.add(none, BorderLayout.EAST);
        //this.affichage.card.previous(affichage.getController());
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
