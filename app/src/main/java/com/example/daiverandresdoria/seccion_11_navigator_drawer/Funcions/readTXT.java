package com.example.daiverandresdoria.seccion_11_navigator_drawer.Funcions;

import com.example.daiverandresdoria.seccion_11_navigator_drawer.Model.Student;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class readTXT {
    ArrayList<Student> contactList;
    public ArrayList leerTXT(InputStream direction) {
        contactList = new ArrayList<>();
        String text="";
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader((direction)));
            String temp = "";
            String bfRead;
            while ((bfRead = bf.readLine()) != null) {
                temp = temp + bfRead;
            }
            direction.close();
            text = temp;
        }catch(Exception e) {
            System.out.println(e);
        }
        try{
            JSONObject reader = new JSONObject(text);
            JSONArray results = reader.getJSONArray("results");
            for (int i = 0; i < results.length(); i++) {
                JSONObject r = results.getJSONObject(i);
                int randonID = (int) (Math.random()*100);
                String id = Integer.toString(randonID);
                String name = r.getString("name");
                String address = r.getString("formatted_address");
                JSONObject geometry = r.getJSONObject("geometry");
                JSONObject location = geometry.getJSONObject("location");
                String lat = location.getString("lat");
                String lng = location.getString("lng");

                Student student = new Student();
                student.setId(id);
                student.setName(name);
                student.setAddress(address);
                student.setLat(lat);
                student.setLng(lng);

                contactList.add(student);
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return contactList;
    }
}
