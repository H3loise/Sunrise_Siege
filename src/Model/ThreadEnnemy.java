package Model;

import Model.Personnages.Ennemy;

public class ThreadEnnemy extends Thread{

    private final Map map;
    private Ennemy ennemi;

    public ThreadEnnemy(Map map, Ennemy ennemi){
        this.map = map;
    }

    @Override
    public void run() {
    }
}
