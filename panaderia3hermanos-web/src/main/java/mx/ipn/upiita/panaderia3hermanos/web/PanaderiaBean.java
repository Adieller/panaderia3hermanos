package mx.ipn.upiita.panaderia3hermanos.web;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Named("panaderiaBean")
@RequestScoped
public class PanaderiaBean {

    @Resource(lookup = "java:/MyAppDS") // Asegúrate que coincida con tu JNDI en el server
    private DataSource dataSource;

    private List<Producto> productos;

    @PostConstruct
    public void cargarProductos() {
        productos = new ArrayList<>();
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT ProdCodProducto, ProdNombre, ProdDescripcion, ProdPrecio FROM Producto";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("ProdCodProducto");
                String nombre = rs.getString("ProdNombre");
                String descripcion = rs.getString("ProdDescripcion");
                BigDecimal precio = rs.getBigDecimal("ProdPrecio");

                productos.add(new Producto(id, nombre, descripcion, precio));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "No se pudieron cargar los productos.", null));
        }
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public String cerrarSesion() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "login?faces-redirect=true";
    }
}
