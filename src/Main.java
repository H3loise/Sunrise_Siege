import Controller.ActionPanel;
import Model.Map;
import Model.Personnages.Archer;
import Model.Personnages.Ennemy;
import Model.Personnages.Villageois;
import Model.ThreadWipeDeadCharacters;
import Model.TimeChanger;
import Vue.Affichage;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Map map = new Map();
        Thread.sleep(800);
        //Ennemy z = new Ennemy(700,900,map);
        Ennemy z1 = new Ennemy(100,100,map);
        //map.addEnnemy(z);
        //Archer arch = new Archer(300,100,map);
        //map.addCharacter(arch);
        //Guerrier guer = new Guerrier(400,100,map);
        //map.addCharacter(guer);
        //new ThreadScanEnnemies(map,arch).start();
        Villageois testDeplacement = new Villageois( 200,200,map);
        map.addCharacter(testDeplacement);
        Affichage affichage = new Affichage(map);
        ActionPanel actionPanel = new ActionPanel(map,affichage);
        affichage.addMouseListener(actionPanel);
        new TimeChanger(map).start();
        new ThreadWipeDeadCharacters(map).start();
        //System.out.println(Math.hypot(map.getNexus().getX(),));
    }
}
