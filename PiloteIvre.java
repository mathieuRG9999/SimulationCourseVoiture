
public class PiloteIvre extends VoitureDecorator {

    private int cpt;

    public PiloteIvre(Comportement v) {
        super(v);
    }

    @Override
    public void avancer(boolean utiliseEssence) {
        if (cpt % 2 != 0) {
            super.avancer(utiliseEssence);
            cpt += 1;
        } else {
            super.reculer(utiliseEssence);
            cpt += 1;
        }
    }

}
