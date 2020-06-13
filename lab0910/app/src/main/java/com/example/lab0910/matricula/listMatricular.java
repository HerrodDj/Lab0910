package com.example.lab0910.matricula;

import android.os.Bundle;

import com.example.lab0910.BaseDatos.DataBase;
import com.example.lab0910.data.adapter.AdapterCurso;
import com.example.lab0910.data.helper.cursoHelper;
import com.example.lab0910.data.helper.matricularCursoHelper;
import com.example.lab0910.model.Curso;
import com.example.lab0910.model.Matricula;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.Toast;

import com.example.lab0910.R;

import java.util.ArrayList;

public class listMatricular extends AppCompatActivity  implements AdapterCurso.AdapterCursoListener, cursoHelper.RecyclerItemTouchHelperListener {

    private RecyclerView rVLC;
    private AdapterCurso adapterCurso;
    private ArrayList<Curso> listaC;
    private CoordinatorLayout coordinatorLayout;
    static String idSesion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_matricular);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        coordinatorLayout=findViewById(R.id.coordinator_layout);

        idSesion=(String) getIntent().getSerializableExtra("idSesion");

        rVLC = findViewById(R.id.recyclerViewCursos);
        rVLC.setItemAnimator(new DefaultItemAnimator());
        rVLC.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        LinearLayoutManager LL = new LinearLayoutManager(this);
        rVLC.setLayoutManager(LL);


        listaC = (ArrayList<Curso>) DataBase.getInstancia(listMatricular.this).listCursosDisponiblesMatricula(idSesion);
        adapterCurso = new AdapterCurso(listaC, this);
        rVLC.setAdapter(adapterCurso);
        adapterCurso.notifyDataSetChanged();

        //Despues, verificar con un if
        ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new matricularCursoHelper(0,  ItemTouchHelper.RIGHT, this);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(rVLC);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public void onContactSelected(Curso curso) {

    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
        try{
        Curso aux = adapterCurso.getSwipedItem(viewHolder.getAdapterPosition());
        idSesion=(String) getIntent().getSerializableExtra("idSesion");
        if(DataBase.getInstancia(listMatricular.this).insertar(new Matricula(aux.getId(),idSesion))){
            Toast.makeText(listMatricular.this, "Se ha matriculado en el curso: "+ aux.toString(), Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(listMatricular.this, "No se ha matriculado ", Toast.LENGTH_SHORT).show();
        }
        }catch (Exception e){
            Toast.makeText(listMatricular.this, "Algo salio mal ", Toast.LENGTH_SHORT).show();

        }


    }

    @Override
    public void onItemMove(int source, int target) {
            adapterCurso.onItemMove(source, target);
    }
}