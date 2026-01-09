
import java.util.*;
import javax.swing.Timer;

public class Core_game {

    private final ArrayList<Comportement> listeVoitureDecorator;
    private final ArrayList<ObservateurJeu> listeObservable;
    private final paysage board;
    private boolean estEnPause;
    private static Core_game jeuSingleton;
    private Timer timer;

    private int voitureDecoree;
    private boolean pasPhaseSelection;

    private Core_game() {
        this.listeVoitureDecorator = new ArrayList<>();
        this.listeObservable = new ArrayList<>();
        this.board = new paysage();
    }

    public static Core_game getInstanceCoreGame() {
        if (jeuSingleton == null) {
            jeuSingleton = new Core_game();
        }
        return jeuSingleton;
    }

    public void addVoiture(Comportement v) {
        if (listeVoitureDecorator.size() < 3 && board.getRoute().contains(v.getACoordonnees())) {
            listeVoitureDecorator.add(v);
            System.out.println("on ajoute une voiture " + v.getNom());
        } else {
            System.out.println("on ne peut avoir que trois voitures à la fois, les coordonnes ne sont pas sur la route");
        }
    }

    public ArrayList<Comportement> getLeaderBord() {
        int taille = listeVoitureDecorator.size();
        if (taille <= 1) {
            return new ArrayList<>(listeVoitureDecorator);
        }

        ArrayList<Comportement> classement = new ArrayList<>(listeVoitureDecorator);

        classement.sort((v1, v2) -> {
            int cmpTours = Integer.compare(v2.getNbTour(), v1.getNbTour());
            if (cmpTours != 0) {
                return cmpTours;
            }
            return Integer.compare(board.getIndexCoord(v2.getACoordonnees()),
                    board.getIndexCoord(v1.getACoordonnees()));
        });

        return classement;
    }

    public String getNomClasse(int i) {
        return getNomClasse(getLeaderBord(), i);
    }

    private String getNomClasse(ArrayList<Comportement> listeVoiture, int i) {
        if (i >= listeVoiture.size() || i < 0) {
            System.out.println("on demande le nom de la voiture a un index qui n'est pas compris dans la liste");
            return "";
        }
        return listeVoiture.get(i).getNom();
    }

    public int getSizeVoiture() {
        return listeVoitureDecorator.size();
    }

    public Comportement getVoiture(int i) {
        return listeVoitureDecorator.get(i);
    }

    public Coordonnees getVoitureCoord(int index) {
        return listeVoitureDecorator.get(index).getACoordonnees();
    }

    public paysage getPaysage() {
        return board;
    }

    public void enregistrer(ObservateurJeu o) {
        listeObservable.add(o);
    }

    public void unenregistrer(ObservateurJeu o) {
        listeObservable.remove(o);
    }

    public void changerDeMode() {
        estEnPause = !estEnPause;
        notifierObservateurs();

        if (timer != null) {
            if (estEnPause) {
                timer.stop();
                System.out.println("le jeu a été mis en pause");
            } else {
                timer.start();
                System.out.println("le jeu a repris");
            }
        }
    }

    public boolean getEstEnPause() {
        return estEnPause;
    }

    public boolean finDeJeu() {
        for (Comportement c : listeVoitureDecorator) {
            if (c.getNbTour() >= 3) {
                System.out.println("fin de partie");
                return true;
            }
        }
        return false;
    }

    public void notifier() {
        if (toutesVoituresSansCarburant() || finDeJeu()) {
            notifierObservateurs();
            System.out.println("on arrête le Timer -> les voitures sont à court de carburant");
            timer.stop();
            return;
        }
        if (!estEnPause) {
            bougeVoiture();
            notifierObservateurs();
        }
    }

    private void bougeVoiture() {
        for (Comportement v : listeVoitureDecorator) {
            if (!v.plusDeCarburant()) {
                v.avancer(true);
                //v.bougerDimCarbu();
            }
        }
    }

    public void notifierObservateurs() {
        for (ObservateurJeu o : listeObservable) {
            o.miseAJour();
        }
    }

    public void start() {
        pasPhaseSelection = false;
        voitureDecoree = 0;

        if (listeVoitureDecorator.isEmpty()) {
            return;
        }

        new choisirDecorationVoiture(0);
    }

    public void appliquerDecorateurs(int index, boolean[] tabBool) { //applique a une voiture les potentiels decorator
        if (pasPhaseSelection || listeVoitureDecorator.isEmpty() || index < 0) {
            return;
        }
        Comportement c = listeVoitureDecorator.get(index);
        if (tabBool[0]) {
            c = new VoitureBooster(c);
        }
        if (tabBool[1]) {
            c = new PiloteIvre(c);
        }
        if (tabBool[2]) {
            c = new SystemHybrid(c);
        }
        listeVoitureDecorator.set(index, c);
        voitureDecoree += 1;
        if (voitureDecoree < listeVoitureDecorator.size()) {
            new choisirDecorationVoiture(voitureDecoree);
        } else {
            startGame();
        }
    }

    private void startGame() {

        if (timer == null) {
            pasPhaseSelection = true;
            timer = new Timer(1500, e -> notifier());
            timer.start();
            System.out.println("on démarre le Timer");
        }
    }

    public boolean toutesVoituresSansCarburant() {
        for (Comportement v : listeVoitureDecorator) {
            if (!v.plusDeCarburant()) {
                return false;
            }
        }
        return true;
    }

    public boolean estEnPhaseSelection() {
        return !pasPhaseSelection;
    }

}
