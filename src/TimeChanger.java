import Model.Map;

public class TimeChanger extends Thread{
    Map m;
    private final int delai = 5000;
    /**
     * Constructeur de la classe TimeChanger.
     * @param m de type Modele.Map
     */
    public TimeChanger(Map m){
        this.m=m;
    }
    /**
     * La méthode run permet le changement entre le jour et la nuit tout les "delais" temps.
     */
    @Override
    public void run() {
        try {
            sleep(delai);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while (true) {
            super.run();
            if (this.m.getDay()) {
                m.setDay(false);
            } else {
                m.setDay(true);
            }
            try {
                sleep(delai);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
