package com.example.tarea4.ModeloRv;

public class Eventos {
    String titulo_eve, fechaIni_eve, fechaFin_eve, horaIni_eve, horaFin_eve, repeticion_eve, notificacion_eve, tipoEvento_eve, curso_eve, id_usu;
    int id_eve;

    public Eventos(){

    }
    public Eventos(String titulo_eve, String fechaIni_eve, String fechaFin_eve, String horaIni_eve, String horaFin_eve, String repeticion_eve,
                   String notificacion_eve, String tipoEvento_eve, String curso_eve, String id_usu, int id_eve) {
        this.titulo_eve = titulo_eve;
        this.fechaIni_eve = fechaIni_eve;
        this.fechaFin_eve = fechaFin_eve;
        this.horaIni_eve = horaIni_eve;
        this.horaFin_eve = horaFin_eve;
        this.repeticion_eve = repeticion_eve;
        this.notificacion_eve = notificacion_eve;
        this.tipoEvento_eve = tipoEvento_eve;
        this.curso_eve = curso_eve;
        this.id_usu = id_usu;
        this.id_eve = id_eve;
    }

    public String getTitulo_eve() {
        return titulo_eve;
    }

    public void setTitulo_eve(String titulo_eve) {
        this.titulo_eve = titulo_eve;
    }

    public String getFechaIni_eve() {
        return fechaIni_eve;
    }

    public void setFechaIni_eve(String fechaIni_eve) {
        this.fechaIni_eve = fechaIni_eve;
    }

    public String getFechaFin_eve() {
        return fechaFin_eve;
    }

    public void setFechaFin_eve(String fechaFin_eve) {
        this.fechaFin_eve = fechaFin_eve;
    }

    public String getHoraIni_eve() {
        return horaIni_eve;
    }

    public void setHoraIni_eve(String horaIni_eve) {
        this.horaIni_eve = horaIni_eve;
    }

    public String getHoraFin_eve() {
        return horaFin_eve;
    }

    public void setHoraFin_eve(String horaFin_eve) {
        this.horaFin_eve = horaFin_eve;
    }

    public String getRepeticion_eve() {
        return repeticion_eve;
    }

    public void setRepeticion_eve(String repeticion_eve) {
        this.repeticion_eve = repeticion_eve;
    }

    public String getNotificacion_eve() {
        return notificacion_eve;
    }

    public void setNotificacion_eve(String notificacion_eve) {
        this.notificacion_eve = notificacion_eve;
    }

    public String getTipoEvento_eve() {
        return tipoEvento_eve;
    }

    public void setTipoEvento_eve(String tipoEvento_eve) {
        this.tipoEvento_eve = tipoEvento_eve;
    }

    public String getCurso_eve() {
        return curso_eve;
    }

    public void setCurso_eve(String curso_eve) {
        this.curso_eve = curso_eve;
    }

    public String getId_usu() {
        return id_usu;
    }

    public void setId_usu(String id_usu) {
        this.id_usu = id_usu;
    }

    public int getId_eve() {
        return id_eve;
    }

    public void setId_eve(int id_eve) {
        this.id_eve = id_eve;
    }
}
