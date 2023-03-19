package Vue.ControllerView;

import Model.Map;

import javax.swing.*;
import java.awt.*;

public abstract class VueController extends JPanel{
    private Map map;


    public VueController(Map m){
        this.map=m;
        this.setPreferredSize(new Dimension(300,1000));
        this.setBackground(Color.GRAY);
    }


}
