package com.example.calico.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Stack;

/**
 * Represents a regular tile
 */
public class RegularTile extends Tile {
    private final Color color;
    private final TilePattern pattern;

    /**
     * game.Color of the tile
     */
    public Color getColor() {
        return color;
    }

    /**
     * Pattern of the tile
     */
    public TilePattern getPattern() {
        return pattern;
    }

    public RegularTile(Color color, TilePattern pattern) {
        this.color = color;
        this.pattern = pattern;
    }

    /**
     * Gets a random tile
     *
     * @param random random variable
     * @param blocked fields from which color and pattern has to be different
     * @return a random tile
     */
    public static RegularTile getRandom(Random random, Field... blocked) {
        Color[] blockedColors = new Color[blocked.length];
        TilePattern[] blockedPatterns = new TilePattern[blocked.length];
        for (int i = 0; i < blocked.length; i++) {
            blockedColors[i] = blocked[i].getRegularTile().getColor();
            blockedPatterns[i] = blocked[i].getRegularTile().getPattern();
        }
        return new RegularTile(Color.getRandomColor(random, true, blockedColors), TilePattern.getRandomPattern(random, blockedPatterns));
    }

    /**
     * Gets every tile in random order
     *
     * @param random random variable
     * @return shuffled tiles
     */
    public static Stack<RegularTile> getAllTilesShuffled(Random random) {
        ArrayList<RegularTile> list = new ArrayList<>();
        Color[] colors = Color.getValuesNoRainbow();
        TilePattern[] patterns = TilePattern.values();
        for (Color c : colors)
            for (TilePattern p : patterns)
                for (int i = 0; i < 3; i++)
                    list.add(new RegularTile(c, p));
        Stack<RegularTile> result = new Stack<>();
        Collections.shuffle(list, random);
        result.addAll(list);
        return result;
    }
}
