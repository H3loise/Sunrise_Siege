package Model.Batiments;

/**
 * Cette classe permet de stocker nos Archer/Guerrier la nuit et de les améliorer.
 * Elle permet aussi d'acheter des archers/guerriers.
 * Les fonctions liées à l'achat/l'amélioration d'unités sont @Modele.Map.upgradeCaserne(), @Modele.Map.acheterGuerrier() et @Modele.Map.acheterArcher()
 */
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

    /**
     * Méthode permettant l'upgrade de la caserne, ses pvMax augmentent bien que ils soient inutiles,
     * Le level est augmenté, ce qui permet d'augmenter le niveau des guerriers/archer en fonction dans
     *  @Modele.Map.acheterGuerrier() et @Modele.Map.acheterArcher()
     */
    public void upgrade(){
        setLevel(getLevel()+1);
        setPvMax(pvMax+100);
        setPv(pvMax);
    }

}
