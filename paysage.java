
import java.util.ArrayList;

public final class paysage {

    private final int[][] tab; // 1 pour la route (grise), 0= vert, -1 =arrivée, -2 =départ, toutes les valeurs supérieurs à 1 représentent un élément
    private final ArrayList<Coordonnees> route = new ArrayList<>();

    public paysage() {
        tab = new int[20][10];
        setTabExemple();
        setRoute();
    }

    private void setTabExemple() {
        tab[17][8] = -2;
        tab[18][8] = -1;
        tab[1][8] = 5;
        tab[1][2] = 3;
        tab[12][2] = 2;
        tab[12][5] = 2;
        tab[18][1] = 2;
        tab[15][1] = 2;
        tab[15][5] = 2;

        avancerHorizontalement(2, 17, 8);
        avancerHorizontalement(2, 12, 2);
        avancerHorizontalement(13, 15, 5);
        avancerHorizontalement(16, 18, 1);

        avancerVerticalement(3, 8, 1);
        avancerVerticalement(3, 5, 12);
        avancerVerticalement(2, 5, 15);
        avancerVerticalement(2, 8, 18);
    }

    public void setRoute() {
        route.clear();
        boolean[][] visited = new boolean[tab.length][tab[0].length];

        for (int i = 0; i < tab.length; i++) {
            for (int j = 0; j < tab[0].length; j++) {
                if (tab[i][j] == -2) {
                    dfs(i, j, visited);
                }
            }
        }

    }

    private void dfs(int i, int j, boolean[][] visited) {
        if (i < 0 || j < 0 || i >= tab.length || j >= tab[0].length) {
            return;
        }
        if (visited[i][j] || tab[i][j] == 0) {
            return;
        }

        visited[i][j] = true;
        route.add(new Coordonnees(i, j));

        dfs(i, j - 1, visited);
        dfs(i - 1, j, visited);
        dfs(i, j + 1, visited);
        dfs(i + 1, j, visited);

    }

    private void avancerHorizontalement(int xDepart, int xArrivée, int y) {
        for (int i = xDepart; i < xArrivée; i++) {
            tab[i][y] = 1;
        }
    }

    private void avancerVerticalement(int yDepart, int yArrivée, int x) {
        for (int i = yDepart; i < yArrivée; i++) {
            tab[x][i] = 1;
        }
    }

    public Coordonnees getNextCoord(Coordonnees actuel, int pas, Comportement v) {
        var i = route.indexOf(actuel);
        int taille = route.size();

        if (aDerape(actuel, pas, i, taille)) {
            v.setDamaged();
        }
        if (pas < 0 && i + pas < 0) {
            return route.get(0);
        }
        if (i + pas >= taille) {
            v.incrementerTour();
        }
        int index = (i + pas) % taille;
        if (index < 0) {
            index += taille;
        }
        return route.get(index);
    }

//fonction déparage
    private boolean aDerape(Coordonnees actuel, int pas, int i, int taille) {
        int direction = direction(pas);
        int pas1 = Math.abs(pas);

        while (pas1 > 0) {
            i = (i + direction) % taille;
            if (i < 0) {
                i += taille;
            }
            Coordonnees c = route.get(i);
            int x = c.getX();
            int y = c.getY();

            if (tab[x][y] > 1 && pas >= tab[x][y]) {
                return true;
            }
            pas1--;
        }
        return false;
    }

    private static int direction(int pas) {
        if (pas >= 0) {
            return 1;
        } else {
            return -1;
        }
    }

    public Coordonnees arrive() {
        return route.get(route.size() - 1);
    }

    public void affichageTableau() {
        for (int i = 0; i < tab.length; i++) {
            for (int j = 0; j < tab[i].length; j++) {
                System.out.print(tab[i][j]);
            }
            System.out.println("");
        }
    }

    private void transformation(int i, int j, int etat) {
        if (etat < 0 || etat > 1) {
            System.out.println("L'état n'est pas bon, erreur");
            return;
        }
        if (limiteTabI(i, tab.length) && limiteTabJ(j)) {
            tab[i][j] = etat;
            System.out.println("on a changé l'état à la case (" + i + ", " + j + ")");
        }
    }

    private boolean limiteTabI(int i, int length) {
        return (i >= 0 && i < length);
    }

    private boolean limiteTabJ(int j) {
        for (int i = 0; i < tab.length; i++) {
            if (!limiteTabI(j, tab[i].length)) {
                return false;
            }
        }
        return true;
    }

    public void devientRoute(int i, int j) {
        transformation(i, j, 1);
    }

    public void devientPaysage(int i, int j) {
        transformation(i, j, 0);
    }

    public int[][] getTabPaysage() {
        return tab;
    }

    public int getColonnes() {
        return tab.length;
    }

    public ArrayList<Coordonnees> getRoute() {
        return this.route;
    }

    public int getLigne() {
        return tab[0].length;
    }

    public int getIndexCoord(Coordonnees c) {
        return route.indexOf(c);
    }
}
