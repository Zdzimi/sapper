package com.zdzimi.controler;

public enum Config {

    E (10, 10,20),
    M (15, 15,25),
    H (20, 20,40);

    private final int width;
    private final int height;
    private final int bomb;

    Config(int width, int height, int bomb) {
        this.width = width;
        this.height = height;
        this.bomb = bomb;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getBomb() {
        return bomb;
    }
}
