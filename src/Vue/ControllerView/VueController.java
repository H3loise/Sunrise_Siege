package Vue.ControllerView;

import Model.Map;

import javax.swing.*;
import java.awt.*;

public abstract class VueController extends JPanel{
    protected Map map;


    public VueController(Map m){
        this.map=m;
        this.setPreferredSize(new Dimension(300,1030));
        this.setBackground(Color.GRAY);
    }


}
