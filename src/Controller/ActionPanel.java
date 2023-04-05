package Controller;

import Model.Map;
import Model.Obstacles.Obstacle;
import Model.Personnages.*;
import Vue.Affichage;
import Vue.ControllerView.NoneController;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

/**
 * Classe permettant le controle ou l'affichage d'information sur les unités controlables ou non controlable.
 * Elle fonctionne via l'utilisation du MouseClicked(MouseEvent) et des coordonnées de la souris.
 * Le jeu fonctionnant principalement au clic, cela est nécessaire d'avoir cette classe.
 */
public class ActionPanel implements MouseListener {

    private Map map;
    private Affichage affichage;

    public ActionPanel (Map m, Affichage a){
        this.map=m;
        this.affichage=a;
    }


    /**
     * Fonction permettant la selection d'objet sur la map, selon la coordonnée de la souris et l'objet le plus proche, elle choisit
     * l'objet Selectionné.
     * Selon instanceof de l'objet, cela lance la fonction adéquate et affiche le panneau correspondant.
     * Pour les villageois, elle permet le deplacement sur un Obstacle en lançant Modele.Map.deplacementPersoMiner(Villageois,Obstacle).
     * Pour les unités déplaçables elle lance le déplacement normal.
     * Les villageois ne sont selectionnables que la journée, et les Guerrier/Archer que la nuit
     * Les ennemis ont seulement un panneau d'information.
     * @param e the event to be processed
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("clique carte");
        NoneController none = new NoneController(map);
        ArrayList<Personnage> personnages = map.getPersonnages();
        boolean changed = true;
        System.out.println("le perso actionner " + map.getActionner());

        System.out.println("coordonnées chateau : x= " + this.map.getNexus().getX() + " y=" + this.map.getNexus().getY());
        System.out.println("coordonnées click souris : x=" + e.getX() + " y = " + e.getY());
        if (e.getX() < 1000 && e.getY() < 1000) {
            if (map.getActionner() == null) {
                if (e.getX() >= map.getNexus().getX() && e.getX() <= (map.getNexus().getX() + 150) && e.getY() >= map.getNexus().getY() && e.getY() <= (map.getNexus().getY() + 150)) {
                    System.out.println("change pour le chateau");
                    this.affichage.card.show(this.affichage.getController(), "nexus");
                    changed = false;
                }
                if (e.getX() >= map.getCaserne().getX() && e.getX() <= (map.getCaserne().getX() + 150) && e.getY() >= map.getCaserne().getY() && e.getY() <= (map.getCaserne().getY() + 150)) {
                    System.out.println("change pour le caserne");
                    this.affichage.card.show(this.affichage.getController(), "caserne");
                    changed = false;
                }
                for (Personnage p : personnages) {
                    if (e.getX() >= p.getX() && e.getX() <= p.getX() + 80 && e.getY() >= p.getY() && e.getY() <= p.getY() + 80) {
                        if (p instanceof Archer && !map.getDay()) {
                            System.out.println("change pour l'archer");
                            this.affichage.card.show(this.affichage.getController(), "archer");
                            this.map.setActionner(p);
                            System.out.println("HPPPPPPPPPPPPPPPPPPPPP " + map.getActionner().getHealth_points());
                        }
                        if (p instanceof Guerrier && !map.getDay()) {
                            System.out.println("change pour le guerrier");
                            this.affichage.card.show(this.affichage.getController(), "guerrier");
                            this.map.setActionner(p);
                        }
                        if (p instanceof Villageois && map.getDay()) {
                            System.out.println("change pour le villageois");
                            this.affichage.card.show(this.affichage.getController(), "villageois");
                            this.map.setActionner(p);
                        }
                        changed = false;

                    }
                }
                for (Ennemy mechant : map.getEnnemies()) {
                    if (e.getX() >= mechant.getX() && e.getX() <= mechant.getX() + 80 && e.getY() >= mechant.getY() && e.getY() <= mechant.getY() + 80) {
                        System.out.println("change pour le méchant");
                        this.affichage.card.show(this.affichage.getController(), "ennemy");
                        this.map.setActionner(mechant);
                        changed = false;
                    }
                }
                if (changed) {
                    this.affichage.card.show(this.affichage.getController(), "none");
                }
            } else {
                boolean mining = true;
                if (map.getActionner() instanceof Villageois) {
                    for (Obstacle o : map.getObstacles()) {
                        if (o.getX() <= e.getX() && o.getX() + 40 >= e.getX() && o.getY() <= e.getY() && e.getX() + 40 >= e.getY()) {
                            map.deplacementPersoMiner(map.getActionner(), o);
                            System.out.println("minage ");
                            mining = false;
                            map.setActionner(null);
                            this.affichage.card.show(this.affichage.getController(), "none");
                        }
                    }
                    if (mining) {
                        map.deplacementPerso(map.getActionner(), e.getX(), e.getY());
                        map.setActionner(null);
                        this.affichage.card.show(this.affichage.getController(), "none");
                    }
                } else {
                    if (!(map.getActionner() instanceof Ennemy)) {
                        System.out.println("On change les coordonnées du pointeur");
                        this.map.setyActionner(e.getY());
                        this.map.setxActionner(e.getX());

                        map.deplacementPerso(map.getActionner(), e.getX(), e.getY());
                    }
                    map.setActionner(null);
                    this.affichage.card.show(this.affichage.getController(), "none");
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
