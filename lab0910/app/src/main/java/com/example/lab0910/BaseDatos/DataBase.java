package com.example.lab0910.BaseDatos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.lab0910.model.Curso;
import com.example.lab0910.model.Usuario;

import java.util.ArrayList;
import java.util.List;

public class DataBase extends SQLiteOpenHelper {


    public static final String CURSO_TABLE = "CURSO_TABLE";
    public static final String COLUMN_DESCRIPCION_CUR = "DESCRIPCION_CUR";
    public static final String COLUMN_CREDITOS_CUR = "CREDITOS_CUR";
    public static final String COLUMN_ID_CUR = "ID_CUR";

    public static final String USUARIO_TABLE="USUARIO_TABLE";
    public static final String COLUMN_ID_USER="ID_USER";
    public static final String COLUMN_NOMBRE_USER="NOMBRE_USER";
    public static final String COLUMN_APELLIDOS_USER ="APELLIDOS_USER";
    public static final String COLUMN_PASSWORD_USER = "PASSWORD_USER";
    public static final String COLUMN_ROLE_USER ="ROLE_USER";
    public static final String  COLUMN_EDAD_USER="EDAD_USER";



    public DataBase(@Nullable Context context) {
        super(context, "matricula.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableCurso = "CREATE TABLE " + CURSO_TABLE +
                "(" + COLUMN_ID_CUR + " TEXT PRIMARY KEY, " + COLUMN_DESCRIPCION_CUR + " TEXT, " + COLUMN_CREDITOS_CUR + " INTEGER  )";
        db.execSQL(createTableCurso);
        String createTableUsuario = "CREATE TABLE " + USUARIO_TABLE +
                "(" + COLUMN_ID_USER + " TEXT PRIMARY KEY, " + COLUMN_NOMBRE_USER + " TEXT, " + COLUMN_APELLIDOS_USER +" TEXT, "
                + COLUMN_PASSWORD_USER + " TEXT, " + COLUMN_ROLE_USER + " TEXT, " + COLUMN_EDAD_USER + " INTEGER )";
        db.execSQL(createTableUsuario);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean insertar(Curso curso){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_ID_CUR, curso.getId());
        cv.put(COLUMN_DESCRIPCION_CUR, curso.getDescripcion());
        cv.put(COLUMN_CREDITOS_CUR, curso.getCreditos());
        if( db.insert(CURSO_TABLE,null,cv) == -1){
            return false;}
        else{return true;}
    }



    public List<Curso> listarTodoCurso(){
        List<Curso> list = new ArrayList<>();
        String queryString = "SELECT * FROM " +CURSO_TABLE;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString,null);
        if(cursor.moveToFirst()){
            do{
                String id = cursor.getString(0);
                String descripcion = cursor.getString(1);
                int creditos = cursor.getInt(2);
                Curso curso = new Curso(id,descripcion,creditos);
                list.add(curso);
            }while (cursor.moveToNext());

        }

        cursor.close();
        db.close();
        return list;


    }




    // CRUD para cursos
    public boolean insertar(Usuario usuario){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_ID_USER, usuario.getId());
        cv.put(COLUMN_NOMBRE_USER, usuario.getNombre());
        cv.put(COLUMN_APELLIDOS_USER, usuario.getApellido());
        cv.put(COLUMN_PASSWORD_USER, usuario.getPassword());
        cv.put(COLUMN_ROLE_USER, usuario.getRole());
        cv.put(COLUMN_EDAD_USER,usuario.getEdad());
        if( db.insert(USUARIO_TABLE,null,cv) == -1){
            return false;}
        else{return true;}
    }


}