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
        int pdv = map.getCaserne().getPvMax();
        int level = map.getCaserne().getLevel();
        JButton upgrade = new JButton("UPGRADE");
        JLabel hp = new JLabel("HP : " + pdv);
        JLabel lvl = new JLabel("Niveau : " + level);
        JButton acheterVillageois = new JButton("acheter villageois");
        JButton acheterArchers = new JButton("acheter archers");
        JButton acheterGuerriers = new JButton("acheter guerriers");
        JButton healNexus = new JButton("heal Nexus");
        this.add(upgrade);
        this.add(hp);
        this.add(lvl);
        this.add(acheterArchers);
        this.add(acheterGuerriers);
        this.add(acheterVillageois);
        this.add(healNexus);

        acheterArchers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Achete archer");
                map.acheterArcher();
            }
        });
        healNexus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Nexus soigné mon gâté");
                map.healingNexus();
            }
        });
        acheterGuerriers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Achete archer");
                map.acheterGuerrier();
            }
        });
        acheterVillageois.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Achete archer");
                map.acheterVillageois();
            }
        });
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
