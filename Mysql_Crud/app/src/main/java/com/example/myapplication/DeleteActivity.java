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

import org.json.JSONException;
import org.json.JSONObject;

public class DeleteActivity extends AppCompatActivity {
    EditText name;
    Button del;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        name=findViewById(R.id.txtDeletename);
        del=findViewById(R.id.deleteBtn);
        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteUser(view);
           //     Toast.makeText(getApplicationContext(),"clicked",Toast.LENGTH_LONG).show();
            }
        });
    }

    private void deleteUser(View view) {
        String deleteName=name.getText().toString();
        String url="http://10.0.2.2/android/delete.php?txtDeletename=" +deleteName+" ";
//        Toast.makeText(getApplicationContext(),url,Toast.LENGTH_LONG).show();

        StringRequest sr=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.equals("Deleted")){
                    Toast.makeText(getApplicationContext(),"Record Deleted",Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(),ShowActivity.class));
                }else if(response.equals("Not Deleted")){
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