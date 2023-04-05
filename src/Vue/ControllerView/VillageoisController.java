package Vue.ControllerView;

import Model.Map;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class VillageoisController extends VueController {

    int test = 0;
    public VillageoisController(Map map) {
        super(map);
        JLabel texte = new JLabel("Villageois");
        this.add(texte);
    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);
    }
}
