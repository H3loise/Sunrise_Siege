package Vue.ControllerView;

import Model.Map;
import Model.Personnages.Archer;
import Model.Personnages.Personnage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ArcherController extends VueController {

    Map map;
    int pdv = 0;
    int pdvMax = 0;
    int attack = 0;
    private JLabel nom;
    private JLabel hp;
    private JLabel atk;
    /**
     * Controleur de l'Archer (layout de droite) qui permet de fournir les informations sur les archers notamment celui selectionné
     * @param map
     */
    public ArcherController(Map map) {
        super(map);
        this.map = map;

        nom = new JLabel("Archer");
        setBackground(Color.gray);
        nom.setHorizontalAlignment(JLabel.CENTER);
        setLayout(new BorderLayout());
        add(nom, BorderLayout.NORTH);
        JPanel contentPanel = new JPanel();
        contentPanel.setBackground(Color.lightGray);
        if (map.getActionner() != null) {
            pdv = map.getActionner().getHealth_points();
            pdvMax = map.getActionner().getHpMax();
            System.out.println("aaaaaaaaaaaaaaaaaaaaaaaa3" + pdv);
            System.out.println("aaaaaaaaaaaaaaaaaaaaaaaa3" + pdvMax);
        }
        hp = new JLabel("HP : " + pdv + "/" +  pdvMax );
        atk = new JLabel("Dégat : " + attack);
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        contentPanel.add(hp);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        contentPanel.add(atk);
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
     * Permet d'actualiser les statistiques de l'archer selectionnée
     */
    private void updateContent() {
        if (map.getActionner() != null) {
            pdv = map.getActionner().getHealth_points();
            pdvMax = map.getActionner().getHpMax();
            attack = map.getActionner().getAttack_points();
            hp.setText("HP : " + pdv + "/" + pdvMax);
            atk.setText("Dégats : " + attack);
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
    }
}