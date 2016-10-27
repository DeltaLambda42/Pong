import java.awt.*;
import java.awt.event.KeyEvent;

public class Racquet2 {
    private static final int Y = 0;
    private static final int WITH = 60;
    private static final int HEIGHT = 10;
    int x1 = 60;
    int x1a = 0;

    //Seeing as how Racquet2 is basically a carbon copy of the other one
    //There are no other comments here
    private Game game;

    public Racquet2(Game game) {
        this.game = game;
    }

    public void move() {
        if (x1 + x1a > 0 && x1 + x1a < game.getWidth() - WITH) {
            x1 = x1 + x1a;
        }
    }


    public void paint(Graphics2D g ) {
        g.setColor(Color.RED); g.fillRect(x1, Y, WITH, HEIGHT);
    }

    public void keyReleased(KeyEvent e) {
        x1a = 0;
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_A) {
            x1a = -game.speed;
        }
        if (e.getKeyCode() == KeyEvent.VK_D) {
            x1a = game.speed;
        }
    }

    public Rectangle getBounds() {
        return new Rectangle(x1, Y, WITH, HEIGHT);
    }

    public int getTopY() {
        return Y - HEIGHT;
    }
}
