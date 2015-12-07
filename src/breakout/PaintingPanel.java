/*
 * This class is to paint all componments to the canvas.
 */
package breakout;

/*
 * 
 * @author Wuxin Yang & Yixiao Chen
 */

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;

public class PaintingPanel implements KeyListener {

    private JFrame jframe;
    Canvas canvas;
    Brick brick;
    Paddle paddle;
    Ball ball;
    //Create the canvas
    private int canvaswidth = 400;
    private int canvasheight = 300;
    int gravity;
    Breakout breakout;

    public PaintingPanel(Breakout breakout) {

        this.breakout = breakout;
        paddle = new Paddle(breakout);
        brick = new Brick();
        gravity = 3;


    }

    //display the GUI
    public void showGUI() {
    
        jframe = new JFrame("Breakout");
        canvas = new Canvas();
        canvas.setSize(canvaswidth, canvasheight);
        canvas.addKeyListener(this);
        setCanvas(canvas);
        jframe.add(canvas);
        jframe.setBounds(500, 100, 810, 620);
        jframe.addKeyListener(this);
        jframe.setVisible(true);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    //Paint the brick and the ball matrix
    public void paint(Brick brick, Ball[] ball) {
        Graphics g = canvas.getGraphics();
        //background color
        g.setColor(Color.black);
        g.fillRect(0, 0, 400, 350);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (brick.matrix[i][j]) {
                    g.setColor(brick.brickcolor[i][j]);
                    g.fillRect(i * 50, j * 25, 50, 25);
                    g.setColor(Color.white);//
                    g.drawRect(i * 50, j * 25, 50, 25);
                }
            }
        }
        g.setColor(Color.RED);
        if (ball[0].y <= 350) {
            g.drawImage(ball[0].getImage(), ball[0].x, ball[0].y, ball[0].radius, ball[0].radius, null);
        }
        if (ball[1].y <= 350) {
            g.drawImage(ball[1].getImage(), ball[1].x, ball[1].y, ball[1].radius, ball[1].radius, null);
        }
        if (ball[2].y <= 350) {
            g.drawImage(ball[2].getImage(), ball[2].x, ball[2].y, ball[2].radius, ball[2].radius, null);
        }
        int i = (ball[0].score + ball[1].score + ball[2].score);
        int hitted = (ball[0].hit + ball[1].hit +ball[2].hit );
        String score = "score: " + i;
        g.clearRect(410, 190, 120, 120);
        g.drawString(score, 410, 210);

        String timeleft = "Change gravity times left:" + gravity/2;
        g.clearRect(405, 230, 190, 60);
        g.drawString(timeleft, 405, 250);
        
        
    }
   
    //Show the information
    public void paint(Ball[] ball) {

        Graphics g = canvas.getGraphics();
        g.drawString("Press Enter To Start", 405, 100);
        g.drawString("Press Space to pause ", 405, 150);
        g.drawString("and change the grivaty", 405, 170);
        if (ball[2].y > 360 && ball[1].y > 360 && ball[0].y > 360) {

            g.setFont(new Font("Times New Roman", Font.BOLD, 35));
            g.drawString("You Lose", 410, 50);
            breakout.stopGame();
        }
        if (ball[1].y > 360) {
           
            ball[1].diry = 0;
            ball[1].dirx = 0;
        }
        
        if (ball[2].y > 360) {
            
            ball[2].diry = 0;
            ball[2].dirx = 0;
        }
        
        if (ball[0].y > 360) {
           
            ball[0].diry = 0;
            ball[0].dirx = 0;
        }
    }

    @Override
    //The keyboard listener
    public void keyPressed(KeyEvent e) {
        int keycode = e.getKeyCode();
        switch (keycode) {
            case KeyEvent.VK_LEFT://left
                if (brick.leftPoint >= 10) {
                    brick.leftPoint -= 10;
                }
                paddle.paintrect(brick.leftPoint);
                break;

            case KeyEvent.VK_RIGHT://right
                if (brick.leftPoint <= 1) //move paddle
                {
                    brick.leftPoint += 10;
                }
                paddle.paintrect(brick.leftPoint);
                break;

            case KeyEvent.VK_ENTER://ENTER
                breakout.stopGame();

                breakout.beginGame();

                break;
            case KeyEvent.VK_SPACE://space
                if (gravity > 0) {
                    breakout.pause();
                    gravity--;
                }
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }
}
