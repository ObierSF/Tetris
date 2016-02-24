import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Created by User on 15.02.2016.
 */
public class BoardModelTest {
    private BoardModel b;

    private void setBoard() {
        b = new BoardModel();
    }

    private void setRandomTile() throws Exception {
        TileGenerator g = new TileGenerator();
        assertTrue(b.addTileIfPossible(g.generateRandomTile()));
    }

    private void setAndMoveTileToEnd(Tile tile) throws Exception {
        assertTrue(b.addTileIfPossible(tile));
        b.tileFallToBottom();
    }

    private Tile getIShapeTile() {
        Tile tile = new Tile();
        tile.shape = new int[][]{   {0, 1, 0, 0},
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
        return tile;
    }

    private void printBoard(int[][] board) {
        for (int row = 0; row < 16; row++) {
            for (int col = 0; col < 10; col++) {
                System.out.print(board[row][col]);
            }
            System.out.println();
        }
        System.out.println();
    }

    private boolean areBoardsEquals(int[][] board1, int[][] board2) {
        for (int row = 0; row < board1.length; row++) {
            for (int col = 0; col < board1[0].length; col++) {
                if (board1[row][col] != board2[row][col])
                    return false;
            }
        }
        return true;
    }

    @Test
    public void addTile() throws Exception {
        setBoard();
        setRandomTile();
    }

    @Test
    public void getBoard() throws Exception {
        setBoard();
        int[][] board;
        board = b.getBoard();
        int[][] testboard = new int[16][10];
                assertTrue(areBoardsEquals(board, testboard));
    }

    @Test
    public void rotateTileLeft() throws Exception {
        setBoard();
        Tile tile = getIShapeTile();
        b.addTileIfPossible(tile);
        int[][] board = b.getBoard();
        b.rotateTile("left");
        assertTrue(b.moveTileLeft());
        assertTrue(b.moveTileLeft());
        b.rotateTile("left");
        assertTrue(b.moveTileLeft());
        b.rotateTile("left");
        int[][] testboard = new int[16][10];
        testboard[1][1] = 1;
        testboard[1][2] = 1;
        testboard[1][3] = 1;
        testboard[1][4] = 1;
        assertTrue(areBoardsEquals(board, testboard));
    }

    @Test
    public void rotateTileRight() throws Exception {
        setBoard();
        Tile tile = getIShapeTile();
        b.addTileIfPossible(tile);
        int[][] board = b.getBoard();
        b.rotateTile("right");
        assertTrue(b.moveTileLeft());
        assertTrue(b.moveTileLeft());
        assertTrue(b.moveTileLeft());
        b.rotateTile("right");
        assertTrue(b.moveTileLeft());
        b.rotateTile("right");
        int[][] testboard = new int[16][10];
        testboard[2][0] = 1;
        testboard[2][1] = 1;
        testboard[2][2] = 1;
        testboard[2][3] = 1;
        assertTrue(areBoardsEquals(board, testboard));
    }

    @Test
    public void tileFall() throws Exception {
        setBoard();
        setRandomTile();
        assertTrue(b.tileFall());
    }

    @Test
    public void tileFallToBottom() throws Exception {
        setBoard();
        Tile tile = getIShapeTile();
        assertTrue(b.addTileIfPossible(tile));
        int[][] board = b.getBoard();
        b.tileFallToBottom();
        int[][] testBoard = new int[16][10];
        for (int row=12; row<16; row++) {
            for (int col=4; col<8; col++) {
                testBoard[row][col] = tile.shape[row-12][col-4];
            }
        }
        assertTrue(areBoardsEquals(board, testBoard));
    }

    @Test
    public void blockOnBlock() throws Exception {
        setBoard();
        Tile tile = getIShapeTile();
        setAndMoveTileToEnd(tile);
        setAndMoveTileToEnd(tile);
    }

    @Test
    public void gameEnd() throws Exception {
        setBoard();
        Tile tile = new Tile();
        tile.shape = new int[][]{   {0, 1, 0, 0},
                                    {0, 1, 0, 0},
                                    {0, 1, 1, 0},
                                    {0, 0, 0, 0}};
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
        setAndMoveTileToEnd(tile);
        setAndMoveTileToEnd(tile);
        setAndMoveTileToEnd(tile);
        setAndMoveTileToEnd(tile);
        assertTrue(b.addTileIfPossible(tile));
        assertTrue(b.tileFall());
        assertFalse(b.tileFall());
        tile.shape = new int[][]{   {0, 0, 1, 0},
                                    {0, 0, 1, 0},
                                    {0, 1, 1, 0},
                                    {0, 0, 0, 0}};
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
        assertFalse(b.addTileIfPossible(tile));
    }

    @Test
    public void moveTileMaxLeft() throws Exception {
        setBoard();
        Tile tile = getIShapeTile();
        assertTrue(b.addTileIfPossible(tile));
        for (int i=0; i<5; i++) {
            assertTrue(b.moveTileLeft());
        }
        assertFalse(b.moveTileLeft());
    }

    @Test
    public void moveTileMaxRight()throws Exception {
        setBoard();
        Tile tile = getIShapeTile();
        assertTrue(b.addTileIfPossible(tile));
        for (int i=0; i<4; i++) {
            assertTrue(b.moveTileRight());
        }
        assertFalse(b.moveTileRight());
    }

    @Test
    public void tileCollisionLeft() throws Exception {
        setBoard();
        Tile tile = getIShapeTile();
        int[][] board = b.getBoard();
        assertTrue(b.addTileIfPossible(tile));
        for (int i=0; i<5; i++) {
            assertTrue(b.moveTileLeft());
        }
        assertFalse(b.moveTileLeft());
        for (int i=0; i<12; i++) {
            assertTrue(b.tileFall());
        }
        assertFalse(b.tileFall());
        assertTrue(b.addTileIfPossible(tile));
        for (int i=0; i<11; i++) {
            assertTrue(b.tileFall());
        }
        for (int i=0; i<4; i++) {
            assertTrue(b.moveTileLeft());
        }
        assertFalse(b.moveTileLeft());
        b.resetGame();
        int[][] cleanBoard = new int[16][10];
        assertTrue(areBoardsEquals(board, cleanBoard));
    }

    @Test
    public void tileCollisionRight() throws Exception {
        setBoard();
        Tile tile = getIShapeTile();
        assertTrue(b.addTileIfPossible(tile));
        int[][] board = b.getBoard();
        for (int i=0; i<4; i++) {
            assertTrue(b.moveTileRight());
        }
        assertFalse(b.moveTileRight());
        for (int i=0; i<12; i++) {
            assertTrue(b.tileFall());
        }
        assertFalse(b.tileFall());
        assertTrue(b.addTileIfPossible(tile));
        for (int i=0; i<9; i++) {
            assertTrue(b.tileFall());
        }
        for (int i=0; i<3; i++) {
            assertTrue(b.moveTileRight());
        }
        assertFalse(b.moveTileRight());
    }

    @Test
    public void isRowCompleted() throws Exception {
        setBoard();
        int[][] board = b.getBoard();
        Tile tile = getIShapeTile();
        assertTrue(b.addTileIfPossible(tile));
        b.rotateTile("left");
        assertTrue(b.moveTileLeft());
        assertTrue(b.moveTileLeft());
        assertTrue(b.moveTileLeft());
        assertTrue(b.moveTileLeft());
        for (int i=0; i<13; i++) {
            assertTrue(b.tileFall());
        }
        assertFalse(b.tileFall());
        assertTrue(b.addTileIfPossible(tile));
        for (int i=0; i<13; i++) {
            assertTrue(b.tileFall());
        }
        assertFalse(b.tileFall());
        tile = new Tile();
        tile.shape = new int[][]{   {0,0,0,0},
                                    {0,1,1,0},
                                    {0,1,1,0},
                                    {0,0,0,0}};
        tile.rotation = 0;
        assertTrue(b.addTileIfPossible(tile));
        assertTrue(b.moveTileRight());
        assertTrue(b.moveTileRight());
        assertTrue(b.moveTileRight());
        for (int i=0; i<13; i++) {
            assertTrue(b.tileFall());
        }
        assertFalse(b.tileFall());
        b.cleanCompletedRows();
        int[][] testBoard = new int[16][10];
        testBoard[15][8] = 1;
        testBoard[15][9] = 1;
        assertTrue(areBoardsEquals(board, testBoard));
        assertEquals(100, b.getScore());
    }
}