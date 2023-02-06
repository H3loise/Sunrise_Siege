package Vue.Infos;

import Model.Personnages.Personnage;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class VueInfosPersos extends JPanel {

    Personnage personnage;

    public VueInfosPersos(Personnage personnage){
        this.personnage = personnage;
    }

    @Override
    public void paint(Graphics g){
        try {
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/ringbearer.ttf")).deriveFont(18f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(customFont);
            g.setFont(customFont);
            g.drawString(this.personnage.toString(),10,10);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
    }
}
