package com.example.lab0910.matricula;

import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.example.lab0910.BaseDatos.DataBase;
import com.example.lab0910.data.adapter.AdapterCurso;
import com.example.lab0910.data.adapter.AdapterMatriculados;
import com.example.lab0910.data.helper.cursoHelper;
import com.example.lab0910.data.helper.desmatricularCursoHelper;
import com.example.lab0910.data.helper.matricularCursoHelper;
import com.example.lab0910.model.Curso;
import com.example.lab0910.model.Matricula;
import com.example.lab0910.principalMenu;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.lab0910.R;

import java.util.ArrayList;
import java.util.List;

public class ListMatriculados extends AppCompatActivity implements AdapterMatriculados.AdapterMatriculadosListener, desmatricularCursoHelper.RecyclerItemTouchHelperListener {


    private RecyclerView rVLC;
    private AdapterMatriculados adapterCurso;
    private ArrayList<Curso> listaC;
    private CoordinatorLayout coordinatorLayout;
    static String idSesion;
    private SearchView searchView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_matriculados);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        coordinatorLayout = findViewById(R.id.coordinator_layout);

        idSesion = (String) getIntent().getSerializableExtra("idSesion");

        rVLC = findViewById(R.id.recyclerViewCursos);
        rVLC.setItemAnimator(new DefaultItemAnimator());
        LinearLayoutManager LL = new LinearLayoutManager(this);
        rVLC.setLayoutManager(LL);


        listaC = (ArrayList<Curso>) DataBase.getInstancia(ListMatriculados.this).listCursosMatPorEstudiante(idSesion);
        adapterCurso = new AdapterMatriculados(listaC, this);
        rVLC.setAdapter(adapterCurso);
        adapterCurso.notifyDataSetChanged();

        //Despues, verificar con un if
        ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new desmatricularCursoHelper(0, ItemTouchHelper.RIGHT, this);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(rVLC);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mat();
            }
        });
    }

    @Override
    public void onContactSelected(Curso curso) {

    }

    public void mat(){
        Intent a = new Intent(this, listMatricular.class);
        a.putExtra("idSesion",idSesion);
        startActivity(a);
    }

    @Override
    public void onSwiped(final RecyclerView.ViewHolder viewHolder, int direction, int position) {
        final Curso aux = adapterCurso.getSwipedItem(viewHolder.getAdapterPosition());
        idSesion = (String) getIntent().getSerializableExtra("idSesion");
        new AlertDialog.Builder(ListMatriculados.this, R.style.Theme_AppCompat_DayNight_Dialog_Alert)
                .setTitle("Desea desmatricular el curso")
                .setMessage(aux.toString())
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Continue with delete operation
                        try {
                            if (DataBase.getInstancia(ListMatriculados.this).delete(new Matricula(aux.getId(), idSesion))) {
                                Toast.makeText(ListMatriculados.this, "Se ha desmatriculado " + aux.getDescripcion(), Toast.LENGTH_SHORT).show();
                                adapterCurso.removeItem(viewHolder.getAdapterPosition());

                            } else {
                                Toast.makeText(ListMatriculados.this, "No se ha desmatriculado ", Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            Toast.makeText(ListMatriculados.this, "Algo salio mal ", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .setNegativeButton(android.R.string.no, null)
                .setIcon(R.drawable.ic_alert)
                .show();
                adapterCurso.notifyDataSetChanged();
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

