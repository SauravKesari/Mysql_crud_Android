package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

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
import java.util.HashMap;

public class ShowActivity extends AppCompatActivity {
    String url="http://10.0.2.2/android/display.php";
    ListView lv;
    customAdapter adapter;
    ArrayList<List_data> ls;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        lv=findViewById(R.id.userListview);
        ls=new ArrayList<>();
        getData();
    }

    private void getData() {

        StringRequest stringRequest =new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    JSONArray array=jsonObject.getJSONArray("data");
                    for (int i=0; i<array.length(); i++){
                        JSONObject ob=array.getJSONObject(i);
                        List_data data=new List_data(ob.getInt("userid"),ob.getString("username"),
                                ob.getString("useremail"),ob.getString("userpass"),
                                ob.getString("usergender"));
                        ls.add(data);
                    }
                    adapter=new customAdapter(getApplicationContext(),R.layout.list_data,ls);
                    lv.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}


