import gamepackage.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProjectTileTests {
    @Test
    public void projectTile1() {
        Board board = new Board();
        ProjectTile left = new ProjectTile(ProjectTileType.AAABBC);
        ProjectTile middle = new ProjectTile(ProjectTileType.AAABBB);
        ProjectTile right = new ProjectTile(ProjectTileType.AAABBC);
        Tile tile1 = new RegularTile(Color.PURPLE, TilePattern.DOTS);
        Tile tile2 = new RegularTile(Color.LIGHT_BLUE, TilePattern.DOTS);
        Tile tile3 = new RegularTile(Color.GREEN, TilePattern.CROSSES);
        Tile tile4 = new RegularTile(Color.PURPLE, TilePattern.DOTS);
        Tile tile5 = new RegularTile(Color.GREEN, TilePattern.FERN);
        Tile tile6 = new RegularTile(Color.LIGHT_BLUE, TilePattern.FERN);
        board.putTile(tile1, 1, 4);
        board.putTile(tile2, 3, 4);
        board.putTile(tile3, 1, 3);
        board.putTile(tile4, 2, 3);
        board.putTile(tile5, 1, 5);
        board.putTile(tile6, 2, 5);
        board.setProjectTiles(left, middle, right);
        left.updateMatches(board.getNeighbours(board.getField(2, 4)));
        assertEquals(7, left.getScore());
    }

    @Test
    public void projectTile2() {
        Board board = new Board();
        ProjectTile left = new ProjectTile(ProjectTileType.AAABBC);
        ProjectTile middle = new ProjectTile(ProjectTileType.AAABBB);
        ProjectTile right = new ProjectTile(ProjectTileType.AAABBC);
        Tile tile1 = new RegularTile(Color.PURPLE, TilePattern.DOTS);
        Tile tile2 = new RegularTile(Color.LIGHT_BLUE, TilePattern.DOTS);
        Tile tile3 = new RegularTile(Color.GREEN, TilePattern.CROSSES);
        Tile tile4 = new RegularTile(Color.PURPLE, TilePattern.DOTS);
        Tile tile5 = new RegularTile(Color.GREEN, TilePattern.FERN);
        Tile tile6 = new RegularTile(Color.LIGHT_BLUE, TilePattern.FERN);
        board.putTile(tile1, 2, 2);
        board.putTile(tile2, 4, 2);
        board.putTile(tile3, 2, 1);
        board.putTile(tile4, 3, 1);
        board.putTile(tile5, 2, 3);
        board.putTile(tile6, 3, 3);
        board.setProjectTiles(left, middle, right);
        middle.updateMatches(board.getNeighbours(board.getField(3, 2)));
        assertEquals(0, middle.getScore());
    }

    @Test
    public void projectTile3() {
        Board board = new Board();
        ProjectTile left = new ProjectTile(ProjectTileType.AAABBC);
        ProjectTile middle = new ProjectTile(ProjectTileType.AAABBB);
        ProjectTile right = new ProjectTile(ProjectTileType.AAABBC);
        Tile tile1 = new RegularTile(Color.PURPLE, TilePattern.DOTS);
        Tile tile2 = new RegularTile(Color.LIGHT_BLUE, TilePattern.DOTS);
        Tile tile3 = new RegularTile(Color.GREEN, TilePattern.FERN);
        Tile tile4 = new RegularTile(Color.PURPLE, TilePattern.DOTS);
        Tile tile5 = new RegularTile(Color.GREEN, TilePattern.FERN);
        Tile tile6 = new RegularTile(Color.LIGHT_BLUE, TilePattern.FERN);
        board.putTile(tile1, 2, 2);
        board.putTile(tile2, 4, 2);
        board.putTile(tile3, 2, 1);
        board.putTile(tile4, 3, 1);
        board.putTile(tile5, 2, 3);
        board.putTile(tile6, 3, 3);
        board.setProjectTiles(left, middle, right);
        middle.updateMatches(board.getNeighbours(board.getField(3, 2)));
        assertEquals(8, middle.getScore());
    }

    @Test
    public void projectTile4() {
        Board board = new Board();
        ProjectTile left = new ProjectTile(ProjectTileType.AAABBC);
        ProjectTile middle = new ProjectTile(ProjectTileType.AAABBB);
        ProjectTile right = new ProjectTile(ProjectTileType.AAABBC);
        Tile tile1 = new RegularTile(Color.PURPLE, TilePattern.DOTS);
        Tile tile2 = new RegularTile(Color.LIGHT_BLUE, TilePattern.DOTS);
        Tile tile3 = new RegularTile(Color.PURPLE, TilePattern.FERN);
        Tile tile4 = new RegularTile(Color.PURPLE, TilePattern.DOTS);
        Tile tile5 = new RegularTile(Color.LIGHT_BLUE, TilePattern.FERN);
        Tile tile6 = new RegularTile(Color.LIGHT_BLUE, TilePattern.FERN);
        board.putTile(tile1, 2, 2);
        board.putTile(tile2, 4, 2);
        board.putTile(tile3, 2, 1);
        board.putTile(tile4, 3, 1);
        board.putTile(tile5, 2, 3);
        board.putTile(tile6, 3, 3);
        board.setProjectTiles(left, middle, right);
        middle.updateMatches(board.getNeighbours(board.getField(3, 2)));
        assertEquals(13, middle.getScore());
    }

    @Test
    public void projectTile5() {
        Board board = new Board();
        ProjectTile left = new ProjectTile(ProjectTileType.AAABBC);
        ProjectTile middle = new ProjectTile(ProjectTileType.AAABBB);
        ProjectTile right = new ProjectTile(ProjectTileType.AAABBC);
        Tile tile1 = new RegularTile(Color.PURPLE, TilePattern.FERN);
        Tile tile2 = new RegularTile(Color.LIGHT_BLUE, TilePattern.DOTS);
        Tile tile3 = new RegularTile(Color.GREEN, TilePattern.FERN);
        Tile tile4 = new RegularTile(Color.PURPLE, TilePattern.DOTS);
        Tile tile5 = new RegularTile(Color.GREEN, TilePattern.FERN);
        Tile tile6 = new RegularTile(Color.LIGHT_BLUE, TilePattern.FERN);
        board.putTile(tile1, 2, 2);
        board.putTile(tile2, 4, 2);
        board.putTile(tile3, 2, 1);
        board.putTile(tile4, 3, 1);
        board.putTile(tile5, 2, 3);
        board.putTile(tile6, 3, 3);
        board.setProjectTiles(left, middle, right);
        middle.updateMatches(board.getNeighbours(board.getField(3, 2)));
        assertEquals(0, middle.getScore());
    }

    @Test
    public void projectTile6() {
        Board board = new Board();
        ProjectTile left = new ProjectTile(ProjectTileType.AABBCD);
        ProjectTile middle = new ProjectTile(ProjectTileType.AAABBB);
        ProjectTile right = new ProjectTile(ProjectTileType.AAABBC);
        Tile tile1 = new RegularTile(Color.PURPLE, TilePattern.DOTS);
        Tile tile2 = new RegularTile(Color.LIGHT_BLUE, TilePattern.DOTS);
        Tile tile3 = new RegularTile(Color.GREEN, TilePattern.CROSSES);
        Tile tile4 = new RegularTile(Color.PURPLE, TilePattern.PLANTS);
        Tile tile5 = new RegularTile(Color.GREEN, TilePattern.FERN);
        Tile tile6 = new RegularTile(Color.LIGHT_BLUE, TilePattern.FERN);
        board.putTile(tile1, 1, 4);
        board.putTile(tile2, 3, 4);
        board.putTile(tile3, 1, 3);
        board.putTile(tile4, 2, 3);
        board.putTile(tile5, 1, 5);
        board.putTile(tile6, 2, 5);
        board.setProjectTiles(left, middle, right);
        left.updateMatches(board.getNeighbours(board.getField(2, 4)));
        assertEquals(5, left.getScore());
    }

    @Test
    public void projectTile7() {
        Board board = new Board();
        ProjectTile left = new ProjectTile(ProjectTileType.AABBCD);
        ProjectTile middle = new ProjectTile(ProjectTileType.AAABBB);
        ProjectTile right = new ProjectTile(ProjectTileType.AAABBC);
        Tile tile1 = new RegularTile(Color.PURPLE, TilePattern.DOTS);
        Tile tile2 = new RegularTile(Color.LIGHT_BLUE, TilePattern.DOTS);
        Tile tile3 = new RegularTile(Color.GREEN, TilePattern.CROSSES);
        Tile tile4 = new RegularTile(Color.PURPLE, TilePattern.PLANTS);
        Tile tile5 = new RegularTile(Color.MAGENTA, TilePattern.FERN);
        Tile tile6 = new RegularTile(Color.LIGHT_BLUE, TilePattern.FERN);
        board.putTile(tile1, 1, 4);
        board.putTile(tile2, 3, 4);
        board.putTile(tile3, 1, 3);
        board.putTile(tile4, 2, 3);
        board.putTile(tile5, 1, 5);
        board.putTile(tile6, 2, 5);
        board.setProjectTiles(left, middle, right);
        left.updateMatches(board.getNeighbours(board.getField(2, 4)));
        assertEquals(8, left.getScore());
    }

    @Test
    public void projectTile8() {
        Board board = new Board();
        ProjectTile left = new ProjectTile(ProjectTileType.AAABBC);
        ProjectTile middle = new ProjectTile(ProjectTileType.AAABBB);
        ProjectTile right = new ProjectTile(ProjectTileType.AAABBC);
        Tile tile1 = new RegularTile(Color.PURPLE, TilePattern.DOTS);
        Tile tile2 = new RegularTile(Color.LIGHT_BLUE, TilePattern.DOTS);
        Tile tile3 = new RegularTile(Color.GREEN, TilePattern.CROSSES);
        Tile tile4 = new RegularTile(Color.GREEN, TilePattern.DOTS);
        Tile tile5 = new RegularTile(Color.GREEN, TilePattern.FERN);
        Tile tile6 = new RegularTile(Color.LIGHT_BLUE, TilePattern.FERN);
        board.putTile(tile1, 1, 4);
        board.putTile(tile2, 3, 4);
        board.putTile(tile3, 1, 3);
        board.putTile(tile4, 2, 3);
        board.putTile(tile5, 1, 5);
        board.putTile(tile6, 2, 5);
        board.setProjectTiles(left, middle, right);
        left.updateMatches(board.getNeighbours(board.getField(2, 4)));
        assertEquals(11, left.getScore());
    }
    @Test
    public void projectTile9() {
        Board board = new Board();
        ProjectTile left = new ProjectTile(ProjectTileType.AAABBC);
        ProjectTile middle = new ProjectTile(ProjectTileType.AAABBB);
        ProjectTile right = new ProjectTile(ProjectTileType.AAABBC);
        Tile tile1 = new RegularTile(Color.PURPLE, TilePattern.DOTS);
        Tile tile2 = new RegularTile(Color.GREEN, TilePattern.CROSSES);
        Tile tile3 = new RegularTile(Color.GREEN, TilePattern.CROSSES);
        Tile tile4 = new RegularTile(Color.GREEN, TilePattern.DOTS);
        Tile tile5 = new RegularTile(Color.GREEN, TilePattern.FERN);
        Tile tile6 = new RegularTile(Color.LIGHT_BLUE, TilePattern.FERN);
        board.putTile(tile1, 1, 4);
        board.putTile(tile2, 3, 4);
        board.putTile(tile3, 1, 3);
        board.putTile(tile4, 2, 3);
        board.putTile(tile5, 1, 5);
        board.putTile(tile6, 2, 5);
        board.setProjectTiles(left, middle, right);
        left.updateMatches(board.getNeighbours(board.getField(2, 4)));
        assertEquals(0, left.getScore());
    }
    @Test
    public void projectTile10() {
        Board board = new Board();
        ProjectTile left = new ProjectTile(ProjectTileType.AAABBC);
        ProjectTile middle = new ProjectTile(ProjectTileType.AAABBB);
        ProjectTile right = new ProjectTile(ProjectTileType.ABCDEF);
        Tile tile1 = new RegularTile(Color.PURPLE, TilePattern.DOTS);
        Tile tile2 = new RegularTile(Color.GREEN, TilePattern.CROSSES);
        Tile tile3 = new RegularTile(Color.MAGENTA, TilePattern.STRIPES);
        Tile tile4 = new RegularTile(Color.YELLOW, TilePattern.FLOWERS);
        Tile tile5 = new RegularTile(Color.DARK_BLUE, TilePattern.PLANTS);
        Tile tile6 = new RegularTile(Color.LIGHT_BLUE, TilePattern.FERN);
        board.putTile(tile1, 4, 2);
        board.putTile(tile2, 5, 2);
        board.putTile(tile3, 3, 3);
        board.putTile(tile4, 5, 3);
        board.putTile(tile5, 4, 4);
        board.putTile(tile6, 5, 4);
        board.setProjectTiles(left, middle, right);
        right.updateMatches(board.getNeighbours(board.getField(4, 3)));
        assertEquals(15, right.getScore());
    }

    @Test
    public void projectTile11() {
        Board board = new Board();
        ProjectTile left = new ProjectTile(ProjectTileType.AAABBC);
        ProjectTile middle = new ProjectTile(ProjectTileType.AAABBB);
        ProjectTile right = new ProjectTile(ProjectTileType.AAABBC);
        Tile tile1 = new RegularTile(Color.PURPLE, TilePattern.DOTS);
        Tile tile2 = new RegularTile(Color.LIGHT_BLUE, TilePattern.DOTS);
        Tile tile3 = new RegularTile(Color.GREEN, TilePattern.CROSSES);
        Tile tile4 = new RegularTile(Color.PURPLE, TilePattern.DOTS);
        Tile tile5 = new RegularTile(Color.GREEN, TilePattern.FERN);
        board.putTile(tile1, 1, 4);
        board.putTile(tile2, 3, 4);
        board.putTile(tile3, 1, 3);
        board.putTile(tile4, 2, 3);
        board.putTile(tile5, 1, 5);
        board.setProjectTiles(left, middle, right);
        left.updateMatches(board.getNeighbours(board.getField(2, 4)));
        assertEquals(0, left.getScore());
    }


}
