package Vue.ControllerView;

import Model.Map;

import javax.swing.*;
import java.awt.*;

public class NoneController extends VueController {


    public NoneController(Map map) {
        super(map);
        JLabel text = new JLabel("None");
        text.setLocation(125,150);
        this.add(text);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
    }
}
