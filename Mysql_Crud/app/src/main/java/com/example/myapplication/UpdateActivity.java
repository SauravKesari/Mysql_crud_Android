package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class UpdateActivity extends AppCompatActivity {
    EditText name,email,gender,pass,id;
    Button update;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        id=findViewById(R.id.txtUpdateuid);
        name=findViewById(R.id.txtUpdateName);
        email=findViewById(R.id.txtUpdateEmail);
        gender=findViewById(R.id.txtUpdateGender);
        pass=findViewById(R.id.txtUpdatePass);
        update=findViewById(R.id.updateButton);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateData(view);
            }
        });

    }

    private void updateData(View view) {
        int uid=Integer.parseInt(id.getText().toString());
        String uname=name.getText().toString();
        String uemail=email.getText().toString();
        String ugender=gender.getText().toString();
        String upass=pass.getText().toString();

        String url="http://10.0.2.2/android/update.php?txtUpdateName="+uname+" &txtUpdateEmail="+uemail+" &txtUpdatePass="+upass+" &txtUpdateGender="+ugender+" &txtUpdateid="+uid+"";
        StringRequest sr=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.equals("Updated")){
                    Toast.makeText(getApplicationContext(),"Record Deleted",Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(),ShowActivity.class));
                }else if(response.equals("Failed")){
                    Toast.makeText(getApplicationContext(),"Record not Deleted",Toast.LENGTH_LONG).show();

                }else{
                    Toast.makeText(getApplicationContext(),"Something went wrong!",Toast.LENGTH_LONG).show();

                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        RequestQueue queue= Volley.newRequestQueue(this);
        queue.add(sr);
    }
}
