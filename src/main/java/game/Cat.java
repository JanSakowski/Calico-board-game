package game;

import java.util.Random;

/**
 * Class representing a cat
 */
public enum Cat {
    MILLIE(3, ShapeType.N_OR_MORE, 3), TIBBIT(5, ShapeType.N_OR_MORE, 4), COCONUT(7, ShapeType.N_OR_MORE, 5),
    CIRA(9, ShapeType.N_OR_MORE, 6), GWENIVERSE(11, ShapeType.N_OR_MORE, 7), CALLIE(3, ShapeType.CLUSTER, 3),
    RUMI(5, ShapeType.LINE, 3), TECOLOTE(7, ShapeType.LINE, 4), ALMOND(9, ShapeType.CLUSTER, 5),
    LEO(11, ShapeType.LINE, 5);

    /**
     * Cats with one dot
     */
    private static final Cat[] oneDot = {MILLIE, TIBBIT, CALLIE, RUMI};
    /**
     * Cats with two dots
     */
    private static final Cat[] twoDots = {COCONUT, CIRA, TECOLOTE, ALMOND};
    /**
     * Cats with three dots
     */
    private static final Cat[] threeDots = {GWENIVERSE, LEO};

    private final int score;
    private final ShapeType shapeType;
    private final int shapeCount;

    /**
     * Amount of points a button of this cat gives
     */
    public int getScore() {
        return score;
    }

    /**
     * Type of shape type this cat prefers
     */
    public ShapeType getShapeType() {
        return shapeType;
    }

    /**
     * Size of the shape this cat prefers
     */
    public int getShapeCount() {
        return shapeCount;
    }

    Cat(int score, ShapeType shapeType, int shapeCount) {
        this.score = score;
        this.shapeType = shapeType;
        this.shapeCount = shapeCount;
    }

    /**
     * Gets three random cats one with each dot count
     *
     * @param random random variable
     * @return three random cats
     */
    public static Cat[] GetRandom(Random random) {
        Cat[] result = new Cat[3];
        result[0] = oneDot[random.nextInt(oneDot.length)];
        result[1] = twoDots[random.nextInt(twoDots.length)];
        result[2] = threeDots[random.nextInt(threeDots.length)];
        return result;
    }
}
