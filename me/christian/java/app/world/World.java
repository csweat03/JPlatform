package me.christian.java.app.world;

import me.christian.java.firmware.ApplicationLogic;
import me.christian.java.firmware.utilities.IProcess;

public class World implements IProcess {

    private String name = "";
    private long genID = 0;

    public String name() {
        return "World" + name;
    }

    public String[] file() {
        return new String[] {
                "src\\me\\christian\\json\\World" + name + ".json"
        };
    }

    public World(String name, long genID) {
        this.name = name;
        this.genID = genID;

        ApplicationLogic.processes.get().add(this);
    }

    public void loading() {
        log.log("world").push();
    }

    public void finalizing() {

    }

    public void update() {



    }

    public String getName() {
        return name;
    }

    public long getGenID() {
        return genID;
    }

}
