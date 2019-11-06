package com.example.daiverandresdoria.seccion_11_navigator_drawer.Activities;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.daiverandresdoria.seccion_11_navigator_drawer.Fragments.StudentsAddFragment;
import com.example.daiverandresdoria.seccion_11_navigator_drawer.Fragments.StudentsListFragment;
import com.example.daiverandresdoria.seccion_11_navigator_drawer.Funcions.ConexionSQLiteHelper;
import com.example.daiverandresdoria.seccion_11_navigator_drawer.R;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //config database
        ConexionSQLiteHelper connect = new ConexionSQLiteHelper(this, "bd_student", null,1);
        //app start
        setToolbar();
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navView);
        setDefoultFragment();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                boolean fragmentTransaction = false;
                Fragment fragment = null;

                switch (item.getItemId()){
                    case R.id.menu_list:
                        fragment = new StudentsListFragment();
                        fragmentTransaction = true;
                        break;
                    case R.id.menu_add:
                        fragment = new StudentsAddFragment();
                        fragmentTransaction = true;
                        break;
                }
                if (fragmentTransaction){
                    changeFragment(fragment,item);
                    drawerLayout.closeDrawers();
                }
                return true;
            }
        });
    }

    private void setToolbar(){
        toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_home);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Home");
    }

    private void setDefoultFragment(){
        MenuItem item = navigationView.getMenu().getItem(0);
        Fragment fragment = new StudentsListFragment();
        changeFragment(fragment,item);
    }

    private void changeFragment(Fragment fragment, MenuItem item){
        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, fragment ).commit();
        item.setChecked(true);
        getSupportActionBar().setTitle(item.getTitle());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                //metodo para abrir el drawer
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
