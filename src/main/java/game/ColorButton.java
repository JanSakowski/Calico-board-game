package game;

import java.io.Serializable;

/**
 * Represents a color button
 */
public class ColorButton extends Button implements Serializable {
    private final Color color;

    /**
     * game.Color of the button
     */
    public Color getColor() {
        return color;
    }

    public ColorButton(Color color) {
        this.color = color;
    }

    @Override
    public int getScore() {
        return 3;
    }
}
