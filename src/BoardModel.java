public class BoardModel {
    private final int height = 16;
    private final int width = 10;
    private final int startYPosition = 0;
    private final int startXPosition = 4;
    private int score = 0;
    private TileInMove tileInMove;
    private int[][] boardWithLocatedTiles;
    private int[][] boardWithMovingTile;

    BoardModel() {
        boardWithLocatedTiles = new int[height][width];
        boardWithMovingTile = new int[height][width];
    }

    public void resetGame() {
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                boardWithLocatedTiles[row][col] = 0;
                boardWithMovingTile[row][col] = 0;
            }
        }
        score = 0;
    }

    public int[][] getBoard() {
        return boardWithMovingTile;
    }

    public int getScore() {
        return score;
    }

    public boolean addTileIfPossible(Tile tile) {
        tileInMove = new TileInMove(tile);
        tileInMove.setPosition(startYPosition, startXPosition);
        if (isAddingPossible()) {
            return true;
        }
        else {
            while (!isAddingPossible()) {
                tileInMove.moveTileUp();
            }
            return false;
        }
    }

    private boolean isAddingPossible() {
        if (isTileCollisonFree(0, 0))
            return true;
        else
            return false;
    }

    public void rotateTile(String side) {
        switch(side) {
            case "left":
                rotateLeftIfPossible();
                break;
            case "right":
                rotateRightIfPossible();
                break;
        }
    }

    private void rotateRightIfPossible() {
        tileInMove.rotateRight();
        if (!isTileCollisonFree(0, 0)) {
            tileInMove.rotateLeft();
            setPreviousState();
        }
    }

    private void rotateLeftIfPossible() {
        tileInMove.rotateLeft();
        if (!isTileCollisonFree(0, 0)) {
            tileInMove.rotateRight();
            setPreviousState();
        }
    }

    public boolean moveTileLeft() {
        if (isTileCollisonFree(0, -1)) {
            tileInMove.moveTileLeft();
            return true;
        }
        else {
            setPreviousState();
            return false;
        }
    }

    public boolean moveTileRight() {
        if (isTileCollisonFree(0, 1)) {
            tileInMove.moveTileRight();
            return true;
        }
        else {
            setPreviousState();
            return false;
        }
    }

    private void setPreviousState() {
        int[][] tileShape = tileInMove.tile.shape;
        int x = tileInMove.getXposition();
        int y = tileInMove.getYposition();
        for (int row = y; row < y + tileShape.length; row++) {
            for (int col = x; col < x + tileShape[0].length; col++) {
                if (isTileOccupingBlock(tileShape[row - y][col - x])) {
                    boardWithMovingTile[row][col] = tileShape[row - y][col - x];
                }
            }
        }
    }

    public boolean tileFall() {
        if (isTileCollisonFree(1, 0)) {
            tileInMove.moveTileDown();
            return true;
        }
        else {
            locateMovingTile();
            return false;
        }
    }

    private boolean isTileCollisonFree(int y, int x) {
        int[][] tileShape = tileInMove.tile.shape;
        copyBoard();
        x += tileInMove.getXposition();
        y += tileInMove.getYposition();
        for (int row = y; row < y + tileShape.length; row++) {
            for (int col = x; col < x + tileShape[0].length; col++) {
                if (isTileOccupingBlock(tileShape[row - y][col - x])) {
                    if (row < 0) {
                    }
                    else if (isOutOfBorders(row, col) || isBlockFree(boardWithLocatedTiles[row][col])) {
                        copyBoard();
                        return false;
                    }
                    else {
                        boardWithMovingTile[row][col] = tileShape[row - y][col - x];
                    }
                }
            }
        }
        return true;
    }

    private boolean isTileOccupingBlock(int i) {
        return i > 0;
    }

    private boolean isBlockFree(int i) {
        return i != 0;
    }

    private boolean isOutOfBorders(int row, int col) {
        return col < 0 || col > (width - 1) || row > (height - 1);
    }

    private void copyBoard() {
        for (int row = 0; row <height; row++) {
            for (int col=0; col<width; col++) {
                boardWithMovingTile[row][col] = boardWithLocatedTiles[row][col];
            }
        }
    }

    private void locateMovingTile() {
        int[][] tileShape = tileInMove.tile.shape;
        int x = tileInMove.getXposition();
        int y = tileInMove.getYposition();
        for (int row=y; row<y+tileShape.length; row++) {
            for (int col=x; col<x+tileShape[0].length; col++) {
                if (row < height && tileShape[row-y][col-x] > 0)
                    boardWithLocatedTiles[row][col] = tileShape[row-y][col-x];
            }
        }
        copyBoard();
    }

    public void cleanCompletedRows() {
        int counter = 0;
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                if (boardWithLocatedTiles[row][col] > 0)
                    counter++;
            }
            if (counter == width) {
                cleanRow(row);
            }
            counter = 0;
        }
    }

    private void cleanRow(int rowToClean) {
        for (int row = rowToClean; row > 0; row--) {
            for (int col = 0; col < width; col++) {
                boardWithLocatedTiles[row][col] = boardWithLocatedTiles[row-1][col];
            }
        }
        for (int col = 0; col < width; col++) {
            boardWithLocatedTiles[0][col] = 0;
        }
        copyBoard();
        score += 100;
    }

    public void tileFallToBottom() {
        while(tileFall()) {}
    }
}
