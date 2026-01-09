
public class SystemHybrid extends VoitureDecorator {

    private int batterie;

    public SystemHybrid(Comportement v) {
        super(v);
        this.batterie = 100;
    }

    @Override
    public void reculer(boolean utiliseEssence) {
        super.reculer(gaspillerEssence());
        System.out.println("batterie =" + batterie);
    }

    @Override
    public boolean onPeutRalentirEtat() {
        var cpt = super.onPeutRalentirEtat();
        if (cpt) {
            batterie = Math.min(100, batterie + 5);
            System.out.println("augmentation batterie =" + batterie);
        }
        return cpt;
    }

    @Override
    public void avancer(boolean utiliseEssence) {
        super.avancer(gaspillerEssence());
        System.out.println("batterie =" + batterie);
    }

    private boolean gaspillerEssence() {
        if (batterie >= 10) {
            if (depenseCarbu()) {
                batterie -= 10; //
            }
            return false;
        }
        return true;
    }

}
