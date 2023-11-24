package com.example.tarea4.ModeloRv;

public class Cursos {
    String nombre_cur, nomDocente_cur, descripcion_cur, id_usu;
    int id_cur;

    public Cursos(){

    }
    public Cursos(String nombre_cur, String nomDocente_cur, String descripcion_cur, String id_usu, int id_cur) {
        this.nombre_cur = nombre_cur;
        this.nomDocente_cur = nomDocente_cur;
        this.descripcion_cur = descripcion_cur;
        this.id_usu = id_usu;
        this.id_cur = id_cur;
    }

    public String getNombre_cur() {
        return nombre_cur;
    }

    public void setNombre_cur(String nombre_cur) {
        this.nombre_cur = nombre_cur;
    }

    public String getNomDocente_cur() {
        return nomDocente_cur;
    }

    public void setNomDocente_cur(String nomDocente_cur) {
        this.nomDocente_cur = nomDocente_cur;
    }

    public String getDescripcion_cur() {
        return descripcion_cur;
    }

    public void setDescripcion_cur(String descripcion_cur) {
        this.descripcion_cur = descripcion_cur;
    }

    public String getId_usu() {
        return id_usu;
    }

    public void setId_usu(String id_usu) {
        this.id_usu = id_usu;
    }

    public int getId_cur() {
        return id_cur;
    }

    public void setId_cur(int id_cur) {
        this.id_cur = id_cur;
    }
}
