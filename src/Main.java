import Controller.ActionPanel;
import Model.Map;
import Model.Personnages.Archer;
import Model.Personnages.Ennemy;
import Model.Personnages.Guerrier;
import Model.Personnages.Villageois;
import Model.ThreadWipeDeadCharacters;
import Model.TimeChanger;
import Vue.Affichage;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Map map = new Map();
        Thread.sleep(800);
        Ennemy z = new Ennemy(700,900);
        Ennemy z1 = new Ennemy(100,10);
        map.addEnnemy(z);
        Archer arch = new Archer(300,100);
        map.addCharacter(arch);
        Guerrier guer = new Guerrier(400,100);
        map.addCharacter(guer);
        Villageois testDeplacement = new Villageois( 200,200);
        map.addCharacter(testDeplacement);
        Affichage affichage = new Affichage(map);
        ActionPanel actionPanel = new ActionPanel(map,affichage);
        affichage.addMouseListener(actionPanel);
        new TimeChanger(map).start();
        new ThreadWipeDeadCharacters(map).start();
    }
}
