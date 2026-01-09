
public class App {

    public static void main(String[] args) {
        var jeu = Core_game.getInstanceCoreGame();

        var c = new Coordonnees(17, 8);

        Comportement c1 = new Voiture(c, "voiture1");
        Comportement c2 = new Voiture(c, "voiture2");
        Comportement c3 = new Voiture(c, "voiture3");

        new ControleurJeu();

        jeu.start();
    }

}
