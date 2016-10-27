import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Game extends JPanel {

    //creation of both the ball and the racquets
    Ball ball = new Ball(this);
    Racquet racquet1 = new Racquet(this);
    Racquet2 racquet2 = new Racquet2(this);
    //initial speed
    int speed = 1;

    //returns scores
    private int getScore() {
        return speed - 1;
    }
    private int getScore2() {
        return speed - 1;
    }



    //Game proper
    public Game() {
        addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                //keyboard has no button pressing down on it
                racquet1.keyReleased(e);
                racquet1.keyReleased(e);
            }

            public void keyPressed(KeyEvent e) {
                //taking input from the keyboard
                racquet1.keyPressed(e);
                racquet2.keyPressed(e);
            }
        }
        );
        setFocusable(true);

    }

    private void move() {
        //function the moves the objects based on what their moves are
        ball.move();
        racquet1.move();
        racquet2.move();
    }

    @Override
    public void paint(Graphics g) {
        //painting
        //
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(Color.CYAN);
        ball.paint(g2d);
        racquet1.paint(g2d);
        racquet2.paint(g2d);

        g2d.setColor(Color.GREEN);
        g2d.setFont(new Font("Times New Roman", Font.BOLD, 30));
        //draws player one's score, over on the opposite side
        g2d.drawString(String.valueOf(getScore()), 10, 30);
        //draws player two's score
        //I mean, you're paying attention to the otherside
        g2d.drawString(String.valueOf(getScore2()), 260, 350);

    }

    public void gameOver() {
        Sound.BACK.stop();
        Sound.GAMEOVER.play();
        JOptionPane.showMessageDialog(this, "Your score is: " + getScore(),
                "GAME OVER", JOptionPane.YES_NO_OPTION);
        int reply = JOptionPane.showConfirmDialog(null,
                "Start A New Game?", "Yes", JOptionPane.YES_NO_OPTION);
        //restarting new game, if click on yes, then continue
        if (reply == JOptionPane.YES_OPTION) {
            JFrame frame = new JFrame("A Pong Type");
            Game game = new Game();
            frame.add(game);
            frame.setSize(300, 400);
            frame.setVisible(true);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            while (true) {
                game.move();
                game.repaint();
                try {
                    Thread.sleep(10);
                }
                catch(InterruptedException e) {
                    //Does nothing
                }
            }

        } else {
            //otherwise, exit
            System.exit(ABORT);
        }
        //safety net
        System.exit(ABORT);
    }


    public static void main(String[] args) throws InterruptedException {
        JFrame frame = new JFrame("A Pong Type");
        Game game = new Game();
        frame.add(game);
        frame.setSize(300, 400);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        while (true) {
            game.move();
            game.repaint();
            Thread.sleep(10);
        }
    }
}