package Modele;

public class TimeChanger extends Thread{
    Map m;

    public TimeChanger(Map m){
        this.m=m;
    }

    @Override
    public void run() {
        super.run();
        if(m.getDay()){
            m.setDay(false);
        }else{
            m.setDay(true);
        }
        try {
            sleep(60000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
