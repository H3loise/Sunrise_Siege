package Vue.ControllerView;

import Model.Map;
import Model.Personnages.Villageois;

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
    int n;
    private JLabel hp;
    private JLabel lvl;
    private JLabel atk;
    private JLabel upgradeRessource1;
    private JLabel upgradeRessource2;
    private JLabel repairRessource1;
    private JLabel repairRessource2;
    private JLabel villageoisRessource1;
    private JLabel villageoisRessource2;

    private JButton upgrade;
    private JButton acheterVillageois;
    private JButton healNexus;

    /**
     * Controleur du Nexus (layout de droite) qui permet de fournir les informations sur le Nexus lorsqu'il est selectionné
     * @param map
     */
    public NexusController(Map map) {
        super(map);
        this.map=map;
        pdvMax = map.getNexus().getPvMax();
        pdv = map.getNexus().getPv();
        level = map.getNexus().getLevel();
        attack = map.getNexus().getAttack();
        n = map.getNexus().getMinimumOfEach();

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
        upgradeRessource1 = new JLabel("Vous avez besoin de : " + n*map.getNexus().getLevel() + " bois, "+ n*map.getNexus().getLevel() +" pierre et " + n*map.getNexus().getLevel() +" blé");
        upgradeRessource2 = new JLabel("pour améliorer le nexus");
        repairRessource1 = new JLabel("Vous avez besoin de : " + n * (map.getNexus().getLevel()-1) + " bois, "+ n * (map.getNexus().getLevel()-1) +" pierre et " + n * (map.getNexus().getLevel()-1) +" blé");
        repairRessource2 = new JLabel("pour réparer le nexus");
        villageoisRessource1 = new JLabel("Vous avez besoin de : " + Villageois.woodPrice + " bois, "+ Villageois.stonePrice +" pierre et " + Villageois.wheatPrice +" blé");
        villageoisRessource2 = new JLabel("pour acheter un villageois");
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
        contentPanel.add(upgradeRessource1);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        contentPanel.add(upgradeRessource2);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        contentPanel.add(acheterVillageois);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        contentPanel.add(villageoisRessource1);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        contentPanel.add(villageoisRessource2);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        contentPanel.add(healNexus);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        contentPanel.add(repairRessource1);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        contentPanel.add(repairRessource2);

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

    /**
     * Permet d'actualiser les statitisques du Nexus lorsqu'il est selectionnée
     */
    private void updateContent() {
        pdv = map.getNexus().getPv();
        pdvMax = map.getNexus().getPvMax();
        level = map.getNexus().getLevel();
        attack = map.getNexus().getAttack();
        n = map.getNexus().getMinimumOfEach();
        hp.setText("HP : " + pdv + "/" + pdvMax);
        lvl.setText("Niveau : " + level);
        atk.setText("Dégats des canons : " + attack);
        upgradeRessource1.setText("Vous avez besoin de : " + n*map.getNexus().getLevel() + " bois, "+ n*map.getNexus().getLevel() +" pierre et " + n*map.getNexus().getLevel() +" blé");
        upgradeRessource2.setText("pour améliorer le nexus");
        if(map.getNexus().getLevel() ==1){
            repairRessource1.setText("Vous avez besoin de : 4 bois, 4 pierre et 4 blé");
        }else {
            repairRessource1.setText("Vous avez besoin de : " + n * (map.getNexus().getLevel() - 1) + " bois, " + n * (map.getNexus().getLevel() - 1) + " pierre et " + n * (map.getNexus().getLevel() - 1) + " blé");
        }
        repairRessource2.setText("pour réparer le nexus");
        villageoisRessource1.setText("Vous avez besoin de : " + Villageois.woodPrice + " bois, "+ Villageois.stonePrice +" pierre et " + Villageois.wheatPrice +" blé");
        villageoisRessource2.setText("pour acheter un villageois");
        acheterVillageois.setVisible(map.getDay());
    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);
    }
}