package me.christian.java.firmware.utilities;

import org.json.JSONObject;

public class JSONHelper {

    public static JSONObject get(String name) {
        return new JSONObject(name);
    }

    public static JSONObject get(String name, JSONObject... parent) {
        JSONObject obj;
        if (parent != null) {
            obj = parent[0].getJSONObject(name);
        } else obj = new JSONObject(name);

        return obj;
    }

}
