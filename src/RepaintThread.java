import Vue.Affichage;

public class RepaintThread extends Thread{
    Affichage aff;
    private final int delai = 20;
    /**
     * Constructeur de la classe RepaintThread.
     * @param aff de type Vue.Affichage
     */
    public RepaintThread(Affichage aff){
        this.aff = aff;
    }
    /**
     * La méthode run permet l'actualisation du jeu en permanence ce qui donne une impression de fluidité.
     */
    @Override
    public void run() {
        while (true) {
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