package com.example.daiverandresdoria.seccion_11_navigator_drawer.Fragments;


import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.daiverandresdoria.seccion_11_navigator_drawer.Funcions.ConexionSQLiteHelper;
import com.example.daiverandresdoria.seccion_11_navigator_drawer.R;
import com.example.daiverandresdoria.seccion_11_navigator_drawer.utility.Utilitys;

public class StudentsAddFragment extends Fragment {
    private EditText editTextName;
    private EditText editTextAddress;
    private EditText editTextLat;
    private EditText editTextLng;
    private Button btnSave;

    public StudentsAddFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_studentadd, container, false);
        editTextName = view.findViewById(R.id.editTextName);
        editTextAddress = view.findViewById(R.id.editTextAddress);
        editTextLat = view.findViewById(R.id.editTextLat);
        editTextLng = view.findViewById(R.id.editTextLng);
        btnSave = view.findViewById(R.id.btnSaveData);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerStudent(view.getContext());
            }
        });

        return view;
    }

    private void registerStudent(Context context) {
        ConexionSQLiteHelper connect = new ConexionSQLiteHelper(context, "bd_student", null,1);

        SQLiteDatabase db = connect.getWritableDatabase();

        ContentValues values = new ContentValues();
        int randonID = (int) (Math.random()*100);
        values.put(Utilitys.CAMPO_ID, randonID);
        values.put(Utilitys.CAMPO_NAME, editTextName.getText().toString());
        values.put(Utilitys.CAMPO_ADDRESS, editTextAddress.getText().toString());
        values.put(Utilitys.CAMPO_LAT, editTextLat.getText().toString());
        values.put(Utilitys.CAMPO_LNG, editTextLng.getText().toString());
        db.insert(Utilitys.TABLA_STUDENTS, Utilitys.CAMPO_ID, values);
        db.close();

    }
}
