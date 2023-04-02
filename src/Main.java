import Controller.ActionPanel;
import Model.Map;
import Model.Personnages.Archer;
import Model.Personnages.Ennemy;
import Model.ThreadScanEnnemies;
import Model.Personnages.Villageois;
import Model.ThreadWipeDeadCharacters;
import Model.TimeChanger;
import Vue.Affichage;

public class Main {
    public static void main(String[] args) {
        Map map = new Map();
        /**JFrame fenetre=new JFrame("Sunrise Siege");
        //fenetre.setPreferredSize(new Dimension(1500,1000));
        VueJeu vueJeu=new VueJeu(map);
        ArcherController archerController= new ArcherController(map);
        fenetre.add(vueJeu,BorderLayout.CENTER);
        fenetre.add(archerController,BorderLayout.EAST);
        //new ThreadAfficheur(vueJeu).start();
        //vueJeu.repaint();
        //new TimeChanger(map).start();
        fenetre.setPreferredSize(new Dimension(1300,1000));
        fenetre.pack();
        fenetre.setVisible(true);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         **/
        Ennemy z = new Ennemy(100,100);
        map.addEnnemy(z);
        Archer arch = new Archer(300,100);
        map.addCharacter(arch);
        new ThreadScanEnnemies(map,arch).start();
        Villageois testDeplacement = new Villageois( 200,200);
        map.addCharacter(testDeplacement);
        Affichage affichage = new Affichage(map);
        ActionPanel actionPanel = new ActionPanel(map,affichage);
        affichage.addMouseListener(actionPanel);
        new TimeChanger(map).start();
        new ThreadWipeDeadCharacters(map).start();
    }
}
