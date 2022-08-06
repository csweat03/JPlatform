package me.christian.java.firmware.window.attrib;

import javax.swing.*;

public enum CloseOperation {

    NOTHING(JFrame.DO_NOTHING_ON_CLOSE), HIDE(JFrame.HIDE_ON_CLOSE), DISPOSE(JFrame.DISPOSE_ON_CLOSE), CLOSE(JFrame.EXIT_ON_CLOSE);

    private int operation;

    CloseOperation(int op) {
        this.operation = op;
    }

    public int getOperation() {
        return operation;
    }
}
