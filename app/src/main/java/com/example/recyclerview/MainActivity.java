package com.example.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    RecyclerView rcvData;
    List<Object> mData ; ;
    DataAdapter dataAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rcvData = findViewById(R.id.rcvData);


        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rcvData.setLayoutManager(layoutManager);
        mData = new ArrayList<>();

        getDataUser();
        getDataText();
        getDataImg();

        //Recycler view trong Recycler view
        mData.add(R.layout.row_recyclerview);
        rcvData.setAdapter(dataAdapter);

    }

    private void getDataUser() {
        System.out.println("click start user");
        AndroidNetworking.get("https://jsonplaceholder.typicode.com/users/")
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        System.out.println("Click user");

                        for (int i = 0; i <response.length() ; i++) {
                            try {
                                User user = new User(response.getJSONObject(i));
                                mData.add(user);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                         dataAdapter = new DataAdapter(mData, MainActivity.this);
                        rcvData.setAdapter(dataAdapter);
                    }
                    @Override
                    public void onError(ANError anError) {
                        System.out.println("Click Fail");
                    }
                });
    }
    public void getDataImg (){
        System.out.println("click start img");
        AndroidNetworking.get("https://api.github.com/users")
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        System.out.println("click  img");
                        for (int i = 0; i <response.length() ; i++) {
                            try {
                                Img img = new Img(response.getJSONObject(i));
                                mData.add(img);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                         dataAdapter = new DataAdapter(mData, MainActivity.this);
                    }
                    @Override
                    public void onError(ANError anError) {
                    }
                });
    }
    public void getDataText(){
        AndroidNetworking.get("https://api.github.com/users")
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        System.out.println("click Text");
                        for (int i = 0; i <response.length() ; i++) {
                            try {
                                Text text = new Text(response.getJSONObject(i));
                                mData.add(text);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                         dataAdapter = new DataAdapter(mData, MainActivity.this);

                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });
    }
}
