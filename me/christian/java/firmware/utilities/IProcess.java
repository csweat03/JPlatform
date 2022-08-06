package me.christian.java.firmware.utilities;

import me.christian.java.app.world.World;
import me.christian.java.firmware.ApplicationLogic;
import me.christian.java.firmware.utilities.interfaces.IFile;
import me.christian.java.firmware.utilities.interfaces.ILog;
import me.christian.java.firmware.utilities.interfaces.ITime;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;

public interface IProcess extends ILog, IFile, ITime {

    AtomicReference<ProcessState> state = new AtomicReference<>();

    default void invokeProcess() {
        setSenderMode(1);

        set(ProcessState.LOADING);
        for (int i = 0; i < file().length; i++)
            openFile(i);
        loading();
        set(ProcessState.FINALIZING);
        finalizing();
        set(ProcessState.FINISHED);
    }

    default void loading() {

    }

    default void finalizing() {

    }

    default void update() {

    }

    default String name() {
        return "Process";
    }

    default void set(ProcessState process) {
        state.set(process);
        log.log(name() + " is " + process.toString().toLowerCase() + ".").push();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    enum ProcessState {
        LOADING, FINALIZING, FINISHED;

        @Override
        public String toString() {
            return name().substring(0, 1).toUpperCase() + name().substring(1).toLowerCase();
        }
    }


}
