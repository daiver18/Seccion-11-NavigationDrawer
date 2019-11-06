package com.example.daiverandresdoria.seccion_11_navigator_drawer.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.daiverandresdoria.seccion_11_navigator_drawer.Model.Student;
import com.example.daiverandresdoria.seccion_11_navigator_drawer.R;

import java.util.List;

public class MyAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<Object> students;

    public MyAdapter(Context context, int layout, List<Object> students) {
        this.context = context;
        this.layout = layout;
        this.students = students;
    }

    @Override
    public int getCount() {
        return students.size();
    }

    @Override
    public Object getItem(int position) {
        return this.students.get(position);
    }

    @Override
    public long getItemId(int id) {
        return id;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {

        LayoutInflater layoutInflater = LayoutInflater.from(this.context);
        convertView = layoutInflater.inflate(R.layout.list_item,null);

        Student currentName = (Student) students.get(position);

        TextView textViewName = convertView.findViewById(R.id.textViewName);
        TextView textViewAddress = convertView.findViewById(R.id.textViewAddress);
        TextView textViewID = convertView.findViewById(R.id.textViewID);

        textViewName.setText("Name: " + currentName.getName());
        textViewAddress.setText("Address: " + currentName.getAddress());
        textViewID.setText("ID: " + currentName.getId());



        return convertView;
    }
}
