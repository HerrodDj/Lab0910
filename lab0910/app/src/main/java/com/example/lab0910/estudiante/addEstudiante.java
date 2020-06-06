package com.example.lab0910.estudiante;

import androidx.appcompat.app.AppCompatActivity;
import com.example.lab0910.R;
import com.example.lab0910.curso.addCurso;
import com.example.lab0910.model.Estudiante;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class addEstudiante extends AppCompatActivity {

    private TextInputEditText idEst;
    private TextInputEditText nombreEst;
    private TextInputEditText apellidosEst;
    private EditText edadEst;
    private FloatingActionButton addEstbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_estudiante);
        idEst = findViewById(R.id.idEstAdd);
        nombreEst = findViewById(R.id.nomEstAdd);
        apellidosEst = findViewById(R.id.apelliEstAdd);
        edadEst =findViewById(R.id.edadEstAdd);
        addEstbtn = findViewById(R.id.addEstBtn);


        addEstbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Estudiante estudiante = new Estudiante(idEst.getText().toString(),
                        nombreEst.getText().toString(), apellidosEst.getText().toString(), Integer.parseInt(edadEst.getText().toString()));
                Toast.makeText(addEstudiante.this, "Datos adquirdos:"+estudiante.toString(), Toast.LENGTH_SHORT).show();

            }
        });



    }
}