package Vue.Controller;

import Model.Map;

import javax.swing.*;
import java.awt.*;

public abstract class VueController extends JPanel {
    private final Map map;
    private final int hauteur = Map.taille;
    private final int longueur = 300;


    public VueController(Map map){
        this.map = map;
        setBackground(Color.GRAY);
        setPreferredSize(new Dimension(longueur,hauteur));
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
    }

    public void type(){
        if (this instanceof GuerrierController){
            System.out.println("controller guerrier");
        }else{
            if(this instanceof ArcherController){
                System.out.println("controller archer");
            }else{
                System.out.println("controller none");
            }
        }
    }
}
