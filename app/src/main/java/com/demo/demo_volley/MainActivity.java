package com.demo.demo_volley;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RequestQueue requestQueue;
    StringRequest stringRequest;
    String url = "https://jsonplaceholder.typicode.com/photos";

    TextView view;

    ArrayList<Responseobject> responseobjects;

    RecyclerView recyclerView;
    Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.main_recyc);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        responseobjects = new ArrayList<>();
        getData();


    }

    private void getData() {
        requestQueue = Volley.newRequestQueue(this);
        stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        Responseobject responseobject = new Responseobject(jsonObject.getInt("albumId"), jsonObject.getInt("id"), jsonObject.getString("title"), jsonObject.getString("url"), jsonObject.getString("thumbnailUrl"));
                        responseobjects.add(responseobject);
                    }

                    recyclerView.setAdapter(new Adapter(responseobjects, MainActivity.this));
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue.add(stringRequest);
    }

}