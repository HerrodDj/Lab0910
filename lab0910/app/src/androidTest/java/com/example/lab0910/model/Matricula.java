package com.example.lab0910.model;

public class Matricula {


    private String idCurso;
    private String idEstudiante;

    public Matricula(String idCurso, String idEstudiante) {
        this.idCurso = idCurso;
        this.idEstudiante = idEstudiante;
    }

    public Matricula() {
        this.idCurso = null;
        this.idEstudiante = null;
    }

    public String getIdCurso() {
        return idCurso;
    }

    public String getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdCurso(String idCurso) {
        this.idCurso = idCurso;
    }

    public void setIdEstudiante(String idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    @Override
    public String toString() {
        return "Matricula{" +
                "idCurso='" + idCurso + '\'' +
                ", idEstudiante='" + idEstudiante + '\'' +
                '}';
    }
}
