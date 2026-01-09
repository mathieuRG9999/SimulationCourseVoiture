
public class StoppedState implements State {

    @Override
    public void changeVitesse(Voiture v) {
        v.setVitesse(0);
    }

    @Override
    public String getEtat() {
        return "stopped";
    }

    @Override
    public void dimCarburant(Voiture v) {
        v.dimCarburant(0);
    }

    @Override
    public int getDimCarburant() {
        return 0;
    }

    @Override
    public void FonctionAvancer(Voiture v) {
        // La voiture ne bouge pas en état "stopped"
    }

    @Override
    public boolean onPeutAccelererEtat(Voiture v) {
        v.setState(new LowState());
        return true;
    }

    @Override
    public boolean onPeutRalentirEtat(Voiture v) {
        return false;
    }

    @Override
    public String getInfo() {
        return "la voiture est déjà arretée et elle ne peut pas ralentir ultérieurement";
    }

    @Override
    public boolean casExtreme() {
        return true;
    }

    @Override
    public boolean depenseCarbu() {
        return false;
    }

}
