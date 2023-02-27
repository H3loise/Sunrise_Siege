import Model.Map;

public class TimeChanger extends Thread{
    Map m;
    private final int delai = 400;
    public TimeChanger(Map m){
        this.m=m;
    }

    @Override
    public void run() {
        super.run();
        while (!m.testLoose()) {
            if (this.m.getDay()) {
                m.setDay(false);
            } else {
                m.update();
                m.upScore();

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
