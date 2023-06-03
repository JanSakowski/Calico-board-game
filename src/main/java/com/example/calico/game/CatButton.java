package com.example.calico.game;

/**
 * Class representing a cat button
 */
public class CatButton extends Button {
    private final Cat cat;

    /**
     * game.Cat of the button
     */
    public Cat getCat() {
        return cat;
    }

    public CatButton(Cat cat) {
        this.cat = cat;
    }

    @Override
    public int getScore() {
        return cat.getScore();
    }
}
