/*
 * This Class is to build the Ball class
 * And move the class
 */
package breakout;
/**
 *
 * @author Wuxin Yang & Yixiao Chen
 */
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Ball extends Thread  {

    Breakout breakout;
    public int x = 200;
    public int y = 340;
    public int dirx;
    public int diry;
    public int r;
    public int hit = 0;
    public int radius = 10;
    public int score = 0;
    public int speed = 7;
    Image image;
    //Add the photo
    String ballImage = "../image/ball99.png";

    public Ball(Breakout breakout) {
       
        this.breakout = breakout;
        //Find the photo resource
        ImageIcon ii = new ImageIcon(this.getClass().getResource(ballImage));
        image = ii.getImage();
    }

    protected Image getImage()
    {
      return image;
    }
    
    public void move(boolean[][] matrix, int leftpoint) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (matrix[i][j]) {
                    if (x >= 390) {
                        x = 390;
                    }
                    //Move the ball
                    if (x < (i + 1) * 50 && x >= i * 50) {
                        if (y <= (j + 1) * 25 && y >= j * 25) {
                            diry = -diry;
                            dirx = -dirx;
                            matrix[i][j] = false;
                            score++;
                        
                            if (hit == 864) {
                                
                                Graphics g = breakout.canvas.getGraphics();
                                g.setColor(Color.black);
                                g.fillRect(0, 0, 400, 50);
                                //Add the win words
                                g.setFont(new Font("Times New Roman",Font.BOLD,25));
                                g.drawString("You Won!!!",410,50);
                                g.drawString("Press Enter To Start", 410, 70);
                                breakout.stopGame();
                             }
                        }
                    }
                    
                    if (y <= (j + 1) * 25 && y >= j * 25) {
                        if (x <= (i + 1) * 50 && x > i * 50) {
                            dirx = -dirx;
                            matrix[i][j] = false;
                            score++;
                     
                          
                        }
                    }
                }
            }
        }
        //If the ball meet the wall
        if (x <= 0) {
            dirx = -dirx;
        }
        if (y <= 0 && diry > 0) {
            diry = -diry;
        }
        if (x >= 390) {
            dirx = -dirx;
        }
        //If the ball touch the paddle
        if (y >= 340 && diry <= 0) {       
            // paddle longth
            if (x >= leftpoint && x <= leftpoint + 390) 
            {
                diry = -diry;
            }
        }
    }

    public void reflect(Brick brick) {
        move(brick.matrix, brick.leftPoint);

        x += dirx;
        y -= diry;
        hit++;
    }
}
