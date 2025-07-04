package mx.ipn.upiita.panaderia3hermanos.web;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Named("carritoBean")
@SessionScoped
public class CarritoBean implements Serializable {

    private List<ItemCarrito> items = new ArrayList<>();

    // Agregar producto al carrito
    public void agregarItem(ItemCarrito nuevo) {
        for (ItemCarrito item : items) {
            if (item.getProductoId() == nuevo.getProductoId()) {
                item.setCantidad(item.getCantidad() + nuevo.getCantidad());
                return;
            }
        }
        items.add(nuevo);
    }

    // Eliminar un producto por ID
    public void eliminarItem(int productoId) {
        items.removeIf(item -> item.getProductoId() == productoId);
    }

    // Calcular total del carrito
    public BigDecimal getTotal() {
        return items.stream()
                .map(ItemCarrito::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    // Vaciar carrito
    public void vaciar() {
        items.clear();
    }

    // Acceso a la lista de productos
    public List<ItemCarrito> getItems() {
        return items;
    }

    // Método opcional para confirmar compra más adelante
    public String confirmarCompra() {
        // Aquí podrías procesar el pedido e insertar en la BD
        vaciar();
        return "confirmacionCompra?faces-redirect=true";
    }
}
