package com.example.lab0910.curso;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.MicrophoneInfo;
import android.os.Build;
import android.os.Bundle;

import com.example.lab0910.BaseDatos.DataBase;
import com.example.lab0910.data.adapter.AdapterCurso;
import com.example.lab0910.data.helper.cursoHelper;
import com.example.lab0910.estudiante.AddEstudiante;
import com.example.lab0910.estudiante.listEstudiante;
import com.example.lab0910.matricula.ListMatriculados;
import com.example.lab0910.model.Curso;
import com.example.lab0910.principalMenu;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Parcelable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.Toast;

import com.example.lab0910.R;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ListCurso extends AppCompatActivity implements AdapterCurso.AdapterCursoListener, cursoHelper.RecyclerItemTouchHelperListener{


    private RecyclerView rVLC;
    private AdapterCurso adapterCurso;
    private ArrayList<Curso> listaC;
    private CoordinatorLayout coordinatorLayout;
    private SearchView searchView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_curso);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        coordinatorLayout=findViewById(R.id.coordinator_layout);

        rVLC = findViewById(R.id.recyclerViewCursos);
        rVLC.setItemAnimator(new DefaultItemAnimator());
        LinearLayoutManager LL = new LinearLayoutManager(this);
        rVLC.setLayoutManager(LL);

        listaC = (ArrayList<Curso>) DataBase.getInstancia(ListCurso.this).listarTodoCurso();
        adapterCurso = new AdapterCurso(listaC, this);
        rVLC.setAdapter(adapterCurso);
        adapterCurso.notifyDataSetChanged();

        //Despues, verificar con un if
        ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new cursoHelper(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT, this);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(rVLC);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addCurso();
            }
        });
    }

    @Override
    public void onContactSelected(Curso curso) {

    }

    private void addCurso(){
        Intent intent = new Intent(this, AddCurso.class);
        intent.putExtra("editable", false);
        startActivity(intent);
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
        if (direction == ItemTouchHelper.START) {
            if (viewHolder instanceof AdapterCurso.MyViewHolder) {
                // get the removed item name to display it in snack bar
                String name = listaC.get(viewHolder.getAdapterPosition()).getId();

                // save the index deleted
                final int deletedIndex = viewHolder.getAdapterPosition();
                // remove the item from recyclerView
                adapterCurso.removeItem(viewHolder.getAdapterPosition());

                // showing snack bar with Undo option

                if(DataBase.getInstancia(ListCurso.this).deleteCurso(name)
                        && DataBase.getInstancia(ListCurso.this).deleteMatriculaCurs(name)){
                Snackbar snackbar = Snackbar.make(coordinatorLayout, name + " removido!", Snackbar.LENGTH_LONG);
                snackbar.show();
                }
                else{
                    Toast.makeText(ListCurso.this, "No se ha podidio eliminar",Toast.LENGTH_SHORT).show();
                }
            }
        } else {
            //If is editing a row object
            Curso aux = adapterCurso.getSwipedItem(viewHolder.getAdapterPosition());
            //send data to Edit Activity
            Intent intent = new Intent(this, AddCurso.class);
            intent.putExtra("editable", true);
            intent.putExtra("curso", aux);
            adapterCurso.notifyDataSetChanged(); //restart left swipe view
            startActivity(intent);
        }
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds carreraList to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search, menu);

        // Associate searchable configuration with the SearchView   !IMPORTANT
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search)
                .getActionView();
        searchView.setSearchableInfo(searchManager
                .getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        // listening to search query text change, every type on input
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // filter recycler view when query submitted
                adapterCurso.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                // filter recycler view when text is changed
                adapterCurso.getFilter().filter(query);
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
