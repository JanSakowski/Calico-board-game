package gamepackage;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.Random;

/**
 * Represents a project tile
 */
public class ProjectTile extends Tile implements Scored {
    private final ProjectTileType type;
    private boolean colorsMatch;
    private boolean patternsMatch;

    /**
     * Type of the project tile (condition)
     */
    public ProjectTileType getType() {
        return type;
    }

    /**
     * If colors around match the condition
     */
    public boolean colorsMatch() {
        return colorsMatch;
    }

    /**
     * If patterns around match the condition
     */
    public boolean patternMatch() {
        return patternsMatch;
    }

    public ProjectTile(ProjectTileType type) {
        this.type = type;
    }

    /**
     * Updates the matches based on the neighbours
     *
     * @param neighbours neighbours of the field the tile is on
     */
    public void updateMatches(ArrayList<Field> neighbours) {
        colorsMatch = false;
        patternsMatch = false;
        if (neighbours.size() < 6)
            return;
        EnumMap<Color, Integer> colorsCounter = new EnumMap<>(Color.class);
        EnumMap<TilePattern, Integer> patternCounter = new EnumMap<>(TilePattern.class);
        for (int i = 0; i < 6; i++) {
            if (!neighbours.get(i).hasRegularTile())
                return;
            RegularTile t = neighbours.get(i).getRegularTile();
            Color c = t.getColor();
            TilePattern p = t.getPattern();
            colorsCounter.put(c, colorsCounter.getOrDefault(c, 0) + 1);
            patternCounter.put(p, patternCounter.getOrDefault(p, 0) + 1);
        }
        int[] colorCounts = colorsCounter.values().stream().mapToInt(x -> x).sorted().toArray();
        int[] patternCounts = patternCounter.values().stream().mapToInt(x -> x).sorted().toArray();
        colorsMatch = type.compareCounts(colorCounts);
        patternsMatch = type.compareCounts(patternCounts);
    }

    @Override
    public int getScore() {
        if (colorsMatch && patternsMatch)
            return type.getDoublePoints();
        if (colorsMatch || patternsMatch)
            return type.getSinglePoints();
        return 0;
    }

    /**
     * Gets four random project tile from which the player will choose three to place on their boards
     *
     * @param random random variable
     * @return four random project tiles
     */
    public static ProjectTile[] getPlayersRandom(Random random) {
        ProjectTileType[] allTypes = ProjectTileType.values();
        int a = random.nextInt(6);
        int b;
        do {
            b = random.nextInt(6);
        } while (b == a);
        ProjectTile[] result = new ProjectTile[4];
        int last = 0;
        for (int i = 0; i < 6; i++)
            if (i != a && i != b)
                result[last++] = new ProjectTile(allTypes[i]);
        return result;
    }
}
