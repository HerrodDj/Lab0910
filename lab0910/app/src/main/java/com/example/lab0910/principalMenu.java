package com.example.lab0910;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lab0910.curso.AddCurso;
import com.example.lab0910.curso.ListCurso;
import com.example.lab0910.estudiante.listEstudiante;
import com.example.lab0910.matricula.ListMatriculados;
import com.example.lab0910.matricula.listMatricular;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class principalMenu extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private AppBarConfiguration mAppBarConfiguration;
    static String rol;
    static String idSesion;
    private TextView bien;
    private TextView nombre;
    private ImageView ima;
    private static int ses =1;
    private static String folder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        bien=findViewById(R.id.textView2);
        nombre=findViewById(R.id.textView3);
        ima=findViewById(R.id.imageView2);




        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {logOut();
            }
        });


        Bundle extras = getIntent().getExtras();
        if(extras!=null) {
            rol = (String) getIntent().getSerializableExtra("rol");
            idSesion=(String) getIntent().getSerializableExtra("id");
        }

        checkRol(rol);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home)
                .setDrawerLayout(drawer)
                .build();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.principal_menu, menu);
        return true;
    }

    private void checkRol(String r) {
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        Menu menu = navigationView.getMenu();
        MenuItem holder;
        if(r.equals("Estudiante")){
            holder = menu.findItem(R.id.Cursos);
            holder.setEnabled(false);
            holder = menu.findItem(R.id.Estudiantes);
            holder.setEnabled(false);
            ima.setBackground(getDrawable(R.drawable.ic_estudiante));
            if(ses==1){
                nombre.setText((String) getIntent().getSerializableExtra("nombre"));
                folder=(String) getIntent().getSerializableExtra("nombre");
                ses++;
            }else {
                nombre.setText(folder);
            }
        }else{
            holder = menu.findItem(R.id.matricular);
            holder.setEnabled(false);
            holder = menu.findItem(R.id.listMatriculados);
            holder.setEnabled(false);
            ima.setBackground(getDrawable(R.drawable.ic_persona));
            if(ses==1){
                nombre.setText((String) getIntent().getSerializableExtra("nombre"));
                folder=(String) getIntent().getSerializableExtra("nombre");
                ses++;
            }else{
                nombre.setText(folder);
            }
        }
    }



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.matricular) {
            matricular();
        }else if (id == R.id.listMatriculados) {
            matriculados();
        }else if(id==R.id.Cursos){
            cursos();
        }
        else if(id==R.id.Estudiantes){
            Estudiantes();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void cursos() {
        finish();
        Intent a = new Intent(this, ListCurso.class);
        startActivity(a);
    }

    private void Estudiantes(){
        finish();
        Intent a = new Intent(this, listEstudiante.class);
        startActivity(a);
    }

    private void matricular() {
        finish();
        Intent a = new Intent(this, listMatricular.class);
        a.putExtra("idSesion", idSesion);


        startActivity(a);
    }

    private void matriculados(){
        finish();
        Intent a = new Intent(this, ListMatriculados.class);
        a.putExtra("idSesion", idSesion);
        startActivity(a);

    }

    private void logOut() {
        ses=1;
        finish();
        Intent a = new Intent(this, MainActivity.class);
        startActivity(a);
    }
}