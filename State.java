
public interface State {

    public void changeVitesse(Voiture v);

    public String getEtat();

    public void dimCarburant(Voiture v);

    public boolean onPeutAccelererEtat(Voiture v);

    public boolean onPeutRalentirEtat(Voiture v);

    public int getDimCarburant();

    public default void FonctionAvancer(Voiture v) {
        var game = Core_game.getInstanceCoreGame();
        changeVitesse(v);
        var coordActuel = v.getACoordonnees();
        var nextCoord = game.getPaysage().getNextCoord(coordActuel, v.getVitesse(), v);
        v.setActualCoordonnees(nextCoord);
    }

    public String getInfo();

    public default boolean casExtreme() { //boost et stopped
        return false;
    }

    public default boolean depenseCarbu() {
        return true;
    }
}
