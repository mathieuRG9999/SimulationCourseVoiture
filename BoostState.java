
import java.util.Random;

public class BoostState implements State {

    @Override
    public void changeVitesse(Voiture v) {
        Random random = new Random();
        v.setVitesse(random.nextInt((10 - 5) + 1) + 5);
    }

    @Override
    public String getEtat() {
        return "boost";
    }

    @Override
    public void dimCarburant(Voiture v) {
        v.dimCarburant(5);
    }

    @Override
    public int getDimCarburant() {
        return 5;
    }

    @Override
    public boolean onPeutAccelererEtat(Voiture v) {
        return false;
    }

    @Override
    public boolean onPeutRalentirEtat(Voiture v) {
        v.setState(new NormalState());
        return true;
    }

    @Override
    public String getInfo() {
        return "la voiture est déjà à la vitesse maximale";
    }

    @Override
    public boolean casExtreme() {
        return true;
    }
}
