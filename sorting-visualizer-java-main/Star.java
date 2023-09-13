import java.awt.*;

public class Star {
    private int x;
    private int y;
    private Color color;
    private int panelWidth;
    private int panelHeight;

    public Star(int x, int y, Color color, int panelWidth, int panelHeight) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.panelWidth = panelWidth;
        this.panelHeight = panelHeight;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Color getColor() {
        return color;
    }

    public void move(int dx, int dy) {
        x += dx;
        y += dy;

        // Wrap around if stars go out of bounds
        if (x < 0) x = panelWidth;
        if (x > panelWidth) x = 0;
        if (y < 0) y = panelHeight;
        if (y > panelHeight) y = 0;
    }
}
