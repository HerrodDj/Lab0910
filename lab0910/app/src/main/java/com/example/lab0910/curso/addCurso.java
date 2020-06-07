package com.example.lab0910.curso;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lab0910.BaseDatos.DataBase;
import com.example.lab0910.R;
import com.example.lab0910.estudiante.addEstudiante;
import com.example.lab0910.model.Curso;
import com.google.android.material.textfield.TextInputEditText;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;


public class addCurso extends AppCompatActivity {

    private ToggleButton addCurso;
    private TextInputEditText idCurso;
    private TextInputEditText descripcionCurso;
    private EditText creditosCurso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_curso);
        this.addCurso = findViewById(R.id.addCursoBtn);
        this.idCurso = findViewById(R.id.idCursoAdd);
        this.descripcionCurso = findViewById(R.id.descripcionAddCur);
        this.creditosCurso = findViewById(R.id.creditosAddCur);


        addCurso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Curso curso;
                try {
                   curso= new Curso(idCurso.getText().toString(),
                            descripcionCurso.getText().toString(),
                            Integer.parseInt(creditosCurso.getText().toString()));
                    Toast.makeText(addCurso.this, "Datos adquirdos:" + curso.toString(), Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    Toast.makeText(addCurso.this, "Error al crear el Curso", Toast.LENGTH_SHORT).show();
                    curso = new Curso(null, null, -1);
                }

                DataBase database = new DataBase(addCurso.this);
            }
        });


    }
}