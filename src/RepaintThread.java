import Model.Map;
import Vue.Affichage;

public class RepaintThread extends Thread{
    private Affichage aff;
    private Map m;
    private final int delai = 20;
    public RepaintThread(Affichage affichage,Map m){
        this.aff = affichage;
        this.m = m;
    }

    @Override
    public void run() {
        while(!m.testLoose()) {
            super.run();
            aff.repaint();
            try {
                sleep(delai);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
