package mx.ipn.upiita.panaderia3hermanos.web;

import jakarta.annotation.Resource;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;

@Named(value = "datosSesion")
@RequestScoped
public class InicioSesion {

    @Resource(lookup = "java:/MyAppDS")
    private DataSource ds;

    private String email;
    private String password;
    private String mensaje;

    public void testConexion() {
        try (Connection conn = ds.getConnection()) {
            mensaje = "Conectado a: " + conn.getMetaData().getDatabaseProductName();
        } catch (Exception e) {
            mensaje = "Error de conexión: " + e.getMessage();
        }
    }

    public List<String> obtenerNombres() {
        List<String> nombres = new ArrayList<>();
        try (Connection conn = ds.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT ClieCorreo FROM Cliente");
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                nombres.add(rs.getString("ClieCorreo"));
            }

        } catch (Exception e) {
            mensaje = "Error al consultar nombres: " + e.getMessage();
        }
        return nombres;
    }

    public String enviar() {
        try {
            if ("1234".equals(password)) {
                return "/basic/salida/salida1";
            } else {
                throw new Exception("Contraseña incorrecta");
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(
                    "formSesion:enviarBtn",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null)
            );
            return null;
        }
    }

    // Getters y Setters

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMensaje() {
        return mensaje;
    }
}
