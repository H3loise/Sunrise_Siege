package Vue.ControllerView;

import Model.Map;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EnnemyController extends VueController {

    Map map;
    int pdv = 0;
    int pdvMax = 0;
    int attack = 0;
    private JLabel nom;
    private JLabel hp;
    private JLabel atk;
    /**
     * Controleur des Ennemies (layout de droite) qui permet de fournir les informations sur les ennemies notamment celui selectionné
     * @param map
     */
    public EnnemyController(Map map) {
        super(map);
        this.map = map;

        nom = new JLabel("Guerrier");
        setBackground(Color.gray);
        nom.setHorizontalAlignment(JLabel.CENTER);
        setLayout(new BorderLayout());
        add(nom, BorderLayout.NORTH);

        if (map.getActionner() != null) {
            pdv = map.getActionner().getHealth_points()+1;
            pdvMax = map.getActionner().getHpMax();
        }
        hp = new JLabel("HP : " + pdv + "/" +  pdvMax );
        atk = new JLabel("Dégat : " + atk);
        JLabel descr = new JLabel("Doit détruire le nexus mais peut etre arrêté\n pour combattre des guerriers/archer avec des attaques de mélée");
        JPanel contentPanel = new JPanel();
        contentPanel.setBackground(Color.lightGray);
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        contentPanel.add(hp);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        contentPanel.add(atk);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        contentPanel.add(descr);
        add(contentPanel, BorderLayout.CENTER);

        int delay = 100;
        Timer timer = new Timer(delay, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateContent();
            }
        });
        timer.start();
    }

    /**
     * Permet d'actualiser les statistiques de l'ennemie selectionnée
     */
    private void updateContent() {
        if (map.getActionner() != null) {
            pdv = map.getActionner().getHealth_points();
            pdvMax = map.getActionner().getHpMax();
            attack = map.getActionner().getAttack_points();
        }
    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);
    }
}