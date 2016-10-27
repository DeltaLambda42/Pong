import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Ball {
    private static final int DIAMETER = 30;

    int x = 0;
    int y = 0;
    int xa = 1;
    int ya = 1;
    private int count1 = 0;
    private int count2 = 0;
    private Game game;

    public Ball(Game game) {
        this.game = game;
    }

    void move() {
        boolean changeDirection = true;
        //From here
        if (x + xa < 0) {
            xa = game.speed;
        }
        else if (x + xa > game.getWidth() - DIAMETER) {
            xa = -game.speed;
        }
        else if (y + ya < 0) {
            count1++;
            game.gameOver();
        }
        else if (y + ya > game.getHeight() - DIAMETER) {
            count2++;
            game.gameOver();
        }
        else if (y + ya < 0 && count1 >= 3) {
            game.gameOver();
        }
        else if (y + ya > game.getHeight() - DIAMETER && count2 >= 3) {
            game.gameOver();
        }
        //Till here, limits of the game board.
        //If it reaches either end, it's a gameover

        //seemingly unnecessary distinction
        else if (collision1()){
            ya = -game.speed;
            //y = game.racquet2.getTopY() - DIAMETER;

            Sound.BACK.play();
            game.speed++;
        }
        else if (collision2()) {
            ya = -game.speed;
            y = game.racquet1.getTopY() - DIAMETER;

            Sound.BACK.play();
            game.speed++;
        } else {
            //no change in direction, therefore no playing sound down below
            changeDirection = false;
        }

        if (changeDirection)
            //used actual pong sounds, not that echo-y stuff
            Sound.BALL.play();
        //ball accelerating down both x and y
        x = x + xa;
        y = y + ya;
    }

    private boolean collision1() {
        return game.racquet1.getBounds().intersects(getBounds());
    }
    private boolean collision2() {
        return game.racquet2.getBounds().intersects(getBounds());
    }

    //it fills it in, I don't know what to say
    public void paint(Graphics2D g) {
        g.fillOval(x, y, DIAMETER, DIAMETER);
    }

    //limits of rectangle, like a hit box
    public Rectangle getBounds() {
        return new Rectangle(x, y, DIAMETER, DIAMETER);
    }
}
