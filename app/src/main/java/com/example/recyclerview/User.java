package com.example.recyclerview;

import org.json.JSONException;
import org.json.JSONObject;

public class User {
    String name,address;

    public User (JSONObject jsonObject) throws JSONException {
        if (jsonObject.has("name")) {
            name = jsonObject.getString("name");
        }
        if (jsonObject.getJSONObject("address").has("city")) {
           address = jsonObject.getJSONObject("address").getString("city");
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
