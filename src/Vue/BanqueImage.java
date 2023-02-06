package Vue;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Classe permettant de stocker les différentes images du jeu, elles sont en public static final donc accessibles depuis
 * toutes les autres classes, cependant elles ne pourront pas être modifiés
 * Chaque image est entouré d'un try catch, obligatoire car une erreur est possible.
 * Le path est depuis le src.
 */
public abstract class BanqueImage {
    public  final static BufferedImage imgWheat;

    static {
        try {
            imgWheat = ImageIO.read(new File("src/Images/wheat.png"));
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
    public final  static BufferedImage imgFondRessources;

    static {
        try {
            imgFondRessources = ImageIO.read(new File("src/Images/fond_jeu.jpg"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
