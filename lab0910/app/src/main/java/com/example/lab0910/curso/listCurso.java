package com.example.lab0910.curso;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.lab0910.BaseDatos.DataBase;
import com.example.lab0910.R;
import com.example.lab0910.estudiante.addEstudiante;
import com.example.lab0910.model.Curso;

import java.util.List;

public class listCurso extends AppCompatActivity {


    private Button list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_curso);

        list = findViewById(R.id.list);

        list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataBase dataBase =new DataBase(listCurso.this);
                List<Curso> lista = dataBase.listarTodoCurso();
                Toast.makeText(listCurso.this, "Datos adquirdos:" + lista.toString(), Toast.LENGTH_SHORT).show();

            }
        });

    }
}