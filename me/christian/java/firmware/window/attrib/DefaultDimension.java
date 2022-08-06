package me.christian.java.firmware.window.attrib;

import java.awt.*;

public enum DefaultDimension {

    _8K(8192, 4320),
    _6K(6144, 3160),
    _5K(5120, 2700),
    _4K(4096, 2160),
    _1080P(1920, 1080),
    _720P(1280, 720);

    private int width, height;

    DefaultDimension(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

}
