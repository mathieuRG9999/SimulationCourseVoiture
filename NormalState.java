
import java.util.Random;

public class NormalState implements State {

    @Override
    public void changeVitesse(Voiture v) {
        Random random = new Random();
        v.setVitesse(random.nextInt((6 - 1) + 1) + 1);
    }

    @Override
    public String getEtat() {
        return "normal";
    }

    @Override
    public void dimCarburant(Voiture v) {
        v.dimCarburant(2);
    }

    @Override
    public int getDimCarburant() {
        return 2;
    }

    @Override
    public boolean onPeutAccelererEtat(Voiture v) {
        v.setState(new BoostState());
        return true;
    }

    @Override
    public boolean onPeutRalentirEtat(Voiture v) {
        v.setState(new LowState());
        return true;
    }

    @Override
    public String getInfo() {
        return "";
    }

}
