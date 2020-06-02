package com.example.recyclerview;

import org.json.JSONException;
import org.json.JSONObject;

public class Text {
    String name;

    public Text (JSONObject jsonObject) throws JSONException {
        if (jsonObject.has("login")) {
            name = jsonObject.getString("login");
        }
    }

    public String getName() {
        return name;
    }
}
