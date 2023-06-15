package gamepackage;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Represents a player
 */
public class Player implements Scored, Serializable {
    private final ArrayList<RegularTile> tilesOnHand;
    private final ProjectTile[] projectTiles;
    private final Board board;
    private final int index;

    /**
     * game.Player's board
     */
    public Board getBoard() {
        return board;
    }

    /**
     * Random 4 project tiles for the player to choose 3 from
     */
    public ProjectTile[] getProjectTiles() {
        return projectTiles;
    }

    /**
     * Current two tiles on players hand
     */
    public ArrayList<RegularTile> getTilesOnHand() {
        return tilesOnHand;
    }

    /**
     * Index of the player
     */
    public int getIndex() {
        return index;
    }

    /**
     * Initializes a new player with a given index, board and project tiles
     *
     * @param index        index of the player
     * @param board        empty board
     * @param projectTiles 4 project tiles to choose from
     */
    public Player(int index, Board board, ProjectTile[] projectTiles) {
        this.index = index;
        this.board = board;
        this.projectTiles = projectTiles;
        tilesOnHand = new ArrayList<>();
    }

    /**
     * Sets project tiles on board after checking for duplicates and if indices are in bound
     *
     * @param left   index of the project tile to place on the left
     * @param center index of the project tile to place in the middle
     * @param right  index of the project tile to place on the right
     * @return if placing the project tiles was successful
     */
    public boolean setProjectTiles(int left, int center, int right) {
        if (left == center || center == right || left == right || left < 0 || center < 0 || right < 0 || left > 3 ||
                center > 3 || right > 3)
            return false;
        board.setProjectTiles(projectTiles[left], projectTiles[center], projectTiles[right]);
        return true;
    }

    /**
     * Puts tile on board after checking if the player has two tiles and the spot is correct, removes the placed tile
     *
     * @param tileIndex index of the tile to place
     * @param x         x position of the tile
     * @param y         y position of the tile
     * @return if placing the tile was successful
     */
    public boolean putTile(int tileIndex, int x, int y) {
        if (tilesOnHand.size() != 2)
            return false;
        boolean result = board.putTile(tilesOnHand.get(tileIndex), x, y);
        if (result)
            tilesOnHand.remove(tileIndex);
        return result;
    }

    /**
     * Adds tile to the players hand after checking if player has less than two tiles
     *
     * @param tile tile to add
     * @return if adding the tile was successful
     */
    public boolean addTile(RegularTile tile) {
        if (tilesOnHand.size() >= 2)
            return false;
        tilesOnHand.add(tile);
        return true;
    }

    /**
     * Tries to put a color button on the board
     *
     * @param x x position of the field
     * @param y y position of the field
     * @return if putting a color button was successful
     */
    public boolean putColorButton(int x, int y) {
        return board.putColorButton(x, y);
    }

    /**
     * Tries to put a rainbow button on the board
     *
     * @param x x position of the field
     * @param y y position of the field
     * @return if putting a color button was successful
     */
    public boolean putRainbowButton(int x, int y) {
        return board.putRainbowButton(x, y);
    }

    /**
     * Tries to put a cat button on the board
     *
     * @param cat board to base the cat button on
     * @param x   x position of the field
     * @param y   y position of the field
     * @return if putting a color button was successful
     */
    public boolean putCatButton(CatBoard cat, int x, int y) {
        return board.putCatButton(new CatButton(cat.getCat()), x, y, cat.getPreferredPatterns());
    }

    @Override
    public int getScore() {
        return board.getScore();
    }
}
