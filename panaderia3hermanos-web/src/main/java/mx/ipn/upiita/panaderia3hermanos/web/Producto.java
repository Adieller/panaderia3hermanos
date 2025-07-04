package mx.ipn.upiita.panaderia3hermanos.web;

import jakarta.faces.context.FacesContext;

import java.math.BigDecimal;

public class Producto {
    private int id;

    private String nombre;
    private String descripcion;
    private BigDecimal precio;
    //private String imagen;

    public Producto(int id,String nombre, String descripcion,BigDecimal precio) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        //this.imagen = imagen;
    }

    // Getters necesarios para JSF
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getNombre() { return nombre; }
    public String getDescripcion() { return descripcion; }
    public BigDecimal getPrecio() { return precio; }
    //public String getImagen() { return imagen; }
}