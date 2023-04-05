package Model.Batiments;

public class Caserne extends Batiment{
    public int getMinimumOfEach() {
        return minimumOfEach;
    }

    private final int minimumOfEach = 10;
    /**
     * Le niveau d'un batiment est à 1 lors de sa création, on peut l'augmenter avec la sous_fonction cheat.
     *
     * @param x
     * @param y
     * @return Batiment
     */
    public Caserne(int x, int y) {
        super(x, y);
        this.setPv(this.pvMax);
    }
    public void upgrade(){
        setLevel(getLevel()+1);
        setPvMax(pvMax+100);
        setPv(pvMax);
    }

}
