package com.example.tarea4.ModeloRv;

public class Recordatorios {
    String nombre_rec, fecha_rec, hora_rec, repeticion_rec, id_usu;
    int id_rec;

    public  Recordatorios(){}
    public Recordatorios(String nombre_rec, String fecha_rec, String hora_rec, String repeticion_rec, String id_usu, int id_rec) {
        this.nombre_rec = nombre_rec;
        this.fecha_rec = fecha_rec;
        this.hora_rec = hora_rec;
        this.repeticion_rec = repeticion_rec;
        this.id_usu = id_usu;
        this.id_rec = id_rec;
    }

    public String getNombre_rec() {
        return nombre_rec;
    }

    public void setNombre_rec(String nombre_rec) {
        this.nombre_rec = nombre_rec;
    }

    public String getFecha_rec() {
        return fecha_rec;
    }

    public void setFecha_rec(String fecha_rec) {
        this.fecha_rec = fecha_rec;
    }

    public String getHora_rec() {
        return hora_rec;
    }

    public void setHora_rec(String hora_rec) {
        this.hora_rec = hora_rec;
    }

    public String getRepeticion_rec() {
        return repeticion_rec;
    }

    public void setRepeticion_rec(String repeticion_rec) {
        this.repeticion_rec = repeticion_rec;
    }

    public String getId_usu() {
        return id_usu;
    }

    public void setId_usu(String id_usu) {
        this.id_usu = id_usu;
    }

    public int getId_rec() {
        return id_rec;
    }

    public void setId_rec(int id_rec) {
        this.id_rec = id_rec;
    }
}
