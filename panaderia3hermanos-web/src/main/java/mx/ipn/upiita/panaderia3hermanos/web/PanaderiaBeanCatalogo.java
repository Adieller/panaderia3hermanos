package mx.ipn.upiita.panaderia3hermanos.web;
import jakarta.faces.context.FacesContext;
import mx.ipn.upiita.panaderia3hermanos.web.Producto;


import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.inject.Named;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Named("catalogoBean")
@RequestScoped
public class PanaderiaBeanCatalogo {

    @Resource(lookup = "java:/MyAppDS")
    private DataSource dataSource;

    private List<Producto> productos;

    @PostConstruct
    public void init() {
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
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al cargar el catálogo", null));
        }
    }

    public List<Producto> getProductos() {
        return productos;
    }
}