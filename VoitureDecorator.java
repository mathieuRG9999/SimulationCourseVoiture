
public abstract class VoitureDecorator implements Comportement {

    protected Comportement voiture;

    public VoitureDecorator(Comportement v) {
        this.voiture = v;
    }

    @Override
    public void avancer(boolean cpt) {
        voiture.avancer(cpt);
    }

    @Override
    public String getInformation() {
        return voiture.getInformation();
    }

    @Override
    public boolean estEtatSpecial() {
        return voiture.estEtatSpecial();
    }

    @Override
    public boolean onPeutRalentirEtat() {
        return voiture.onPeutRalentirEtat();
    }

    @Override
    public boolean onPeutAccelererEtat() {
        return voiture.onPeutAccelererEtat();
    }

    @Override
    public void reculer(boolean utiliseEssence) {
        voiture.reculer(utiliseEssence);
    }

    @Override
    public int getNbTour() {
        return this.voiture.getNbTour();
    }

    @Override
    public void setDamaged() {
        this.voiture.setDamaged();
    }

    @Override
    public Coordonnees getACoordonnees() {
        return this.voiture.getACoordonnees();
    }

    @Override
    public String getNom() {
        return this.voiture.getNom();
    }

    @Override
    public boolean plusDeCarburant() {
        return this.voiture.plusDeCarburant();
    }

    @Override
    public int getCarburant() {
        return this.voiture.getCarburant();
    }

    @Override
    public String getEtat() {
        return this.voiture.getEtat();
    }

    @Override
    public boolean depenseCarbu() {
        return this.voiture.depenseCarbu();
    }

    @Override
    public void incrementerTour() {
        this.voiture.incrementerTour();
    }
}
