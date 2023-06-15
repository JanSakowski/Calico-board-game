package gamepackage;

import java.io.Serializable;

/**
 * Represents a field on the board
 */
public class Field implements Serializable {
    private Tile tile;
    private Button button;
    private final int x;
    private final int y;
    private boolean catGroup;
    private boolean colorGroup;

    public Field(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * X position of the field
     */
    public int getX() {
        return x;
    }

    /**
     * Y position of the group
     */
    public int getY() {
        return y;
    }

    /**
     * If the field belongs to a group of fields containing a cat button
     */
    public boolean isCatGroup() {
        return catGroup;
    }

    /**
     * If the field belongs to a group of fields containing a color button
     */
    public boolean isColorGroup() {
        return colorGroup;
    }

    /**
     * Sets the field to be a part of a group of fields containing a cat button
     */
    public void setCatGroup() {
        catGroup = true;
    }

    /**
     * Sets the field to be a part of a group of fields containing a color button
     */
    public void setColorGroup() {
        colorGroup = true;
    }

    /**
     * If the field contains a tile
     */
    public boolean hasTile() {
        return tile != null;
    }

    /**
     * If the field contains a regular tile
     */
    public boolean hasRegularTile() {
        return tile != null && tile instanceof RegularTile;
    }

    /**
     * If the field contains a project tile
     */
    public boolean hasProjectTile() {
        return tile != null && tile instanceof ProjectTile;
    }

    /**
     * If the field contains a button
     */
    public boolean hasButton() {
        return button != null;
    }

    /**
     * If the field contains a cat button
     */
    public boolean hasCatButton() {
        return button != null && button instanceof CatButton;
    }

    /**
     * If the field contains a color button
     */
    public boolean hasColorButton() {
        return button != null && button instanceof ColorButton;
    }

    /**
     * Gets regular tile of a field
     */
    public RegularTile getRegularTile() {
        // If the field contains the project tile
        if (x == 2 && y ==4) return null;
        if (x == 4 && y ==3) return null;
        if (x == 3 && y ==1) return null;
        return (RegularTile) tile;
    }

    /**
     * Gets project tile of a field
     */
    public ProjectTile getProjectTile() {
        return (ProjectTile) tile;
    }

    /**
     * Gets cat button of a field
     */
    public CatButton getCatButton() {
        return (CatButton) button;
    }

    /**
     * Gets color button of a field
     */
    public ColorButton getColorButton() {
        return (ColorButton) button;
    }

    /**
     * Gets a button of a field
     */
    public Button getButton() {
        return button;
    }

    /**
     * Puts tile on a field if possible (field doesn't contain a tile)
     *
     * @param tile tile to put
     * @return if putting the tile was successful
     */
    public boolean putTile(Tile tile) {
        if (hasTile())
            return false;
        this.tile = tile;
        return true;
    }

    /**
     * Puts button on a field (checks are in the board class)
     *
     * @param button button to put
     */
    public void putButton(Button button) {
        this.button = button;
    }
}
