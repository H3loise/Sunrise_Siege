package Vue;

import Model.Batiments.Batiment;
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
    private int frameIndexGA = 0;
    private int frameIndexV = 0;
    private int frameIndexZ = 0;

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
        frameIndexGA = (frameIndexGA + 1) % 12;
        frameIndexV = (frameIndexV + 1) % 8;
        frameIndexZ = (frameIndexZ + 1) % 11;
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
                        g.drawImage(BanqueImage.gifArcherWalk.get(frameIndexGA).getImage(), p.getX(), p.getY(), Personnage.taille, Personnage.taille, null);
                    }else if(p.isAttacking()){
                        g.drawImage(BanqueImage.gifArcherAttack.get(frameIndexGA).getImage(), p.getX(), p.getY(), Personnage.taille, Personnage.taille, null);
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
                            g.drawImage(BanqueImage.gifGuerrierWalk.get(frameIndexGA).getImage(), p.getX(), p.getY(), Personnage.taille, Personnage.taille, null);
                        }else if(p.isAttacking()){
                            g.drawImage(BanqueImage.gifGuerrierAttack.get(frameIndexGA).getImage(), p.getX(), p.getY(), Personnage.taille, Personnage.taille, null);
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
                    g.drawImage(BanqueImage.gifZombieWalk.get(frameIndexZ).getImage(), e.getX(), e.getY(), Personnage.taille, Personnage.taille, null);
                } else {
                    g.drawImage(BanqueImage.gifZombieWalk.get(5).getImage(), e.getX(), e.getY(), Personnage.taille, Personnage.taille, null);
                }
            }
        }
        }

    public void paintBatiments(Graphics g) {
        for (Batiment b : map.getBatiments()) {
            //g.drawImage(BanqueImage.imgNexus1,b.getX(),b.getY(),150,150,null);
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