package Vue;

import Model.Batiments.Batiment;
import Model.Batiments.Nexus;
import Model.Map;
import Model.Node;
import Model.Obstacles.Obstacle;
import Model.Obstacles.Taille;
import Model.Obstacles.Type;
import Model.Personnages.*;

import javax.swing.*;
import java.awt.*;

public class VueJeu extends JPanel {
    private int largeur;
    private int hauteur;
    private Map map;
    private int frameIndexGAMarche = 0;
    private int frameIndexV = 0;
    private int frameIndexEMarche = 0;
    private int frameIndexEAttaque = 0;
    private int frameIndexAAttaque = 0;
    private int frameIndexGAttaque = 0;

    private Timer timer;
    public VueJeu(Map m) {
        this.largeur = 1000;
        this.hauteur = 1000;
        this.setPreferredSize(new Dimension(largeur, hauteur));
        this.map = m;
        timer = new Timer(100, e -> updateFrameIndex());
        timer.start();
    }

    private void updateFrameIndex() {
        frameIndexGAMarche = (frameIndexGAMarche + 1) % 12;
        frameIndexAAttaque = (frameIndexAAttaque + 1) % 23;
        frameIndexGAttaque = (frameIndexGAttaque + 1) % 9;
        frameIndexV = (frameIndexV + 1) % 8;
        frameIndexEMarche = (frameIndexEMarche + 1) % 4;
        frameIndexEAttaque = (frameIndexEAttaque + 1) % 5;
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(BanqueImage.imgBackground, 0, 0, largeur, hauteur, null);
        paintBatiments(g);
        paintPersonnages(g);
        paintObstacles(g);
        if (!map.getDay()) {
            Color color = new Color(25, 25, 112, 80);
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
            int hpMax = p.getHpMax(); // récupère les hpMax
            int hpCurrent = p.getHealth_points(); // récupère les hp actuel
            double ratio = (double) hpCurrent / hpMax; // calcule le ratio des hps (entre 0 et 1)
            int hpWidth = (int) (ratio * (Personnage.taille/2)); // calcule la largeur du rectangle représentant la progression
            if (p instanceof Archer) {
                if (!map.getDay() || (p.getX()!=map.getCaserne().getX() && p.getY()!=map.getCaserne().getY())) {
                    g.setColor(Color.BLACK);
                    g.drawRect(p.getX()+Personnage.taille/4, p.getY()-3, Personnage.taille / 2+1, 6);
                    g.setColor(Color.RED);
                    g.fillRect(p.getX()+Personnage.taille/4+1, p.getY()-2, Personnage.taille/2,5) ;
                    g.setColor(Color.GREEN);
                    g.fillRect(p.getX()+Personnage.taille/4+1, p.getY()-2, hpWidth,5);

                    if (p.isMoving()) {
                        g.drawImage(BanqueImage.gifArcherWalk.get(frameIndexGAMarche).getImage(), p.getX(), p.getY(), Personnage.taille, Personnage.taille, null);
                    }else if(p.isAttacking()){
                        g.drawImage(BanqueImage.gifArcherAttack.get(frameIndexAAttaque).getImage(), p.getX(), p.getY(), Personnage.taille, Personnage.taille, null);
                    }else {
                        g.drawImage(BanqueImage.gifArcherWalk.get(7).getImage(), p.getX(), p.getY(), Personnage.taille, Personnage.taille, null);
                    }
                }
            } else {
                if (p instanceof Guerrier) {
                    if(!map.getDay() || (p.getX()!=map.getCaserne().getX() && p.getY()!=map.getCaserne().getY())){
                        g.setColor(Color.BLACK);
                        g.drawRect(p.getX()+Personnage.taille/4-7, p.getY()-3, Personnage.taille / 2+1, 6);
                        g.setColor(Color.RED);
                        g.fillRect(p.getX()+Personnage.taille/4-6, p.getY()-2, Personnage.taille/2,5) ;
                        g.setColor(Color.GREEN);
                        g.fillRect(p.getX()+Personnage.taille/4-6, p.getY()-2, hpWidth,5);
                        if(p.isMoving()) {
                            g.drawImage(BanqueImage.gifGuerrierWalk.get(frameIndexGAMarche).getImage(), p.getX(), p.getY(), Personnage.taille, Personnage.taille, null);
                        }else if(p.isAttacking()){
                            g.drawImage(BanqueImage.gifGuerrierAttack.get(frameIndexGAttaque).getImage(), p.getX(), p.getY(), Personnage.taille, Personnage.taille, null);
                        } else {
                            g.drawImage(BanqueImage.gifGuerrierWalk.get(11).getImage(), p.getX(), p.getY(), Personnage.taille, Personnage.taille, null);
                        }
                    }
                } else {
                    if (p instanceof Villageois) {
                        if (map.getDay()) {
                            g.setColor(Color.BLACK);
                            g.drawRect(p.getX()+Personnage.taille/4, p.getY()-3, Personnage.taille / 2+1, 6);
                            g.setColor(Color.RED);
                            g.fillRect(p.getX()+Personnage.taille/4+1, p.getY()-2, Personnage.taille/2,5) ;
                            g.setColor(Color.GREEN);
                            g.fillRect(p.getX()+Personnage.taille/4+1, p.getY()-2, hpWidth,5);
                            if (p.isMoving()) {
                                g.drawImage(BanqueImage.gifVillagerWalk.get(frameIndexV).getImage(), p.getX(), p.getY(), Personnage.taille, Personnage.taille, null);
                            } else {
                                g.drawImage(BanqueImage.gifVillagerWalk.get(5).getImage(), p.getX(), p.getY(), Personnage.taille, Personnage.taille, null);
                            }
                        }
                    }
                }
            }
        }
        for(Ennemy e : map.getEnnemies()){
            int hpMax = e.getHpMax(); // récupère les hpMax
            int hpCurrent = e.getHealth_points(); // récupère les hp actuel
            double ratio = (double) hpCurrent / hpMax; // calcule le ratio des hps (entre 0 et 1)
            int hpWidth = (int) (ratio * (Personnage.taille/2)); // calcule la largeur du rectangle représentant la progression
            if(!map.getDay()) {
                g.setColor(Color.BLACK);
                g.drawRect(e.getX()+Personnage.taille/4, e.getY()-9, Personnage.taille / 2+1, 6);
                g.setColor(Color.RED);
                g.fillRect(e.getX()+Personnage.taille/4+1, e.getY()-8, Personnage.taille/2,5) ;
                g.setColor(Color.GREEN);
                g.fillRect(e.getX()+Personnage.taille/4+1, e.getY()-8, hpWidth,5);
                if (e.isMoving()) {
                    g.drawImage(BanqueImage.gifGoblinWalk.get(frameIndexEMarche).getImage(), e.getX(), e.getY(), Personnage.taille+10, Personnage.taille, null);
                }else if(e.isAttacking()){
                    g.drawImage(BanqueImage.gifGoblinAtk.get(frameIndexEAttaque).getImage(), e.getX(), e.getY(), Personnage.taille+10, Personnage.taille, null);
                } else {
                    g.drawImage(BanqueImage.gifGoblinWalk.get(1).getImage(), e.getX(), e.getY(), Personnage.taille, Personnage.taille+10, null);
                }
            }
        }
    }

    public void paintBatiments(Graphics g) {
        for (Batiment b : map.getBatiments()) {
            if (b instanceof Nexus) {
                int hpMax = b.getPvMax(); // récupère les hpMax
                int hpCurrent = b.getPv(); // récupère les hp actuel
                double ratio = (double) hpCurrent / hpMax; // calcule le ratio des hps (entre 0 et 1)
                int hpWidth = (int) (ratio * (map.getNexus().getTaille() / 2)); // calcule la largeur du rectangle représentant la progression
                g.setColor(Color.BLACK);
                g.drawRect(b.getX() + map.getNexus().getTaille() / 4, b.getY() - 9, map.getNexus().getTaille() / 2 + 1, 6);
                g.setColor(Color.RED);
                g.fillRect(b.getX() + map.getNexus().getTaille() / 4 + 1, b.getY() - 8, map.getNexus().getTaille()/2, 5);
                g.setColor(Color.GREEN);
                g.fillRect(b.getX() + map.getNexus().getTaille() / 4 + 1, b.getY() - 8, hpWidth, 5);
            }
            if (b.getLevel() == 1) {
                g.drawImage(BanqueImage.imgNexus1, b.getX(), b.getY(), map.getNexus().getTaille(), map.getNexus().getTaille(), null);
            }
            if (b.getLevel() == 2) {
                g.drawImage(BanqueImage.imgNexus2, b.getX(), b.getY(), map.getNexus().getTaille(), map.getNexus().getTaille(), null);
            }
            if (b.getLevel() >= 3) {
                g.drawImage(BanqueImage.imgNexus3, b.getX(), b.getY(), map.getNexus().getTaille(), map.getNexus().getTaille(), null);
            }
        }
    }



}