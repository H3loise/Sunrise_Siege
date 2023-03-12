package Vue;

public class ThreadAfficheur extends Thread{

    private Affichage aff;

    public ThreadAfficheur(Affichage a){
        this.aff=a;
    }

    @Override
    public void run() {
        super.run();
        while(true) {
            aff.repaint();
            try {
                sleep(40);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
