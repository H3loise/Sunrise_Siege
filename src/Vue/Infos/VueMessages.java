package Vue.Infos;

import Model.Personnages.Personnage;

import javax.swing.*;
import java.awt.*;

public class VueMessages extends JPanel {
    private int y = 10;


    private void drawString(Graphics g, String text, int x, int y) {
        for (String line : text.split("\n"))
            g.drawString(line, x, y += g.getFontMetrics().getHeight());
    }

    private void drawBackground(Graphics g){
        g.setColor(Color.decode("#7f7169"));
        g.fillRect(10,10,500,500);
    }

    private void drawDeadString(Graphics g, Personnage p){
        g.drawString("fzazqefqzgfzqgzqgqzrgfqs",10,100);
        this.drawBackground(g);
    }

    @Override
    public void paint(Graphics g) {
        this.drawBackground(g);
    }
}
