package Vue;

public class ThreadAfficheur extends Thread{
    VueJeu vue;

    public ThreadAfficheur(VueJeu v){
        this.vue=v;
    }

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
