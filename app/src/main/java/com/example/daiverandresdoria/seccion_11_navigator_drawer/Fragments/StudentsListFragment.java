package com.example.daiverandresdoria.seccion_11_navigator_drawer.Fragments;


import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.daiverandresdoria.seccion_11_navigator_drawer.Funcions.ConexionSQLiteHelper;
import com.example.daiverandresdoria.seccion_11_navigator_drawer.Funcions.readTXT;
import com.example.daiverandresdoria.seccion_11_navigator_drawer.Model.Student;
import com.example.daiverandresdoria.seccion_11_navigator_drawer.R;
import com.example.daiverandresdoria.seccion_11_navigator_drawer.adapter.MyAdapter;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class StudentsListFragment extends Fragment implements View.OnClickListener {
    private ListView listView;
    private List<Object> students;
    private List<Student> s;
    private readTXT read = new readTXT();

    ConexionSQLiteHelper connect;

    FloatingActionButton fabMain, fabOne, fabTwo;
    Float translationY = 100f;

    OvershootInterpolator interpolator = new OvershootInterpolator();
    Boolean isMenuOpen = false;

    public StudentsListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_studentslist, container, false);
        listView = view.findViewById(R.id.listViewStudents);
        initFabMenu(view);

        connect = new ConexionSQLiteHelper(view.getContext(), "bd_student", null,1);
        consultarListaStudent();

        InputStream direccion = this.getResources().openRawResource(R.raw.json);
        s =  read.leerTXT(direccion);
        students = new ArrayList<Object>();

        for (int i = 0; i<s.size(); i++){
            students.add(s.get(i));
        }
        MyAdapter myAdapter = new MyAdapter(view.getContext(), R.layout.list_item, students);
        listView.setAdapter(myAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Student student = (Student) students.get(i);
                Toast.makeText(view.getContext(), "mensaje" + student.getId(), Toast.LENGTH_LONG).show();
            }
        });
        return view;
    }

    private void consultarListaStudent() {
        SQLiteDatabase db = connect.getReadableDatabase();

        Student student = null;
        
    }

    private void initFabMenu(View view) {
        fabMain = view.findViewById(R.id.fabMain);
        fabOne = view.findViewById(R.id.fabOne);
        fabTwo = view.findViewById(R.id.fabTwo);

        fabOne.setAlpha(0f);
        fabTwo.setAlpha(0f);

        fabOne.setTranslationY(translationY);
        fabTwo.setTranslationY(translationY);

        fabMain.setOnClickListener(this);
        fabOne.setOnClickListener(this);
        fabTwo.setOnClickListener(this);
    }
    private void openMenu() {
        isMenuOpen = !isMenuOpen;

        fabMain.animate().setInterpolator(interpolator).rotation(45f).setDuration(300).start();

        fabOne.animate().translationY(0f).alpha(1f).setInterpolator(interpolator).setDuration(300).start();
        fabTwo.animate().translationY(0f).alpha(1f).setInterpolator(interpolator).setDuration(300).start();
    }
    private void closeMenu() {
        isMenuOpen = !isMenuOpen;

        fabMain.animate().setInterpolator(interpolator).rotation(0f).setDuration(300).start();

        fabOne.animate().translationY(translationY).alpha(0f).setInterpolator(interpolator).setDuration(300).start();
        fabTwo.animate().translationY(translationY).alpha(0f).setInterpolator(interpolator).setDuration(300).start();
    }
    private void handleFabOne() {
        Log.i(TAG, "handleFabOne: ");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fabMain:
                Log.i(TAG, "onClick: fab main");
                if (isMenuOpen) {
                    closeMenu();
                } else {
                    openMenu();
                }
                break;
            case R.id.fabOne:
                Log.i(TAG, "onClick: fab one");
                handleFabOne();
                if (isMenuOpen) {
                    closeMenu();
                } else {
                    openMenu();
                }
                break;
            case R.id.fabTwo:
                Log.i(TAG, "onClick: fab two");
                if (isMenuOpen) {
                    closeMenu();
                } else {
                    openMenu();
                }
                break;
            default:
                if (isMenuOpen) {
                    closeMenu();
                } else {
                    openMenu();
                }
                break;

        }
    }
}
