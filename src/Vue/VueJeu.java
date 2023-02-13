package Vue;

import Model.Batiments.Batiment;
import Model.Map;
import Model.Obstacles.Obstacle;
import Model.Obstacles.Taille;
import Model.Obstacles.Type;
import Model.Personnages.Personnage;


import javax.swing.*;
import java.awt.*;

public class VueJeu extends JPanel {
    private Map map;
    private VueRessources vueRessources;
    private final int largeur = map.taille;
    private final int hauteur = map.taille;

    /**
     * Création d'un Vue.Affichage
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

    @Override
    public void paint(Graphics g){
        paintBatiments(g);
        paintObstacles(g);
        paintPersonnages(g);
    }
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
    private void paintObstacles(Graphics g){
        g.setColor(Color.YELLOW);
        for(Obstacle o : map.getObstacles()){
            if(o.getType() == Type.Wheat){
                if(o.getSize() == Taille.Small){
                    g.drawImage(BanqueImage.imgWheat_Field1,o.getX(),o.getY(),50,50,null);
                }
                if(o.getSize() == Taille.Average){
                    g.drawImage(BanqueImage.imgWheat_Field2,o.getX(),o.getY(),50,50,null);
                }
                if(o.getSize() == Taille.Big){
                    g.drawImage(BanqueImage.imgWheat_Field3,o.getX(),o.getY(),50,50,null);
                }
            }
            if(o.getType() == Type.Rock){
                if(o.getSize() == Taille.Small){
                    g.drawImage(BanqueImage.imgRock1,o.getX(),o.getY(),50,50,null);
                }
                if(o.getSize() == Taille.Average){
                    g.drawImage(BanqueImage.imgRock2,o.getX(),o.getY(),50,50,null);
                }
                if(o.getSize() == Taille.Big){
                    g.drawImage(BanqueImage.imgRock3,o.getX(),o.getY(),50,50,null);
                }
            }
            if(o.getType() == Type.Tree){
                if(o.getSize() == Taille.Small){
                    g.drawImage(BanqueImage.imgTree1,o.getX(),o.getY(),50,50,null);
                }
                if(o.getSize() == Taille.Average){
                    g.drawImage(BanqueImage.imgTree2,o.getX(),o.getY(),50,50,null);
                }
                if(o.getSize() == Taille.Big){
                    g.drawImage(BanqueImage.imgTree3,o.getX(),o.getY(),50,50,null);
                }
            }
        }
    }

    private void paintPersonnages(Graphics g){
        g.setColor(Color.BLUE);
        for(Personnage p: map.getPersonnages()){
            g.drawRect(p.getX(), p.getY(), 10, -10);
        }
    }

}