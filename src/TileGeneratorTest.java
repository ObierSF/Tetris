import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Created by User on 14.02.2016.
 */
public class TileGeneratorTest {
    private TileGenerator g;

    private void setTileGenerator() {
        g = new TileGenerator();
    }

    @Test
    public void generateTile() throws Exception {
        setTileGenerator();
        Tile t = g.generateRandomTile();
    }

    @Test
    public void generateDifferetType() throws Exception {
        setTileGenerator();
        Tile t = g.generateRandomTile();
        Tile test = g.generateRandomTile();
        int counter1 = 0;
        int counter2 = 0;
        int border;
        if (t.shape.length > test.shape.length) {
            border = t.shape.length;
        }
        else {
            border = test.shape.length;
        }
        for (int row = 0; row < border; row++) {
            for (int col = 0; col < border; col++) {
                if ( row < t.shape.length && col < t.shape.length)
                    counter1 += t.shape[row][col];
                if ( row < test.shape.length && col < test.shape.length)
                    counter2 += test.shape[row][col];
            }
        }
        assertNotEquals(counter1, counter2); //assertion may fail.
    }
}