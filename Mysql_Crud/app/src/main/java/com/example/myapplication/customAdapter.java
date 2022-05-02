package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class customAdapter extends ArrayAdapter<List_data> {
    ArrayList<List_data> list_data;
    Context context;
    int resource;
    public customAdapter(@NonNull Context context,int resource, @NonNull ArrayList<List_data> userdata) {
        super(context, resource,userdata);
        this.list_data= (ArrayList<List_data>) userdata;
        this.context=context;
        this.resource=resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView==null){
            LayoutInflater layoutInflater=(LayoutInflater)getContext().getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView=layoutInflater.inflate(R.layout.list_data,null,true);
        }
        List_data userdataList=getItem(position);
        TextView uid=convertView.findViewById(R.id.txtAdapteruid);
        TextView listname=(TextView) convertView.findViewById(R.id.txtAdapterName);
        TextView listemail=(TextView) convertView.findViewById(R.id.txtAdapterEmail);
        TextView listgender=(TextView) convertView.findViewById(R.id.txtAdapterGender);
        TextView listpass=(TextView) convertView.findViewById(R.id.txtAdapterPass);
        uid.setText("UserId:- "+userdataList.getId());
        listname.setText("Name:-  "+userdataList.getName());
        listemail.setText("Email:- "+userdataList.getEmail());
        listgender.setText("Gender:- "+userdataList.getGender());
        listpass.setText("Password:- "+userdataList.getPass());
        return convertView;
    }
}
