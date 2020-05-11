package com.shourov.sqlite_project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class StudentAdapter extends ArrayAdapter<Model> {
    Context context;
    ArrayList<Model> arrayList;


    public StudentAdapter(@NonNull Context context, ArrayList<Model> arrayList) {
        super(context, R.layout.single_row,arrayList);
        this.context=context;
        this.arrayList=arrayList;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);

        View view= layoutInflater.inflate(R.layout.single_row,parent,false);


        TextView name=    view.findViewById(R.id.nameTV);
        TextView age =  view.findViewById(R.id.ageTV);
        TextView address = view.findViewById(R.id.addressTV);


        name.setText(arrayList.get(position).getName());
        age.setText(String.valueOf(arrayList.get(position).getAge()));
        address.setText(arrayList.get(position).getAddress());




        return view;




    }
}
