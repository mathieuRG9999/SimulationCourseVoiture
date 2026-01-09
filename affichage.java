
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import javax.swing.JComponent;
import javax.swing.JFrame;

public class affichage extends JComponent implements ObservateurJeu {

    // changer code pour avoir les voitures qui te prennent 1/nb voiture taille
    public final static int tailleCarre = 50;
    private final static double tailleCarreJaune = 0.75;

    private final static String nom = "Course";
    private final Core_game game;
    private final int nbLigne;
    private final int nbColonne;

    public affichage() {
        this.game = Core_game.getInstanceCoreGame();
        nbColonne = game.getPaysage().getColonnes();
        nbLigne = game.getPaysage().getLigne();
        game.enregistrer(this);
        this.setPreferredSize(new Dimension(nbColonne * tailleCarre, nbLigne * tailleCarre));
        JFrame frame = new JFrame(nom);
        frame.setContentPane(this);
        frame.pack();
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());
        coloriage(g, game.getPaysage().getTabPaysage());
        drawCaseSpecial(g, game.getPaysage().getTabPaysage());
        drawVoitures(g);

        g.setColor(Color.BLACK);
        drawGrid(g, getWidth(), getHeight(), tailleCarre);
    }

    private void drawVoitures(Graphics g) {
        int nbVoitures = game.getSizeVoiture();
        int tailleReduite;
        if (nbVoitures > 0) {
            tailleReduite = tailleCarre / nbVoitures;
        } else {
            tailleReduite = tailleCarre;
        }

        for (int i = 0; i < nbVoitures; i++) {
            Coordonnees pos = game.getVoitureCoord(i);
            if (pos != null) {
                int caseX = pos.getX() * tailleCarre;
                int caseY = pos.getY() * tailleCarre;

                int offsetY = i * tailleReduite;

                int offsetX = (tailleCarre - tailleReduite) / 2;

                switch (i) {
                    case 0 ->
                        g.setColor(Color.BLUE);
                    case 1 ->
                        g.setColor(Color.RED);
                    case 2 ->
                        g.setColor(Color.ORANGE);
                    default ->
                        g.setColor(Color.BLACK);
                }

                g.fillRect(caseX + offsetX, caseY + offsetY, tailleReduite, tailleReduite);
            }
        }
    }

    private void drawCaseSpecial(Graphics g, int[][] tab) {
        drawPointSpecial(g, tab, -2, "D");
        drawPointSpecial(g, tab, -1, "A");
        drawCaseTournant(g, tab);
    }

    private void drawCaseTournant(Graphics g, int[][] tab) {
        int carreSize = (int) (tailleCarre * tailleCarreJaune);
        int offset = (tailleCarre - carreSize) / 2;

        for (int i = 0; i < tab.length; i++) {
            for (int j = 0; j < tab[0].length; j++) {
                int x = i * tailleCarre + offset;
                int y = j * tailleCarre + offset;

                String text = null;

                if (tab[i][j] > 1) {
                    text = String.valueOf(tab[i][j]);
                }

                if (text != null) {
                    ecrire(g, carreSize, x, y, text);

                }
            }
        }
    }

    private void drawPointSpecial(Graphics g, int[][] tab, int valeur, String texte) {
        int carreSize = tailleCarre;
        for (int i = 0; i < tab.length; i++) {
            for (int j = 0; j < tab[0].length; j++) {
                if (tab[i][j] == valeur) {
                    ecrire(g, carreSize, i * tailleCarre, j * tailleCarre, texte);
                    return;
                }
            }
        }
    }

    private void ecrire(Graphics g, int carreSize, int x, int y, String text) {
        g.setFont(new Font("Arial", Font.BOLD, carreSize * 3 / 4));
        g.setColor(Color.YELLOW);
        g.fillRect(x, y, carreSize, carreSize);
        g.setColor(Color.BLACK);
        g.drawRect(x, y, carreSize, carreSize);

        FontMetrics fm = g.getFontMetrics();
        int textWidth = fm.stringWidth(text);
        int textHeight = fm.getAscent();
        int textX = x + (carreSize - textWidth) / 2;
        int textY = y + (carreSize + textHeight) / 2 - 2;

        g.drawString(text, textX, textY);
    }

    private void drawGrid(Graphics g, int largeur, int hauteur, int tailleCarre) {

        for (int i = 0; i <= nbLigne; i++) {
            g.drawLine(0, i * tailleCarre, largeur, i * tailleCarre);
        }
        for (int i = 0; i < nbColonne; i++) {
            g.drawLine(i * tailleCarre, 0, i * tailleCarre, hauteur);
        }
    }

    private void colorBox(Graphics g, int i, int j) {
        int tailleCarree = tailleCarre;
        g.fillRect(i * tailleCarree, j * tailleCarree, tailleCarree, tailleCarree);
    }

    private void coloriage(Graphics g, int[][] tab) {
        for (int i = 0; i < tab.length; i++) {
            for (int j = 0; j < tab[i].length; j++) {
                if (tab[i][j] == 0) {
                    g.setColor(new Color(0, 120, 38));
                } else {
                    g.setColor(Color.GRAY);
                }
                colorBox(g, i, j);

            }
        }
    }

    @Override
    public void miseAJour() {
        repaint();
    }

}
