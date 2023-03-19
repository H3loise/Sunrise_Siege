package Vue.ControllerView;

import Model.Map;

import javax.swing.*;
import java.awt.*;

public class VillageoisController extends VueController {


    public VillageoisController(Map map) {
        super(map);
        JLabel texte = new JLabel("Villageois");
        JButton recolter = new JButton("RÉCOLTER");
        JButton deplacement = new JButton("DÉPLACEMENT");
        this.add(texte);
        this.add(recolter);
        this.add(deplacement);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
    }
}
