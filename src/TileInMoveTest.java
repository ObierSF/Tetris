import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;

/**
 * Created by User on 14.02.2016.
 */
public class TileInMoveTest {
    private TileInMove t;

    private void setTile(Tile tile) {
        t = new TileInMove(tile);
    }

    private void setRandomTile() {
        TileGenerator g = new TileGenerator();
        setTile(g.generateRandomTile());
    }

    @Test
    public void defineTile() throws Exception {
        setRandomTile();
    }

    @Test
    public void rotateLeft() throws Exception {
        Tile tile = new Tile();
        tile.shape = new int[][]{  {0, 1, 0, 0},
                {0, 1, 0, 0},
                {0, 1, 0, 0},
                {0, 1, 0, 0}};
        tile.rotate0Shape = new int[][]{  {0, 1, 0, 0},
                {0, 1, 0, 0},
                {0, 1, 0, 0},
                {0, 1, 0, 0}};
        tile.rotate1Shape = new int[][]{  {0, 0, 0, 0},
                {1, 1, 1, 1},
                {0, 0, 0, 0},
                {0, 0, 0, 0}};
        tile.rotate2Shape = new int[][]{  {0, 0, 1, 0},
                {0, 0, 1, 0},
                {0, 0, 1, 0},
                {0, 0, 1, 0}};
        tile.rotate3Shape = new int[][]{  {0, 0, 0, 0},
                {0, 0, 0, 0},
                {1, 1, 1, 1},
                {0, 0, 0, 0}};
        tile.rotation = 0;
        setTile(tile);
        t.rotateLeft();
        Tile tileAfterRotate = new Tile();
        tileAfterRotate.shape = new int[][]{  {0, 0, 0, 0},
                                                {0, 0, 0, 0},
                                                {1, 1, 1, 1},
                                                {0, 0, 0, 0}};
        for (int col = 0; col < 4; col++) {
            for (int row = 0; row < 4; row++) {
                assertEquals(tile.shape[row][col], tileAfterRotate.shape[row][col]);
            }
        }
    }

    @Test
    public void rotateRight() throws Exception {
        Tile tile = new Tile();
        tile.shape = new int[][]{  {0, 1, 0, 0},
                {0, 1, 0, 0},
                {0, 1, 0, 0},
                {0, 1, 0, 0}};
        tile.rotate0Shape = new int[][]{  {0, 1, 0, 0},
                {0, 1, 0, 0},
                {0, 1, 0, 0},
                {0, 1, 0, 0}};
        tile.rotate1Shape = new int[][]{  {0, 0, 0, 0},
                {1, 1, 1, 1},
                {0, 0, 0, 0},
                {0, 0, 0, 0}};
        tile.rotate2Shape = new int[][]{  {0, 0, 1, 0},
                {0, 0, 1, 0},
                {0, 0, 1, 0},
                {0, 0, 1, 0}};
        tile.rotate3Shape = new int[][]{  {0, 0, 0, 0},
                {0, 0, 0, 0},
                {1, 1, 1, 1},
                {0, 0, 0, 0}};
        tile.rotation = 0;
        setTile(tile);
        t.rotateRight();
        tile = t.tile;
        Tile tileAfterRotate = new Tile();
        tileAfterRotate.shape = new int[][]{  {0, 0, 0, 0},
                {1, 1, 1, 1},
                {0, 0, 0, 0},
                {0, 0, 0, 0}};
        for (int col = 0; col < 4; col++) {
            for (int row = 0; row < 4; row++) {
                assertEquals(tile.shape[row][col], tileAfterRotate.shape[row][col]);
            }
        }
    }

    @Test
    public void setPosition() throws Exception {
        setRandomTile();
        t.setPosition(0, 3);
    }

    @Test
    public void moveTileDown() throws Exception {
        setRandomTile();
        t.setPosition(0, 3);
        t.moveTileDown();
        int y = 1, newy = t.getYposition();
        assertEquals(newy, y);
    }

    @Test
    public void moveTileLeft() throws Exception {
        setRandomTile();
        t.setPosition(0, 3);
        t.moveTileLeft();
        int x = 2, newx = t.getXposition();
        assertEquals(newx, x);
    }

    @Test
    public void moveTileRight() throws Exception {
        setRandomTile();
        t.setPosition(0, 3);
        t.moveTileRight();
        int x = 4, newx = t.getXposition();
        assertEquals(newx, x);
    }
}