package me.christian.java.firmware.utilities;

import me.christian.java.firmware.utilities.interfaces.ILog;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Files {

    private static Files instance = getInstance();
    private static File workingFile;
    private List<String> data = new ArrayList<>();

    private Files() {
    }

    public static boolean exists(File file) {
        if (file == null || !file.exists()) {
            ILog.log.log("Attempted to open Invalid/Null File").push();
            try {
                ILog.log.log("Attempting to create file with path declared.").push();

                new File(file.getParentFile().getAbsolutePath()).mkdirs();
                new File(file.getAbsolutePath()).createNewFile();
            } catch (IOException | NullPointerException npe) {
                System.err.println("File: file, Provided is null.\nTry re-specifying your path.");
                npe.printStackTrace();
            }
            return false;
        }

        return true;
    }

    public static Files open(File file) {
        workingFile = exists(file) ? file : null;
        return getInstance();
    }

    public static Files open(String path) {
        return open(new File(path));
    }

    public List<String> read() {
        try {
            data.addAll(new BufferedReader(new FileReader(workingFile)).lines().collect(Collectors.toList()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return data;
    }

    public String read(Object... object) {
        StringBuilder builder = new StringBuilder();
        try {
            new BufferedReader(new FileReader(workingFile)).lines().forEach(line -> builder.append(line).append("\n"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return builder.toString();
    }

    public Files write(String... contents) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(workingFile));

            for (String content : contents) {
                writer.write(content);
                writer.newLine();
            }

            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return getInstance();
    }

    public Files write(List<String> contents) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(workingFile));

            for (String content : contents) {
                writer.write(content);
                writer.newLine();
            }

            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return getInstance();
    }

    private static Files getInstance() {
        if (instance == null) instance = new Files();
        return instance;
    }

}
