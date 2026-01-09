
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class affichageDecorator extends JPanel {

    JFrame frame;

    public affichageDecorator(int LARGEUR_FENETRE, int HAUTEUR_FENETRE, int X_ENDROIT_AFFICHAGE, int Y_ENDROIT_AFFICHAGE, String str) {
        this.setPreferredSize(new Dimension(LARGEUR_FENETRE, HAUTEUR_FENETRE));
        frame = new JFrame(str);
        frame.setContentPane(this);
        frame.setResizable(false);
        frame.pack();
        frame.setLocation(X_ENDROIT_AFFICHAGE, Y_ENDROIT_AFFICHAGE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void enleverAffichage() {
        frame.setVisible(false);
    }
}
