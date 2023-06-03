package com.example.calico.game;

import com.example.calico.game.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
public class CatTests {
    @Test
    public void cattest1() {
        Board board = new Board();
        ProjectTile left = new ProjectTile(ProjectTileType.AAABBC);
        ProjectTile middle = new ProjectTile(ProjectTileType.AAABBB);
        ProjectTile right = new ProjectTile(ProjectTileType.AAABBC);
        Tile tile1 = new RegularTile(Color.PURPLE, TilePattern.FERN);
        Tile tile2 = new RegularTile(Color.LIGHT_BLUE, TilePattern.DOTS);
        Tile tile3 = new RegularTile(Color.GREEN, TilePattern.DOTS);
        Tile tile4 = new RegularTile(Color.PURPLE, TilePattern.DOTS);
        Tile tile5 = new RegularTile(Color.GREEN, TilePattern.FERN);
        Tile tile6 = new RegularTile(Color.LIGHT_BLUE, TilePattern.FERN);
        CatButton catButton = new CatButton(Cat.CALLIE);
        TilePattern[] patterns = {TilePattern.DOTS, TilePattern.CROSSES};

        board.setProjectTiles(left, middle, right);

        board.putTile(tile1, 0, 0);
        board.putTile(tile2, 0, 1);
        board.putTile(tile3, 1, 0);
        board.putTile(tile4, 2, 3);
        board.putTile(tile5, 1, 5);
        board.putTile(tile6, 2, 5);

        boolean result = board.putCatButton(catButton, 0, 0, patterns);

        assertNotEquals(true, result);
    }

    @Test
    public void cattest2() {
        Board board = new Board();
        ProjectTile left = new ProjectTile(ProjectTileType.AAABBC);
        ProjectTile middle = new ProjectTile(ProjectTileType.AAABBB);
        ProjectTile right = new ProjectTile(ProjectTileType.AAABBC);
        Tile tile1 = new RegularTile(Color.PURPLE, TilePattern.DOTS);
        Tile tile2 = new RegularTile(Color.LIGHT_BLUE, TilePattern.DOTS);
        Tile tile3 = new RegularTile(Color.GREEN, TilePattern.DOTS);
        Tile tile4 = new RegularTile(Color.PURPLE, TilePattern.DOTS);
        Tile tile5 = new RegularTile(Color.GREEN, TilePattern.FERN);
        Tile tile6 = new RegularTile(Color.LIGHT_BLUE, TilePattern.FERN);
        CatButton catButton = new CatButton(Cat.CALLIE);
        TilePattern[] patterns = {TilePattern.DOTS, TilePattern.CROSSES};

        board.setProjectTiles(left, middle, right);

        board.putTile(tile1, 0, 0);
        board.putTile(tile2, 0, 1);
        board.putTile(tile3, 1, 0);
        board.putTile(tile4, 2, 3);
        board.putTile(tile5, 1, 5);
        board.putTile(tile6, 2, 5);

        boolean result = board.putCatButton(catButton, 0, 0, patterns);

        assertTrue(result);
    }

    @Test
    public void cattest3() {
        Board board = new Board();
        ProjectTile left = new ProjectTile(ProjectTileType.AAABBC);
        ProjectTile middle = new ProjectTile(ProjectTileType.AAABBB);
        ProjectTile right = new ProjectTile(ProjectTileType.AAABBC);
        Tile tile1 = new RegularTile(Color.PURPLE, TilePattern.DOTS);
        Tile tile2 = new RegularTile(Color.LIGHT_BLUE, TilePattern.STRIPES);
        Tile tile3 = new RegularTile(Color.GREEN, TilePattern.STRIPES);
        Tile tile4 = new RegularTile(Color.PURPLE, TilePattern.STRIPES);
        Tile tile5 = new RegularTile(Color.GREEN, TilePattern.FERN);
        Tile tile6 = new RegularTile(Color.LIGHT_BLUE, TilePattern.FERN);
        CatButton catButton = new CatButton(Cat.CALLIE);
        TilePattern[] patterns = {TilePattern.DOTS, TilePattern.CROSSES};

        board.setProjectTiles(left, middle, right);

        board.putTile(tile1, 0, 0);
        board.putTile(tile2, 4, 4);
        board.putTile(tile3, 5, 4);
        board.putTile(tile4, 4, 5);
        board.putTile(tile5, 1, 5);
        board.putTile(tile6, 2, 5);

        boolean result = board.putCatButton(catButton, 4, 4, patterns);
        assertNotEquals(true, result);
    }

    @Test
    public void cattest4() {
        Board board = new Board();
        ProjectTile left = new ProjectTile(ProjectTileType.AAABBC);
        ProjectTile middle = new ProjectTile(ProjectTileType.AAABBB);
        ProjectTile right = new ProjectTile(ProjectTileType.AAABBC);
        Tile tile1 = new RegularTile(Color.PURPLE, TilePattern.DOTS);
        Tile tile2 = new RegularTile(Color.LIGHT_BLUE, TilePattern.DOTS);
        Tile tile3 = new RegularTile(Color.GREEN, TilePattern.DOTS);
        Tile tile4 = new RegularTile(Color.PURPLE, TilePattern.DOTS);
        Tile tile5 = new RegularTile(Color.GREEN, TilePattern.FERN);
        Tile tile6 = new RegularTile(Color.LIGHT_BLUE, TilePattern.FERN);
        CatButton catButton = new CatButton(Cat.CALLIE);
        TilePattern[] patterns = {TilePattern.DOTS, TilePattern.CROSSES};

        board.setProjectTiles(left, middle, right);

        board.putTile(tile1, 1, 0);
        board.putTile(tile2, 1, 1);
        board.putTile(tile3, 0, 1);
        board.putTile(tile4, 2, 3);
        board.putTile(tile5, 1, 5);
        board.putTile(tile6, 2, 5);

        boolean result = board.putCatButton(catButton, 1, 0, patterns);

        assertTrue(result);

    }

    @Test
    public void cattest5() {
        Board board = new Board();
        ProjectTile left = new ProjectTile(ProjectTileType.AAABBC);
        ProjectTile middle = new ProjectTile(ProjectTileType.AAABBB);
        ProjectTile right = new ProjectTile(ProjectTileType.AAABBC);
        Tile tile1 = new RegularTile(Color.PURPLE, TilePattern.FERN);
        Tile tile2 = new RegularTile(Color.LIGHT_BLUE, TilePattern.DOTS);
        Tile tile3 = new RegularTile(Color.GREEN, TilePattern.DOTS);
        Tile tile4 = new RegularTile(Color.PURPLE, TilePattern.DOTS);
        Tile tile5 = new RegularTile(Color.GREEN, TilePattern.FERN);
        Tile tile6 = new RegularTile(Color.LIGHT_BLUE, TilePattern.FERN);
        CatButton catButton = new CatButton(Cat.CALLIE);
        TilePattern[] patterns = {TilePattern.DOTS, TilePattern.CROSSES};

        board.setProjectTiles(left, middle, right);

        board.putTile(tile1, 0, 0);
        board.putTile(tile2, 0, 1);
        board.putTile(tile3, 1, 0);
        board.putTile(tile4, 2, 3);
        board.putTile(tile5, 1, 5);
        board.putTile(tile6, 2, 5);

        boolean result = board.putCatButton(catButton, 2, 2, patterns);

        assertNotEquals(true, result);

    }

    @Test
    public void cattest6() {
        Board board = new Board();
        ProjectTile left = new ProjectTile(ProjectTileType.AAABBC);
        ProjectTile middle = new ProjectTile(ProjectTileType.AAABBB);
        ProjectTile right = new ProjectTile(ProjectTileType.AAABBC);
        Tile tile1 = new RegularTile(Color.PURPLE, TilePattern.FERN);
        Tile tile2 = new RegularTile(Color.LIGHT_BLUE, TilePattern.DOTS);
        Tile tile3 = new RegularTile(Color.GREEN, TilePattern.DOTS);
        Tile tile4 = new RegularTile(Color.PURPLE, TilePattern.DOTS);
        Tile tile5 = new RegularTile(Color.GREEN, TilePattern.FERN);
        Tile tile6 = new RegularTile(Color.LIGHT_BLUE, TilePattern.FERN);
        CatButton catButton = new CatButton(Cat.CALLIE);
        TilePattern[] patterns = {TilePattern.DOTS, TilePattern.CROSSES};

        board.setProjectTiles(left, middle, right);

        board.putTile(tile1, 0, 0);
        board.putTile(tile2, 0, 1);
        board.putTile(tile3, 1, 0);
        board.putTile(tile4, 2, 3);
        board.putTile(tile5, 1, 5);
        board.putTile(tile6, 2, 5);

        boolean result = board.putCatButton(catButton, 2, 4, patterns);

        assertNotEquals(true, result);

    }

    @Test
    public void cattest7() {
        Board board = new Board();
        ProjectTile left = new ProjectTile(ProjectTileType.AAABBC);
        ProjectTile middle = new ProjectTile(ProjectTileType.AAABBB);
        ProjectTile right = new ProjectTile(ProjectTileType.AAABBC);
        Tile tile1 = new RegularTile(Color.PURPLE, TilePattern.FERN);
        Tile tile2 = new RegularTile(Color.LIGHT_BLUE, TilePattern.DOTS);
        Tile tile3 = new RegularTile(Color.GREEN, TilePattern.DOTS);
        Tile tile4 = new RegularTile(Color.PURPLE, TilePattern.DOTS);
        Tile tile5 = new RegularTile(Color.GREEN, TilePattern.FERN);
        Tile tile6 = new RegularTile(Color.LIGHT_BLUE, TilePattern.FERN);
        Tile tile7 = new RegularTile(Color.PURPLE, TilePattern.DOTS);
        CatButton catButton = new CatButton(Cat.CALLIE);
        TilePattern[] patterns = {TilePattern.DOTS, TilePattern.CROSSES};

        board.setProjectTiles(left, middle, right);

        board.putTile(tile1, 0, 0);
        board.putTile(tile2, 0, 1);
        board.putTile(tile3, 1, 0);
        board.putTile(tile4, 2, 3);
        board.putTile(tile5, 1, 5);
        board.putTile(tile6, 2, 5);
        board.putTile(tile7, 6, 6);

        boolean result = board.putCatButton(catButton, 6, 6, patterns);

        assertNotEquals(true, result);

    }

    @Test
    public void cattest8() {
        Board board = new Board();
        ProjectTile left = new ProjectTile(ProjectTileType.AAABBC);
        ProjectTile middle = new ProjectTile(ProjectTileType.AAABBB);
        ProjectTile right = new ProjectTile(ProjectTileType.AAABBC);
        Tile tile1 = new RegularTile(Color.PURPLE, TilePattern.FERN);
        Tile tile2 = new RegularTile(Color.LIGHT_BLUE, TilePattern.DOTS);
        Tile tile3 = new RegularTile(Color.GREEN, TilePattern.DOTS);
        Tile tile4 = new RegularTile(Color.PURPLE, TilePattern.DOTS);
        Tile tile5 = new RegularTile(Color.GREEN, TilePattern.FERN);
        Tile tile6 = new RegularTile(Color.LIGHT_BLUE, TilePattern.FERN);
        CatButton catButton = new CatButton(Cat.CALLIE);
        TilePattern[] patterns = {TilePattern.DOTS, TilePattern.CROSSES};

        board.setProjectTiles(left, middle, right);

        board.putTile(tile1, 0, 0);
        board.putTile(tile2, 0, 1);
        board.putTile(tile3, 1, 0);
        board.putTile(tile4, 2, 3);
        board.putTile(tile5, 1, 5);
        board.putTile(tile6, 2, 5);

        boolean result = board.putCatButton(catButton, 4, 5, patterns);

        assertNotEquals(true, result);
    }

    @Test
    public void cattest9() {
        Board board = new Board();
        ProjectTile left = new ProjectTile(ProjectTileType.AAABBC);
        ProjectTile middle = new ProjectTile(ProjectTileType.AAABBB);
        ProjectTile right = new ProjectTile(ProjectTileType.AAABBC);
        Tile tile1 = new RegularTile(Color.PURPLE, TilePattern.STRIPES);
        Tile tile2 = new RegularTile(Color.LIGHT_BLUE, TilePattern.STRIPES);
        Tile tile3 = new RegularTile(Color.GREEN, TilePattern.STRIPES);
        Tile tile4 = new RegularTile(Color.PURPLE, TilePattern.STRIPES);
        Tile tile5 = new RegularTile(Color.GREEN, TilePattern.STRIPES);
        CatButton catButton = new CatButton(Cat.LEO);
        TilePattern[] patterns = {TilePattern.DOTS, TilePattern.STRIPES};

        board.setProjectTiles(left, middle, right);

        board.putTile(tile1, 1, 1);
        board.putTile(tile2, 2, 1);
        board.putTile(tile3, 3, 1);
        board.putTile(tile4, 4, 1);
        board.putTile(tile5, 5, 1);

        boolean result = board.putCatButton(catButton, 3, 1, patterns);
        assertTrue(result);
    }

    @Test
    public void testcat10() {
        Board board = new Board();
        ProjectTile left = new ProjectTile(ProjectTileType.AAABBC);
        ProjectTile middle = new ProjectTile(ProjectTileType.AAABBB);
        ProjectTile right = new ProjectTile(ProjectTileType.AAABBC);
        Tile tile1 = new RegularTile(Color.PURPLE, TilePattern.STRIPES);
        Tile tile2 = new RegularTile(Color.LIGHT_BLUE, TilePattern.STRIPES);
        Tile tile3 = new RegularTile(Color.GREEN, TilePattern.STRIPES);
        Tile tile4 = new RegularTile(Color.PURPLE, TilePattern.STRIPES);
        Tile tile5 = new RegularTile(Color.GREEN, TilePattern.STRIPES);
        CatButton catButton = new CatButton(Cat.TECOLOTE);
        TilePattern[] patterns = {TilePattern.DOTS, TilePattern.STRIPES};

        board.setProjectTiles(left, middle, right);

        board.putTile(tile1, 1, 3);
        board.putTile(tile2, 2, 3);
        board.putTile(tile3, 3, 3);
        board.putTile(tile4, 3, 3);
        board.putTile(tile5, 5, 3);

        boolean result = board.putCatButton(catButton, 3, 3, patterns);
        assertNotEquals(true, result);
    }

    @Test
    public void testcat11() {
        Board board = new Board();
        ProjectTile left = new ProjectTile(ProjectTileType.AAABBC);
        ProjectTile middle = new ProjectTile(ProjectTileType.AAABBB);
        ProjectTile right = new ProjectTile(ProjectTileType.AAABBC);
        Tile tile1 = new RegularTile(Color.PURPLE, TilePattern.FLOWERS);
        Tile tile2 = new RegularTile(Color.LIGHT_BLUE, TilePattern.FLOWERS);
        Tile tile3 = new RegularTile(Color.GREEN, TilePattern.FLOWERS);
        Tile tile4 = new RegularTile(Color.PURPLE, TilePattern.FLOWERS);
        Tile tile5 = new RegularTile(Color.GREEN, TilePattern.FLOWERS);
        CatButton catButton = new CatButton(Cat.ALMOND);
        TilePattern[] patterns = {TilePattern.DOTS, TilePattern.FLOWERS};

        board.setProjectTiles(left, middle, right);

        board.putTile(tile1, 3, 3);
        board.putTile(tile2, 3, 4);
        board.putTile(tile3, 4, 4);
        board.putTile(tile4, 2, 5);
        board.putTile(tile5, 3, 5);

        boolean result = board.putCatButton(catButton, 3, 3, patterns);
        assertTrue(result);
    }

    @Test
    public void testcat12() {
        Board board = new Board();
        ProjectTile left = new ProjectTile(ProjectTileType.AAABBC);
        ProjectTile middle = new ProjectTile(ProjectTileType.AAABBB);
        ProjectTile right = new ProjectTile(ProjectTileType.AAABBC);
        Tile tile1 = new RegularTile(Color.PURPLE, TilePattern.FLOWERS);
        Tile tile2 = new RegularTile(Color.LIGHT_BLUE, TilePattern.FLOWERS);
        Tile tile3 = new RegularTile(Color.GREEN, TilePattern.FLOWERS);
        Tile tile4 = new RegularTile(Color.PURPLE, TilePattern.FLOWERS);
        Tile tile5 = new RegularTile(Color.GREEN, TilePattern.FLOWERS);
        CatButton catButton = new CatButton(Cat.ALMOND);
        TilePattern[] patterns = {TilePattern.DOTS, TilePattern.FLOWERS};

        board.setProjectTiles(left, middle, right);

        board.putTile(tile1, 3, 2);
        board.putTile(tile2, 4, 2);
        board.putTile(tile3, 2, 3);
        board.putTile(tile4, 3, 3);
        board.putTile(tile5, 4, 3);


        boolean result = board.putCatButton(catButton, 2, 3, patterns);
        assertNotEquals(true, result);
    }

    @Test
    public void cattest13() {
        Board board = new Board();
        ProjectTile left = new ProjectTile(ProjectTileType.AAABBC);
        ProjectTile middle = new ProjectTile(ProjectTileType.AAABBB);
        ProjectTile right = new ProjectTile(ProjectTileType.AAABBC);
        Tile tile1 = new RegularTile(Color.PURPLE, TilePattern.FERN);
        Tile tile2 = new RegularTile(Color.LIGHT_BLUE, TilePattern.DOTS);
        Tile tile3 = new RegularTile(Color.GREEN, TilePattern.DOTS);
        Tile tile4 = new RegularTile(Color.PURPLE, TilePattern.DOTS);
        Tile tile5 = new RegularTile(Color.GREEN, TilePattern.FERN);
        Tile tile6 = new RegularTile(Color.LIGHT_BLUE, TilePattern.FERN);
        Tile tile7 = new RegularTile(Color.MAGENTA, TilePattern.PLANTS);
        Tile tile8 = new RegularTile(Color.PURPLE, TilePattern.FLOWERS);
        Tile tile9 = new RegularTile(Color.LIGHT_BLUE, TilePattern.DOTS);
        Tile tile10 = new RegularTile(Color.DARK_BLUE, TilePattern.DOTS);
        Tile tile11 = new RegularTile(Color.YELLOW, TilePattern.PLANTS);
        Tile tile12 = new RegularTile(Color.YELLOW, TilePattern.FERN);

        CatButton catButton = new CatButton(Cat.ALMOND);
        TilePattern[] patterns = {TilePattern.FERN, TilePattern.STRIPES};

        board.setProjectTiles(left, middle, right);

        board.putTile(tile1, 0, 0);
        board.putTile(tile2, 0, 1);
        board.putTile(tile3, 1, 0);
        board.putTile(tile4, 2, 3);
        board.putTile(tile5, 1, 5);
        board.putTile(tile6, 2, 5);
        board.putTile(tile7, 1, 1);
        board.putTile(tile8, 1, 2);
        board.putTile(tile9, 2, 1);
        board.putTile(tile10, 2, 2);
        board.putTile(tile11, 2, 0);
        board.putTile(tile12, 0, 2);

        boolean result = board.putCatButton(catButton, 4, 5, patterns);

        assertNotEquals(true, result);

    }

    @Test
    public void cattest14() {
        Board board = new Board();
        ProjectTile left = new ProjectTile(ProjectTileType.AAABBC);
        ProjectTile middle = new ProjectTile(ProjectTileType.AAABBB);
        ProjectTile right = new ProjectTile(ProjectTileType.AAABBC);
        Tile tile1 = new RegularTile(Color.PURPLE, TilePattern.FERN);
        Tile tile2 = new RegularTile(Color.LIGHT_BLUE, TilePattern.DOTS);
        Tile tile3 = new RegularTile(Color.GREEN, TilePattern.PLANTS);
        Tile tile4 = new RegularTile(Color.PURPLE, TilePattern.PLANTS);
        Tile tile5 = new RegularTile(Color.GREEN, TilePattern.FERN);
        Tile tile6 = new RegularTile(Color.LIGHT_BLUE, TilePattern.FERN);
        Tile tile7 = new RegularTile(Color.MAGENTA, TilePattern.PLANTS);
        Tile tile8 = new RegularTile(Color.PURPLE, TilePattern.PLANTS);
        Tile tile9 = new RegularTile(Color.LIGHT_BLUE, TilePattern.DOTS);
        Tile tile10 = new RegularTile(Color.DARK_BLUE, TilePattern.PLANTS);
        Tile tile11 = new RegularTile(Color.YELLOW, TilePattern.PLANTS);
        Tile tile12 = new RegularTile(Color.YELLOW, TilePattern.FERN);

        CatButton catButton = new CatButton(Cat.ALMOND);
        TilePattern[] patterns = {TilePattern.PLANTS, TilePattern.STRIPES};

        board.setProjectTiles(left, middle, right);

        board.putTile(tile1, 0, 0);
        board.putTile(tile2, 0, 1);
        board.putTile(tile3, 1, 3);
        board.putTile(tile4, 2, 3);
        board.putTile(tile5, 1, 5);
        board.putTile(tile6, 2, 5);
        board.putTile(tile7, 1, 1);
        board.putTile(tile8, 1, 2);
        board.putTile(tile9, 2, 1);
        board.putTile(tile10, 2, 2);
        board.putTile(tile11, 2, 0);
        board.putTile(tile12, 0, 2);

        boolean result = board.putCatButton(catButton, 1, 2, patterns);

        assertNotEquals(false, result);

    }

    @Test
    public void cattest15() {
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
        Tile tile8 = new RegularTile(Color.PURPLE, TilePattern.PLANTS);
        Tile tile9 = new RegularTile(Color.LIGHT_BLUE, TilePattern.DOTS);
        Tile tile10 = new RegularTile(Color.DARK_BLUE, TilePattern.PLANTS);
        Tile tile11 = new RegularTile(Color.YELLOW, TilePattern.PLANTS);
        Tile tile12 = new RegularTile(Color.YELLOW, TilePattern.FERN);

        CatButton catButton = new CatButton(Cat.ALMOND);
        TilePattern[] patterns = {TilePattern.PLANTS, TilePattern.STRIPES};

        board.setProjectTiles(left, middle, right);

        board.putTile(tile1, 0, 0);
        board.putTile(tile2, 0, 1);
        board.putTile(tile3, 1, 3);
        board.putTile(tile4, 2, 3);
        board.putTile(tile5, 1, 5);
        board.putTile(tile6, 2, 5);
        board.putTile(tile7, 1, 1);
        board.putTile(tile8, 1, 2);
        board.putTile(tile9, 2, 1);
        board.putTile(tile10, 2, 2);
        board.putTile(tile11, 2, 0);
        board.putTile(tile12, 0, 2);

        boolean result = board.putCatButton(catButton, 1, 2, patterns);

        assertNotEquals(false, result);

    }

    @Test
    public void cattest16() {
        Board board = new Board();
        ProjectTile left = new ProjectTile(ProjectTileType.AAABBC);
        ProjectTile middle = new ProjectTile(ProjectTileType.AAABBB);
        ProjectTile right = new ProjectTile(ProjectTileType.AAABBC);
        Tile tile1 = new RegularTile(Color.PURPLE, TilePattern.DOTS);
        Tile tile2 = new RegularTile(Color.LIGHT_BLUE, TilePattern.DOTS);
        Tile tile3 = new RegularTile(Color.GREEN, TilePattern.DOTS);
        Tile tile4 = new RegularTile(Color.PURPLE, TilePattern.DOTS);
        Tile tile5 = new RegularTile(Color.GREEN, TilePattern.FLOWERS);
        Tile tile6 = new RegularTile(Color.LIGHT_BLUE, TilePattern.PLANTS);
        Tile tile7 = new RegularTile(Color.MAGENTA, TilePattern.PLANTS);
        Tile tile8 = new RegularTile(Color.PURPLE, TilePattern.DOTS);
        Tile tile9 = new RegularTile(Color.LIGHT_BLUE, TilePattern.DOTS);
        Tile tile10 = new RegularTile(Color.DARK_BLUE, TilePattern.PLANTS);
        Tile tile11 = new RegularTile(Color.YELLOW, TilePattern.STRIPES);
        Tile tile12 = new RegularTile(Color.YELLOW, TilePattern.FERN);

        CatButton catButton = new CatButton(Cat.ALMOND);
        TilePattern[] patterns = {TilePattern.DOTS, TilePattern.FLOWERS};

        board.setProjectTiles(left, middle, right);

        board.putTile(tile1, 0, 3);
        board.putTile(tile2, 0, 1);
        board.putTile(tile3, 1, 3);
        board.putTile(tile4, 2, 3);
        board.putTile(tile5, 1, 5);
        board.putTile(tile6, 2, 5);
        board.putTile(tile7, 1, 1);
        board.putTile(tile8, 1, 2);
        board.putTile(tile9, 0, 2);
        board.putTile(tile10, 2, 2);
        board.putTile(tile11, 2, 0);
        board.putTile(tile12, 0, 2);

        boolean result = board.putCatButton(catButton, 1, 3, patterns);

        assertTrue(result);

    }

    @Test
    public void cattest17() {
        Board board = new Board();
        ProjectTile left = new ProjectTile(ProjectTileType.AAABBC);
        ProjectTile middle = new ProjectTile(ProjectTileType.AAABBB);
        ProjectTile right = new ProjectTile(ProjectTileType.AAABBC);
        Tile tile1 = new RegularTile(Color.PURPLE, TilePattern.DOTS);
        Tile tile2 = new RegularTile(Color.LIGHT_BLUE, TilePattern.DOTS);
        Tile tile3 = new RegularTile(Color.GREEN, TilePattern.DOTS);
        Tile tile4 = new RegularTile(Color.PURPLE, TilePattern.DOTS);
        Tile tile5 = new RegularTile(Color.GREEN, TilePattern.FLOWERS);
        Tile tile6 = new RegularTile(Color.LIGHT_BLUE, TilePattern.PLANTS);
        Tile tile7 = new RegularTile(Color.MAGENTA, TilePattern.PLANTS);
        Tile tile8 = new RegularTile(Color.PURPLE, TilePattern.DOTS);
        Tile tile9 = new RegularTile(Color.LIGHT_BLUE, TilePattern.DOTS);
        Tile tile10 = new RegularTile(Color.DARK_BLUE, TilePattern.PLANTS);
        Tile tile11 = new RegularTile(Color.YELLOW, TilePattern.STRIPES);
        Tile tile12 = new RegularTile(Color.YELLOW, TilePattern.FERN);

        CatButton catButton = new CatButton(Cat.ALMOND);
        TilePattern[] patterns = {TilePattern.PLANTS, TilePattern.FLOWERS};

        board.setProjectTiles(left, middle, right);

        board.putTile(tile1, 0, 3);
        board.putTile(tile2, 0, 1);
        board.putTile(tile3, 1, 3);
        board.putTile(tile4, 2, 3);
        board.putTile(tile5, 1, 5);
        board.putTile(tile6, 2, 5);
        board.putTile(tile7, 1, 1);
        board.putTile(tile8, 1, 2);
        board.putTile(tile9, 3, 2);
        board.putTile(tile10, 2, 2);
        board.putTile(tile11, 2, 0);
        board.putTile(tile12, 0, 2);

        boolean result = board.putCatButton(catButton, 1, 3, patterns);

        assertNotEquals(true, result);

    }
}
