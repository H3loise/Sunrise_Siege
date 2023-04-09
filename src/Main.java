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
        Villageois testDeplacement = new Villageois( 200,200);
        map.addCharacter(testDeplacement);
        Affichage affichage = new Affichage(map);
        ActionPanel actionPanel = new ActionPanel(map,affichage);
        affichage.addMouseListener(actionPanel);
        new TimeChanger(map).start();
        new ThreadWipeDeadCharacters(map).start();
    }
}
