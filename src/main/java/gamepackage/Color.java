package gamepackage;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Represents a color of a tile or a color button
 */
public enum Color {
    GREEN(0x70A041), PURPLE(0x782B93), MAGENTA(0xE13072), YELLOW(0xFFB739), LIGHT_BLUE(0x66CCD4), DARK_BLUE(0x173799),
    RAINBOW(-1);

    private static final Color[] noRainbow = {GREEN, PURPLE, MAGENTA, YELLOW, LIGHT_BLUE, DARK_BLUE};

    private final int hexValue;

    /**
     * A hex value of a color, -1 if rainbow
     */
    public int getHexValue() {
        return hexValue;
    }

    Color(int hexValue) {
        this.hexValue = hexValue;
    }

    /**
     * Gets a random color
     *
     * @param random       random variable
     * @param blockRainbow true if ignoring rainbow
     * @param used         colors to ignore
     * @return a random color
     */
    public static Color getRandomColor(Random random, boolean blockRainbow, Color... used) {
        List<Color> blocked = Arrays.asList(used);
        Color[] options = blockRainbow ? noRainbow : Color.values();
        Color result;
        do {
            result = options[random.nextInt(options.length)];
        } while (blocked.contains(result));
        return result;
    }

    /**
     * All constants without rainbow
     *
     * @return enum constants
     */
    public static Color[] getValuesNoRainbow() {
        return noRainbow;
    }
}
