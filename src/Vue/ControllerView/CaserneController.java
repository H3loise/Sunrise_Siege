package Vue.ControllerView;

import Model.Map;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CaserneController extends VueController {
    private Map map;
    int pdv = 0;
    int pdvMax = 0;
    int level = 0;
    private JLabel hp;
    private JLabel lvl;
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
        contentPanel.add(acheterArchers);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        contentPanel.add(acheterGuerriers);
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
                System.out.println("Achete archer");
                map.acheterArcher();
            }
        });

        acheterGuerriers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Achete Guerrier");
                map.acheterGuerrier();
            }
        });

        upgrade.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("change le Caserne");
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
        hp.setText("HP : " + pdv + "/" + pdvMax);
        lvl.setText("Niveau : " + level);
        acheterArchers.setVisible(!map.getDay());
        acheterGuerriers.setVisible(!map.getDay());
    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);
    }
}
