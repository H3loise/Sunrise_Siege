package Model;

import Model.Personnages.Ennemy;

public class ThreadAttack extends Thread{
    private Map map;
    private int x;
    private int y;
    private final int cooldown = 300;

    public ThreadAttack(Map m){
        this.map = m;
        this.x = m.getNexus().getX();
        this.y = m.getNexus().getY();
    }

    @Override
    public void run() {
        Ennemy target = null;
        while (map.getNexus().getPv()>0) {

            double min = map.getNexus().getRange();
            for (Ennemy p :
                    map.getEnnemies()) {
                if (Math.abs(p.getX() - x) + Math.abs(p.getY() - y)<= min) {
                    target = p;
                }
            }
            if(target !=null){
                target.attackedPersonnage(map.getNexus().getAttack());
            }
            try {
                sleep(cooldown);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }


}
