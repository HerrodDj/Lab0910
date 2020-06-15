package com.example.lab0910.estudiante;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.example.lab0910.BaseDatos.DataBase;
import com.example.lab0910.curso.AddCurso;
import com.example.lab0910.curso.ListCurso;
import com.example.lab0910.data.adapter.AdapterCurso;
import com.example.lab0910.data.adapter.AdapterEstudiante;
import com.example.lab0910.data.helper.cursoHelper;
import com.example.lab0910.data.helper.estudianteHelper;
import com.example.lab0910.model.Curso;
import com.example.lab0910.model.Usuario;
import com.example.lab0910.principalMenu;
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

public class listEstudiante extends AppCompatActivity implements AdapterEstudiante.AdapterEstudianteListener, estudianteHelper.RecyclerItemTouchHelperListener{
    private RecyclerView rVLC;
    private AdapterEstudiante adapterEst;
    private ArrayList<Usuario> listaC;
    private CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_estudiante);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        coordinatorLayout=findViewById(R.id.coordinator_layout);

        rVLC = findViewById(R.id.recyclerViewEst);
        rVLC.setItemAnimator(new DefaultItemAnimator());
        LinearLayoutManager LL = new LinearLayoutManager(this);
        rVLC.setLayoutManager(LL);

        listaC = (ArrayList<Usuario>) DataBase.getInstancia(listEstudiante.this).listarTodoEstudiante();
        adapterEst = new AdapterEstudiante(listaC, this);
        rVLC.setAdapter(adapterEst);
        adapterEst.notifyDataSetChanged();

        ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new estudianteHelper(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT, this);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(rVLC);

        adapterEst.notifyDataSetChanged();
        //Despues, verificar con un if
        FloatingActionButton fab1 = findViewById(R.id.fab);
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addEstudiante();
            }
        });
    }



    private void addEstudiante(){
        Intent intent = new Intent(this, AddEstudiante.class);
        intent.putExtra("editable", false);
        startActivity(intent);
    }



    @Override
    public void onContactSelected(Usuario usuario) {

    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
        if (direction == ItemTouchHelper.START) {
            if (viewHolder instanceof AdapterEstudiante.MyViewHolder) {
                // get the removed item name to display it in snack bar
                String name = listaC.get(viewHolder.getAdapterPosition()).getId();

                // save the index deleted
                final int deletedIndex = viewHolder.getAdapterPosition();
                // remove the item from recyclerView
                adapterEst.removeItem(viewHolder.getAdapterPosition());

                // showing snack bar with Undo option
                if(DataBase.getInstancia(listEstudiante.this).deleteUsuario(name)){
                Snackbar snackbar = Snackbar.make(coordinatorLayout, name + " removido!", Snackbar.LENGTH_LONG);
                snackbar.show();
                }
                else{
                    Toast.makeText(listEstudiante.this, "No se ha podidio eliminar",Toast.LENGTH_SHORT).show();
                }
            }
        } else {
            //If is editing a row object
            Usuario aux = adapterEst.getSwipedItem(viewHolder.getAdapterPosition());
            //send data to Edit Activity
            Intent intent = new Intent(this, AddEstudiante.class);
            intent.putExtra("editable", true);
            intent.putExtra("user", aux);
            adapterEst.notifyDataSetChanged(); //restart left swipe view
            startActivity(intent);
        }

    }

    @Override
    public void onItemMove(int source, int target) {
        adapterEst.onItemMove(source, target);
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