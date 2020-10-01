package com.example.misconciertos.dto;

public class Conciertos {
    private String artista;
    private String fecha;
    private String genero;
    private int valor;
    private int calificacion;

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }



    @Override
    public String toString(){
        return fecha+ "   " + artista + "   " + genero+ "   " + valor +"   " + calificacion;
    }

}
