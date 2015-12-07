/*
 * This class the main class of the game
 */
package breakout;

/**
 *
 * @author Wuxin Yang & Yixiao Chen
 */
import java.awt.Canvas;
import java.awt.Graphics;
import java.util.Timer;

public class Breakout {

    //Create the timer
    Timer timer = new Timer(true);
    Canvas canvas;
    Brick brick;
    //Create three balls
    Ball[] ball = new Ball[3];
    Paddle paddle;
    PaintingPanel paintingPanel;

    public Breakout() {
       
        paintingPanel = new PaintingPanel(this);
        paintingPanel.showGUI();
        canvas = paintingPanel.getCanvas();
        beginGame();
    }

    //Start the game
    public void beginGame() {
        ball[0] = new Ball(this);
        ball[1] = new Ball(this);
        ball[2] = new Ball(this);
        brick = new Brick();
        paddle = new Paddle(this);
        run();
        ball[0].dirx = 5;
        ball[0].diry = 10;
        ball[1].dirx = 5;
        ball[1].diry = 10;
        ball[2].dirx = 5;
        ball[2].diry = 10;
        int width = (int) (canvas.getBounds().getWidth());
        int height = (int) (canvas.getBounds().getHeight());
        Graphics g = canvas.getGraphics();
        g.clearRect(0, 0, width, height);
        paintingPanel.gravity = 6;
    }

    //end the game
    public void stopGame() {
        
        timer.cancel();
    }

    //Stop the game
    public void pause() {

        if (ball[0].dirx == 0 && ball[1].dirx == 0 && ball[2].dirx == 0) {
            ball[0].dirx = 5;
            ball[0].diry = 10;
            ball[1].dirx = 5;
            ball[1].diry = 10;
            ball[2].dirx = 5;
            ball[2].diry = 10;
        } 
        else {
            ball[0].dirx -= ball[0].dirx;
            ball[0].diry -= ball[0].diry;
            ball[1].dirx -= ball[1].dirx;
            ball[1].diry -= ball[1].diry;
            ball[2].dirx -= ball[2].dirx;
            ball[2].diry -= ball[2].diry;
        }
    }

    // control the speed
    public void run() {
        timer = new Timer(true);
        timer.schedule(new java.util.TimerTask() {
           
            @Override
            public void run() {
                paintingPanel.paint(ball);
                paintingPanel.paint(brick, ball);
                paddle.paintrect(brick.leftPoint);
                handleCollisions();
            }
        }, 0, (ball[0].speed * 1));
    }

    //Find the collisions
    public void handleCollisions() {
        
        ball[0].reflect(brick);
        ball[1].reflect(brick);
        ball[2].reflect(brick);
    }

    //The main method
    public static void main(String[] args) {
        Breakout game = new Breakout();
    }
}
