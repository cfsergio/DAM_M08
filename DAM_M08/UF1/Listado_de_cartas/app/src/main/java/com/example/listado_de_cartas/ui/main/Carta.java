package com.example.listado_de_cartas.ui.main;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

/**
 * https://carlesgm.gitlab.io/tutorials-m08/3-client-themovie-db/4-carregar-dades-internet/1-crear-el-pojo/
 * https://carlesgm.gitlab.io/tutorials-m08/3-client-themovie-db/7-detall/1-preparar-pojo/
 * https://carlesgm.gitlab.io/tutorials-m08/3-client-themovie-db/9-base-de-dades/2-preparar-les-entitats/
 */
@Entity
public class Carta implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String nombre;
    private String colores;
    private String tipo;
    private String texto;
    private String imagen;
    private String rareza;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getColores() {
        return colores;
    }

    public void setColores(String colores) {
        this.colores = colores;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getRareza() {
        return rareza;
    }

    public void setRareza(String rareza) {
        this.rareza = rareza;
    }

    @Override
    public String toString() {
        return "Carta{" +
                "nombre='" + nombre + '\'' +
                ", colores='" + colores + '\'' +
                ", tipo='" + tipo + '\'' +
                ", texto='" + texto + '\'' +
                ", imagen='" + imagen + '\'' +
                ", rareza='" + rareza + '\'' +
                '}';
    }
}
