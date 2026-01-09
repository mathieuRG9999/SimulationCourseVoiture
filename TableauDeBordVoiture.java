
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TableauDeBordVoiture extends JPanel implements ObservateurJeu {

    private static final int HAUTEUR_FENETRE = 150;
    private static final int LARGEUR_FENETRE = 400;
    private final JLabel[] tabLabel;
    private final JButton[] tabBouton;
    private static final Core_game jeu = Core_game.getInstanceCoreGame();
    private final int index;

    public TableauDeBordVoiture(Color color, Comportement v, int x, int y) {
        var fenetre = new affichageDecorator(LARGEUR_FENETRE, HAUTEUR_FENETRE, x, y, "tableau de bord de " + v.getNom());
        this.index = jeu.getLeaderBord().indexOf(v);
        jeu.enregistrer(this);

        tabLabel = new JLabel[7];
        tabBouton = new JButton[3];

        tabLabel[0] = new JLabel("Carburant restant :");
        tabLabel[1] = new JLabel("" + jeu.getVoiture(index).getCarburant());
        tabLabel[2] = new JLabel("Nombre de tour de pistes réalisés :");
        tabLabel[3] = new JLabel("" + jeu.getVoiture(index).getNbTour());
        tabLabel[4] = new JLabel("Etat ");
        tabLabel[5] = new JLabel("" + jeu.getVoiture(index).getEtat());
        tabLabel[6] = new JLabel();

        for (int i = 0; i < tabBouton.length; i++) {
            tabBouton[i] = new JButton();
        }
        tabBouton[1].setText("Accelerer");
        tabBouton[2].setText("Ralentir");

        //Action boutons
        tabBouton[1].addActionListener(e -> {
            boolean changementReussi = jeu.getVoiture(index).onPeutAccelererEtat();
            if (changementReussi) {
                tabLabel[6].setText("");
            } else {
                tabLabel[6].setText(v.getInformation());
            }
        });

        tabBouton[2].addActionListener(e -> {
            boolean changementReussi = jeu.getVoiture(index).onPeutRalentirEtat();

            if (changementReussi) {
                tabLabel[6].setText("");
            } else {
                tabLabel[6].setText(v.getInformation());
            }
        });

        tabBouton[0].addActionListener(e -> {
            System.out.println("Bouton cliqué, on change d'état");
            jeu.changerDeMode();
        });

        for (int i = tabBouton.length - 1; i >= 0; i--) {
            fenetre.add(tabBouton[i]);
        }

        fenetre.setBackground(color);
        JPanel panel = new JPanel(new GridLayout(3, 2));
        panel.setBackground(color);
        panel.setOpaque(true);

        for (int i = 0; i < 6; i++) {
            panel.add(tabLabel[i]);
        }

        fenetre.add(panel);

        JPanel panelInfo = new JPanel();
        panelInfo.setBackground(color);
        panelInfo.setOpaque(true);

        tabLabel[6].setText("");
        panelInfo.add(tabLabel[6]);

        fenetre.add(panelInfo);

        fenetre.setLayout(new FlowLayout(FlowLayout.CENTER));

        miseAJour();
    }

    @Override
    public void miseAJour() {
        tabLabel[1].setText("" + jeu.getVoiture(index).getCarburant());
        tabLabel[3].setText("" + jeu.getVoiture(index).getNbTour());
        tabLabel[5].setText("" + jeu.getVoiture(index).getEtat());
        if (!(jeu.getVoiture(index).estEtatSpecial())) {
            tabLabel[6].setText(jeu.getVoiture(index).getInformation());

        }

        if (jeu.toutesVoituresSansCarburant()) {
            tabBouton[0].setText("course terminée");
            tabBouton[0].setEnabled(false);
        } else {
            tabBouton[0].setEnabled(true);
            if (jeu.getEstEnPause()) {
                tabBouton[0].setText("Reprendre");
            } else {
                tabBouton[0].setText("Pause");
            }
        }

    }
}
