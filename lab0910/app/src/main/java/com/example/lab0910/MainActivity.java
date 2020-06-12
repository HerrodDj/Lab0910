package com.example.lab0910;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.lab0910.BaseDatos.DataBase;
import com.example.lab0910.curso.AddCurso;
import com.example.lab0910.curso.ListCurso;
import com.example.lab0910.estudiante.AddEstudiante;
import com.example.lab0910.model.Usuario;

import java.security.Principal;

public class MainActivity extends AppCompatActivity {
    private EditText name;
    private EditText pass;
    private ImageButton btn;
    private static String rol;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn= findViewById(R.id.login);
        name = findViewById(R.id.username);
        pass = findViewById(R.id.password);



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = name.getText().toString();
                if(validateForm()){
                    if(validUser(id)){
                        Intent a = new Intent(MainActivity.this, principalMenu.class);
                        a.putExtra("rol", rol);
                        a.putExtra("id", id);
                        startActivity(a);
                        finish();
                    }
                }
            }
        });
    }

    private boolean validUser(String id) {
        boolean flag = false;
        try {
            Usuario usuario = DataBase.getInstancia(MainActivity.this).getUsuario(id);
            rol=usuario.getRole();
            flag=true;
        }catch (Exception e){
            Toast.makeText(getApplicationContext(), "No pudo Autentificar", Toast.LENGTH_LONG).show();  // display a toast message
        }
        return flag;
    }


    public boolean validateForm() {
        int error = 0;
        if (TextUtils.isEmpty(this.name.getText())) {
            name.setError("Usuario requerido");
            error++;
        }
        if (TextUtils.isEmpty(this.pass.getText())) {
            pass.setError("Contraseña requerida");
            error++;
        }
        if (error > 0) {
            Toast.makeText(getApplicationContext(), "LLenar todos los campos", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

}