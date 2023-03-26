package Model;

public class TimeChanger extends Thread{
    Map m;
    private final int delai = 50000;
    public TimeChanger(Map m){
        this.m=m;
    }

    @Override
    public void run() {
        super.run();
        System.out.println(m.getNexus().toString());
        while (!m.testLoose()) {

            if (this.m.getDay()) {
                m.setDay(false);
                m.update();
            } else {
                m.setDay(true);
                m.update();


            }
            try {
                sleep(delai);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
