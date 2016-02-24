import javax.swing.*;
import java.awt.*;

/**
 * Created by User on 23.02.2016.
 */
public class GameView extends JPanel {
    private static final int DIM_WIDTH = 400;
    private static final int DIM_HEIGHT = 640;
    private static final int SQARE_SIZE = 40;
    private static final int BOARDER_SIZE = 1;
    private int[][] board = new int[16][10];

    GameView(int[][] board) {
        this.board = board;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int row = 0; row < 16; row++) {
            for (int col = 0; col < 10; col++) {
                g.setColor(Color.BLACK);
                g.fillRect(col * SQARE_SIZE, row * SQARE_SIZE, SQARE_SIZE, SQARE_SIZE);
                switch(board[row][col]) {
                    case 1:
                        g.setColor(Color.RED);
                        break;
                    case 2:
                        g.setColor(Color.GREEN);
                        break;
                    case 3:
                        g.setColor(Color.BLUE);
                        break;
                    case 4:
                        g.setColor(Color.YELLOW);
                        break;
                    case 5:
                        g.setColor(Color.DARK_GRAY);
                        break;
                    case 6:
                        g.setColor(Color.ORANGE);
                        break;
                    case 7:
                        g.setColor(Color.PINK);
                        break;
                    default:
                        g.setColor(Color.WHITE);
                        break;
                }
                g.fillRect((col * SQARE_SIZE)+BOARDER_SIZE, (row * SQARE_SIZE)+BOARDER_SIZE, SQARE_SIZE-(2*BOARDER_SIZE), SQARE_SIZE-(2*BOARDER_SIZE));
            }
        }
    }

    public Dimension getPreferredSize() {
        return new Dimension(DIM_WIDTH, DIM_HEIGHT);
    }
}
