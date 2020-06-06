package com.example.lab0910.model;
import java.util.ArrayList;


public class Estudiante extends Usuario {
    private int edad;
    private ArrayList<Curso> listCursos;


    public Estudiante(String id, String nombre, String apellidos, int edad, ArrayList<Curso> list) {
        super(id, nombre, apellidos, "Estudiante");
        this.edad =edad;
        this.listCursos =list;
    }

    public Estudiante(String id, String nombre, String apellidos, int edad) {
        super(id, nombre, apellidos, "Estudiante");
        this.edad =edad;
        this.listCursos = new ArrayList<>();
    }

    public int getEdad() {
        return edad;
    }

    public ArrayList<Curso> getListCursos() {
        return listCursos;
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
                super.toString() +
                "edad=" + edad +
                ", listCursos=" + listCursos +
                '}';
    }
}
