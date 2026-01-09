
public class Coordonnees {

    private int x;
    private int y;

    public Coordonnees(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Coordonnees(Coordonnees c) {
        x = c.getX();
        y = c.getY();
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Coordonnees)) {
            return false;
        }
        Coordonnees c = (Coordonnees) o;
        return (this.x == c.x && this.y == c.y);
    }

    public boolean estAuVoisinnage(Coordonnees c) {
        var x1 = c.getX();
        var y1 = c.getY();
        return verif(x, y, x1, y1) || verif(y, x, y1, x1);
    }

    private boolean verif(int var1, int var2, int x1, int y1) {
        return x1 == var1 && (Math.abs(y1 - var2) == 1);
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setC(Coordonnees c) {
        x = c.getX();
        y = c.getY();
    }

    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
