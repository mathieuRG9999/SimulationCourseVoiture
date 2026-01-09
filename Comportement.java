
public interface Comportement {

    public abstract void avancer(boolean utiliseEssence);

    public abstract void reculer(boolean utiliseEssence);

    public abstract boolean onPeutRalentirEtat();

    public abstract boolean onPeutAccelererEtat();

    public abstract int getNbTour();

    public abstract boolean depenseCarbu();

    public abstract Coordonnees getACoordonnees();

    public abstract String getNom();

    public abstract boolean plusDeCarburant();

    public abstract int getCarburant();

    public abstract String getEtat();

    public abstract void incrementerTour();

    public abstract void setDamaged();

    public abstract String getInformation();

    public abstract boolean estEtatSpecial();
}
