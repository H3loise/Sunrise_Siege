package Vue.ControllerView;

import Model.Map;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NexusController extends VueController {
    private Map map;

    public NexusController(Map map) {
        super(map);
        this.map=map;
        JButton upgrade = new JButton("UPGRADE");
        JButton acheterVillageois = new JButton("acheter villageois");
        JButton acheterArchers = new JButton("acheter archers");
        JButton acheterGuerriers = new JButton("acheter guerriers");
        this.add(upgrade);
        this.add(acheterArchers);
        this.add(acheterGuerriers);
        this.add(acheterVillageois);
        upgrade.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("change le nexus");
                map.upgradeNexus();
            }
        });
    }


    @Override
    public void paint(Graphics g) {
        super.paint(g);
    }
}
