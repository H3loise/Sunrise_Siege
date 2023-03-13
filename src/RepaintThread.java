import Model.Map;
import Vue.Affichage;
import Vue.Controller.VueController;
import Vue.VueJeu;
import Vue.VueRessources;

public class RepaintThread extends Thread{
    private VueJeu vueJeu;
    private VueRessources vueRessources;
    private Map m;
    //private final int delai = 20;
    private final int delai=1000;
    public RepaintThread(VueJeu vj, VueRessources vr,Map m){
        //this.aff = affichage;
        this.vueJeu=vj;
        this.vueRessources=vr;
        this.m = m;
    }

    @Override
    public void run() {
        while(!m.testLoose()) {
            super.run();
            System.out.println("thd");
            System.out.println("the");
            vueRessources.repaint();
            vueJeu.repaint();
            try {
                sleep(delai);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}