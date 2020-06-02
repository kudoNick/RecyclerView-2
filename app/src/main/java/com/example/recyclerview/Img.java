package com.example.recyclerview;

import org.json.JSONException;
import org.json.JSONObject;

public class Img {
    String img;

    public Img (JSONObject jsonObject){
        if (jsonObject.has("avatar_url")){
            try {
                img = jsonObject.getString("avatar_url");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
    public String getImg() {
        return img;
    }
}
