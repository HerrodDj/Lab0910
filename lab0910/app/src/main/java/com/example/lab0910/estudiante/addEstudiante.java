package com.example.lab0910.estudiante;

import androidx.appcompat.app.AppCompatActivity;
import com.example.lab0910.R;
import com.example.lab0910.model.Usuario;
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
    private TextInputEditText passwordEst;

    private FloatingActionButton addEstbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_estudiante);
        idEst = findViewById(R.id.idEstAdd);
        nombreEst = findViewById(R.id.nomEstAdd);
        apellidosEst = findViewById(R.id.apelliEstAdd);
        edadEst =findViewById(R.id.edadEstAdd);
        passwordEst = findViewById(R.id.passwordEstAdd);
        addEstbtn = findViewById(R.id.addEstBtn);


        addEstbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Usuario usuario = new Usuario(idEst.getText().toString(),
                            nombreEst.getText().toString(), apellidosEst.getText().toString(),"estudiante", passwordEst.getText().toString(), Integer.getInteger(edadEst.toString()));
                    Toast.makeText(addEstudiante.this, "Datos adquirdos:" + usuario.toString(), Toast.LENGTH_SHORT).show();


                }catch (Exception e){
                    Toast.makeText(addEstudiante.this, "Error al crear el Estudiante" , Toast.LENGTH_SHORT).show();
                }
            }
        });



    }
}