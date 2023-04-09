package Vue.ControllerView;

import Model.Map;
import Model.Personnages.Archer;
import Model.Personnages.Guerrier;
import Model.Personnages.Villageois;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CaserneController extends VueController {
    private int pdv = 0;
    private int pdvMax = 0;
    private int level = 0;
    private int n;
    private JLabel hp;
    private JLabel lvl;
    private JLabel txtExplicatif1;
    private JLabel txtExplicatif2;
    private JLabel upgradeRessource1;
    private JLabel upgradeRessource2;
    private JLabel archerRessource1;
    private JLabel archerRessource2;
    private JLabel guerrierRessource1;
    private JLabel guerrierRessource2;
    private JButton acheterArchers;
    private JButton acheterGuerriers;

    /**
     * Controleur de la Caserne (layout de droite) qui permet de fournir les informations sur la Caserne lorsqu'elle est selectionné
     * @param map
     */    public CaserneController(Map map) {
        super(map);
        this.map=map;
        pdv = map.getCaserne().getPv();
        pdvMax = map.getCaserne().getPvMax();
        level = map.getCaserne().getLevel();
        n = map.getCaserne().getMinimumOfEach();


        JLabel nom = new JLabel("Caserne");
        setBackground(Color.gray);
        nom.setHorizontalAlignment(JLabel.CENTER);
        setLayout(new BorderLayout());
        add(nom, BorderLayout.NORTH);

        JButton upgrade = new JButton("Améliorer");
        hp = new JLabel("HP : " + pdv +"/"+ pdvMax);
        lvl = new JLabel("Niveau des troupes : " + level);
        acheterArchers = new JButton("Acheter un archer");
        acheterGuerriers = new JButton("Acheter un guerrier");
        txtExplicatif1 = new JLabel("Ceci est une caserne, recrutez des archer");
        txtExplicatif2 = new JLabel("ou des guerriers pour défendre le royaume");
        upgradeRessource1 = new JLabel("Vous avez besoin de : " + n*map.getNexus().getLevel() + " bois, "+ n*map.getNexus().getLevel() +" pierre et " + n*map.getNexus().getLevel() +" blé");
        upgradeRessource2 = new JLabel("pour améliorer le nexus");
        archerRessource1 = new JLabel("Vous avez besoin de : " + Archer.woodPrice + " bois, "+ Archer.stonePrice +" pierre et " + Archer.wheatPrice +" blé");
        archerRessource2 = new JLabel("pour acheter un Archer");
        guerrierRessource1 = new JLabel("Vous avez besoin de : " + Guerrier.woodPrice + " bois, "+ Guerrier.stonePrice +" pierre et " + Guerrier.wheatPrice +" blé");
        guerrierRessource2 = new JLabel("pour acheter un Guerrier");
        Label name = new Label();
        Label niveau = new Label();
        JPanel contentPanel = new JPanel();
        contentPanel.setBackground(Color.lightGray);
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        contentPanel.add(hp);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        contentPanel.add(lvl);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        contentPanel.add(upgrade);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        contentPanel.add(upgradeRessource1);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        contentPanel.add(upgradeRessource2);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        contentPanel.add(acheterArchers);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        contentPanel.add(archerRessource1);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        contentPanel.add(archerRessource2);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        contentPanel.add(acheterGuerriers);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        contentPanel.add(guerrierRessource1);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        contentPanel.add(guerrierRessource2);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        contentPanel.add(txtExplicatif1);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        contentPanel.add(txtExplicatif2);
        add(contentPanel, BorderLayout.CENTER);

        int delay = 100;
        Timer timer = new Timer(delay, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateContent();
            }
        });
        timer.start();

        acheterArchers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                map.acheterArcher();
            }
        });

        acheterGuerriers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                map.acheterGuerrier();
            }
        });

        upgrade.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                map.upgradeCaserne();
            }
        });
    }

    /**
     * Permet d'actualiser les statitisques du Caserne lorsqu'il est selectionnée
     */
    private void updateContent() {
        pdv = map.getCaserne().getPv();
        pdvMax = map.getCaserne().getPvMax();
        level = map.getCaserne().getLevel();
        n = map.getCaserne().getMinimumOfEach();
        hp.setText("HP : " + pdv + "/" + pdvMax);
        lvl.setText("Niveau : " + level);
        upgradeRessource1.setText("Vous avez besoin de : " + n*map.getCaserne().getLevel() + " bois, "+ n*map.getCaserne().getLevel() +" pierre et " + n*map.getCaserne().getLevel() +" blé");
        upgradeRessource2.setText("pour améliorer la caserne");
        archerRessource1.setText("Vous avez besoin de : " + Archer.woodPrice + " bois, "+ Archer.stonePrice +" pierre et " + Archer.wheatPrice +" blé");
        archerRessource2.setText("pour acheter un Archer");
        guerrierRessource1.setText("Vous avez besoin de : " + Guerrier.woodPrice + " bois, "+ Guerrier.stonePrice +" pierre et " + Guerrier.wheatPrice +" blé");
        guerrierRessource2.setText("pour acheter un Guerrier");
        acheterArchers.setVisible(!map.getDay());
        acheterGuerriers.setVisible(!map.getDay());
    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);
    }
}