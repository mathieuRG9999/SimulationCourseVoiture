
public class Voiture implements Comportement {

    private final static Core_game game = Core_game.getInstanceCoreGame();
    private Coordonnees coordActuel;
    private int carburant;
    private int nb_tour;
    private int vitesse;
    private final String nom;
    private State state;

    public Voiture(Coordonnees c, String name) {
        this.carburant = 60;
        this.nb_tour = 0;
        this.state = new NormalState();
        this.changeVitesse();
        this.coordActuel = c;
        this.nom = name;
        game.addVoiture(this);
    }

    @Override
    public Coordonnees getACoordonnees() {
        return this.coordActuel;
    }

    @Override
    public String getEtat() {
        return this.state.getEtat();
    }

    public void changeVitesse() {
        this.state.changeVitesse(this);
    }

    public void afficherEtat() {
        System.out.println(this.state.getEtat());
    }

    public int getVitesse() {
        return this.vitesse;
    }

    @Override
    public void setDamaged() {
        this.state = new DamagedState();
    }

    public void dimCarburant() {
        this.state.dimCarburant(this);
    }

    @Override
    public boolean onPeutAccelererEtat() {
        return this.state.onPeutAccelererEtat(this);
    }

    @Override
    public boolean onPeutRalentirEtat() {
        return this.state.onPeutRalentirEtat(this);
    }

    @Override
    public String getInformation() {
        return state.getInfo();
    }

    public void setActualCoordonnees(Coordonnees c) {
        this.coordActuel = c;
    }

    @Override
    public boolean estEtatSpecial() {
        return state.casExtreme();
    }

    @Override
    public int getNbTour() {
        return nb_tour;
    }

    @Override
    public void incrementerTour() {
        this.nb_tour++;
    }

    @Override
    public String getNom() {
        return nom;
    }

    @Override
    public int getCarburant() {
        return carburant;
    }

    public void bougerDimCarbu() {
        dimCarburant();
    }

    @Override
    public void avancer(boolean utiliseEssence) {
        if (utiliseEssence) {
            boolean bloque = false;
            if (carburant < state.getDimCarburant()) {
                if (!onPeutRalentirEtat()) {
                    bloque = true;
                }
            }
            if (!bloque) {
                dimCarburant();
                this.state.FonctionAvancer(this);
            }
        } else {
            this.state.FonctionAvancer(this);
        }
    }

    @Override
    public boolean depenseCarbu() {
        return state.depenseCarbu();
    }

    @Override
    public void reculer(boolean utiliseEssence) {
        vitesse = -vitesse;
        avancer(utiliseEssence);
    }

    public void setVitesse(int v) {
        this.vitesse = v;
    }

    public void dimCarburant(int c) {
        this.carburant -= c;
    }

    public void setState(State s) {
        this.state = s;
    }

    @Override
    public boolean plusDeCarburant() {
        return carburant <= 0;
    }

}
