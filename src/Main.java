import Controller.ActionPanel;
import Model.Map;
import Model.TimeChanger;
import Vue.Affichage;
import Model.Personnages.Guerrier;

public class Main {
    public static void main(String[] args) {
        Map map = new Map();
        Guerrier testDeplacement = new Guerrier( 200,200);
        map.addCharacter(testDeplacement);
        Affichage affichage = new Affichage(map);
        ActionPanel actionPanel = new ActionPanel(map,affichage);
        affichage.addMouseListener(actionPanel);
        new TimeChanger(map).start();
    }
}
