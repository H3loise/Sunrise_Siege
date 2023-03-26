package Vue;

import Model.Batiments.Batiment;
import Model.Map;
import Model.Node;
import Model.Obstacles.Obstacle;
import Model.Obstacles.Taille;
import Model.Obstacles.Type;
import Model.Personnages.Archer;
import Model.Personnages.Guerrier;
import Model.Personnages.Personnage;

import javax.swing.*;
import java.awt.*;

public class VueJeu extends JPanel {
    private int largeur;
    private int hauteur;
    private Map map;

    public VueJeu(Map m) {
        this.largeur = 1000;
        this.hauteur = 1000;
        this.setPreferredSize(new Dimension(largeur, hauteur));
        this.map = m;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(BanqueImage.imgBackground, 0, 0, largeur, hauteur, null);
        paintBatiments(g);
        paintPersonnages(g);
        paintObstacles(g);
        if (!map.getDay()) {
            Color color = new Color(25, 25, 112, 120);
            g.setColor(color);
            g.fillRect(0, 0, map.taille, map.taille);
        }
        //funTest(g);
    }

    public void paintObstacles(Graphics g) {
        for (Obstacle o : map.getObstacles()) {
            if (o.getType() == Type.Rock) {
                if (o.getSize() == Taille.Small) {
                    g.drawImage(BanqueImage.imgRock1, o.getX(), o.getY(), 40, 40, null);
                } else {
                    if (o.getSize() == Taille.Average) {
                        g.drawImage(BanqueImage.imgRock2, o.getX(), o.getY(), 40, 40, null);
                    } else {
                        g.drawImage(BanqueImage.imgRock3, o.getX(), o.getY(), 40, 40, null);
                    }
                }
            } else {
                if (o.getType() == Type.Tree) {
                    if (o.getSize() == Taille.Small) {
                        g.drawImage(BanqueImage.imgTree1, o.getX(), o.getY(), 40, 40, null);
                    } else {
                        if (o.getSize() == Taille.Average) {
                            g.drawImage(BanqueImage.imgTree2, o.getX(), o.getY(), 40, 40, null);
                        } else {
                            g.drawImage(BanqueImage.imgTree3, o.getX(), o.getY(), 40, 40, null);
                        }
                    }
                } else {
                    if (o.getSize() == Taille.Small) {
                        g.drawImage(BanqueImage.imgWheat_Field1, o.getX(), o.getY(), 40, 40, null);
                    } else {
                        if (o.getSize() == Taille.Average) {
                            g.drawImage(BanqueImage.imgWheat_Field2, o.getX(), o.getY(), 40, 40, null);
                        } else {
                            g.drawImage(BanqueImage.imgWheat_Field3, o.getX(), o.getY(), 40, 40, null);
                        }
                    }
                }
            }
        }
    }


    public void paintPersonnages(Graphics g) {
        for (Personnage p : map.getPersonnages()) {
            if (p instanceof Archer) {
                g.drawImage(BanqueImage.imgArcher, p.getX(), p.getY(), 40, 40, null);
                g.drawRect(p.getX(), p.getY(), 40, 40);
            } else {
                if (p instanceof Guerrier) {
                    g.drawImage(BanqueImage.imgGuerrier, p.getX(), p.getY(), 40, 40, null);
                } else {
                    g.drawImage(BanqueImage.imgVillageois, p.getX(), p.getY(), 40, 40, null);
                }
            }
        }
    }

    public void paintBatiments(Graphics g) {
        for (Batiment b : map.getBatiments()) {
            //g.drawImage(BanqueImage.imgNexus1,b.getX(),b.getY(),150,150,null);
            if (b.getLevel() == 1) {
                g.drawImage(BanqueImage.imgNexus1, b.getX(), b.getY(), 150, 150, null);
            }
            if (b.getLevel() == 2) {
                g.drawImage(BanqueImage.imgNexus2, b.getX(), b.getY(), 150, 150, null);
            }
            if (b.getLevel() == 3) {
                g.drawImage(BanqueImage.imgNexus3, b.getX(), b.getY(), 150, 150, null);
            }
        }
    }

    private void funTest(Graphics g) {
    g.setColor(Color.black);
        for (Node[] c: map.getNodes()) {
            for (Node node: c) {
                if(node.isSolid())
                g.drawRect(node.getCol(),node.getRow(),1,1);
            }
        }
    }

}