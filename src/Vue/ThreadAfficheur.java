package Vue;

public class ThreadAfficheur extends Thread{
    VueJeu vue;
    private Thread gifThread;

    public ThreadAfficheur(VueJeu v){
        this.vue=v;
    }

    /**
     * Permet de repaint le jeu en boucle
     */
    public void run() {
        while(true){
            vue.repaint();
            try {
                sleep(20);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
