/**
 * Created by User on 14.02.2016.
 */
public class TileInMove {
    public Tile tile;
    private int xposition;
    private int yposition;

    TileInMove(Tile tile) {
        this.tile = tile;
    }

    public void setPosition(int y, int x) {
        xposition = x;
        yposition = y;
    }

    public int getXposition() {
        return xposition;
    }

    public int getYposition() {
        return yposition;
    }

    public void moveTileUp() {
        yposition--;
    }

    public void moveTileDown() {
        yposition++;
    }

    public void moveTileLeft() {
        xposition--;
    }

    public void moveTileRight() {
        xposition++;
    }

    public void rotateLeft() {
        for (int row = 0; row < tile.shape.length; row++) {
            for (int col = 0; col < tile.shape[0].length; col++) {
                switch(tile.rotation) {
                    case 0:
                        tile.shape[row][col] = tile.rotate3Shape[row][col];
                        break;
                    case 1:
                        tile.shape[row][col] = tile.rotate0Shape[row][col];
                        break;
                    case 2:
                        tile.shape[row][col] = tile.rotate1Shape[row][col];
                        break;
                    case 3:
                        tile.shape[row][col] = tile.rotate2Shape[row][col];
                        break;
                }

            }
        }
        tile.rotation = (4 + tile.rotation - 1) % 4;
    }

    public void rotateRight() {
        for (int row = 0; row < tile.shape.length; row++) {
            for (int col = 0; col < tile.shape[0].length; col++) {
                switch(tile.rotation) {
                    case 0:
                        tile.shape[row][col] = tile.rotate1Shape[row][col];
                        break;
                    case 1:
                        tile.shape[row][col] = tile.rotate2Shape[row][col];
                        break;
                    case 2:
                        tile.shape[row][col] = tile.rotate3Shape[row][col];
                        break;
                    case 3:
                        tile.shape[row][col] = tile.rotate0Shape[row][col];
                        break;
                }

            }
        }
        tile.rotation = (tile.rotation + 1) % 4;
    }
}