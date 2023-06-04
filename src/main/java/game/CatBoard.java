package game;

import java.io.Serializable;
import java.util.EnumMap;
import java.util.Random;

/**
 * Class representing a cat board
 */
public class CatBoard implements Serializable {
    private final Cat cat;
    private final TilePattern[] preferredPatterns;

    /**
     * game.Cat of this board
     */
    public Cat getCat() {
        return cat;
    }

    /**
     * Preferred patterns of this board's cat
     */
    public TilePattern[] getPreferredPatterns() {
        return preferredPatterns;
    }

    public CatBoard(Cat cat, TilePattern... preferredPatterns) {
        this.cat = cat;
        this.preferredPatterns = preferredPatterns;
    }

    /**
     * Gets three random cat boards one with each dot counts with chosen preferred patterns
     *
     * @param random random variable
     * @return three random cat boards
     */
    public static EnumMap<Cat, CatBoard> GetRandom(Random random) {
        EnumMap<Cat, CatBoard> result = new EnumMap<>(Cat.class);
        Cat[] chosenCats = Cat.GetRandom(random);
        TilePattern[] ordered = TilePattern.getRandomOrder(random);
        for (int i = 0; i < 3; i++)
            result.put(chosenCats[i], new CatBoard(chosenCats[i], ordered[2 * i], ordered[2 * i + 1]));
        return result;
    }
}
