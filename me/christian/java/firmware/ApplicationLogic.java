package me.christian.java.firmware;

import me.christian.java.firmware.utilities.IProcess;
import me.christian.java.firmware.utilities.JSONHelper;
import me.christian.java.firmware.utilities.interfaces.IFile;
import me.christian.java.firmware.utilities.interfaces.ITime;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public interface ApplicationLogic extends IProcess, IFile, ITime {

    int[] NAMESPACE = new int[] {9999998, 9999999};

    AtomicBoolean running = new AtomicBoolean(false);

    AtomicInteger
            fps = new AtomicInteger(0),
            maxFps = new AtomicInteger(0);

    AtomicReference<String>
            name = new AtomicReference<>(),
            version = new AtomicReference<>(),
            developer = new AtomicReference<>(),
            format = new AtomicReference<>();

    AtomicReference<List<IProcess>> processes = new AtomicReference<>(new ArrayList<>());

    default String[] file() {
        return new String[]{
                "src\\me\\christian\\json\\AppSettings.json"
        };
    }

    default String name() {
        return "Main Thread";
    }

    @Override
    default void loading() {
        JSONObject parent = JSONHelper.get(openFile(0).read(new Object()));

        JSONObject firmwareData = JSONHelper.get("firmwareData", parent);
        JSONObject appData = JSONHelper.get("appData", parent);
        JSONObject application = JSONHelper.get("application", parent);

        name.set(firmwareData.getString("name"));
        version.set(firmwareData.getString("version"));
        developer.set(firmwareData.getString("developer"));
        format.set(firmwareData.getString("format")
                .replace("%n%", name.get())
                .replace("%v%", version.get())
                .replace("%d%", developer.get()));



        System.err.println(format);

        name.set(appData.getString("name"));
        version.set(appData.getString("version"));
        developer.set(appData.getString("developer"));
        format.set(appData.getString("format")
                .replace("%n%", name.get())
                .replace("%v%", version.get())
                .replace("%d%", developer.get()));

        System.out.println(format);

        maxFps.set(application.getInt("maxFps"));

        timerMap.put(NAMESPACE[0], new Timer());
        timerMap.put(NAMESPACE[1], new Timer());
    }

    @Override
    default void finalizing() {
        internalStart();
        running.set(true);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            running.set(false);
            shutdown();
        }));

        final long CONST = 1_000L;
        long offset = (CONST) / (maxFps.get() > 1000 ? CONST : maxFps.get());

        new Thread(() -> {
            while (running.get()) {
                if (timer(NAMESPACE[0]).hasTimeElapsed(offset)) {
                    internalUpdate();
                    fps.set(fps.get() + 1);
                } else if (timer(NAMESPACE[1]).hasTimeElapsed(CONST)) {
                    fps.set(0);
                }
            }
        }).start();
    }

    default void internalStart() {
        start();

        processes.get().forEach(IProcess::invokeProcess);
    }
    void start();

    default void internalUpdate() {
        if (processes.get().size() == 0)
            System.exit(0);

        update();
        processes.get().forEach(IProcess::update);
    }
    void update();

    void shutdown();

    default boolean isRunning() {
        return running.get();
    }

    default int getFps() {
        return fps.get();
    }

    default String getName() {
        return name.get();
    }

    default String getVersion() {
        return version.get();
    }

    default String getDeveloper() {
        return developer.get();
    }

    default String getFormat() {
        return format.get();
    }

}
