package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SearchActivity extends AppCompatActivity {
    EditText id;
    Button search;
    TextView t1,t2,t3,t4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        id = findViewById(R.id.txtSearchid);
        search = findViewById(R.id.searchBtn);
        t1=findViewById(R.id.txtSearchName);
        t2=findViewById(R.id.txtSearchEmail);
        t3=findViewById(R.id.txtSearchPass);
        t4=findViewById(R.id.txtSearchGender);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchData(view);
            }
        });
    }

    private void searchData(View v) {
        int searchId = Integer.parseInt(id.getText().toString());
        String url = "http://10.0.2.2/android/search.php?txtSearchid="+searchId+"";

// Toast.makeText(getApplicationContext(),url,Toast.LENGTH_LONG).show();

        StringRequest sr = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();
                    try {
                        JSONObject obj = new JSONObject(response);
                        JSONArray result = obj.getJSONArray("data");
                        for (int i = 0; i < result.length(); i++) {
                            JSONObject res = result.getJSONObject(i);
                            String name = res.getString("username");
                            String email = res.getString("useremail");
                            String pass = res.getString("userpass");
                            String gender = res.getString("usergender");

                            t1.setText("Name: " + name);
                            t2.setText("Email: " + email);
                            t3.setText("Gender: " + gender);
                            t4.setText("Password: " + pass);
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }





            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(sr);
    }
}
