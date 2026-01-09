
public class DamagedState implements State {

    private int cptDamaged = 0;

    @Override
    public void changeVitesse(Voiture v) {
        v.setVitesse(0);
    }

    @Override
    public String getEtat() {
        return "damaged";
    }

    @Override
    public void dimCarburant(Voiture v) {
        v.dimCarburant(0);
    }

    @Override
    public void FonctionAvancer(Voiture v) {
        if (cptDamaged >= 5) {
            v.setState(new LowState());
            cptDamaged = 0;
        }
        cptDamaged += 1;
    }

    @Override
    public int getDimCarburant() {
        return 0;
    }

    @Override
    public boolean onPeutAccelererEtat(Voiture v) {
        return false;
    }

    @Override
    public boolean onPeutRalentirEtat(Voiture v) {
        return false;
    }

    @Override
    public String getInfo() {
        return "la voiture est endommagée donc elle ne peut pas bouger";
    }

    @Override
    public boolean depenseCarbu() {
        return false;
    }
}
