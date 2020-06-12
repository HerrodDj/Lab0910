package com.example.lab0910.estudiante;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lab0910.BaseDatos.DataBase;
import com.example.lab0910.R;
import com.example.lab0910.model.Usuario;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

public class AddEstudiante extends AppCompatActivity {

    private TextInputEditText id;
    private TextInputEditText nombre;
    private TextInputEditText apellidos;
    private EditText edad;
    private TextInputEditText password;
    private FloatingActionButton addEst;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_estudiante);
        id= findViewById(R.id.idEstAdd);
        nombre=findViewById(R.id.nomEstAdd);
        apellidos =findViewById(R.id.apelliEstAdd);
        edad = findViewById(R.id.edadEstAdd);
        password=findViewById(R.id.passwordEstAdd);
        addEst=findViewById(R.id.addEstBtn);

        addEst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Usuario usuario = new Usuario( id.getText().toString(), nombre.getText().toString(), apellidos.getText().toString(),password.getText().toString(),"Estudiante", Integer.parseInt(edad.getText().toString()) );
                    //DataBase dataBase= new DataBase(AddEstudiante.this);

                    if (DataBase.getInstancia(AddEstudiante.this).insertar(usuario)){
                        Toast.makeText(AddEstudiante.this, usuario.toString(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(AddEstudiante.this, listEstudiante.class);
                        AddEstudiante.this.startActivity(intent);

                    }else{
                        Toast.makeText(AddEstudiante.this, "Algo salio mal", Toast.LENGTH_SHORT).show();
                    }


                }
                catch (Exception e){
                    Toast.makeText(AddEstudiante.this, "Algo salio mal", Toast.LENGTH_SHORT).show();

                }
            }
        });


    }
}