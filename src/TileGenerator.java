import java.util.Random;

/**
 * Created by User on 14.02.2016.
 */
public class TileGenerator {
    private Tile tile;
    private Random rand;

    TileGenerator() {
        rand = new Random();
    }

    public Tile generateRandomTile() {
        tile = new Tile();
        char type = getRandomType();
        selectType(type);
        randomRotation();
        return tile;
    }

    private char getRandomType() {
        char[] type = {'i', 'o', 'l', 'j', 'z', 's', 't'};
        return type[rand.nextInt(7)];
    }

    private void selectType(char type) {
        switch (type) {
            case 'i':
                iShape();
                break;
            case 'o':
                oShape();
                break;
            case 'l':
                lShape();
                break;
            case 'j':
                jShape();
                break;
            case 'z':
                zShape();
                break;
            case 's':
                sShape();
                break;
            case 't':
                tShape();
                break;
            default:
                break;
        }
    }

    private void tShape() {
        tile.shape = new int[3][3];
        tile.rotate0Shape = new int[][]{{0, 7, 0},
                                        {7, 7, 0},
                                        {0, 7, 0}};
        createOtherRotation();
    }

    private void sShape() {
        tile.shape = new int[3][3];
        tile.rotate0Shape = new int[][]{{6, 0, 0},
                                        {6, 6, 0},
                                        {0, 6, 0}};
        createOtherRotation();
    }

    private void zShape() {
        tile.shape = new int[3][3];
        tile.rotate0Shape = new int[][]{{0, 5, 0},
                                        {5, 5, 0},
                                        {5, 0, 0}};
        createOtherRotation();
    }

    private void jShape() {
        tile.shape = new int[3][3];
        tile.rotate0Shape = new int[][]{{0, 4, 0},
                                        {0, 4, 0},
                                        {4, 4, 0}};
        createOtherRotation();
    }

    private void lShape() {
        tile.shape = new int[3][3];
        tile.rotate0Shape = new int[][]{{3, 0, 0},
                                        {3, 0, 0},
                                        {3, 3, 0}};
        createOtherRotation();
    }

    private void oShape() {
        tile.shape = new int[2][2];
        tile.rotate0Shape = new int[][]{{ 2, 2},
                                        { 2, 2}};
        createOtherRotation();
    }

    private void iShape() {
        tile.shape = new int[4][4];
        tile.rotate0Shape = new int[][]{{0, 1, 0, 0},
                                        {0, 1, 0, 0},
                                        {0, 1, 0, 0},
                                        {0, 1, 0, 0}};
        createOtherRotation();
    }

    private void createOtherRotation() {
        tile.rotate1Shape = rotateRight(tile.rotate0Shape);
        tile.rotate2Shape = rotateRight(tile.rotate1Shape);
        tile.rotate3Shape = rotateRight(tile.rotate2Shape);
        tile.rotation = 0;
    }

    private int[][] rotateRight(int[][] shapeToRotate) {
        int[][] newShape = new int[shapeToRotate.length][shapeToRotate[0].length];
        int tempCol = shapeToRotate[0].length-1, tempRow = 0;
        for (int col = 0; col < shapeToRotate[0].length; col++) {
            for (int row = 0; row < shapeToRotate.length; row++) {
                newShape[tempRow][tempCol] = shapeToRotate[row][col];
                tempCol--;
            }
            tempRow++;
            tempCol = shapeToRotate[0].length-1;
        }
        return newShape;
    }

    private void randomRotation() {
        int i = rand.nextInt(4);
        for (int row = 0; row < tile.shape.length; row++) {
            for (int col = 0; col < tile.shape[0].length; col++) {
                switch(i) {
                    case 0:
                        tile.shape[row][col] = tile.rotate0Shape[row][col];
                        break;
                    case 1:
                        tile.shape[row][col] = tile.rotate1Shape[row][col];
                        break;
                    case 2:
                        tile.shape[row][col] = tile.rotate2Shape[row][col];
                        break;
                    case 3:
                        tile.shape[row][col] = tile.rotate3Shape[row][col];
                        break;
                }
            }
        }
        tile.rotation = i;
    }
}
