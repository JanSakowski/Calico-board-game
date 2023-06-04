import game.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ButtonTests {

    @Test
    public void button1(){
        Board board = new Board();
        ProjectTile left = new ProjectTile(ProjectTileType.AAABBC);
        ProjectTile middle = new ProjectTile(ProjectTileType.AAABBB);
        ProjectTile right = new ProjectTile(ProjectTileType.AAABBC);
        Tile tile1 = new RegularTile(Color.PURPLE, TilePattern.PLANTS);
        Tile tile2 = new RegularTile(Color.LIGHT_BLUE, TilePattern.DOTS);
        Tile tile3 = new RegularTile(Color.GREEN, TilePattern.PLANTS);
        Tile tile4 = new RegularTile(Color.PURPLE, TilePattern.PLANTS);
        Tile tile5 = new RegularTile(Color.GREEN, TilePattern.FERN);
        Tile tile6 = new RegularTile(Color.LIGHT_BLUE, TilePattern.PLANTS);
        Tile tile7 = new RegularTile(Color.MAGENTA, TilePattern.PLANTS);
        Tile tile8 = new RegularTile(Color.GREEN, TilePattern.PLANTS);
        Tile tile9 = new RegularTile(Color.GREEN, TilePattern.DOTS);
        Tile tile10 = new RegularTile(Color.DARK_BLUE, TilePattern.PLANTS);
        Tile tile11 = new RegularTile(Color.YELLOW, TilePattern.PLANTS);
        Tile tile12 = new RegularTile(Color.YELLOW, TilePattern.FERN);
        board.putTile(tile1, 0, 0);
        board.putTile(tile2, 0, 1);
        board.putTile(tile3, 0, 2);
        board.putTile(tile4, 1, 0);
        board.putTile(tile5, 1, 1); //
        board.putTile(tile6, 1, 2);
        board.putTile(tile7,2, 0 );
        board.putTile(tile8, 2,1); //
        board.putTile(tile9, 2,2); //
        board.putTile(tile10, 3,0);
        board.putTile(tile11, 3, 1);
        board.putTile(tile12, 3, 4);
        board.setProjectTiles(left, middle, right);
        boolean result = board.putColorButton(1, 1);
        assertEquals(true, result);
    }

    @Test
    public void button2(){
        Board board = new Board();
        ProjectTile left = new ProjectTile(ProjectTileType.AAABBC);
        ProjectTile middle = new ProjectTile(ProjectTileType.AAABBB);
        ProjectTile right = new ProjectTile(ProjectTileType.AAABBC);
        Tile tile1 = new RegularTile(Color.YELLOW, TilePattern.PLANTS);
        Tile tile2 = new RegularTile(Color.YELLOW, TilePattern.DOTS);
        Tile tile3 = new RegularTile(Color.YELLOW, TilePattern.PLANTS);
        Tile tile4 = new RegularTile(Color.YELLOW, TilePattern.PLANTS);
        Tile tile5 = new RegularTile(Color.LIGHT_BLUE, TilePattern.FERN);
        Tile tile6 = new RegularTile(Color.YELLOW, TilePattern.PLANTS);
        Tile tile7 = new RegularTile(Color.YELLOW, TilePattern.PLANTS);
        Tile tile8 = new RegularTile(Color.GREEN, TilePattern.PLANTS);
        Tile tile9 = new RegularTile(Color.GREEN, TilePattern.DOTS);
        Tile tile10 = new RegularTile(Color.YELLOW, TilePattern.PLANTS);
        Tile tile11 = new RegularTile(Color.YELLOW, TilePattern.PLANTS);
        Tile tile12 = new RegularTile(Color.YELLOW, TilePattern.FERN);
        board.putTile(tile1, 0, 0);
        board.putTile(tile2, 0, 1);
        board.putTile(tile3, 0, 2);
        board.putTile(tile4, 1, 0);
        board.putTile(tile5, 1, 1); //
        board.putTile(tile6, 1, 2);
        board.putTile(tile7,2, 0 );
        board.putTile(tile8, 2,1); //
        board.putTile(tile9, 2,2); //
        board.putTile(tile10, 3,0);
        board.putTile(tile11, 3, 1);
        board.putTile(tile12, 3, 4);
        board.setProjectTiles(left, middle, right);
        boolean result = board.putColorButton(1, 1);
        assertEquals(false, result);
    }

    @Test
    public void button3(){
        Board board = new Board();
        ProjectTile left = new ProjectTile(ProjectTileType.AAABBC);
        ProjectTile middle = new ProjectTile(ProjectTileType.AAABBB);
        ProjectTile right = new ProjectTile(ProjectTileType.AAABBC);
        Tile tile1 = new RegularTile(Color.YELLOW, TilePattern.PLANTS);
        Tile tile2 = new RegularTile(Color.YELLOW, TilePattern.DOTS);
        Tile tile3 = new RegularTile(Color.YELLOW, TilePattern.PLANTS);
        Tile tile4 = new RegularTile(Color.YELLOW, TilePattern.PLANTS);
        Tile tile5 = new RegularTile(Color.LIGHT_BLUE, TilePattern.FERN);
        Tile tile6 = new RegularTile(Color.YELLOW, TilePattern.PLANTS);
        Tile tile7 = new RegularTile(Color.YELLOW, TilePattern.PLANTS);
        Tile tile8 = new RegularTile(Color.GREEN, TilePattern.PLANTS);
        Tile tile9 = new RegularTile(Color.GREEN, TilePattern.DOTS);
        Tile tile10 = new RegularTile(Color.YELLOW, TilePattern.PLANTS);
        Tile tile11 = new RegularTile(Color.YELLOW, TilePattern.PLANTS);
        Tile tile12 = new RegularTile(Color.YELLOW, TilePattern.FERN);
        board.putTile(tile1, 0, 0);
        board.putTile(tile2, 0, 1);
        board.putTile(tile3, 0, 2);
        board.putTile(tile4, 1, 0);
        board.putTile(tile5, 1, 1);
        board.putTile(tile6, 1, 2);
        board.putTile(tile7,2, 0 );
        board.putTile(tile8, 2,1);
        board.putTile(tile9, 2,2);
        board.putTile(tile10, 3,0);
        board.putTile(tile11, 3, 1);
        board.putTile(tile12, 3, 4);
        board.setProjectTiles(left, middle, right);
        boolean result = board.putColorButton(3, 0);
        assertEquals(true, result);
    }

    @Test
    public void button4(){
        Board board = new Board();
        ProjectTile left = new ProjectTile(ProjectTileType.AAABBC);
        ProjectTile middle = new ProjectTile(ProjectTileType.AAABBB);
        ProjectTile right = new ProjectTile(ProjectTileType.AAABBC);
        Tile tile1 = new RegularTile(Color.YELLOW, TilePattern.PLANTS);
        Tile tile2 = new RegularTile(Color.YELLOW, TilePattern.DOTS);
        Tile tile3 = new RegularTile(Color.YELLOW, TilePattern.PLANTS);
        Tile tile4 = new RegularTile(Color.YELLOW, TilePattern.PLANTS);
        Tile tile5 = new RegularTile(Color.LIGHT_BLUE, TilePattern.FERN);
        Tile tile6 = new RegularTile(Color.YELLOW, TilePattern.PLANTS);
        Tile tile7 = new RegularTile(Color.YELLOW, TilePattern.PLANTS);
        Tile tile8 = new RegularTile(Color.GREEN, TilePattern.PLANTS);
        Tile tile9 = new RegularTile(Color.GREEN, TilePattern.DOTS);
        Tile tile10 = new RegularTile(Color.YELLOW, TilePattern.PLANTS);
        Tile tile11 = new RegularTile(Color.YELLOW, TilePattern.PLANTS);
        Tile tile12 = new RegularTile(Color.GREEN, TilePattern.FERN);
        board.putTile(tile1, 0, 0);
        board.putTile(tile2, 0, 1);
        board.putTile(tile3, 0, 2);
        board.putTile(tile4, 1, 0);
        board.putTile(tile5, 1, 1);
        board.putTile(tile6, 1, 2);
        board.putTile(tile7,2, 0 );
        board.putTile(tile8, 2,1);
        board.putTile(tile9, 2,2);
        board.putTile(tile10, 3,0);
        board.putTile(tile11, 3, 1);
        board.putTile(tile12, 3, 4);
        board.setProjectTiles(left, middle, right);
        boolean result = board.putColorButton(3, 4);
        assertEquals(false, result);
    }

    @Test
    public void button5(){
        Board board = new Board();
        ProjectTile left = new ProjectTile(ProjectTileType.AAABBC);
        ProjectTile middle = new ProjectTile(ProjectTileType.AAABBB);
        ProjectTile right = new ProjectTile(ProjectTileType.AAABBC);
        Tile tile1 = new RegularTile(Color.YELLOW, TilePattern.PLANTS);
        Tile tile2 = new RegularTile(Color.YELLOW, TilePattern.DOTS);
        Tile tile3 = new RegularTile(Color.YELLOW, TilePattern.PLANTS);
        Tile tile4 = new RegularTile(Color.YELLOW, TilePattern.PLANTS);
        Tile tile5 = new RegularTile(Color.LIGHT_BLUE, TilePattern.FERN);
        Tile tile6 = new RegularTile(Color.YELLOW, TilePattern.PLANTS);
        Tile tile7 = new RegularTile(Color.YELLOW, TilePattern.PLANTS);
        Tile tile8 = new RegularTile(Color.GREEN, TilePattern.PLANTS);
        Tile tile9 = new RegularTile(Color.GREEN, TilePattern.DOTS);
        Tile tile10 = new RegularTile(Color.YELLOW, TilePattern.PLANTS);
        Tile tile11 = new RegularTile(Color.YELLOW, TilePattern.PLANTS);
        Tile tile12 = new RegularTile(Color.YELLOW, TilePattern.FERN);
        board.putTile(tile1, 0, 0);
        board.putTile(tile2, 0, 1);
        board.putTile(tile3, 0, 2);
        board.putTile(tile4, 1, 0);
        board.putTile(tile5, 1, 1);
        board.putTile(tile6, 1, 2);
        board.putTile(tile7,2, 0 );
        board.putTile(tile8, 2,1);
        board.putTile(tile9, 2,2);
        board.putTile(tile10, 3,0);
        board.putTile(tile11, 3, 1);
        board.putTile(tile12, 3, 4);
        board.setProjectTiles(left, middle, right);
        boolean result = board.putColorButton(6, 6);
        assertEquals(false, result);
    }

    @Test
    public void button6(){
        Board board = new Board();
        ProjectTile left = new ProjectTile(ProjectTileType.AAABBC);
        ProjectTile middle = new ProjectTile(ProjectTileType.AAABBB);
        ProjectTile right = new ProjectTile(ProjectTileType.AAABBC);
        Tile tile1 = new RegularTile(Color.YELLOW, TilePattern.PLANTS);
        Tile tile2 = new RegularTile(Color.YELLOW, TilePattern.DOTS);
        Tile tile3 = new RegularTile(Color.YELLOW, TilePattern.PLANTS);
        Tile tile4 = new RegularTile(Color.YELLOW, TilePattern.PLANTS);
        Tile tile5 = new RegularTile(Color.LIGHT_BLUE, TilePattern.FERN);
        Tile tile6 = new RegularTile(Color.YELLOW, TilePattern.PLANTS);
        Tile tile7 = new RegularTile(Color.YELLOW, TilePattern.PLANTS);
        Tile tile8 = new RegularTile(Color.GREEN, TilePattern.PLANTS);
        Tile tile9 = new RegularTile(Color.GREEN, TilePattern.DOTS);
        Tile tile10 = new RegularTile(Color.YELLOW, TilePattern.PLANTS);
        Tile tile11 = new RegularTile(Color.YELLOW, TilePattern.PLANTS);
        Tile tile12 = new RegularTile(Color.YELLOW, TilePattern.FERN);
        board.putTile(tile1, 0, 0);
        board.putTile(tile2, 0, 1);
        board.putTile(tile3, 0, 2);
        board.putTile(tile4, 1, 0);
        board.putTile(tile5, 1, 1);
        board.putTile(tile6, 1, 2);
        board.putTile(tile7,2, 0 );
        board.putTile(tile8, 2,1);
        board.putTile(tile9, 2,2);
        board.putTile(tile10, 3,0);
        board.putTile(tile11, 3, 1);
        board.putTile(tile12, 3, 4);
        board.setProjectTiles(left, middle, right);
        boolean result = board.putColorButton(3, 2);
        assertEquals(false, result);
    }

    @Test
    public void button7(){
        Board board = new Board();
        ProjectTile left = new ProjectTile(ProjectTileType.AAABBC);
        ProjectTile middle = new ProjectTile(ProjectTileType.AAABBB);
        ProjectTile right = new ProjectTile(ProjectTileType.AAABBC);
        Tile tile1 = new RegularTile(Color.YELLOW, TilePattern.PLANTS);
        Tile tile2 = new RegularTile(Color.YELLOW, TilePattern.DOTS);
        Tile tile3 = new RegularTile(Color.YELLOW, TilePattern.PLANTS);
        Tile tile4 = new RegularTile(Color.YELLOW, TilePattern.PLANTS);
        Tile tile5 = new RegularTile(Color.LIGHT_BLUE, TilePattern.FERN);
        Tile tile6 = new RegularTile(Color.YELLOW, TilePattern.PLANTS);
        Tile tile7 = new RegularTile(Color.YELLOW, TilePattern.PLANTS);
        Tile tile8 = new RegularTile(Color.GREEN, TilePattern.PLANTS);
        Tile tile9 = new RegularTile(Color.GREEN, TilePattern.DOTS);
        Tile tile10 = new RegularTile(Color.YELLOW, TilePattern.PLANTS);
        Tile tile11 = new RegularTile(Color.YELLOW, TilePattern.PLANTS);
        Tile tile12 = new RegularTile(Color.YELLOW, TilePattern.FERN);
        board.putTile(tile1, 0, 0);
        board.putTile(tile2, 0, 1);
        board.putTile(tile3, 0, 2);
        board.putTile(tile4, 1, 0);
        board.putTile(tile5, 1, 1);
        board.putTile(tile6, 1, 2);
        board.putTile(tile7,2, 0 );
        board.putTile(tile8, 2,1);
        board.putTile(tile9, 2,2);
        board.putTile(tile10, 3,0);
        board.putTile(tile11, 3, 1);
        board.putTile(tile12, 3, 4);
        board.setProjectTiles(left, middle, right);
        boolean result = board.putColorButton(5, 5);
        assertEquals(false, result);
    }

    @Test
    public void button8(){
        Board board = new Board();
        ProjectTile left = new ProjectTile(ProjectTileType.AAABBC);
        ProjectTile middle = new ProjectTile(ProjectTileType.AAABBB);
        ProjectTile right = new ProjectTile(ProjectTileType.AAABBC);
        Tile tile1 = new RegularTile(Color.GREEN, TilePattern.PLANTS);
        Tile tile2 = new RegularTile(Color.GREEN, TilePattern.DOTS);
        Tile tile3 = new RegularTile(Color.GREEN, TilePattern.PLANTS);
        Tile tile4 = new RegularTile(Color.YELLOW, TilePattern.PLANTS);
        Tile tile5 = new RegularTile(Color.YELLOW, TilePattern.FERN);
        Tile tile6 = new RegularTile(Color.YELLOW, TilePattern.PLANTS);
        Tile tile7 = new RegularTile(Color.YELLOW, TilePattern.PLANTS);
        Tile tile8 = new RegularTile(Color.YELLOW, TilePattern.PLANTS);
        Tile tile9 = new RegularTile(Color.YELLOW, TilePattern.DOTS);
        Tile tile10 = new RegularTile(Color.YELLOW, TilePattern.PLANTS);
        Tile tile11 = new RegularTile(Color.YELLOW, TilePattern.PLANTS);
        Tile tile12 = new RegularTile(Color.YELLOW, TilePattern.FERN);
        board.putTile(tile1, 0, 0); //
        board.putTile(tile2, 0, 1); //
        board.putTile(tile3, 0, 2); //
        board.putTile(tile4, 1, 0);
        board.putTile(tile5, 1, 1);
        board.putTile(tile6, 1, 2);
        board.putTile(tile7,2, 0 );
        board.putTile(tile8, 2,1);
        board.putTile(tile9, 2,2);
        board.putTile(tile10, 3,0);
        board.putTile(tile11, 3, 1);
        board.putTile(tile12, 3, 4);
        board.setProjectTiles(left, middle, right);
        boolean result = board.putColorButton(0, 1);
        boolean result1 = board.putColorButton(0, 2);

        assertEquals(false, result1);
    }

    @Test
    public void button9(){
        Board board = new Board();
        ProjectTile left = new ProjectTile(ProjectTileType.AAABBC);
        ProjectTile middle = new ProjectTile(ProjectTileType.AAABBB);
        ProjectTile right = new ProjectTile(ProjectTileType.AAABBC);
        Tile tile1 = new RegularTile(Color.GREEN, TilePattern.PLANTS);
        Tile tile2 = new RegularTile(Color.GREEN, TilePattern.DOTS);
        Tile tile3 = new RegularTile(Color.GREEN, TilePattern.PLANTS);
        Tile tile4 = new RegularTile(Color.PURPLE, TilePattern.PLANTS);
        Tile tile5 = new RegularTile(Color.PURPLE, TilePattern.FERN);
        Tile tile6 = new RegularTile(Color.PURPLE, TilePattern.PLANTS);
        Tile tile7 = new RegularTile(Color.YELLOW, TilePattern.PLANTS);
        Tile tile8 = new RegularTile(Color.YELLOW, TilePattern.PLANTS);
        Tile tile9 = new RegularTile(Color.YELLOW, TilePattern.DOTS);
        Tile tile10 = new RegularTile(Color.YELLOW, TilePattern.PLANTS);
        Tile tile11 = new RegularTile(Color.YELLOW, TilePattern.PLANTS);
        Tile tile12 = new RegularTile(Color.YELLOW, TilePattern.FERN);
        board.putTile(tile1, 0, 0); //
        board.putTile(tile2, 0, 1); //
        board.putTile(tile3, 0, 2); //
        board.putTile(tile4, 1, 0);
        board.putTile(tile5, 1, 1);
        board.putTile(tile6, 1, 2);
        board.putTile(tile7,2, 0 );
        board.putTile(tile8, 2,1);
        board.putTile(tile9, 2,2);
        board.putTile(tile10, 3,0);
        board.putTile(tile11, 3, 1);
        board.putTile(tile12, 3, 4);
        board.setProjectTiles(left, middle, right);
        boolean result = board.putColorButton(0, 1);
        boolean result2 = board.putColorButton(1, 2);
        boolean result3 = result && result2;
        assertEquals(true, result3);
    }

    @Test
    public void button10(){
        Board board = new Board();
        ProjectTile left = new ProjectTile(ProjectTileType.AAABBC);
        ProjectTile middle = new ProjectTile(ProjectTileType.AAABBB);
        ProjectTile right = new ProjectTile(ProjectTileType.AAABBC);
        Tile tile1 = new RegularTile(Color.GREEN, TilePattern.PLANTS);
        Tile tile2 = new RegularTile(Color.GREEN, TilePattern.DOTS);
        Tile tile3 = new RegularTile(Color.GREEN, TilePattern.PLANTS);
        Tile tile4 = new RegularTile(Color.PURPLE, TilePattern.PLANTS);
        Tile tile5 = new RegularTile(Color.PURPLE, TilePattern.FERN);
        Tile tile6 = new RegularTile(Color.GREEN, TilePattern.PLANTS);
        Tile tile7 = new RegularTile(Color.YELLOW, TilePattern.PLANTS);
        Tile tile8 = new RegularTile(Color.YELLOW, TilePattern.PLANTS);
        Tile tile9 = new RegularTile(Color.GREEN, TilePattern.DOTS);
        Tile tile10 = new RegularTile(Color.YELLOW, TilePattern.PLANTS);
        Tile tile11 = new RegularTile(Color.YELLOW, TilePattern.PLANTS);
        Tile tile12 = new RegularTile(Color.YELLOW, TilePattern.FERN);
        board.putTile(tile1, 0, 0); //
        board.putTile(tile2, 0, 1); //
        board.putTile(tile3, 0, 2); //
        board.putTile(tile4, 1, 0);
        board.putTile(tile5, 1, 1);
        board.putTile(tile6, 1, 2); //
        board.putTile(tile7,2, 0 );
        board.putTile(tile8, 2,1);
        board.putTile(tile9, 2,2); //
        board.putTile(tile10, 3,0);
        board.putTile(tile11, 3, 1);
        board.putTile(tile12, 3, 4);
        board.setProjectTiles(left, middle, right);
        boolean result = board.putColorButton(0, 1);
        boolean result2 = board.putColorButton(1, 2);
        boolean result3 = result && result2;
        assertEquals(false, result3);
    }

    @Test
    public void button11(){
        Board board = new Board();
        ProjectTile left = new ProjectTile(ProjectTileType.AAABBC);
        ProjectTile middle = new ProjectTile(ProjectTileType.AAABBB);
        ProjectTile right = new ProjectTile(ProjectTileType.AAABBC);
        Tile tile1 = new RegularTile(Color.YELLOW, TilePattern.PLANTS);
        Tile tile2 = new RegularTile(Color.YELLOW, TilePattern.DOTS);
        Tile tile3 = new RegularTile(Color.YELLOW, TilePattern.PLANTS);
        Tile tile4 = new RegularTile(Color.YELLOW, TilePattern.PLANTS);
        Tile tile5 = new RegularTile(Color.LIGHT_BLUE, TilePattern.FERN);
        Tile tile6 = new RegularTile(Color.YELLOW, TilePattern.PLANTS);
        Tile tile7 = new RegularTile(Color.YELLOW, TilePattern.PLANTS);
        Tile tile8 = new RegularTile(Color.GREEN, TilePattern.PLANTS);
        Tile tile9 = new RegularTile(Color.GREEN, TilePattern.DOTS);
        Tile tile10 = new RegularTile(Color.YELLOW, TilePattern.PLANTS);
        Tile tile11 = new RegularTile(Color.YELLOW, TilePattern.PLANTS);
        Tile tile12 = new RegularTile(Color.YELLOW, TilePattern.FERN);
        board.putTile(tile1, 0, 0);
        board.putTile(tile2, 0, 1);
        board.putTile(tile3, 0, 2);
        board.putTile(tile4, 1, 0);
        board.putTile(tile5, 1, 1);
        board.putTile(tile6, 1, 2);
        board.putTile(tile7,2, 0 );
        board.putTile(tile8, 2,1);
        board.putTile(tile9, 2,2);
        board.putTile(tile10, 3,0);
        board.putTile(tile11, 3, 1);
        board.putTile(tile12, 3, 4);
        board.setProjectTiles(left, middle, right);
        boolean result = board.putColorButton(0, 0);
        boolean result2 = board.putColorButton(0, 0);
        assertEquals(false, result2);
    }
}
