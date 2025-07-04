package mx.ipn.upiita.panaderia3hermanos.web;

import jakarta.annotation.Resource;
import jakarta.faces.context.ExternalContext;
import jakarta.inject.Named;
import javax.sql.DataSource;
import java.sql.*;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.enterprise.context.SessionScoped;
import java.io.Serializable;

@SessionScoped
@Named("datosSesion")
public class InicioSesion implements Serializable {

    @Resource(lookup = "java:/MyAppDS")
    private DataSource ds;

    private String usuario;
    private String contrasenia;
    private int usuarioId;
    private String nombre;
    private String rol;



    public String enviar() {
        try (Connection conn = ds.getConnection()) {
            // Primero intentamos encontrar en Empleado
            String sqlEmp = "SELECT EmpleTipo FROM Empleado WHERE EmpleUsuario = ? AND EmpleContrasenia = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sqlEmp)) {
                stmt.setString(1, usuario);
                stmt.setString(2, contrasenia);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    this.usuarioId = rs.getInt("EmpleID");
                    this.nombre = rs.getString("EmpleNombre");
                    this.rol = "Empleado";
                    String tipo = rs.getString("EmpleTipo");
                    return tipo.equals("Administrador") ? "admin?faces-redirect=true" : "vendedor?faces-redirect=true";
                }
            }

            // Si no es empleado, buscar en Cliente
            String sqlCli = "SELECT ClieID FROM Cliente WHERE ClieUsuario = ? AND ClieContrasenia = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sqlCli)) {
                stmt.setString(1, usuario);
                stmt.setString(2, contrasenia);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
                    ec.redirect(ec.getRequestContextPath() + "/index.xhtml");
                    this.usuarioId = rs.getInt("ClieID");
                    this.nombre = rs.getString("ClieNombre");
                    this.rol = "Cliente"; // o "Administrador" /

                }
            }

            // Si no se encontró en ningún lado
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Credenciales incorrectas", null));

        } catch (Exception e) {
            e.printStackTrace(); // para depuración
        }

        return null;
    }
    public String cerrarSesion() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "login?faces-redirect=true";
    }

    // Getters y setters
    public String getUsuario() { return usuario; }
    public void setUsuario(String usuario) { this.usuario = usuario; }
    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getContrasenia() { return contrasenia; }
    public void setContrasenia(String contrasenia) { this.contrasenia = contrasenia; }
}
