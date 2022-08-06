package me.christian.java.app;

import me.christian.java.app.world.World;
import me.christian.java.firmware.ApplicationLogic;
import me.christian.java.firmware.window.attrib.DefaultDimension;
import me.christian.java.firmware.window.Window;

public class Platform implements ApplicationLogic {

    private static Platform instance = getInstance();

    private static World world = new World("Standard", 0);

    public void start() {
        new Window("Platform", DefaultDimension._720P) {
            public void render() {

            }
        };
    }

    public void update() {

    }

    public void shutdown() {

    }

    public static Platform getInstance() {
        if (instance == null) instance = new Platform();
        return instance;
    }

}
