/*
 * This class is to create the brick matrix in the game
 */
package breakout;

/*
 * @author Wuxin Yang & Yixiao Chen
 */

import java.awt.Color;

public class Brick {

    Ball ball;
    Breakout breakout;
    int score = 0;
    boolean[][] matrix;
    Color[][] brickcolor;
    int[][] brickint;
    static int leftPoint = 0;
    int i, j;

    public Brick() {

        //Create the matrix
        matrix = new boolean[8][8];
        brickcolor = new Color[8][8];
        brickint = new int[8][8];
        for (i = 0; i < 8; i++) {
            for (j = 0; j < 2; j++) {
                
                matrix[i][j] = true;
                brickcolor[i][j] = Color.ORANGE;
            }
            for (j = 2; j < 4; j++) {
                
                matrix[i][j] = true;
                brickcolor[i][j] = Color.BLUE;
            }
            for (j = 4; j < 6; j++) {
                
                matrix[i][j] = true;
                brickcolor[i][j] = Color.GREEN;
            
            }
             for (j = 6; j < 7; j++) {
                
                matrix[i][j] = true;
                brickcolor[i][j] = Color.GREEN;
                brickcolor[5][6] = Color.PINK;
            }
        }
    }
}
