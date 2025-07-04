package mx.ipn.upiita.panaderia3hermanos.web;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

import java.math.BigDecimal;

@Named("catalogoBean")
@RequestScoped
public class PanaderiaBeanCatalogo {

    private int productoId;
    private String nombre;
    private BigDecimal precio;
    private int cantidad;


// Este bean puede ser usado para mostrar productos en el catálogo
    // o para agregar un producto al carrito (junto con CarritoBean)

    public int getProductoId() {
        return productoId;
    }

    public void setProductoId(int productoId) {
        this.productoId = productoId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
