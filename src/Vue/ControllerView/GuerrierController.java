package Vue.ControllerView;

import Model.Map;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuerrierController extends VueController {


    public GuerrierController(Map map) {
        super(map);
        JLabel texte = new JLabel("Guerrier");
        //JLabel hp = new JLabel("HP : " + pdv);
        JButton attaquer = new JButton("ATTAQUER");
        JButton deplacement = new JButton("DÉPLACEMENT");
        JButton upgrade = new JButton("Ameliorer");

        this.add(upgrade);
        this.add(texte);
        this.add(attaquer);
        this.add(deplacement);
        upgrade.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Achete archer");
                //map.upgradeGuerrier(Le guerrier sur lequel j'ai cliqué);
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
    }
}