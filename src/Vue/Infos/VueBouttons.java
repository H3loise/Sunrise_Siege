package Vue.Infos;

import javax.swing.*;
import java.awt.*;

public class VueBouttons extends JPanel {

    private final JButton addArcher = new JButton("Archer");
    private final JButton addGuerrier = new JButton("Guerrier");;
    private final JButton addVillageois = new JButton("Villageois");
    public VueBouttons(){
        this.setLayout(new FlowLayout());
        this.add(addArcher);
        this.add(addGuerrier);
        this.add(addVillageois);
    }
}
