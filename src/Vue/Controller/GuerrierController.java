package Vue.Controller;

import Model.Map;

import javax.swing.*;
import java.awt.*;

public class GuerrierController extends VueController {


    public GuerrierController(Map map) {
        super(map);
        JButton attaquer = new JButton("ATTAQUER");
        this.add(attaquer);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
    }
}