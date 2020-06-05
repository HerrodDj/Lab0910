package com.example.lab0910.model;

public class Usuario {
    private String id;
    private String nombre;
    private String apellidos;
    private String role;

    public Usuario(String id, String nombre, String apellidos, String role) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.role = role;
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

    public String getRole() {
        return role;
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

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", Role='" + role + '\'' +
                '}';
    }
}
