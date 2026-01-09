
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanneauAffichage extends JPanel implements ObservateurJeu {

    private static final int HAUTEUR_FENETRE = 100;
    private static final int LARGEUR_FENETRE = 300;
    private static final int X_ENDROIT_AFFICHAGE = 0;
    private static final int Y_ENDROIT_AFFICHAGE = 550;

    private JLabel[] tab;
    private final static Core_game jeu = Core_game.getInstanceCoreGame();

    public PanneauAffichage() {
        jeu.enregistrer(this);
        var test = new affichageDecorator(LARGEUR_FENETRE, HAUTEUR_FENETRE,
                X_ENDROIT_AFFICHAGE, Y_ENDROIT_AFFICHAGE, "Classement des voitures");

        JPanel panel = new JPanel(new GridLayout(3, 1));
        tab = new JLabel[jeu.getSizeVoiture()];

        for (int i = 0; i < tab.length; i++) {
            tab[i] = new JLabel("");
            panel.add(tab[i]);
        }

        test.add(panel);
        miseAJour();
    }

    @Override
    public void miseAJour() {
        for (int i = 0; i < tab.length; i++) {
            tab[i].setText((i + 1) + "- " + jeu.getNomClasse(i));
        }
    }
}
