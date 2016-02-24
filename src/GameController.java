import javax.swing.*;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import static java.lang.Thread.sleep;

/**
 * Created by User on 22.02.2016.
 */
public class GameController extends JFrame implements Runnable {
    private BoardModel boardModel;
    private TileGenerator generator;
    private GameView gameView;

    GameController() {
        super("Tetris");
        boardModel = new BoardModel();
        generator = new TileGenerator();
        int[][] board = boardModel.getBoard();
        gameView = new GameView(board);
        add(gameView);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationByPlatform(true);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch(e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        boardModel.rotateTile("right");
                        break;
                    case KeyEvent.VK_DOWN:
                        boardModel.rotateTile("left");
                        break;
                    case KeyEvent.VK_LEFT:
                        boardModel.moveTileLeft();
                        break;
                    case KeyEvent.VK_RIGHT:
                        boardModel.moveTileRight();
                        break;
                    case KeyEvent.VK_SPACE:
                        boardModel.tileFallToBottom();
                        break;
                }
                gameView.repaint();
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        setResizable(false);
        pack();
        setVisible(true);
    }

    @Override
    public void run() {
        Tile tile;
        while(true) {
            tile = generator.generateRandomTile();
            gameView.repaint();
            while (boardModel.addTileIfPossible(tile)) {
                try {
                    sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                gameView.repaint();
                while (boardModel.tileFall()) {
                    try {
                        sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    gameView.repaint();
                }
                boardModel.cleanCompletedRows();
                gameView.repaint();
                tile = generator.generateRandomTile();
            }
            int score = boardModel.getScore();
            JOptionPane.showMessageDialog(null, "GAME OVER\nYour score: " + score);
            boardModel.resetGame();
        }
    }
}