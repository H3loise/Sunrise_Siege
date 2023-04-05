package Vue.ControllerView;

import Model.Map;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VillageoisController extends VueController {
    Map map;
    int pdv = 0;
    int pdvMax = 0;
    private JLabel nom;
    private JLabel hp;
    private JLabel txtExplicatif1;
    private JLabel txtExplicatif2;

    /**
     * Controleur du Villageois (layout de droite) qui permet de fournir les informations sur les villageois notamment celui selectionné
     * @param map
     */
    public VillageoisController(Map map) {
        super(map);
        this.map = map;
        nom = new JLabel("Villageois");
        setBackground(Color.gray);
        nom.setHorizontalAlignment(JLabel.CENTER);
        setLayout(new BorderLayout());
        add(nom, BorderLayout.NORTH);
        if (map.getActionner() != null) {
            pdv = map.getActionner().getHealth_points();
            pdvMax = map.getActionner().getHpMax();
        }
        hp = new JLabel("HP : " + pdv + "/" +  pdvMax );
        txtExplicatif1 = new JLabel("Ceci est un villageois, récoltez des ressource");
        txtExplicatif2 = new JLabel("pour remplir les stocks du royaume");
        JLabel Info = new JLabel("");
        JPanel contentPanel = new JPanel();
        contentPanel.setBackground(Color.lightGray);
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        contentPanel.add(hp);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        contentPanel.add(txtExplicatif1);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        contentPanel.add(txtExplicatif2);
        add(contentPanel, BorderLayout.CENTER);

        int delay = 100;
        Timer timer = new Timer(delay, new ActionListener() { //actualise les points de vie grâce à la fonction en dessous tout les 100ms
            @Override
            public void actionPerformed(ActionEvent e) {
                updateContent();
            }
        });
        timer.start();
    }

    /**
     * Permet d'actualiser les statistiques du Villageois selectionnée
     */
    private void updateContent() {
        if (map.getActionner() != null) {
            pdv = map.getActionner().getHealth_points();
            pdvMax = map.getActionner().getHpMax();
            hp.setText("HP : " + pdv + "/" + pdvMax);
        }
    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);
    }
}