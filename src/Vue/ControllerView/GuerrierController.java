package Vue.ControllerView;

import Model.Map;

import javax.swing.*;
import java.awt.*;

public class GuerrierController extends VueController {


    public GuerrierController(Map map) {
        super(map);
        JLabel texte = new JLabel("Guerrier");
        JButton attaquer = new JButton("ATTAQUER");
        JButton deplacement = new JButton("DÉPLACEMENT");
        this.add(texte);
        this.add(attaquer);
        this.add(deplacement);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
    }
}