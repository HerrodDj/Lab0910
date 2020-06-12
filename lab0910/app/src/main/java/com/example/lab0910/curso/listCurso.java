package com.example.lab0910.curso;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.lab0910.BaseDatos.DataBase;
import com.example.lab0910.R;
import com.example.lab0910.data.adapter.AdapterCurso;
import com.example.lab0910.model.Curso;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class listCurso extends AppCompatActivity {

    private RecyclerView rVLC;
    private SearchView searchView;
    private AdapterCurso adapCurso;
    private ArrayList<Curso> listaC;
    private SwipeRefreshLayout refresh;
    private CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




    }

    //depende del permiso
    //super user debe poder ver esto

    public void addCurso(){



    }
}