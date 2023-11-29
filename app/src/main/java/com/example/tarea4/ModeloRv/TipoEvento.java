package com.example.tarea4.ModeloRv;

public class TipoEvento {
    String nombre_tip, color_tip, id_usu;
    int id_tip;

    public TipoEvento(){}
    public TipoEvento(String nombre_tip, String color_tip, String id_usu, int id_tip) {
        this.nombre_tip = nombre_tip;
        this.color_tip = color_tip;
        this.id_usu = id_usu;
        this.id_tip = id_tip;
    }

    public String getNombre_tip() {
        return nombre_tip;
    }

    public void setNombre_tip(String nombre_tip) {
        this.nombre_tip = nombre_tip;
    }

    public String getColor_tip(){
        return color_tip;
    }

    public void setColor_tip(String color_tip){
        this.color_tip = color_tip;
    }

    public String getId_usu() {
        return id_usu;
    }

    public void setId_usu(String id_usu) {
        this.id_usu = id_usu;
    }

    public int getId_tip() {
        return id_tip;
    }

    public void setId_tip(int id_tip) {
        this.id_tip = id_tip;
    }
}
