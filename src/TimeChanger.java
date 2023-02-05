

public class TimeChanger extends Thread{
    Map m;
    private final int delai = 60000;
    public TimeChanger(Map m){
        this.m=m;
    }

    @Override
    public void run() {
        super.run();
        if(this.m.getDay()){
            m.setDay(false);
        }else{
            m.setDay(true);
        }
        try {
            sleep(delai);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }                                      
}
