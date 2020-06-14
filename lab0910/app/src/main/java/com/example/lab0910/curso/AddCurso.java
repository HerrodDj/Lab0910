package com.example.lab0910.curso;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lab0910.BaseDatos.DataBase;
import com.example.lab0910.R;
import com.example.lab0910.estudiante.AddEstudiante;
import com.example.lab0910.model.Curso;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

public class AddCurso extends AppCompatActivity {
    private TextInputEditText idCurso;
    private TextInputEditText descriCurso;
    private EditText crediCursos;
    private FloatingActionButton addCurso;


    private boolean editable = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_curso);
        idCurso = findViewById(R.id.idCursoAdd);
        descriCurso = findViewById(R.id.descripcionAddCur);
        crediCursos = findViewById(R.id.creditosAddCur);
        addCurso = findViewById(R.id.addCursoBtn);


        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            editable = extras.getBoolean("editable");
            if (editable) {   // is editing some row
                Curso aux = (Curso) getIntent().getSerializableExtra("curso");
                idCurso.setText(aux.getId());
                idCurso.setEnabled(false);
                descriCurso.setText(aux.getDescripcion());
                crediCursos.setText(String.valueOf(aux.getCreditos()));
                //edit action
                addCurso.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        editCurso();
                    }
                });
            } else {         // is adding new Carrera object
                //add new action
                addCurso.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        addCurso();
                    }
                });
            }
        }
    }


    public  void addCurso(){
        try{
        Curso curso = new Curso(idCurso.getText().toString(), descriCurso.getText().toString(), Integer.parseInt(crediCursos.getText().toString()));
        DataBase dataBase =DataBase.getInstancia(AddCurso.this);
        if( dataBase.insertar(curso)){
            Toast.makeText(AddCurso.this,"Se inserto"+ curso.toString(),Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(AddCurso.this, ListCurso.class);
            AddCurso.this.startActivity(intent);
        }else{ Toast.makeText(AddCurso.this, "No se agregó el curso",Toast.LENGTH_SHORT).show();
        }
        }
        catch (Exception e){
            Toast.makeText(AddCurso.this, "Algo salio mal", Toast.LENGTH_SHORT).show();

        }
    }


    public void editCurso(){
        try{
        Curso curso = new Curso(idCurso.getText().toString(), descriCurso.getText().toString(), Integer.parseInt(crediCursos.getText().toString()));
        DataBase dataBase =DataBase.getInstancia(AddCurso.this);
        if( dataBase.update(curso)){
            Toast.makeText(AddCurso.this,"Se Modefico: "+ curso.toString(),Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(AddCurso.this, ListCurso.class);
            AddCurso.this.startActivity(intent);
        }else{ Toast.makeText(AddCurso.this, "No se modifico el curso",Toast.LENGTH_SHORT).show();
        }}
        catch (Exception e){
            Toast.makeText(AddCurso.this, "Algo salio mal", Toast.LENGTH_SHORT).show();

        }

    }


    public boolean validateForm() {
        int error = 0;
        if (TextUtils.isEmpty(this.idCurso.getText())) {
            idCurso.setError("Id requerido");
            error++;
        }
        if (TextUtils.isEmpty(this.descriCurso.getText())) {
            descriCurso.setError("Descripcion requerida");
            error++;
        }
        if (TextUtils.isEmpty(this.crediCursos.getText())){
            crediCursos.setError("Creditos Requeridos");
            error++;
        }
        return true;
    }



    @Override
    public void onBackPressed() { //TODO it's not working yet
        /*if (!searchView.isIconified()) {
            searchView.setIconified(true);
            return;
        }*/
        Intent a = new Intent(this, AddCurso.class);
        startActivity(a);
        super.onBackPressed();
    }

}