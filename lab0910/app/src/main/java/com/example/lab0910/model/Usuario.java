
package com.example.lab0910.model;


import java.util.ArrayList;

public class Usuario{

    private String id;
    private String nombre;
    private String apellido;
    private String password;
    private String role;
    private int edad;
    private ArrayList<Curso> listCursos;

    public Usuario(String id, String nombre, String apellido, String password, String role, int edad) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.role = role;
        this.password =password;
        this.edad =edad;
        listCursos =new ArrayList<>();
    }

    public Usuario(String id, String nombre, String apellido, String password, String role, int edad, ArrayList<Curso> listCursos) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.password = password;
        this.role = role;
        this.edad = edad;
        this.listCursos = listCursos;
    }

    public Usuario() {
        this.id = null;
        this.nombre = null;
        this.apellido = null;
        this.role = null;
        this.password =null;
        this.edad =-1;
        listCursos =new ArrayList<>();

    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getRole() {
        return role;
    }

    public String getPassword() {
        return password;
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

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setListCursos(ArrayList<Curso> listCursos) {
        this.listCursos = listCursos;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", edad=" + edad +
                ", listCursos=" + listCursos.toString() +
                '}';
    }
}