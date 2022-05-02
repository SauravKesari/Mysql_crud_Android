package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    EditText name,email,pass;
    RadioGroup rg;
    RadioButton button;
    Button add,show,del,search,update;
    String value;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //int selectedId=rg.getCheckedRadioButtonId();
        rg=findViewById(R.id.genderGroup);
        add=findViewById(R.id.btnAdd);
        name=findViewById(R.id.txtUsername);
        email=findViewById(R.id.txtUserEmail);
        pass=findViewById(R.id.txtUserpass);
        show=findViewById(R.id.btnShow);
        del=findViewById(R.id.btnDelete);
        search=findViewById(R.id.btnSearch);
        update=findViewById(R.id.btnUpdate);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertData(view);
            }
        });
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),ShowActivity.class));
            }
        });
        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),DeleteActivity.class));
            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),SearchActivity.class));
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),UpdateActivity.class));
            }
        });
    }
    public void insertData(View v){
        int selectedId=rg.getCheckedRadioButtonId();
        button=(RadioButton) findViewById(selectedId);
        value=button.getText().toString();
        if(!(name.getText().toString().isEmpty() && email.getText().toString().isEmpty()
                && pass.getText().toString().isEmpty())){
            StringRequest sr=new StringRequest(Request.Method.POST, "http://10.0.2.2/android/insert.php", new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject object=new JSONObject(response);

//                       JSONArray jsonArray=object.getJSONArray();
                        String val=object.getString("msg");
                        int status=object.getInt("status");
                        if(status==1){
                            Toast.makeText(getApplicationContext(),"Data inserted",Toast.LENGTH_LONG).show();
                        }
                        else{
                            Toast.makeText(getApplicationContext(),"Data insertion Failed",Toast.LENGTH_LONG).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                   // Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();

                }
            }){
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> map=new HashMap<>();
                    map.put("username",name.getText().toString());
                    map.put("useremail",email.getText().toString());
                    map.put("userpass",pass.getText().toString());
                    map.put("usergender",value);
                    return map;
                }
            };
            RequestQueue queue= Volley.newRequestQueue(this);
            queue.add(sr);
        }else {
            Toast.makeText(getApplicationContext(),"Please fill all the Fields",Toast.LENGTH_LONG).show();
        }
    }
}