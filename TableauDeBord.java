
import java.awt.Color;
import javax.swing.JPanel;

public class TableauDeBord extends JPanel {

    private static final int X_FENETRE_PLACEMENT = 1010;
    private static final int Y_FENETRE_DECALAGE = 200;

    public TableauDeBord() {
        var jeu = Core_game.getInstanceCoreGame();
        var tab = new TableauDeBordVoiture[jeu.getSizeVoiture()];
        for (int i = 0; i < tab.length; i++) {
            Color color = switch (i) {
                case 0 ->
                    Color.BLUE;
                case 1 ->
                    Color.RED;
                case 2 ->
                    Color.ORANGE;
                default ->
                    Color.PINK;
            };
            tab[i] = new TableauDeBordVoiture(color, jeu.getVoiture(i), X_FENETRE_PLACEMENT, Y_FENETRE_DECALAGE * i);
        }
    }
}
