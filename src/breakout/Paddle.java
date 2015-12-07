/*
 * This class is to create paddle in the game
 * Use own image to show the paddle
 */
package breakout;

/*
 * 
 * @author Wuxin Yang & Yixiao Chen
 */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Paddle {

    Breakout breakout;
    int width, heigth;
    Image image;
    //Find the image
    String paddle = "../image/paddle.png";

    public Paddle(Breakout breakout) {
        ImageIcon ii = new ImageIcon(this.getClass().getResource(paddle));
        image = ii.getImage();
        width = image.getWidth(null);
        heigth = image.getHeight(null);
        this.breakout = breakout;
    }

    protected Image getImage() {
        return image;
    }

    public void paintrect(int leftpoint) {
        Graphics g = breakout.canvas.getGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0, 350, 400, 10);
        //paint the paddle
       // g.drawImage(image, leftpoint, 350, 390, 10, null);
        g.setColor(Color.blue);
		  g.fillRect(leftpoint, 350,390, 10);  
    }
}
