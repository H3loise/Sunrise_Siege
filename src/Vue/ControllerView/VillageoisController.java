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




    public VillageoisController(Map map) {
        super(map);
        this.map = map;
        nom = new JLabel("Villageois");
        setBackground(Color.gray);
        nom.setHorizontalAlignment(JLabel.CENTER);
        setLayout(new BorderLayout());
        add(nom, BorderLayout.NORTH);
        if (map.getActionner() != null) {
            pdv = map.getActionner().getHealth_points()+1;
            pdvMax = map.getActionner().getHpMax();
        }
        hp = new JLabel("HP : " + pdv + "/" +  pdvMax );
        JPanel contentPanel = new JPanel();
        contentPanel.setBackground(Color.lightGray);
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        contentPanel.add(hp);
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

    private void updateContent() {

        if (map.getActionner() != null) {
            pdv = map.getActionner().getHealth_points();
            pdvMax = map.getActionner().getHpMax();
        }

    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);
    }
}
