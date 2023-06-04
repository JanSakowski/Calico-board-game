package game;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Represents a tile pattern
 */
public enum TilePattern {
    FERN, CROSSES, STRIPES, FLOWERS, PLANTS, DOTS;

    /**
     * Gets a random pattern
     * 
     * @param random random variable
     * @param used tile patterns to ignore
     * @return a random pattern
     */
    public static TilePattern getRandomPattern(Random random, TilePattern... used) {
        List<TilePattern> blocked = Arrays.asList(used);
        TilePattern[] options = TilePattern.values();
        TilePattern result;
        do {
            result = options[random.nextInt(options.length)];
        } while (blocked.contains(result));
        return result;
    }

    /**
     * Gets all patterns in a random order
     * 
     * @param random random variable
     * @return all patterns in random order
     */
    public static TilePattern[] getRandomOrder(Random random) {
        List<TilePattern> all = Arrays.asList(TilePattern.values());
        Collections.shuffle(all, random);
        return all.toArray(new TilePattern[6]);
    }
}
