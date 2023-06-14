import gamepackage.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GroupTests {

    public static String fromField(Field field, ArrayList<Field> group) {
        int c, p;
        if (field.hasRegularTile()) {
            List<Color> arrayList = List.of(Color.class.getEnumConstants());
            c = arrayList.indexOf(field.getRegularTile().getColor());
            List<TilePattern> arrayList2 = List.of(TilePattern.class.getEnumConstants());
            p = arrayList2.indexOf(field.getRegularTile().getPattern());
        } else {
            c = 9;
            p = 9;
        }
        boolean contains = group.contains(field);
        return String.format("%s%d%d", contains ? "V" : "X",  c, p);

    }
    @Test
    public void group1(){
        String result = "";
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
        ArrayList<Field> b = board.getGroup(board.getField(0, 0), false);
        for (Field field1 : b) {
            result += fromField(field1, b);
        }
        assertEquals("V14V14", result);

    }

    @Test
    public void group2(){
        String result = "";
        Board board = new Board();
        ProjectTile left = new ProjectTile(ProjectTileType.AAABBC);
        ProjectTile middle = new ProjectTile(ProjectTileType.AAABBB);
        ProjectTile right = new ProjectTile(ProjectTileType.AAABBC);
        board.setProjectTiles(left, middle, right);
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
        Tile tile13 = new RegularTile(Color.PURPLE, TilePattern.STRIPES);
        Tile tile14 = new RegularTile(Color.PURPLE, TilePattern.PLANTS);
        Tile tile15 = new RegularTile(Color.PURPLE, TilePattern.CROSSES);
        board.putTile(tile1, 0, 0);
        board.putTile(tile2, 0, 1);
        board.putTile(tile3, 0, 2);
        board.putTile(tile4, 0, 3);
        board.putTile(tile5, 0, 4);
        board.putTile(tile6, 0, 5);
        board.putTile(tile7, 0, 6);
        board.putTile(tile8, 1, 0);
        board.putTile(tile9, 1, 1); //
        board.putTile(tile10, 1, 2);
        board.putTile(tile11, 1, 3);
        board.putTile(tile12, 1, 4);
        board.putTile(tile13, 1, 5);
        board.putTile(tile14, 1, 6);
        board.putTile(tile15, 2, 0);
        board.putTile(tile1, 2, 1); //
        board.putTile(tile2, 2, 2);
        board.putTile(tile3, 2, 3);
        //board.putTile(tile4, 2, 4);
        board.putTile(tile5, 2, 5);
        board.putTile(tile6, 2, 6);
        board.putTile(tile7, 3, 0);
        board.putTile(tile8, 3, 1); //
        //board.putTile(tile9, 3, 2);
        board.putTile(tile10, 3, 3);
        board.putTile(tile11, 3, 4);
        board.putTile(tile12, 3, 5);
        board.putTile(tile13, 3, 6);
        board.putTile(tile14, 4, 0);
        board.putTile(tile15, 4, 1); //
        board.putTile(tile1, 4, 2);
        //board.putTile(tile2, 4, 3);
        board.putTile(tile3, 4, 4);
        board.putTile(tile4, 4, 5);
        board.putTile(tile5, 4, 6);
        board.putTile(tile6, 5, 0);
        board.putTile(tile7, 5, 1); //
        board.putTile(tile8, 5, 2);
        board.putTile(tile9, 5, 3);
        board.putTile(tile10, 5, 4);
        board.putTile(tile11, 5, 5);
        board.putTile(tile12, 5, 6);
        board.putTile(tile13, 6, 0);
        board.putTile(tile14, 6, 1); //
        board.putTile(tile15, 6, 2);
        board.putTile(tile1, 6, 3);
        board.putTile(tile2, 6, 4);
        board.putTile(tile3, 6, 5);
        board.putTile(tile4, 6, 6);
        ArrayList<Field> b = board.getGroup(board.getField(3, 3), false);
        for (Field field1 : b) {
            result += fromField(field1, b);
        }
        assertEquals("V54", result);

    }

    @Test
    public void group3(){
        String result = "";
        Board board = new Board();
        ProjectTile left = new ProjectTile(ProjectTileType.AAABBC);
        ProjectTile middle = new ProjectTile(ProjectTileType.AAABBB);
        ProjectTile right = new ProjectTile(ProjectTileType.AAABBC);
        board.setProjectTiles(left, middle, right);
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
        Tile tile13 = new RegularTile(Color.PURPLE, TilePattern.STRIPES);
        Tile tile14 = new RegularTile(Color.PURPLE, TilePattern.PLANTS);
        Tile tile15 = new RegularTile(Color.PURPLE, TilePattern.CROSSES);
        board.putTile(tile1, 0, 0);
        board.putTile(tile2, 0, 1);
        board.putTile(tile3, 0, 2);
        board.putTile(tile4, 0, 3);
        board.putTile(tile5, 0, 4);
        board.putTile(tile6, 0, 5);
        board.putTile(tile7, 0, 6);
        board.putTile(tile8, 1, 0);
        board.putTile(tile9, 1, 1); //
        board.putTile(tile10, 1, 2);
        board.putTile(tile11, 1, 3);
        board.putTile(tile12, 1, 4);
        board.putTile(tile13, 1, 5);
        board.putTile(tile14, 1, 6);
        board.putTile(tile15, 2, 0);
        board.putTile(tile1, 2, 1); //
        board.putTile(tile2, 2, 2);
        board.putTile(tile3, 2, 3);
        //board.putTile(tile4, 2, 4);
        board.putTile(tile5, 2, 5);
        board.putTile(tile6, 2, 6);
        board.putTile(tile7, 3, 0);
        board.putTile(tile8, 3, 1); //
        //board.putTile(tile9, 3, 2);
        board.putTile(tile10, 3, 3);
        board.putTile(tile11, 3, 4);
        board.putTile(tile12, 3, 5);
        board.putTile(tile13, 3, 6);
        board.putTile(tile14, 4, 0);
        board.putTile(tile15, 4, 1); //
        board.putTile(tile1, 4, 2);
        //board.putTile(tile2, 4, 3);
        board.putTile(tile3, 4, 4);
        board.putTile(tile4, 4, 5);
        board.putTile(tile5, 4, 6);
        board.putTile(tile6, 5, 0);
        board.putTile(tile7, 5, 1); //
        board.putTile(tile8, 5, 2);
        board.putTile(tile9, 5, 3);
        board.putTile(tile10, 5, 4);
        board.putTile(tile11, 5, 5);
        board.putTile(tile12, 5, 6);
        board.putTile(tile13, 6, 0);
        board.putTile(tile14, 6, 1); //
        board.putTile(tile15, 6, 2);
        board.putTile(tile1, 6, 3);
        board.putTile(tile2, 6, 4);
        board.putTile(tile3, 6, 5);
        board.putTile(tile4, 6, 6);
        ArrayList<Field> b = board.getGroup(board.getField(6, 6), false);
        for (Field field1 : b) {
            result += fromField(field1, b);
        }
        assertEquals("V14", result);

    }

    @Test
    public void group4(){
        String result = "";
        Board board = new Board();
        ProjectTile left = new ProjectTile(ProjectTileType.AAABBC);
        ProjectTile middle = new ProjectTile(ProjectTileType.AAABBB);
        ProjectTile right = new ProjectTile(ProjectTileType.AAABBC);
        board.setProjectTiles(left, middle, right);
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
        Tile tile13 = new RegularTile(Color.PURPLE, TilePattern.STRIPES);
        Tile tile14 = new RegularTile(Color.PURPLE, TilePattern.PLANTS);
        Tile tile15 = new RegularTile(Color.PURPLE, TilePattern.CROSSES);
        board.putTile(tile1, 0, 0);
        board.putTile(tile2, 0, 1);
        board.putTile(tile3, 0, 2);
        board.putTile(tile4, 0, 3);
        board.putTile(tile5, 0, 4);
        board.putTile(tile6, 0, 5);
        board.putTile(tile7, 0, 6);
        board.putTile(tile8, 1, 0);
        board.putTile(tile9, 1, 1); //
        board.putTile(tile10, 1, 2);
        board.putTile(tile11, 1, 3);
        board.putTile(tile12, 1, 4);
        board.putTile(tile13, 1, 5);
        board.putTile(tile14, 1, 6);
        board.putTile(tile15, 2, 0);
        board.putTile(tile1, 2, 1); //
        board.putTile(tile2, 2, 2);
        board.putTile(tile3, 2, 3);
        //board.putTile(tile4, 2, 4);
        board.putTile(tile5, 2, 5);
        board.putTile(tile6, 2, 6);
        board.putTile(tile7, 3, 0);
        board.putTile(tile8, 3, 1); //
        //board.putTile(tile9, 3, 2);
        board.putTile(tile10, 3, 3);
        board.putTile(tile11, 3, 4);
        board.putTile(tile12, 3, 5);
        board.putTile(tile13, 3, 6);
        board.putTile(tile14, 4, 0);
        board.putTile(tile15, 4, 1); //
        board.putTile(tile1, 4, 2);
        //board.putTile(tile2, 4, 3);
        board.putTile(tile3, 4, 4);
        board.putTile(tile4, 4, 5);
        board.putTile(tile5, 4, 6);
        board.putTile(tile6, 5, 0);
        board.putTile(tile7, 5, 1); //
        board.putTile(tile8, 5, 2);
        board.putTile(tile9, 5, 3);
        board.putTile(tile10, 5, 4);
        board.putTile(tile11, 5, 5);
        board.putTile(tile12, 5, 6);
        board.putTile(tile13, 6, 0);
        board.putTile(tile14, 6, 1); //
        board.putTile(tile15, 6, 2);
        board.putTile(tile1, 6, 3);
        board.putTile(tile2, 6, 4);
        board.putTile(tile3, 6, 5);
        board.putTile(tile4, 6, 6);
        ArrayList<Field> b = board.getGroup(board.getField(6, 0), false);
        for (Field field1 : b) {
            result += fromField(field1, b);
        }
        assertEquals("V12V14V11V14", result);

    }
}
