package Vue.ControllerView;

import Model.Map;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CaserneController extends VueController {
    private Map map;

    public CaserneController(Map map) {
        super(map);
        this.map=map;
        JButton upgrade = new JButton("UPGRADE");
        JButton acheterArchers = new JButton("acheter archers");
        JButton acheterGuerriers = new JButton("acheter guerriers");
        Label name = new Label();
        name.setText("Caserne");
        Label niveau = new Label();
        niveau.setText("Niveau : " + map.getCaserne().getLevel());

        this.add(name);
        this.add(niveau) ;
        this.add(upgrade);
        this.add(acheterArchers);
        this.add(acheterGuerriers);

        acheterArchers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Achete archer");
                map.acheterArcher();
            }
        });

        acheterGuerriers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Achete archer");
                map.acheterGuerrier();
            }
        });

        upgrade.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("change le nexus");
                map.upgradeCaserne();
            }
        });
    }


    @Override
    public void paint(Graphics g) {
        super.paint(g);
    }
}
