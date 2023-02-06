package Vue.Infos;

import Model.Personnages.Personnage;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class VueInfosPersos extends JPanel {

    private Personnage personnage;

    public VueInfosPersos(Personnage personnage){
        this.personnage = personnage;
    }

    private void drawString(Graphics g, String text, int x, int y) {
        for (String line : text.split("\n"))
            g.drawString(line, x, y += g.getFontMetrics().getHeight());
    }

    @Override
    public void paint(Graphics g){
        try {
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/ringbearer.ttf")).deriveFont(18f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(customFont);
            g.setFont(customFont);
            drawString(g,this.personnage.toString(),50,20);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
    }
}
