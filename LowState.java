
import java.util.Random;

public class LowState implements State {

    @Override
    public void changeVitesse(Voiture v) {
        Random random = new Random();
        v.setVitesse(random.nextInt((3 - 1) + 1) + 1);
    }

    @Override
    public String getEtat() {
        return "low";
    }

    @Override
    public void dimCarburant(Voiture v) {
        v.dimCarburant(1);
    }

    @Override
    public int getDimCarburant() {
        return 1;
    }

    @Override
    public boolean onPeutAccelererEtat(Voiture v) {
        v.setState(new NormalState());
        return true;
    }

    @Override
    public boolean onPeutRalentirEtat(Voiture v) {
        v.setState(new StoppedState());
        return true;
    }

    @Override
    public String getInfo() {
        return "";
    }

}
