package com.example.calico.game;

import java.io.Serializable;
import java.util.*;

/**
 * Class representing a player's board
 */
public class Board implements Scored, Serializable {
    /**
     * Fields on the board where tiles and buttons can be placed
     */
    private final Field[][] fields;
    /**
     * True if project tiles have been set
     */
    private boolean projectTilesSet;
    /**
     * True if color button of each color has been set
     */
    private boolean allColors;
    /**
     * Map for keeping track of which color buttons has been set
     */
    private final EnumMap<Color, Boolean> colorsSet;

    /**
     * True if rainbow button has been placed
     */
    private boolean rainbowPlaced;

    /**
     * Constructor initializing all fields and map (use getRandom)
     */
    public Board() {
        fields = new Field[7][7];
        for (int i = 0; i < 7; i++)
            for (int j = 0; j < 7; j++)
                fields[i][j] = new Field(j, i);
        colorsSet = new EnumMap<>(Color.class);
        for (Color color : Color.getValuesNoRainbow())
            colorsSet.put(color, false);
    }

    /**
     * Puts tile on the board if target field does not contain a tile
     *
     * @param tile game.Tile to put
     * @param x    x position of the field
     * @param y    y position of the field
     * @return if placing the tile was successful
     */
    public boolean putTile(Tile tile, int x, int y) {
        return fields[y][x].putTile(tile);
    }

    /**
     * Puts rainbow color button on the board if player has placed a color button of each color and the target field
     * contains a regular tile and doesn't contain a button and rainbow button wasn't already placed
     *
     * @param x x position of the field
     * @param y y position of the field
     * @return if placing the button was successful (
     */
    public boolean putRainbowButton(int x, int y) {
        if (!allColors || rainbowPlaced)
            return false;
        Field chosen = fields[y][x];
        if (!chosen.hasRegularTile() || chosen.hasButton())
            return false;
        chosen.putButton(new ColorButton(Color.RAINBOW));
        rainbowPlaced = true;
        return true;
    }

    /**
     * Puts a color button on the board if the target field has a regular tile and does not contain a button and is
     * not a part of a group of tiles that already contain a color button. To do it, it finds the group of tiles with
     * the same color as the target's field tile and if the size of the group is at least three it will place a new
     * button there with the correct color. It updates all the fields of the group so no other color buttons can be
     * placed on it and updates the map to track which color buttons were placed
     *
     * @param x x position of the field
     * @param y y position of the field
     * @return if placing the button was successful
     */
    public boolean putColorButton(int x, int y) {
        Field chosen = fields[y][x];
        if (!chosen.hasRegularTile() || chosen.hasButton() || chosen.isColorGroup())
            return false;
        Color c = chosen.getRegularTile().getColor();
        ArrayList<Field> group = getGroup(chosen, false);
        if (group.size() < 3)
            return false;
        chosen.putButton(new ColorButton(c));
        for (Field field : group)
            field.setColorGroup();
        colorsSet.put(c, true);
        allColors = colorsSet.values().stream().allMatch(b -> b);
        return true;
    }

    /**
     * Gets the length of a horizontal line passing through the field consisting of fields belonging to the group
     *
     * @param field field which the line has to go through
     * @param group group of fields to check
     * @return length of the horizontal line (1 if it is just the field)
     */
    private int horizontalLineLength(Field field, ArrayList<Field> group) {
        int result = 1;
        Field current = getLeft(field);
        while (current != null && group.contains(current)) {
            result++;
            current = getLeft(current);
        }
        current = getRight(field);
        while (current != null && group.contains(current)) {
            result++;
            current = getRight(current);
        }
        return result;
    }

    /**
     * Gets the length of a diagonal line going from top left to bottom right passing through the field consisting of
     * fields belonging to the group
     *
     * @param field field which the line has to go through
     * @param group group of fields to check
     * @return length of the diagonal line (1 if it is just the field)
     */
    private int topLeftBottomRightLineLength(Field field, ArrayList<Field> group) {
        int result = 1;
        Field current = getTopLeft(field);
        while (current != null && group.contains(current)) {
            result++;
            current = getTopLeft(current);
        }
        current = getBottomRight(field);
        while (current != null && group.contains(current)) {
            result++;
            current = getBottomRight(current);
        }
        return result;
    }

    /**
     * Gets the length of a diagonal line going from bottom left to top right passing through the field consisting of
     * fields belonging to the group
     *
     * @param field field which the line has to go through
     * @param group group of fields to check
     * @return length of the diagonal line (1 if it is just the field)
     */
    private int bottomLeftTopRightLineLength(Field field, ArrayList<Field> group) {
        int result = 1;
        Field current = getBottomLeft(field);
        while (current != null && group.contains(current)) {
            result++;
            current = getBottomLeft(current);
        }
        current = getTopRight(field);
        while (current != null && group.contains(current)) {
            result++;
            current = getTopRight(current);
        }
        return result;
    }

    /**
     * Checks if the field is part of a small cluster (three fields in a triangle shape). To do it, it goes through
     * all the neighbours of the field and checks if there exist two neighbours that are next to each other and
     * belong to the group
     *
     * @param field field which has to be part of the cluster
     * @param group group of fields to check
     * @return if the field is part of the cluster
     */
    private boolean isInSmallCluster(Field field, ArrayList<Field> group) {
        Field[] fields = allNeighbours(field);
        for (int i = 0; i < 6; i++) {
            Field left = fields[i];
            Field right = fields[(i + 1) % 6];
            if (left != null && group.contains(left) && right != null && group.contains(right))
                return true;
        }
        return false;
    }

    /**
     * Checks if the field is part of a big cluster (five fields in a trapezoid shape). To do it, it goes through
     * all possible rotations and movements of the shape to check if every field of the shape is in the group
     *
     * @param field field which has to be part of the cluster
     * @param group group of fields to check
     * @return if the field is part of the cluster
     */
    private boolean isInBigCluster(Field field, ArrayList<Field> group) {
        for (int i = 0; i < 6; i++) { //For each rotation
            for (Field check : getBigClusterChecks(field, i)) {
                //For each field that when the shape is centered around them our field will be in the shape
                boolean result = true;
                for (Field f : getBigClusterShape(check, i)) //For each field that is a part of a shape
                    if (f == null || !group.contains(f)) { //If any aren't a part of the group the check fails
                        result = false;
                        break;
                    }
                if (result)
                    return true;
            }
        }
        return false;
    }

    /**
     * Gets all the fields that need to be checked for a given rotation of the cluster shape to check every possible
     * position of the shape
     *
     * @param field    field which has to be part of the cluster
     * @param rotation integer from 0 to 6 representing the rotation. 0 represents horizontal, every next integer
     *                 represents a rotation by additional 60 degrees
     * @return fields that need to be checked
     */
    private Field[] getBigClusterChecks(Field field, int rotation) {
        Field[] result = new Field[5];
        result[0] = field;
        for (int i = 1; i < 5; i++)
            result[i] = getNeighbour(field, (rotation + 1 + i) % 6);
        return result;
    }

    /**
     * Gets all the fields that will be the part of the cluster with a given rotation when the field is the middle
     * bottom field of the shape
     *
     * @param field    middle bottom field
     * @param rotation integer from 0 to 6 representing the rotation. 0 represents horizontal, every next integer
     *                 represents a rotation by additional 60 degree
     * @return fields that will be the part of the cluster
     */
    private Field[] getBigClusterShape(Field field, int rotation) {
        Field[] result = new Field[4];
        for (int i = 0; i < 4; i++)
            result[i] = getNeighbour(field, (rotation + 5 + i) % 6);
        return result;
    }

    /**
     * Puts a cat button on the board if the target field has a regular tile and does not contain a button and is not
     * a part of a group of tiles that already contains a cat button and the pattern of the tile matches either of the
     * cats preferred pattern. To do it, it finds the group of tiles with the same pattern as the target field's tile
     * and applied an appropriate check depending on the cat checking the group size or the line length or performs a
     * cluster check
     *
     * @param button        cat button to place
     * @param x             x position of the field
     * @param y             y position of the field
     * @param catsPreferred cat's preferred patterns
     * @return if placing the button was successful
     */
    public boolean putCatButton(CatButton button, int x, int y, TilePattern[] catsPreferred) {
        Field chosen = fields[y][x];
        if (!chosen.hasRegularTile() || chosen.hasButton() || chosen.isCatGroup())
            return false;
        TilePattern pattern = chosen.getRegularTile().getPattern();
        if (catsPreferred[0] != pattern && catsPreferred[1] != pattern)
            return false;
        ArrayList<Field> group = getGroup(chosen, true);
        Cat c = button.getCat();
        if (c.getShapeType() == ShapeType.N_OR_MORE) {
            if (group.size() < c.getShapeCount())
                return false;
        } else if (c.getShapeType() == ShapeType.LINE) {
            if (horizontalLineLength(chosen, group) < c.getShapeCount() &&
                    topLeftBottomRightLineLength(chosen, group) < c.getShapeCount() &&
                    bottomLeftTopRightLineLength(chosen, group) < c.getShapeCount())
                return false;
        } else {
            if (c.getShapeCount() == 3) {
                if (!isInSmallCluster(chosen, group))
                    return false;
            } else {
                if (!isInBigCluster(chosen, group))
                    return false;
            }
        }
        chosen.putButton(button);
        for (Field field : group)
            field.setCatGroup();
        return true;
    }

    /**
     * Gets group of tiles of either the same color or pattern
     *
     * @param starting  field to start finding the group from
     * @param byPattern searches by patter if true else by color
     * @return group of matching tiles
     */
    public ArrayList<Field> getGroup(Field starting, boolean byPattern) {
        boolean[][] checked = new boolean[7][7];
        Queue<Field> toCheck = new LinkedList<>();
        ArrayList<Field> group = new ArrayList<>();
        Color color = starting.getRegularTile().getColor();
        TilePattern pattern = starting.getRegularTile().getPattern();
        toCheck.add(starting);
        checked[starting.getY()][starting.getX()] = true;
        do {
            Field checking = toCheck.remove();
            Color checkColor = checking.getRegularTile().getColor();
            TilePattern checkPattern = checking.getRegularTile().getPattern();
            if (!byPattern && checkColor != color || byPattern && checkPattern != pattern)
                continue;
            group.add(checking);
            ArrayList<Field> neighbours = getNeighbours(checking);
            for (Field neighbour : neighbours)
                if (neighbour.hasRegularTile() && !checked[neighbour.getY()][neighbour.getX()] &&
                        (!byPattern && !neighbour.isColorGroup() || byPattern && !neighbour.isCatGroup())) {
                    toCheck.add(neighbour);
                    checked[neighbour.getY()][neighbour.getX()] = true;
                }
        } while (!toCheck.isEmpty());
        return group;
    }

    /**
     * Generates a random board (initializes the board and fills the edges with random tiles in such a way so
     * patterns and colors don't end up close to each other
     *
     * @param random random variable
     * @return a random board
     */
    public static Board getRandom(Random random) {
        Board result = new Board();
        result.fields[0][0].putTile(RegularTile.getRandom(random));
        result.fields[0][1].putTile(RegularTile.getRandom(random, result.fields[0][0]));
        for (int i = 2; i < 7; i++)
            result.fields[0][i].putTile(
                    RegularTile.getRandom(random, result.fields[0][i - 1], result.fields[0][i - 2]));
        result.fields[1][6].putTile(RegularTile.getRandom(random, result.fields[0][6], result.fields[0][5]));
        for (int i = 2; i < 7; i++)
            result.fields[i][6].putTile(
                    RegularTile.getRandom(random, result.fields[i - 1][6], result.fields[i - 2][6]));
        result.fields[6][5].putTile(RegularTile.getRandom(random, result.fields[6][6], result.fields[5][6]));
        for (int i = 4; i >= 0; i--)
            result.fields[6][i].putTile(
                    RegularTile.getRandom(random, result.fields[6][i + 1], result.fields[6][i + 2]));
        result.fields[5][0].putTile(RegularTile.getRandom(random, result.fields[6][0], result.fields[6][1]));
        for (int i = 4; i >= 2; i--)
            result.fields[i][0].putTile(
                    RegularTile.getRandom(random, result.fields[i + 1][0], result.fields[i + 2][0]));
        result.fields[1][0].putTile(
                RegularTile.getRandom(random, result.fields[0][0], result.fields[0][1], result.fields[2][0],
                        result.fields[3][0]));
        return result;
    }

    /**
     * Sets the project tiles on the board if they were not already
     *
     * @param left   project tile for x = 2, y = 4
     * @param center project tile for x = 3, y = 2
     * @param right  project tile for x = 4, y = 3
     * @return if setting the project tiles was successful
     */
    public boolean setProjectTiles(ProjectTile left, ProjectTile center, ProjectTile right) {
        if (projectTilesSet)
            return false;
        fields[4][2].putTile(left);
        fields[2][3].putTile(center);
        fields[3][4].putTile(right);
        projectTilesSet = true;
        return true;
    }

    /**
     * Checks if board is full (all the tiles were placed)
     *
     * @return if board is full
     */
    public boolean isFull() {
        for (Field[] array : fields)
            for (Field field : array)
                if (!field.hasTile())
                    return false;
        return true;
    }

    /**
     * Gets a field from the board
     *
     * @param x x position of the field
     * @param y y position of the field
     * @return a field from the board
     */
    public Field getField(int x, int y) {
        return fields[y][x];
    }

    /**
     * Gets a neighbour of a field or null if it doesn't exist
     *
     * @param field field to get the neighbours of
     * @param index number specifying which neighbour: 0 - top left, 1 - top right, 2 - right, 3 - bottom right, 4 -
     *              bottom left, 5 - left
     * @return a neighbour of a field
     */
    public Field getNeighbour(Field field, int index) {
        return switch (index) {
            case 0 -> getTopLeft(field);
            case 1 -> getTopRight(field);
            case 2 -> getRight(field);
            case 3 -> getBottomRight(field);
            case 4 -> getBottomLeft(field);
            case 5 -> getLeft(field);
            default -> throw new IllegalArgumentException();
        };
    }

    /**
     * Gets the left neighbour of a field or null if it doesn't exist
     *
     * @param field field to get the neighbour of
     * @return left neighbour of a field
     */
    public Field getLeft(Field field) {
        int x = field.getX();
        int y = field.getY();
        if (x == 0)
            return null;
        return fields[y][x - 1];
    }

    /**
     * Gets the right neighbour of a field or null if it doesn't exist
     *
     * @param field field to get the neighbour of
     * @return right neighbour of a field
     */
    public Field getRight(Field field) {
        int x = field.getX();
        int y = field.getY();
        if (x == 6)
            return null;
        return fields[y][x + 1];
    }

    /**
     * Gets the top left neighbour of a field or null if it doesn't exist
     *
     * @param field field to get the neighbour of
     * @return top left neighbour of a field
     */
    public Field getTopLeft(Field field) {
        int x = field.getX();
        int y = field.getY();
        if (y == 0 || y % 2 == 0 && x == 0)
            return null;
        return fields[y - 1][x + y % 2 - 1];
    }

    /**
     * Gets the top right neighbour of a field or null if it doesn't exist
     *
     * @param field field to get the neighbour of
     * @return top right neighbour of a field
     */
    public Field getTopRight(Field field) {
        int x = field.getX();
        int y = field.getY();
        if (y == 0 || y % 2 == 1 && x == 6)
            return null;
        return fields[y - 1][x + y % 2];
    }

    /**
     * Gets the bottom left neighbour of a field or null if it doesn't exist
     *
     * @param field field to get the neighbour of
     * @return bottom left neighbour of a field
     */
    public Field getBottomLeft(Field field) {
        int x = field.getX();
        int y = field.getY();
        if (y == 6 || y % 2 == 0 && x == 0)
            return null;
        return fields[y + 1][x + y % 2 - 1];
    }

    /**
     * Gets the bottom right neighbour of a field or null if it doesn't exist
     *
     * @param field field to get the neighbour of
     * @return bottom right neighbour of a field
     */
    public Field getBottomRight(Field field) {
        int x = field.getX();
        int y = field.getY();
        if (y == 6 || y % 2 == 1 && x == 6)
            return null;
        return fields[y + 1][x + y % 2];
    }

    /**
     * Gets all the neighbours of a field including nulls
     *
     * @param field field to get the neighbours of
     * @return neighbours of the field
     */
    private Field[] allNeighbours(Field field) {
        Field[] result = new Field[6];
        for (int i = 0; i < 6; i++)
            result[i] = getNeighbour(field, i);
        return result;
    }

    /**
     * Gets the existing neighbours of a given field
     *
     * @param field field to get the neighbours of
     * @return neighbours of a field
     */
    public ArrayList<Field> getNeighbours(Field field) {
        ArrayList<Field> result = new ArrayList<>();
        for (Field f : allNeighbours(field))
            if (f != null)
                result.add(f);
        return result;
    }

    @Override
    public int getScore() {
        int result = 0;
        for (int i = 0; i < 7; i++)
            for (int j = 0; j < 7; j++)
                if (fields[i][j].hasButton())
                    result += fields[i][j].getButton().getScore();
        result += fields[4][2].getProjectTile().getScore();
        result += fields[2][3].getProjectTile().getScore();
        result += fields[3][4].getProjectTile().getScore();
        return result;
    }
}
