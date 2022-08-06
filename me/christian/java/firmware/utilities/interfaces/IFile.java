package me.christian.java.firmware.utilities.interfaces;

import me.christian.java.firmware.utilities.Files;

import java.util.Arrays;

public interface IFile {

    String[] file();

    default Files openFile() {
        return openFile(0);
    }

    default Files openFile(int fileNum) {
        return Files.open(file()[fileNum]);
    }
}
