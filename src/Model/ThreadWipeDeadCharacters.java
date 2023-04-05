package Model;

public class ThreadWipeDeadCharacters extends Thread{
    private final Map map;
    private final int cooldown = 100;

    public ThreadWipeDeadCharacters(Map map){
        this.map = map;
    }

    /**
     * Thread permettant d'effacer les monstres morts, pour qu'il disparaissent du jeu
     */
    @Override
    public void run() {
        while(!map.testLoose()) {
            map.eraseDeadPeople();
            map.eraseMonsters();
            try {
                sleep(cooldown);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
