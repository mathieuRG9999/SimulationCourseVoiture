
public class VoitureBooster extends VoitureDecorator {

    public VoitureBooster(Comportement v) {
        super(v);
    }

    @Override
    public boolean onPeutAccelererEtat() {
        boolean peutAccelerer = super.onPeutAccelererEtat();
        if (peutAccelerer) {
            System.out.println("VROOUUUUM");
        }
        return peutAccelerer;
    }

}   
