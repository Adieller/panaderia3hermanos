package mx.ipn.upiita.panaderia3hermanos.web;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import jakarta.annotation.Resource;
import javax.sql.DataSource;


@Named("carritoBean")
@SessionScoped
public class CarritoBean implements Serializable {
    @Resource(lookup = "java:/MyAppDS")  // Reemplaza con tu JNDI real
    private DataSource dataSource;

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
        if (items.isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "No hay productos en el carrito", null));
            return null;
        }

        try (Connection conn = dataSource.getConnection()) {
            conn.setAutoCommit(false);

            // Obtener cliente desde sesión
            InicioSesion sesion = (InicioSesion) FacesContext.getCurrentInstance()
                    .getExternalContext().getSessionMap().get("datosSesion");
            int clienteId = sesion.getUsuarioId();

            // Insertar en Compra
            String insertCompra = "INSERT INTO Compra (ComClieID, ComFecha, ComTotal) VALUES (?, CURDATE(), ?)";
            PreparedStatement psCompra = conn.prepareStatement(insertCompra, Statement.RETURN_GENERATED_KEYS);
            psCompra.setInt(1, clienteId);
            psCompra.setBigDecimal(2, getTotal());
            psCompra.executeUpdate();

            ResultSet rs = psCompra.getGeneratedKeys();
            if (!rs.next()) throw new SQLException("No se generó el ID de compra");
            int compraId = rs.getInt(1);

            // Insertar en DetalleCompra
            String insertDetalle = "INSERT INTO DetalleCompra (ComID, ProdCodProducto, PrecioUnitario, Subtotal, Cantidad) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement psDetalle = conn.prepareStatement(insertDetalle);
            for (ItemCarrito item : items) {
                psDetalle.setInt(1, compraId);
                psDetalle.setInt(2, item.getProductoId());
                psDetalle.setBigDecimal(3, item.getPrecio());
                psDetalle.setBigDecimal(4, item.getSubtotal());
                psDetalle.setInt(5, item.getCantidad());
                psDetalle.addBatch();
            }
            psDetalle.executeBatch();

            conn.commit();
            vaciar();
            return "confirmacionCompra?faces-redirect=true";

        } catch (Exception e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al procesar la compra", null));
            return null;
        }
    }
}


