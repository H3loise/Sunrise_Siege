package Vue.ControllerView;

import Model.Map;

import javax.swing.*;
import java.awt.*;

public class NoneController extends VueController {


    public NoneController(Map map) {
        super(map);

        JLabel nom = new JLabel("Rien");
        setBackground(Color.gray);
        nom.setHorizontalAlignment(JLabel.CENTER);
        setLayout(new BorderLayout());
        add(nom, BorderLayout.NORTH);

        JLabel txt1 = new JLabel("Il n'y a rien à faire ici.");
        JLabel txt2 = new JLabel("Occupe toi plutôt du village !");
        JPanel contentPanel = new JPanel();
        contentPanel.setBackground(Color.lightGray);

        contentPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        contentPanel.add(txt1, gbc);

        gbc.gridy = 1;
        contentPanel.add(txt2, gbc);

        add(contentPanel, BorderLayout.CENTER);

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
    }
}
