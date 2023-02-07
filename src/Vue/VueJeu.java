package Vue;

import Model.Batiments.Batiment;
import Model.Map;
import Model.Obstacles.Obstacle;
import Model.Obstacles.Taille;
import Model.Obstacles.Type;
import Model.Personnages.Archer;
import Model.Personnages.Personnage;


import javax.swing.*;
import java.awt.*;

public class VueJeu extends JPanel {
    private Map map;
    private VueRessources vueRessources;
    private final int largeur = Map.taille - 200;
    private final int hauteur = Map.taille - 200;

    /**
     * Création d'un Vue.Affichage pour le Jeu (chateau,personnage,obstacle).
     * @param map de type Modele.Map
     */
    public VueJeu(Map map){
        this.map = map;
        setPreferredSize(new Dimension(largeur, hauteur));
        //this.vueRessources = new VueRessources(map);
        setBackground(Color.GREEN);
    }
    /**
     * Méthode pour dessiner la map, les obstacles, les personnages et les batiments.
     * @param g Instance de la classe Graphics
     */

    public void paint(Graphics g){
        super.repaint();
        g.drawImage(BanqueImage.imgBackground,0,0,map.taille/2,map.taille/2,null);
        g.drawImage(BanqueImage.imgBackground,0,map.taille/2,map.taille/2,map.taille/2,null);
        g.drawImage(BanqueImage.imgBackground,map.taille/2,0,map.taille/2,map.taille/2,null);
        g.drawImage(BanqueImage.imgBackground,map.taille/2,map.taille/2,map.taille/2,map.taille/2,null);
        paintBatiments(g);
        paintObstacles(g);
        paintPersonnages(g);
        Color color = new Color(25,25,112,100);
        g.setColor(color);
        if(!map.getDay()) {
            g.fillRect(0, 0, map.taille, map.taille);
        }
    }

    /**
     * Méthode pour dessiner les batiments.
     * Récupération des images depuis BanqueImage.
     * @param g Instance de la classe Graphics
     */
    private void paintBatiments(Graphics g){
        g.setColor(Color.GRAY);
        for(Batiment b : map.getBatiments()){
            if(b.getLevel() == 1){
                g.drawImage(BanqueImage.imgNexus1,b.getX(),b.getY(),150,150,null);
            }
            if(b.getLevel() == 2){
                g.drawImage(BanqueImage.imgNexus2,b.getX(),b.getY(),150,150,null);
            }
            if(b.getLevel() == 3){
                g.drawImage(BanqueImage.imgNexus3,b.getX(),b.getY(),150,150,null);
            }
        }
    }
    /**
     * Méthode pour dessiner les obstacles.
     * Récupération des images depuis BanqueImage.
     * @param g Instance de la classe Graphics
     */
    private void paintObstacles(Graphics g){
        g.setColor(Color.YELLOW);
        for(Obstacle o : map.getObstacles()){
            if(o.getType() == Type.Wheat){
                paintWheat(g,o);
            }
            if(o.getType() == Type.Rock){
                paintRock(g,o);
            }
            if(o.getType() == Type.Tree){
                paintTree(g,o);
            }
        }
    }
    /**
     * Méthode pour dessiner les rochers.
     * Récupération des images depuis BanqueImage.
     * @param g Instance de la classe Graphics
     */
    private void paintRock(Graphics g,Obstacle o){
        if(o.getSize() == Taille.Small){
            g.drawImage(BanqueImage.imgRock1,o.getX(),o.getY(),60,60,null);
        }
        if(o.getSize() == Taille.Average){
            g.drawImage(BanqueImage.imgRock2,o.getX(),o.getY(),60,60,null);
        }
        if(o.getSize() == Taille.Big){
            g.drawImage(BanqueImage.imgRock3,o.getX(),o.getY(),60,60,null);
        }
    }
    /**
     * Méthode pour dessiner les champs de blés.
     * Récupération des images depuis BanqueImage.
     * @param g Instance de la classe Graphics
     */
    private void paintWheat(Graphics g,Obstacle o){
        if(o.getSize() == Taille.Small){
            g.drawImage(BanqueImage.imgWheat_Field1,o.getX(),o.getY(),60,60,null);
        }
        if(o.getSize() == Taille.Average){
            g.drawImage(BanqueImage.imgWheat_Field2,o.getX(),o.getY(),60,60,null);
        }
        if(o.getSize() == Taille.Big){
            g.drawImage(BanqueImage.imgWheat_Field3,o.getX(),o.getY(),60,60,null);
        }
    }
    /**
     * Méthode pour dessiner les arbres.
     * Récupération des images depuis BanqueImage.
     * @param g Instance de la classe Graphics
     */
    private void paintTree(Graphics g,Obstacle o){
        if(o.getSize() == Taille.Small){
            g.drawImage(BanqueImage.imgTree1,o.getX(),o.getY(),60,60,null);
        }
        if(o.getSize() == Taille.Average){
            g.drawImage(BanqueImage.imgTree2,o.getX(),o.getY(),60,60,null);
        }
        if(o.getSize() == Taille.Big){
            g.drawImage(BanqueImage.imgTree3,o.getX(),o.getY(),60,60,null);
        }
    }
    /**
     * Méthode pour dessiner les personnages.
     * Récupération des images depuis BanqueImage.
     * @param g Instance de la classe Graphics
     */
    private void paintPersonnages(Graphics g){
        g.setColor(Color.BLUE);
        for(Personnage p: map.getPersonnages()){
            if(p.getClass().getSimpleName().equals("Archer")){
                g.drawImage(BanqueImage.imgArcher,p.getX(),p.getY(),60,70,null);
            }
            if(p.getClass().getSimpleName().equals("Guerrier")){
                g.drawImage(BanqueImage.imgGuerrier,p.getX(),p.getY(),60,70,null);
            }
            if(p.getClass().getSimpleName().equals("Villageois")){
                g.drawImage(BanqueImage.imgVillageois,p.getX(),p.getY(),50,60,null);
            }
        }
    }

}