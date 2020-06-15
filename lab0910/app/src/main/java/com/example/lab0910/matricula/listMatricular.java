package com.example.lab0910.matricula;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.example.lab0910.BaseDatos.DataBase;
import com.example.lab0910.data.adapter.AdapterCurso;
import com.example.lab0910.data.adapter.AdapterMatricula;
import com.example.lab0910.data.helper.cursoHelper;
import com.example.lab0910.data.helper.matricularCursoHelper;
import com.example.lab0910.model.Curso;
import com.example.lab0910.model.Matricula;
import com.example.lab0910.principalMenu;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
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

public class listMatricular extends AppCompatActivity  implements AdapterMatricula.AdapterMatriculaListener, matricularCursoHelper.RecyclerItemTouchHelperListener {

    private RecyclerView rVLC;
    private AdapterMatricula adapterCurso;
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
        LinearLayoutManager LL = new LinearLayoutManager(this);
        rVLC.setLayoutManager(LL);



        listaC = (ArrayList<Curso>) DataBase.getInstancia(listMatricular.this).listCursosDisponiblesMatricula(idSesion);
        adapterCurso = new AdapterMatricula(listaC, this);
        rVLC.setAdapter(adapterCurso);
        adapterCurso.notifyDataSetChanged();

        //Despues, verificar con un if
        ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new matricularCursoHelper(0,  ItemTouchHelper.RIGHT, this);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(rVLC);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               back();
            }
        });
    }

    @Override
    public void onContactSelected(Curso curso) {

    }

    public void back(){
        Intent a = new Intent(this, principalMenu.class);
        startActivity(a);
    }

    @Override
    public void onSwiped(final RecyclerView.ViewHolder viewHolder, int direction, int position) {
        final Curso aux = adapterCurso.getSwipedItem(viewHolder.getAdapterPosition());
        idSesion=(String) getIntent().getSerializableExtra("idSesion");
        new AlertDialog.Builder(listMatricular.this)
                .setTitle("Desea matricular el curso")
                .setMessage(aux.toString())
       .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Continue with delete operation
                        try{
                            if(DataBase.getInstancia(listMatricular.this).insertar(new Matricula(aux.getId(),idSesion))){
                                Toast.makeText(listMatricular.this, "Se ha matriculado en el curso: "+ aux.toString(), Toast.LENGTH_SHORT).show();
                                adapterCurso.removeItem(viewHolder.getAdapterPosition());

                            }else{
                                Toast.makeText(listMatricular.this, "No se ha matriculado ", Toast.LENGTH_SHORT).show();
                            }
                        }catch (Exception e){
                                   Toast.makeText(listMatricular.this, "Algo salio mal ", Toast.LENGTH_SHORT).show();
                        }
                    }
                })

                // A null listener allows the button to dismiss the dialog and take no further action.
                .setNegativeButton(android.R.string.no, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();




    }

    @Override
    public void onItemMove(int source, int target) {
            adapterCurso.onItemMove(source, target);
    }


    @Override
    public void onBackPressed() { //TODO it's not working yet
        /*if (!searchView.isIconified()) {
            searchView.setIconified(true);
            return;
        }*/
        Intent a = new Intent(this, principalMenu.class);
        startActivity(a);
        super.onBackPressed();
    }

}