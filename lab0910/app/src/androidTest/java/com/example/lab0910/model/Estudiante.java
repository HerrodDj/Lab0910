package com.example.lab0910.model;
import java.util.ArrayList;


public class Estudiante {

    private String id;
    private String nombre;
    private String apellidos;
    private int edad;
    private ArrayList<Curso> listCursos;


    public Estudiante(String id, String nombre, String apellidos, int edad, ArrayList<Curso> listCursos) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.edad = edad;
        this.listCursos = listCursos;
    }

    public Estudiante(String id, String nombre, String apellidos, int edad) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.edad = edad;
        this.listCursos = new ArrayList<>();
    }

    public Estudiante() {
        this.id = null;
        this.nombre = null;
        this.apellidos = null;
        this.edad = 0;
        this.listCursos = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public int getEdad() {
        return edad;
    }

    public ArrayList<Curso> getListCursos() {
        return listCursos;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setListCursos(ArrayList<Curso> listCursos) {
        this.listCursos = listCursos;
    }

    @Override
    public String toString() {
        return "Estudiante{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", edad=" + edad +
                ", listCursos=" + listCursos.toString() +
                '}';
    }
}
