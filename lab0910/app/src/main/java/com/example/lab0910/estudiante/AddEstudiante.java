package com.example.lab0910.estudiante;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lab0910.BaseDatos.DataBase;
import com.example.lab0910.R;
import com.example.lab0910.model.Curso;
import com.example.lab0910.model.Usuario;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

public class AddEstudiante extends AppCompatActivity {

    private TextInputEditText idEst;
    private TextInputEditText nombreEst;
    private TextInputEditText apellidosEst;
    private EditText edadEst;
    private TextInputEditText passwordEst;
    private FloatingActionButton addEst;
    private boolean editable =true;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_estudiante);
        idEst = findViewById(R.id.idEstAdd);
        nombreEst =findViewById(R.id.nomEstAdd);
        apellidosEst =findViewById(R.id.apelliEstAdd);
        edadEst = findViewById(R.id.edadEstAdd);
        passwordEst =findViewById(R.id.passwordEstAdd);
        addEst=findViewById(R.id.addEstBtn);

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            editable = extras.getBoolean("editable");
            if (editable) {   // is editing some row
                Usuario aux = (Usuario) getIntent().getSerializableExtra("user");
                idEst.setText(aux.getId());
                idEst.setEnabled(false);
                nombreEst.setText(aux.getNombre());
                apellidosEst.setText(aux.getApellido());
                edadEst.setText(String.valueOf(aux.getEdad()));
                //edit action
                addEst.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        editEstudiante();
                    }
                });
            } else {         // is adding new Carrera object
                //add new action
                addEst.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        addEstudiante();
                    }
                });
            }
        }
    }



    public void addEstudiante(){
        try {
            Usuario usuario = new Usuario( idEst.getText().toString(), nombreEst.getText().toString(), apellidosEst.getText().toString(), passwordEst.getText().toString(),"Estudiante", Integer.parseInt(edadEst.getText().toString()) );
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

    public void editEstudiante(){
        try {
            Usuario usuario = new Usuario( idEst.getText().toString(), nombreEst.getText().toString(), apellidosEst.getText().toString(), passwordEst.getText().toString(),"Estudiante", Integer.parseInt(edadEst.getText().toString()) );
            //DataBase dataBase= new DataBase(AddEstudiante.this);
            if (DataBase.getInstancia(AddEstudiante.this).update(usuario)){
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

    public boolean validateForm() {
        int error = 0;
        if (TextUtils.isEmpty(this.idEst.getText())) {
            idEst.setError("Id requerido");
            error++;
        }
        if (TextUtils.isEmpty(this.nombreEst.getText())) {
            nombreEst.setError("Nombre requerido");
            error++;
        }
        if (TextUtils.isEmpty(this.apellidosEst.getText())){
            apellidosEst.setError("Apellidos Requeridos");
            error++;
        }
        if (TextUtils.isEmpty(this.edadEst.getText())){
            edadEst.setError("Edad Requerida");
            error++;
        }
        if (TextUtils.isEmpty(this.passwordEst.getText())){
            passwordEst.setError("Password Requerido");
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
        Intent a = new Intent(this, listEstudiante.class);
        startActivity(a);
        super.onBackPressed();
    }

}