package Vue;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe permettant de stocker les différentes images du jeu, elles sont en public static final donc accessibles depuis
 * toutes les autres classes, cependant elles ne pourront pas être modifiés
 * Chaque image est entouré d'un try catch, obligatoire car une erreur est possible.
 * Le path est depuis le src.
 */
public abstract class BanqueImage {


    /**
     * Ajout des GIFs
     */

    /**
     * GIF archer attaque
     */

    public static final List<ImageIcon> gifArcherAttack;

    static {
        try {
            // Charger les images du GIF
            gifArcherAttack = new ArrayList<>();
            for (int i = 1; i <= 23; i++) {
                ImageIcon gifFrame = new ImageIcon("src/GIF/archer_attaque/frame-" + i + ".gif");
                gifArcherAttack.add(gifFrame);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * GIF archer marche
     */

    public static final List<ImageIcon> gifArcherWalk;

    static {
        try {
            // Charger les images du GIF
            gifArcherWalk = new ArrayList<>();
            for (int i = 1; i <= 12; i++) {
                ImageIcon gifFrame = new ImageIcon("src/GIF/archer_marche/frame-" + i + ".gif");
                gifArcherWalk.add(gifFrame);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * GIF guerrier attaque
     */

    public static final List<ImageIcon> gifGuerrierAttack;

    static {
        try {
            // Charger les images du GIF
            gifGuerrierAttack = new ArrayList<>();
            for (int i = 1; i <= 9; i++) {
                ImageIcon gifFrame = new ImageIcon("src/GIF/guerrier_attaque/frame-" + i + ".gif");
                gifGuerrierAttack.add(gifFrame);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * GIF guerrier marche
     */

    public static final List<ImageIcon> gifGuerrierWalk;

    static {
        try {
            // Charger les images du GIF
            gifGuerrierWalk = new ArrayList<>();
            for (int i = 1; i <= 12; i++) {
                ImageIcon gifFrame = new ImageIcon("src/GIF/guerrier_marche/frame-" + i + ".gif");
                gifGuerrierWalk.add(gifFrame);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * GIF villageois marche
     */

    public static final List<ImageIcon> gifVillagerWalk;

    static {
        try {
            // Charger les images du GIF
            gifVillagerWalk = new ArrayList<>();
            for (int i = 1; i <= 8; i++) {
                ImageIcon gifFrame = new ImageIcon("src/GIF/villageois_marche/frame_" + i + ".gif");
                gifVillagerWalk.add(gifFrame);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * GIF ennemie marche
     */

    public static final List<ImageIcon> gifZombieWalk;

    static {
        try {
            // Charger les images du GIF
            gifZombieWalk = new ArrayList<>();
            for (int i = 0; i <= 11; i++) {
                ImageIcon gifFrame = new ImageIcon("src/GIF/zombie_marche/frame_" + i + ".gif");
                gifZombieWalk.add(gifFrame);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Image du layout ressource
     */

    public final  static BufferedImage imgFondRessources;

    static {
        try {
            imgFondRessources = ImageIO.read(new File("src/Images/fond_jeu.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Image des ressources
     */
    public  final static BufferedImage imgWheat;

    static {
        try {
            imgWheat = ImageIO.read(new File("src/Images/wheat.jpg"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public final  static BufferedImage imgWood;

    static {
        try {
            imgWood = ImageIO.read(new File("src/Images/bois.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public final  static BufferedImage imgStone;

    static {
        try {
            imgStone = ImageIO.read(new File("src/Images/stone.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Image d'arbre de différente tailles
     */
    public final  static BufferedImage imgTree1;

    static {
        try {
            imgTree1 = ImageIO.read(new File("src/Images/tree1.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public final  static BufferedImage imgTree2;

    static {
        try {
            imgTree2 = ImageIO.read(new File("src/Images/tree2.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public final  static BufferedImage imgTree3;

    static {
        try {
            imgTree3 = ImageIO.read(new File("src/Images/tree3.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Image de rocher de différente tailles
     */
    public final  static BufferedImage imgRock1;

    static {
        try {
            imgRock1 = ImageIO.read(new File("src/Images/rock1.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public final  static BufferedImage imgRock2;

    static {
        try {
            imgRock2 = ImageIO.read(new File("src/Images/rock2.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public final  static BufferedImage imgRock3;

    static {
        try {
            imgRock3 = ImageIO.read(new File("src/Images/rock3.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Image de champs de blé de différente tailles
     */

    public  final static BufferedImage imgWheat_Field1;

    static {
        try {
            imgWheat_Field1 = ImageIO.read(new File("src/Images/wheatfield1.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public  final static BufferedImage imgWheat_Field2;

    static {
        try {
            imgWheat_Field2 = ImageIO.read(new File("src/Images/wheatfield2.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public  final static BufferedImage imgWheat_Field3;

    static {
        try {
            imgWheat_Field3 = ImageIO.read(new File("src/Images/wheatfield3.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    /**
     * Image du chateau de différente tailles
     */
    public final  static BufferedImage imgNexus1;

    static {
        try {
            imgNexus1 = ImageIO.read(new File("src/Images/nexus1.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public final  static BufferedImage imgNexus2;

    static {
        try {
            imgNexus2 = ImageIO.read(new File("src/Images/nexus2.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public final  static BufferedImage imgNexus3;

    static {
        try {
            imgNexus3 = ImageIO.read(new File("src/Images/nexus3.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Image du background
     */
    public final  static BufferedImage imgBackground;

    static {
        try {
            imgBackground = ImageIO.read(new File("src/Images/background.jpg"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Image des personnages
     */

    public final  static BufferedImage imgVillageois;

    static {
        try {
            imgVillageois = ImageIO.read(new File("src/Images/villageois.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public final  static BufferedImage imgArcher;

    static {
        try {
            imgArcher = ImageIO.read(new File("src/Images/archer.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public final  static BufferedImage imgGuerrier;

    static {
        try {
            imgGuerrier = ImageIO.read(new File("src/Images/guerrier.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public final static BufferedImage imgEnnemy;

    static {
        try {
            imgEnnemy = ImageIO.read(new File("src/Images/ennemy.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Image du Soleil et de la Lune
     */

    public final  static BufferedImage imgSoleil;

    static {
        try {
            imgSoleil = ImageIO.read(new File("src/Images/soleil.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public final  static BufferedImage imgLune;

    static {
        try {
            imgLune = ImageIO.read(new File("src/Images/lune.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}