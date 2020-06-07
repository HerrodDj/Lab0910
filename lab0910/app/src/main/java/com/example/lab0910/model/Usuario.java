
package com.example.lab0910.model;


public class Usuario{

    private String id;
    private String nombre;
    private String apellido;
    private String password;
    private String role;

    public Usuario(String id, String nombre, String apellido, String role, String password) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.role = role;
        this.password =password;
    }

    public Usuario() {
        this.id = null;
        this.nombre = null;
        this.apellido = null;
        this.role = null;
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

    @Override
    public String toString() {
        return "Usuario{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}