
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class choisirDecorationVoiture {

    private boolean[] tab;
    private String[] tabStr;
    private affichageDecorator panneau;

    private static final String strMettre = "mettre ";
    private static final String strEnlever = "enlever ";
    private static final String strLancer = "Lancer le jeu ";

    // private final ChoisirDecorationGeneral controller;
    public choisirDecorationVoiture(int index) {
        var jeu = Core_game.getInstanceCoreGame();

        tab = new boolean[3];
        tabStr = new String[3];

        tabStr[0] = "un sound booster";
        tabStr[1] = "un pilote ivre";
        tabStr[2] = "un system hybride";

        panneau = new affichageDecorator(400, 100, 400, 550, "choix decorateur pour la voiture " + jeu.getVoiture(index).getNom());

        JButton[] bouton = new JButton[4];

        for (int i = 0; i < tabStr.length; i++) {
            bouton[i] = new JButton();
            panneau.add(bouton[i]);
            bouton[i].setText(strMettre + tabStr[i]);

            final int j = i;
            bouton[j].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    tab[j] = !tab[j];
                    if (tab[j]) {
                        bouton[j].setText(strEnlever + tabStr[j]);
                    } else {
                        bouton[j].setText(strMettre + tabStr[j]);
                    }
                }
            });
        }

        bouton[3] = new JButton();
        bouton[3].setText(strLancer);
        panneau.add(bouton[3]);

        bouton[3].addActionListener(e -> {
            jeu.appliquerDecorateurs(index, tab);
            panneau.enleverAffichage();
        });
    }
}
