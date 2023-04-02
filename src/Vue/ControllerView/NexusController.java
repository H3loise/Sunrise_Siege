package Vue.ControllerView;

import Model.Map;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NexusController extends VueController {
    private Map map;
    int pdv = 0;
    int pdvMax = 0;
    int level = 0;
    int attack = 0;
    private JLabel hp;
    private JLabel lvl;
    private JLabel atk;
    private JButton upgrade;
    private JButton acheterVillageois;
    private JButton healNexus;
    public NexusController(Map map) {
        super(map);
        this.map=map;
        pdvMax = map.getNexus().getPvMax();
        pdv = map.getNexus().getPv();
        level = map.getNexus().getLevel();
        attack = map.getNexus().getAttack();

        JLabel nom = new JLabel("Nexus");
        setBackground(Color.gray);
        nom.setHorizontalAlignment(JLabel.CENTER);
        setLayout(new BorderLayout());
        add(nom, BorderLayout.NORTH);

        upgrade = new JButton("Améliorer");
        hp = new JLabel("HP : " + pdv + "/" +  pdvMax );
        lvl = new JLabel("Niveau : " + level);
        atk = new JLabel( "Dégats des canons : "+ attack);
        acheterVillageois = new JButton("Acheter un Villageois");
        healNexus = new JButton("Réparer");
        JPanel contentPanel = new JPanel();
        contentPanel.setBackground(Color.lightGray);
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        contentPanel.add(hp);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        contentPanel.add(lvl);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        contentPanel.add(atk);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        contentPanel.add(upgrade);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        contentPanel.add(acheterVillageois);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        contentPanel.add(healNexus);
        add(contentPanel, BorderLayout.CENTER);

        int delay = 100;
        Timer timer = new Timer(delay, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateContent();
            }
        });
        timer.start();

        healNexus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Nexus soigné mon gâté");
                map.healingNexus();
            }
        });
        acheterVillageois.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Achete Villageois");
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
    private void updateContent() {
        pdv = map.getNexus().getPv();
        pdvMax = map.getNexus().getPvMax();
        level = map.getNexus().getLevel();
        attack = map.getNexus().getAttack();
        hp.setText("HP : " + pdv + "/" + pdvMax);
        lvl.setText("Niveau : " + level);
        atk.setText("Dégats des canons : " + attack);
        acheterVillageois.setVisible(map.getDay());
    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);
    }
}
