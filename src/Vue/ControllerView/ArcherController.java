package Vue.ControllerView;

import Model.Map;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class ArcherController extends VueController {

    Map map;
    int pdv = 0;
    int pdvMax = 0;
    int attack = 0;
    Integer hpstring;
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
        System.out.println("archer controller : "+ map.getActionner());
        //this.hpstring = this.map.getActionner().getHealth_points();

        nom = new JLabel("Archer");
        setBackground(Color.gray);
        nom.setHorizontalAlignment(JLabel.CENTER);
        setLayout(new BorderLayout());
        //add(nom, BorderLayout.NORTH);

        if (map.getActionner() != null) {
            pdv = map.getActionner().getHealth_points()+1;
            pdvMax = map.getActionner().getHpMax();
        }
        this.hp = new JLabel("HP : " + this.hpstring);
        atk = new JLabel("Dégat : " + atk);
        JLabel descr = new JLabel("Guerrier pouvant attaquer des monstres avec des attaques de mélée");

        JPanel contentPanel = new JPanel();
        contentPanel.setBackground(Color.lightGray);
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        contentPanel.add(hp);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        contentPanel.add(atk);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        //contentPanel.add(descr);
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
        }

    }
    private void drawString(Graphics g, String text, int x, int y) {
        for (String line : text.split("\n"))
            g.drawString(line, x, y += g.getFontMetrics().getHeight()+10);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        /*try {
            Font titleCustomFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/ringbearer.ttf")).deriveFont(Font.BOLD,18f);
            Font classicCustomFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/ringbearer.ttf")).deriveFont(18f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(titleCustomFont);
            g.setFont(titleCustomFont);
            drawString(g,"ARCHER", 100,10);
            ge.registerFont(classicCustomFont);
            g.setFont(classicCustomFont);
            drawString(g,"Points d'attaque : \nPoints de vie : \nDistance d'attaque : \nType : unité d'attaque à distance",0,50);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }*/
    }
}